#ifndef __ENVIRONMENT_MODELER_MODEL__
#define __ENVIRONMENT_MODELER_MODEL__

#include "ChimeID.h"
#include "EMHash.h"

class EMModel: public EMHash
{
	public:
		EMModel( char *file, ChimeID setID );
		~EMModel( void );

		ChimeID modelID;
		void *modelData;
		unsigned int modelDataLength;
};

#endif