#ifndef __ENVIRONMENT_MODELER_MODEL__
#define __ENVIRONMENT_MODELER_MODEL__

#include "ChimeID.h"
#include "EMEntity.h"

class EMModel: public EMEntity
{
	public:
		EMModel( char *file, ChimeID setID );
		virtual ~EMModel( void );

		bool updateModel( char *file );
};

#endif