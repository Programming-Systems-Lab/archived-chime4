#ifndef __ENVIRONMENT_MODELER_DOOR_CONTAINER__
#define __ENVIRONMENT_MODELER_DOOR_CONTAINER__

class EMDoorContainer: public EMRoomList
{
	public:
		EMDoorContainer( EMDoor *setDoor );

		EMDoor *getDoor( void );
	
	protected:	
		class EMDoor *door;
};

#endif