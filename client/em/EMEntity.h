#ifndef __ENVIRONMENT_MODELER_ENTITY__
#define __ENVIRONMENT_MODELER_ENTITY__

class EMEntity
{
	public:
		EMEntity( ChimeID setID );
		virtual ~EMEntity( void );

		void setID( ChimeID setID );
		ChimeID getID( void );

		static void initHash( EMEntity **hash, int size );
		static void clearHash( EMEntity **hash, int size );
		static void addHashEntity( EMEntity **hash, int size, EMEntity *entity );
		static void removeHashEntity( EMEntity **hash, int size, EMEntity *entity );
		static EMEntity *removeHashEntity( EMEntity **hash, int size, ChimeID remID );
		static EMEntity *getHashEntity( EMEntity **hash, int size, ChimeID getID );

	protected:
		EMEntity( void );

		ChimeID entityID;

		class EMEntity *hashNext;
};

#endif