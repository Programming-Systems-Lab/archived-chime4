#ifndef __ENVIRONMENT_MODELER_ROOM_LIST
#define __ENVIRONMENT_MODELER_ROOM_LIST

class EMRoomList
{
	public:
		EMRoomList( void );

		void setNext( EMRoomList *item );
		EMRoomList *getNext( void );

		static EMRoomList *removeFromList( EMRoomList *list, EMRoomList *item );
		static EMRoomList *addToList( EMRoomList *list, EMRoomList *item );

	protected:
		EMRoomList *next;
};

#endif