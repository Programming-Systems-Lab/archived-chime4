#ifndef __ENVIRONMENT_MODELER_ELEMENT__
#define __ENVIRONMENT_MODELER_ELEMENT__

//dependencies
#include "ChimeID.h"
#include "EMCoords.h"
#include "EMHash.h"
#include "EMModel.h"

//element definition
class EMElement: public EMHash
{
	public:
		EMElement( ChimeID setID, Coords setDim, ChimeID modelID );
		virtual ~EMElement( void );

		void setModel( ChimeID setModelID );
	
		ChimeID elementID;
		EMModel *model;
		Coords dimensions;

	private:
		EMElement( void );
};

#endif