#ifndef __COORDINATOR_H__
#define __COORDINATOR_H__

//dependencies
#include "common/ChimeID.h"


//declaration
class Coordinator
{
	public:
		static bool initialize( ChimeID myID, int outPort, int inPort );
		static void shutdown( void );

		static void addClient( ChimeID setID, char *address, int port );
		static void removeClient( ChimeID remID );

		static void sendUpdate( float dx, float dy, float dz,
								float rx, float ry, float rz );
		static void getUpdates( void );

	private:
		Coordinator( void );
};

#endif