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

EMRoom **EMDoor::getLinkRooms( void )
{
	return linkRooms;
}

Coords *EMDoor::getLinkCoords( void )
{
	return linkCoords;
}