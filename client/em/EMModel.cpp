#include <stdio.h>
#include <stdlib.h>
#include "EnvironmentModeler.h"

//crystal space stuff
#include "client94/core.h"


EMModel::EMModel( char *file, ChimeID setID ): EMEntity( setID )
{
	if( !updateModel( file ) )
		EnvironmentModeler::requestModel( setID );
}

EMModel::~EMModel( void )
{
	//dont do anything
}

bool EMModel::updateModel( char *file )
{
	char buff[100];

	//make the entity string
	sprintf(buff, "%d", entityID);

	//load the model and register it under the given ID
	return gApp->LoadModel( file, buff );
}