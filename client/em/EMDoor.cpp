#include "EnvironmentModeler.h"

EMDoor::EMDoor( ChimeID setID, ChimeID roomOne, ChimeID roomTwo,
			   Coords setDim, Coords coordsOne, Coords coordsTwo, ChimeID setModel ):
				EMElement( setID, setDim, setModel )
{
	linkCoords[0] = coordsOne;
	linkCoords[1] = coordsTwo;

	linkRooms[0] = EnvironmentModeler::getRoom( roomOne );
	linkRooms[1] = EnvironmentModeler::getRoom( roomTwo );
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

EMRoom **EMDoor::getLinkRooms( void )
{
	return linkRooms;
}

Coords *EMDoor::getLinkCoords( void )
{
	return linkCoords;
}