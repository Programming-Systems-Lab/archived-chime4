#ifndef __CHIME_ENVIRONMENT_MODELER__
#define __CHIME_ENVIRONMENT_MODELER__

//predeclare
class EMRoom;

//dependencies
#include "EMElement.h"
#include "EMRoomList.h"
#include "EMDoor.h"
#include "EMDoorContainer.h"
#include "EMObject.h"
#include "EMAvatar.h"
#include "EMRoom.h"

//constants
#define EM_MODEL_HASH_SIZE 64
#define EM_AVATAR_HASH_SIZE 64
#define EM_ROOM_HASH_SIZE 64
#define EM_DOOR_HASH_SIZE 64
#define EM_OBJECT_HASH_SIZE 64


//environment modeler definition
class EnvironmentModeler
{
	public:
		static void initialize( void );
		static void shutdown( void );

		static void placeRoom( ChimeID setID, Coords setDim, ChimeID setModel );
		static void placeDoor( ChimeID setID, ChimeID roomOne, ChimeID roomTwo,
			Coords setDim, Coords coordsOne, Coords coordsTwo, ChimeID setModel );
		static void placeObjet( ChimeID setID, ChimeID setRoom, Coords setDim,
			Coords setCoords, ChimeID setModel );
		static void placeAvatar( ChimeID setID, ChimeID setRoom, Coords setDim,
			Coords setCoords, ChimeID setModel );

		static void updateRoom( ChimeID roomID, Coords setDim, ChimeID setModel );
		static void updateDoor( ChimeID doorID, ChimeID roomOne, ChimeID roomTwo,
			Coords setDim, Coords coordsOne, Coords coordsTwo, ChimeID setModel );
		static void updateObject( ChimeID objectID, ChimeID setRoom, Coords setDim,
			Coords setCoords, ChimeID setModel );
		static void updateAvatar( ChimeID avatarID, ChimeID setRoom, Coords setDim,
			Coords setCoords, ChimeID setModel );

		static void removeRoom( ChimeID elementID );
		static void removeDoor( ChimeID elementID );
		static void removeObject( ChimeID elementID );
		static void removeAvatar( ChimeID elementID );

		static EMRoom *getRoom( ChimeID roomID );
		static EMDoor *getDoor( ChimeID doorID );
		static EMAvatar *getAvatar( ChimeID avatarID );
		static EMObject *getObject( ChimeID objectID );
		static EMModel *getModel( ChimeID modelID );

		static void moveAvatar( ChimeID avatarID, Coords setCoords );

		static void updateModel( ChimeID modelID, char *file );

		static void flush( void );
		static void setCamera( ChimeID roomID, Coords location, Coords focus );

	private:

		static EMModel *modelHash[EM_MODEL_HASH_SIZE];
		static EMRoom *roomHash[EM_ROOM_HASH_SIZE];
		static EMDoor *doorHash[EM_DOOR_HASH_SIZE];
		static EMObject *objectHash[EM_OBJECT_HASH_SIZE];
		static EMAvatar *avatarHash[EM_AVATAR_HASH_SIZE];

		static Coords cameraLocation;
		static Coords cameraFocus;
		static EMRoom *cameraRoom;
};

#endif