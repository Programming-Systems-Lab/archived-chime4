#include <windows.h>
#include <winsock.h>
#include "Coordinator.h"
#include "em/EnvironmentModeler.h"

//local constants
#define CLIENT_HASH_SIZE 64

//macros
#define split_number( number, array, start ) \
			array[start] = (number >> 24) & 0xFF; \
			array[start+1] = (number >> 16) & 0xFF; \
			array[start+2] = (number >> 8) & 0xFF; \
			array[start+3] = (number) & 0xFF

#define join_number( array, start ) \
			((array[start] << 24) | (array[start+1] << 16) | \
			 (array[start+2] << 8) | (array[start+3]))

//helper struct
struct CoClient
{
	struct CoClient *next;
	struct sockaddr_in addr;
	ChimeID id;
};
typedef struct CoClient CoClient;

//local data
CoClient *clients;
HANDLE clientsMutex = NULL;
SOCKET outSock;
SOCKET inSock;
ChimeID localID;
bool active = false;


//class definition
bool Coordinator::initialize( ChimeID myID, int outPort, int inPort )
{
	struct sockaddr_in addr;

	//init
	clients = NULL;
	clientsMutex = NULL;
	localID = myID;

	//setup the mutex
	clientsMutex = CreateMutex( NULL, FALSE, NULL );

	//open the sockets
	inSock = socket( AF_INET, SOCK_DGRAM, PF_INET );
	memset( &addr, 0, sizeof(struct sockaddr_in) );
	addr.sin_addr.s_addr = INADDR_ANY;
	addr.sin_port = inPort;
	addr.sin_family = AF_INET;
	
	if( bind( inSock, (struct sockaddr *) &addr, sizeof( struct sockaddr_in ) ) != 0 )
	{
		closesocket( inSock );
		return false;
	}

	outSock = socket( AF_INET, SOCK_DGRAM, PF_INET );
	memset( &addr, 0, sizeof(struct sockaddr_in) );
	addr.sin_addr.s_addr = INADDR_ANY;
	addr.sin_port = outPort;
	addr.sin_family = AF_INET;

	if( bind( outSock, (struct sockaddr *) &addr, sizeof( struct sockaddr_in ) ) != 0 )
	{
		closesocket( outSock );
		closesocket( inSock );
		return false;
	}

	//make active
	active = true;

	//everything's fine
	return true;
}

void Coordinator::shutdown( void )
{
	CoClient *next;

	//lock the mutex
	WaitForSingleObject( clientsMutex, INFINITE );

	//make inactive
	active = false;

	//kill all client sockets
	while( clients != NULL )
	{
		next = clients->next;

		delete clients;

		clients = next;
	}

	//unlock the mutex
	ReleaseMutex( clientsMutex );

	//destroy the mutex
	CloseHandle( clientsMutex );

	//close the socket
	closesocket( inSock );
	closesocket( outSock );
}

void Coordinator::addClient( ChimeID setID, char *address, int port )
{
	CoClient *temp;

	temp = new CoClient;

	memset( temp, 0, sizeof(CoClient) );

	temp->addr.sin_addr.s_addr = inet_addr(address);
	temp->addr.sin_port = htons( port );
	temp->addr.sin_family = AF_INET;
	
	//lock
	WaitForSingleObject( clientsMutex, INFINITE );

	//add to list
	temp->next = clients;
	clients = temp;

	//unlock
	ReleaseMutex( clientsMutex );
}

void Coordinator::removeClient( ChimeID remID )
{
	CoClient *cur;
	CoClient *found = NULL;

	//lock
	WaitForSingleObject( clientsMutex, INFINITE );

	//pull it out
	if( clients->id == remID )
	{
		found = clients;
		clients = found->next;
	}
	else
	{
		for( cur = clients; cur->next != NULL; cur = cur->next )
		{
			if( cur->next->id == remID )
			{
				found = cur->next;
				cur->next = found->next;
				break;
			}
		}
	}

	//unlock
	ReleaseMutex( clientsMutex );

	//kill the found one
	if( found != NULL )
	{
		delete found;
	}
}

void Coordinator::sendUpdate( float dx, float dy, float dz, float rx, float ry, float rz )
{
	char buff[100];
	CoClient *cur;
	
	//translation
	split_number( (int) dx, buff, 0 );
	split_number( (int) ((float)dx - ((int) dx))*10000, buff, 4 );
	split_number( (int) dy, buff, 8 );
	split_number( (int) ((float)dy - ((int) dy))*10000, buff, 12 );
	split_number( (int) dz, buff, 16 );
	split_number( (int) ((float)dz - ((int) dz))*10000, buff, 20 );

	//rotation
	split_number( (int) rx, buff, 24 );
	split_number( (int) ((float)rx - ((int) rx))*10000, buff, 28 );
	split_number( (int) ry, buff, 32 );
	split_number( (int) ((float)ry - ((int) ry))*10000, buff, 36 );
	split_number( (int) rz, buff, 40 );
	split_number( (int) ((float)rz - ((int) rz))*10000, buff, 44 );

	//add my id
	split_number( (int) localID, buff, 48 );

	//lock
	WaitForSingleObject( clientsMutex, INFINITE );

	//go through them all
	for( cur = clients; cur != NULL; cur = cur->next )
	{
		if( sendto( outSock, buff, 52, 0, (struct sockaddr *) &(cur->addr), sizeof(struct sockaddr_in) ) != 0 )
		{
			//LOG
		}
	}

	//unlock
	ReleaseMutex( clientsMutex );
}

void Coordinator::getUpdates( void )
{
	fd_set localSet;
	struct timeval time;
	bool done = false;
	char buff[52];
	struct sockaddr_in addr;
	float trans[3];
	float rotate[3];
	ChimeID clientID;
	int fromLen;

	//setup timeval
	time.tv_sec = 0;
	time.tv_usec = 1000;

	while( !done )
	{
		//setup fdset
		FD_ZERO(&localSet);
		FD_SET( inSock, &localSet );

		if( select( (int) inSock, &localSet, NULL, NULL, NULL ) != 0 )
		{
			if( FD_ISSET(inSock, &localSet) )
			{
				//get the data, up to the packet length
				if( recvfrom( inSock, buff, 52, 0, (struct sockaddr *) &addr,&fromLen ) != 0 )
				{
					//LOG
				}
				else
				{
					//parse the coords
					trans[0] = (float) (((int) join_number( buff, 0 )) +
								(((float) join_number(buff, 4))/10000));
					trans[1] = (float) (((int) join_number( buff, 8 )) +
								(((float) join_number(buff, 12))/10000));
					trans[2] = (float) (((int) join_number( buff, 16 )) +
								(((float) join_number(buff, 20))/10000));

					//parse the rotation
					rotate[0] = (float) (((int) join_number( buff, 24 )) +
								(((float) join_number(buff, 28))/10000));
					rotate[1] = (float) (((int) join_number( buff, 32 )) +
								(((float) join_number(buff, 36))/10000));
					rotate[2] = (float) (((int) join_number( buff, 40 )) +
								(((float) join_number(buff, 44))/10000));
					
					//parse the ID
					clientID = (int) join_number( buff, 48 );

					//do the update
					EnvironmentModeler::moveAvatar( clientID, Coords( trans[0], trans[1], trans[2] ),
						Coords( rotate[0], rotate[1], rotate[2] ) );
				}
			}
		}
		else
			done = true;
	}
}