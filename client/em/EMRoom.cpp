#include "EnvironmentModeler.h"

EMRoom::EMRoom( ChimeID setID, Coords setDim, ChimeID setModel ): EMElement( setID, setDim, setModel )
{
	doors = NULL;
	objects = NULL;
	avatars = NULL;
}

EMRoom::~EMRoom( void )
{
	//kill all doors
	//kill all objects
	//modify all avatars
}

void EMRoom::addDoor( EMDoor *addDoor )
{
	EMDoorContainer *temp;

	temp = new EMDoorContainer;

	temp->door = addDoor;
	temp->next = doors;

	doors = temp;
}

void EMRoom::addObject( EMObject *addObject )
{
	addObject->room = this;
	addObject->next = objects;
	objects = addObject;
}

void EMRoom::addAvatar( EMAvatar *addAvatar )
{
	addAvatar->room = this;
	addAvatar->next = avatars;
	avatars = addAvatar;
}

EMDoor *EMRoom::getDoor( ChimeID doorID )
{
	EMDoorContainer *cur;

	for( cur = doors; cur != NULL; cur = cur->next )
	{
		if( cur->door->elementID == doorID )
			return cur->door;
	}

	return NULL;
}

EMObject *EMRoom::getObject( ChimeID objectID )
{
	EMObject *cur;

	for( cur = objects; cur != NULL; cur = cur->next )
	{
		if( cur->elementID == objectID )
			return cur;
	}

	return NULL;
}

EMAvatar *EMRoom::getAvatar( ChimeID avatarID )
{
	EMAvatar *cur;

	for( cur = avatars; cur != NULL; cur = cur->next )
	{
		if( cur->elementID == avatarID )
			return cur;
	}

	return NULL;
}

void EMRoom::removeDoor( ChimeID doorID )
{
	EMDoorContainer *cur;
	EMDoorContainer *temp;

	//here we remove the container and then delete it, only in this case
	//because the container is just a utility object, the actual door will
	//remain inside the door hash, so that it could possibly be used again.
	//so again, elements must explicitly be cleared if the server wants them to
	//be deallocated.
	if( doors->door->elementID == doorID )
	{
		temp = doors;
		doors = temp->next;
		delete temp;
	}
	else
	{
		for( cur = doors; cur->next != NULL; cur = cur->next )
		{
			if( cur->next->door->elementID == doorID )
			{
				temp = cur->next;
				cur->next = temp->next;
				delete temp;
				break;
			}
		}
	}
}

void EMRoom::removeObject( ChimeID objectID )
{
	EMObject *cur;

	if( objects->elementID == objectID )
		objects = objects->next;
	else
	{
		for( cur = objects; cur->next != NULL; cur = cur->next )
		{
			if( cur->next->elementID == objectID )
			{
				cur->next = cur->next->next;
				break;
			}
		}
	}
}

void EMRoom::removeAvatar( ChimeID avatarID )
{
	EMAvatar *cur;

	if( avatars->elementID == avatarID )
		avatars = avatars->next;
	else
	{
		for( cur = avatars; cur->next != NULL; cur = cur->next )
		{
			if( cur->next->elementID == avatarID )
			{
				cur->next = cur->next->next;
				break;
			}
		}
	}

}