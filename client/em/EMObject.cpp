#include "EnvironmentModeler.h"

EMObject::EMObject( ChimeID setID, ChimeID setRoom, Coords setDim,
			Coords setCoords, ChimeID setModel ):
					EMElement( setID, setDim, setModel ), EMRoomList()

{
	room = EnvironmentModeler::getRoom( setRoom );
	roomCoords = setCoords;
}

void EMObject::setRoomCoords( Coords theCoords )
{
	roomCoords = theCoords;
}

void EMObject::setRoom( EMRoom *theRoom )
{
	room = theRoom;
}

Coords EMObject::getRoomCoords( void )
{
	return roomCoords;
}

EMRoom *EMObject::getRoom( void )
{
	return room;
}