#include "EnvironmentModeler.h"

EMAvatar::EMAvatar( ChimeID setID, ChimeID setRoom, Coords setDim,
			Coords setCoords, Coords setRotate, ChimeID setModel ):
					EMElement( setID, setDim, setModel ), EMRoomList()
{
	next = NULL;
	roomCoords = setCoords;
	rotation = setRotate;
	room = EnvironmentModeler::getRoom( setRoom );
}

void EMAvatar::setRoomCoords( Coords theCoords )
{
	roomCoords = theCoords;
}

void EMAvatar::setRotation( Coords theCoords )
{
	rotation = theCoords;
}

void EMAvatar::setRoom( EMRoom *theRoom )
{
	room = theRoom;
}

Coords EMAvatar::getRoomCoords( void )
{
	return roomCoords;
}

Coords EMAvatar::getRotation( void )
{
	return rotation;
}

EMRoom *EMAvatar::getRoom( void )
{
	return room;
}