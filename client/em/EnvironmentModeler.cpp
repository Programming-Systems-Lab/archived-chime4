#include "EnvironmentModeler.h"


void EnvironmentModeler::initialize( void )
{

}

void EnvironmentModeler::shutdown( void )
{

}

void EnvironmentModeler::placeRoom( ChimeID setID, Coords setDim, ChimeID setModel )
{

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

}

EMDoor *EnvironmentModeler::getDoor( ChimeID doorID )
{

}

EMAvatar *EnvironmentModeler::getAvatar( ChimeID avatarID )
{

}

EMObject *EnvironmentModeler::getObject( ChimeID objectID )
{

}

EMModel *EnvironmentModeler::getModel( ChimeID modelID )
{

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

void EnvironmentModeler::clearHash( EMHash *hash, int size )
{

}