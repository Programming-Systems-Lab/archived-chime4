#include "ChimeID.h"
#include "EMEntity.h"

EMEntity::EMEntity( ChimeID setID )
{
	entityID = setID;
	hashNext = NULL;
}

EMEntity::EMEntity( void )
{
	//no
}

void EMEntity::setID( ChimeID setID )
{
	entityID = setID;
}

ChimeID EMEntity::getID( void )
{
	return entityID;
}

void EMEntity::initHash( EMEntity **hash, int size )
{
	int i;

	for( i = 0; i < size; i++ )
		hash[i] = NULL;
}

void EMEntity::clearHash( EMEntity **hash, int size )
{
	EMEntity *cur;
	int i;

	//go through them all
	for( i = 0; i < size; i++ )
	{
		//kill the head over and over
		while( hash[i] != NULL )
		{
			cur = hash[i]->hashNext;

			delete hash[i];

			hash[i] = cur;
		}
	}
}

EMEntity *EMEntity::removeHashEntity( EMEntity **hash, int size, ChimeID remID )
{
	EMEntity *removed = NULL;
	EMEntity *cur;
	int i;

	//hashing function
	i = remID % size;

	//see if there's anything there
	if( hash[i] == NULL )
		return NULL;

	//check the first one
	if( hash[i]->entityID == remID )
	{
		removed = hash[i];
		hash[i] = removed->hashNext;
	}
	else
	{
		//go through the rest
		for( cur = hash[i]; cur->hashNext != NULL; cur = cur->hashNext )
		{
			//see if the next one's a match
			if( cur->hashNext->entityID == remID )
			{
				removed = cur->hashNext;
				cur->hashNext = removed->hashNext;
				break;
			}
		}
	}

	return removed;
}

void EMEntity::removeHashEntity( EMEntity **hash, int size, EMEntity *entity )
{
	EMEntity *removed = NULL;
	EMEntity *cur;
	int i;

	//hashing function
	i = entity->entityID % size;

	//see if there's anything there
	if( hash[i] == NULL )
		return;

	//check the first one
	if( hash[i] == entity )
	{
		removed = hash[i];
		hash[i] = removed->hashNext;
	}
	else
	{
		//go through the rest
		for( cur = hash[i]; cur->hashNext != NULL; cur = cur->hashNext )
		{
			//see if the next one's a match
			if( cur->hashNext == entity )
			{
				removed = cur->hashNext;
				cur->hashNext = removed->hashNext;
				break;
			}
		}
	}
}

EMEntity *EMEntity::getHashEntity( EMEntity **hash, int size, ChimeID getID )
{
	EMEntity *cur;
	int i;

	//our simple little hashing function
	i = getID % size;

	//go through the linked list
	for( cur = hash[i]; cur != NULL; cur = cur->hashNext )
	{
		//see if the next one's a match
		if( cur->entityID == getID )
			return cur;
	}

	return NULL;
}

void EMEntity::addHashEntity( EMEntity **hash, int size, EMEntity *entity )
{
	int i;

	//our simple hashing function again
	i = entity->entityID % size;

	//insert it into the head
	entity->hashNext = hash[i];
	hash[i] = entity;
}