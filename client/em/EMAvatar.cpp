#include "EnvironmentModeler.h"

EMAvatar::EMAvatar( ChimeID setID, ChimeID setRoom, Coords setDim,
				   Coords setCoords, ChimeID setModel ):
					EMElement( setID, setDim, setModel ), EMRoomList()
{
	next = NULL;
	roomCoords = setCoords;
	room = EnvironmentModeler::getRoom( setRoom );
}

void EMAvatar::setRoomCoords( Coords theCoords )
{
	roomCoords = theCoords;
}

void EMAvatar::setRoom( EMRoom *theRoom )
{
	room = theRoom;
}

Coords EMAvatar::getRoomCoords( void )
{
	return roomCoords;
}

EMRoom *EMAvatar::getRoom( void )
{
	return room;
}