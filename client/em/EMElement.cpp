#include "EnvironmentModeler.h"

EMElement::EMElement( ChimeID setID, Coords setDim, ChimeID modelID ): EMHash()
{
	elementID = setID;
	model = EnvironmentModeler::getModel(modelID);
	dimensions = setDim;
}

EMElement::EMElement( void )
{
	//this shouldn't happen
}

EMElement::~EMElement( void )
{
	if( model != NULL )
		delete model;
}

void EMElement::setModel( ChimeID setModelID )
{	
	model = EnvironmentModeler::getModel( setModelID );
}
