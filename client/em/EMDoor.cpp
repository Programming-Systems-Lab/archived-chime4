#include "EnvironmentModeler.h"

EMDoor::EMDoor( ChimeID setID, ChimeID roomOne, ChimeID roomTwo,
			   Coords setDim, Coords coordsOne, Coords coordsTwo, ChimeID setModel ): EMElement( setID, setDim, setModel )
{
	linkOneCoords = coordsOne;
	linkTwoCoords = coordsTwo;

	linkOne = EnvironmentModeler::getRoom( roomOne );
	linkTwo = EnvironmentModeler::getRoom( roomTwo );
}

EMDoor::~EMDoor( void )
{

}