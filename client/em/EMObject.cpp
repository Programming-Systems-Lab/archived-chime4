#include "EnvironmentModeler.h"

EMObject::EMObject( ChimeID setID, ChimeID setRoom, Coords setDim,
				   Coords setCoords, Coords setRotate, ChimeID setModel ):
					EMElement( setID, setDim, setModel ), EMRoomList()
{
	room = EnvironmentModeler::getRoom( setRoom );
	roomCoords = setCoords;
	rotation = setRotate;
}

void EMObject::setRoomCoords( Coords theCoords )
{
	roomCoords = theCoords;
}

void EMObject::setRotation( Coords theCoords )
{
	rotation = theCoords;
}

void EMObject::setRoom( EMRoom *theRoom )
{
	room = theRoom;
}

Coords EMObject::getRoomCoords( void )
{
	return roomCoords;
}

Coords EMObject::getRotation( void )
{
	return rotation;
}

EMRoom *EMObject::getRoom( void )
{
	return room;
}