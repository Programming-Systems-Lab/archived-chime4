#include "EnvironmentModeler.h"

EMModel::EMModel( char *file, ChimeID setID ): EMEntity( setID )
{

}

EMModel::~EMModel( void )
{

}

void *EMModel::getModelData( int *length )
{
	return modelData;
}