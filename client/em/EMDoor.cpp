#include "EnvironmentModeler.h"

EMDoor::EMDoor( ChimeID setID, ChimeID roomOne, ChimeID roomTwo,
			   Coords setDim, Coords coordsOne, Coords coordsTwo,
			   Coords rotOne, Coords rotTwo, ChimeID setModel ):
				EMElement( setID, setDim, setModel )
{
	linkCoords[0] = coordsOne;
	linkCoords[1] = coordsTwo;

	linkRooms[0] = EnvironmentModeler::getRoom( roomOne );
	linkRooms[1] = EnvironmentModeler::getRoom( roomTwo );

	linkRotation[0] = rotOne;
	linkRotation[1] = rotTwo;
}

void EMDoor::setLinkRooms( EMRoom *roomOne, EMRoom *roomTwo )
{
	linkRooms[0] = roomOne;
	linkRooms[1] = roomTwo;
}

void EMDoor::setLinkCoords( Coords coordsOne, Coords coordsTwo )
{
	linkCoords[0] = coordsOne;
	linkCoords[1] = coordsTwo;
}

void EMDoor::setLinkRotation( Coords rotOne, Coords rotTwo )
{
	linkRotation[0] = rotOne;
	linkRotation[1] = rotTwo;
}

EMRoom **EMDoor::getLinkRooms( void )
{
	return linkRooms;
}

Coords *EMDoor::getLinkCoords( void )
{
	return linkCoords;
}

Coords *EMDoor::getLinkRotation( void )
{
	return linkRotation;
}