#include "EnvironmentModeler.h"

EMModel::EMModel( char *file, ChimeID setID ): EMEntity( setID )
{

}

EMModel::~EMModel( void )
{

}

int EMModel::updateModel( char *file )
{
	return 0;
}

void *EMModel::getModelData( int *length )
{
	return modelData;
}