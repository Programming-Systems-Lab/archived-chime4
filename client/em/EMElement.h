#ifndef __ENVIRONMENT_MODELER_ELEMENT__
#define __ENVIRONMENT_MODELER_ELEMENT__

//dependencies
#include "ChimeID.h"
#include "EMCoords.h"
#include "EMEntity.h"
#include "EMModel.h"

//element definition
class EMElement: public EMEntity
{
	public:
		EMElement( ChimeID setID, Coords setDim, ChimeID modelID );
		virtual ~EMElement( void );

		void setModel( ChimeID setModelID );
		EMModel *getModel( void );

		Coords getDimensions( void );

	private:
		EMElement( void );

		EMModel *model;
		Coords dimensions;
};

#endif