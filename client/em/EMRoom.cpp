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

	temp = new EMDoorContainer( addDoor );

	doors = (EMDoorContainer *) EMRoomList::addToList( doors, temp );
}

void EMRoom::addObject( EMObject *addObject )
{
	objects = (EMObject *) EMRoomList::addToList( objects, addObject );
}

void EMRoom::addAvatar( EMAvatar *addAvatar )
{
	avatars = (EMAvatar *) EMRoomList::addToList( avatars, addAvatar );
}

EMDoor *EMRoom::getDoor( ChimeID doorID )
{
	EMDoorContainer *cur;
	EMDoor *curDoor;

	for( cur = doors; cur != NULL; cur = (EMDoorContainer *) cur->getNext() )
	{
		curDoor = cur->getDoor();
		
		if( curDoor->getID() == doorID )
			return curDoor;
	}

	return NULL;
}

EMObject *EMRoom::getObject( ChimeID objectID )
{
	EMObject *cur;

	for( cur = objects; cur != NULL; cur = (EMObject *) cur->getNext() )
	{
		if( cur->getID() == objectID )
			return cur;
	}

	return NULL;
}

EMAvatar *EMRoom::getAvatar( ChimeID avatarID )
{
	EMAvatar *cur;

	for( cur = avatars; cur != NULL; cur = (EMAvatar *) cur->getNext() )
	{
		if( cur->getID() == avatarID )
			return cur;
	}

	return NULL;
}

EMDoorContainer *EMRoom::getDoorList( void )
{
	return doors;
}

EMObject *EMRoom::getObjectList( void )
{
	return objects;
}

EMAvatar *EMRoom::getAvatarList( void )
{
	return avatars;
}

void EMRoom::removeDoor( ChimeID doorID )
{
	EMDoorContainer *cur;
	EMDoorContainer *temp;
	EMDoor *tempDoor;

	if( doors == NULL )
		return;

	//here we remove the container and then delete it, only in this case
	//because the container is just a utility object, the actual door will
	//remain inside the door hash, so that it could possibly be used again.
	//so again, elements must explicitly be cleared if the server wants them to
	//be deallocated.
	tempDoor = doors->getDoor();

	if( tempDoor->getID() == doorID )
	{
		temp = doors;
		doors = (EMDoorContainer *) temp->getNext();

		//clear the container
		delete temp;
	}
	else
	{
		//go through the rest of them
		for( cur = doors; cur->getNext() != NULL; cur = (EMDoorContainer *) cur->getNext() )
		{
			temp = (EMDoorContainer *) cur->getNext();
			tempDoor = temp->getDoor();

			if( tempDoor->getID() == doorID )
			{
				cur->setNext( temp->getNext() );

				//clear the container
				delete temp;

				//break out, we're done.
				break;
			}
		}
	}
}

void EMRoom::removeObject( ChimeID objectID )
{
	EMObject *temp;
	EMObject *cur;

	if( objects == NULL )
		return;

	if( objects->getID() == objectID )
		objects = (EMObject *) objects->getNext();
	else
	{
		for( cur = objects; cur->getNext() != NULL; cur = (EMObject *) cur->getNext() )
		{
			temp = (EMObject *) cur->getNext();

			if( temp->getID() == objectID )
			{
				cur->setNext( temp->getNext() );
				break;
			}
		}
	}
}

void EMRoom::removeAvatar( ChimeID avatarID )
{
	EMAvatar *temp;
	EMAvatar *cur;

	if( avatars == NULL )
		return;

	if( avatars->getID() == avatarID )
		avatars = (EMAvatar *) avatars->getNext();
	else
	{
		for( cur = avatars; cur->getNext() != NULL; cur = (EMAvatar *) cur->getNext() )
		{
			temp = (EMAvatar *) cur->getNext();

			if( temp->getID() == avatarID )
			{
				cur->setNext( temp->getNext() );
				break;
			}
		}
	}
}