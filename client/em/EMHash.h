#ifndef __ENVIRONMENT_MODELER_HASH__
#define __ENVIRONMENT_MODELER_HASH__

class EMHash
{
	public:
		EMHash( void )
		{
			hashNext = NULL;
		}

		class EMHash *hashNext;
};

#endif