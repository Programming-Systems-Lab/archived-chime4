#include "EnvironmentModeler.h"

EMAvatar::EMAvatar( ChimeID setID, ChimeID setRoom, Coords setDim,
				   Coords setCoords, ChimeID setModel ): EMElement( setID, setDim, setModel )
{
	next = NULL;
	roomCoords = setCoords;
	room = EnvironmentModeler::getRoom( setRoom );
}

EMAvatar::~EMAvatar( void )
{

}

