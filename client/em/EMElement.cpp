#include "EnvironmentModeler.h"

EMElement::EMElement( ChimeID setID, Coords setDim, ChimeID modelID ): EMEntity( setID )
{
	model = EnvironmentModeler::getModel(modelID);
	dimensions = setDim;
}

EMElement::EMElement( void )
{
	//this shouldn't happen
}

EMElement::~EMElement( void )
{

}

void EMElement::setModel( ChimeID setModelID )
{	
	model = EnvironmentModeler::getModel( setModelID );
}

void EMElement::setDimensions( Coords setDims )
{
	dimensions = setDims;
}

EMModel *EMElement::getModel( void )
{
	return model;
}

Coords EMElement::getDimensions( void )
{
	return dimensions;
}