#include "EnvironmentModeler.h"


void EnvironmentModeler::initialize( void )
{

}

void EnvironmentModeler::shutdown( void )
{

}

void EnvironmentModeler::placeRoom( ChimeID setID, Coords setDim, ChimeID setModel )
{
	//make sure we haven't already placed it

}

void EnvironmentModeler::placeDoor( ChimeID setID, ChimeID roomOne, ChimeID roomTwo,
	Coords setDim, Coords coordsOne, Coords coordsTwo, ChimeID setModel )
{

}

void EnvironmentModeler::placeObjet( ChimeID setID, ChimeID setRoom, Coords setDim,
	Coords setCoords, ChimeID setModel )
{

}

void EnvironmentModeler::placeAvatar( ChimeID setID, ChimeID setRoom, Coords setDim,
	Coords setCoords, ChimeID setModel )
{

}

void EnvironmentModeler::updateRoom( ChimeID roomID, Coords setDim, ChimeID setModel )
{

}

void EnvironmentModeler::updateDoor( ChimeID doorID, ChimeID roomOne, ChimeID roomTwo,
	Coords setDim, Coords coordsOne, Coords coordsTwo, ChimeID setModel )
{

}

void EnvironmentModeler::updateObject( ChimeID objectID, ChimeID setRoom, Coords setDim,
	Coords setCoords, ChimeID setModel )
{

}

void EnvironmentModeler::updateAvatar( ChimeID avatarID, ChimeID setRoom, Coords setDim,
	Coords setCoords, ChimeID setModel )
{

}

void EnvironmentModeler::removeRoom( ChimeID elementID )
{

}

void EnvironmentModeler::removeDoor( ChimeID elementID )
{

}

void EnvironmentModeler::removeObject( ChimeID elementID )
{

}

void EnvironmentModeler::removeAvatar( ChimeID elementID )
{

}

EMRoom *EnvironmentModeler::getRoom( ChimeID roomID )
{
	return NULL;
}

EMDoor *EnvironmentModeler::getDoor( ChimeID doorID )
{
	return NULL;
}

EMAvatar *EnvironmentModeler::getAvatar( ChimeID avatarID )
{
	return NULL;
}

EMObject *EnvironmentModeler::getObject( ChimeID objectID )
{
	return NULL;
}

EMModel *EnvironmentModeler::getModel( ChimeID modelID )
{
	return NULL;
}

void EnvironmentModeler::moveAvatar( ChimeID avatarID, Coords setCoords )
{

}

void EnvironmentModeler::updateModel( ChimeID modelID, char *file )
{

}

void EnvironmentModeler::flush( void )
{

}

void EnvironmentModeler::setCamera( ChimeID roomID, Coords location, Coords focus )
{

}