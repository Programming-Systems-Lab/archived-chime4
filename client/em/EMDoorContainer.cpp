#include "EnvironmentModeler.h"

EMDoorContainer::EMDoorContainer( EMDoor *setDoor ): EMRoomList()
{
	door = setDoor;
}

EMDoor *EMDoorContainer::getDoor( void )
{
	return door;
}