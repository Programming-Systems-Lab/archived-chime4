#include "EnvironmentModeler.h"

EMObject::EMObject( ChimeID setID, ChimeID setRoom, Coords setDim,
			Coords setCoords, ChimeID setModel ): EMElement( setID, setDim, setModel )

{
	next = NULL;
	room = EnvironmentModeler::getRoom( setRoom );
	roomCoords = setCoords;
}

EMObject::~EMObject( void )
{

}