#include "EnvironmentModeler.h"

EMRoomList::EMRoomList( void )
{
	next = NULL;
}

void EMRoomList::setNext( EMRoomList *item )
{
	next = item;
}

EMRoomList *EMRoomList::getNext( void )
{
	return next;
}

EMRoomList *EMRoomList::removeFromList( EMRoomList *list, EMRoomList *item )
{
	EMRoomList *cur;

	if( list == NULL )
		return NULL;

	//see if it's the head of the list
	if( list == item )
	{
		list = item->next;
		item->next = NULL;
	}
	else
	{
		//go through the rest of them
		for( cur = list; cur->next != NULL; cur = cur->next )
		{
			//if the next one is the item, take it out
			if( cur->next == item )
			{
				cur->next = item->next;
				break;
			}
		}
	}

	return list;
}

EMRoomList *EMRoomList::addToList( EMRoomList *list, EMRoomList *item )
{
	//insert at the head
	item->next = list;
	list = item;

	return list;
}