#ifndef __CLIENT_ENGINE_H__
#define __CLIENT_ENGINE_H__

//dependencies
#include "em/EnvironmentModeler.h"

class ClientEngine
{
	public:
		static void initialize( csView *setView );

		static void addRoom( EMRoom *room );
		static void addDoor( EMDoor *door );
		static void addObject( EMObject *object );
		static void addAvatar( EMAvatar *avatar );

		static void setCamera( float x, float y, float z );
		static void offsetCamera( float x, float y, float z );
		static void setCameraRotate( float x, float y, float z );
		static void offsetCameraRotate( float x, float y, float z );
		static void setCameraFocus( float x, float y, float z );

		static void moveToRoom( EMRoom *room, float x, float y, float z );

		static void flush( void );

	private:
		static csView *view;
		static iEngine *engine;
		static iSector *defaultRoom;
		static iMeshObjectFactory *defaultFactory;
};

#endif