#ifndef __ENVIRONMENT_MODELER_ROOM__
#define __ENVIRONMENT_MODELER_ROOM__

//dependencies gotten through the EnvironmentModeler.h file

//room definition
class EMRoom: public EMElement
{
	public:
		EMRoom( ChimeID setID, Coords setDim, ChimeID setModel );
		virtual ~EMRoom( void );

		void addDoor( EMDoor *addDoor );
		void addObject( EMObject *addObject );
		void addAvatar( EMAvatar *addAvatar );

		EMDoor *getDoor( ChimeID doorID );
		EMObject *getObject( ChimeID objectID );
		EMAvatar *getAvatar( ChimeID avatarID );

		void removeDoor( ChimeID doorID );
		void removeObject( ChimeID objectID );
		void removeAvatar( ChimeID avatarID );

		EMDoorContainer *doors;
		EMObject *objects;
		EMAvatar *avatars;
};

#endif