#include "EnvironmentModeler.h"


void EnvironmentModeler::initialize( void )
{
	//init hashes
	EMEntity::initHash( (EMEntity **) &modelHash, EM_MODEL_HASH_SIZE );
	EMEntity::initHash( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE );
	EMEntity::initHash( (EMEntity **) &doorHash, EM_DOOR_HASH_SIZE );
	EMEntity::initHash( (EMEntity **) &objectHash, EM_OBJECT_HASH_SIZE );
	EMEntity::initHash( (EMEntity **) &avatarHash, EM_AVATAR_HASH_SIZE );

	//init view
	cameraLocation = Coords( 0, 0, 0 );
	cameraFocus = Coords( 0, 0, 0 );
	cameraRoom = NULL;

	//init the engine
}

void EnvironmentModeler::shutdown( void )
{
	//delete everything
	EMEntity::clearHash( (EMEntity **) &modelHash, EM_MODEL_HASH_SIZE );
	EMEntity::clearHash( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE );
	EMEntity::clearHash( (EMEntity **) &doorHash, EM_DOOR_HASH_SIZE );
	EMEntity::clearHash( (EMEntity **) &objectHash, EM_OBJECT_HASH_SIZE );
	EMEntity::clearHash( (EMEntity **) &avatarHash, EM_AVATAR_HASH_SIZE );

	//shutdown the engine
}

void EnvironmentModeler::placeRoom( ChimeID setID, Coords setDim, ChimeID setModel )
{
	EMRoom *tempRoom;

	//create the room
	tempRoom = new EMRoom( setID, setDim, setModel );

	//just add it into the hash
	EMEntity::addHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, tempRoom );
}

void EnvironmentModeler::placeDoor( ChimeID setID, ChimeID roomOne, ChimeID roomTwo,
	Coords setDim, Coords coordsOne, Coords coordsTwo, ChimeID setModel )
{
	EMRoom *theRoom;
	EMDoor *tempDoor;

	//make the door
	tempDoor = new EMDoor( setID, roomOne, roomTwo, setDim,
							coordsOne, coordsTwo, setModel );

	//add it to the two rooms
	theRoom = (EMRoom *) EMEntity::getHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, roomOne );
	theRoom->addDoor( tempDoor );

	theRoom = (EMRoom *) EMEntity::getHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, roomTwo );
	theRoom->addDoor( tempDoor );

	//add it to the hash
	EMEntity::addHashEntity( (EMEntity **) &doorHash, EM_DOOR_HASH_SIZE, tempDoor );  
}

void EnvironmentModeler::placeObject( ChimeID setID, ChimeID setRoom, Coords setDim,
	Coords setCoords, ChimeID setModel )
{
	EMRoom *theRoom;
	EMObject *tempObject;

	//make the new object
	tempObject = new EMObject( setID, setRoom, setDim, setCoords, setModel );

	//put it in the room
	theRoom = (EMRoom *) EMEntity::getHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, setRoom );
	theRoom->addObject( tempObject );

	//add it to the hash
	EMEntity::addHashEntity( (EMEntity **) &objectHash, EM_OBJECT_HASH_SIZE, tempObject ); 
}

void EnvironmentModeler::placeAvatar( ChimeID setID, ChimeID setRoom, Coords setDim,
	Coords setCoords, ChimeID setModel )
{
	EMRoom *theRoom;
	EMAvatar *tempAvatar;

	//make the new avatar
	tempAvatar = new EMAvatar( setID, setRoom, setDim, setCoords, setModel );

	//put it in the room
	theRoom = (EMRoom *) EMEntity::getHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, setRoom );
	theRoom->addAvatar( tempAvatar );

	//add to the hash
	EMEntity::addHashEntity( (EMEntity **) &avatarHash, EM_AVATAR_HASH_SIZE, tempAvatar ); 
}

void EnvironmentModeler::updateRoom( ChimeID roomID, Coords setDim, ChimeID setModel )
{
	EMRoom *theRoom;

	//get the room
	theRoom = (EMRoom *) EMEntity::getHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, roomID );

	//call update routines
	theRoom->setDimensions( setDim );
	theRoom->setModel( setModel );
}

void EnvironmentModeler::updateDoor( ChimeID doorID, ChimeID roomOne, ChimeID roomTwo,
	Coords setDim, Coords coordsOne, Coords coordsTwo, ChimeID setModel )
{
	EMDoor *theDoor;
	EMRoom **curRooms;

	//get the door
	theDoor = (EMDoor *) EMEntity::getHashEntity( (EMEntity **) &doorHash, EM_DOOR_HASH_SIZE, doorID );

	//remove from current rooms
	curRooms = theDoor->getLinkRooms();

	curRooms[0]->removeDoor( doorID );
	curRooms[1]->removeDoor( doorID );
	
	//update it
	theDoor->setDimensions( setDim );
	theDoor->setModel( setModel );
	theDoor->setLinkCoords( coordsOne, coordsTwo );

	//add it to the two new rooms
	curRooms[0] = (EMRoom *) EMEntity::getHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, roomOne );
	curRooms[0]->addDoor( theDoor );

	curRooms[1] = (EMRoom *) EMEntity::getHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, roomTwo );
	curRooms[1]->addDoor( theDoor );

	//update linked room list
	theDoor->setLinkRooms( curRooms[0], curRooms[1] );
}

void EnvironmentModeler::updateObject( ChimeID objectID, ChimeID setRoom, Coords setDim,
	Coords setCoords, ChimeID setModel )
{
	EMObject *theObject;
	EMRoom *theRoom;

	//get the object
	theObject = (EMObject *) EMEntity::getHashEntity( (EMEntity **) &objectHash, EM_OBJECT_HASH_SIZE, objectID );

	//get the new room
	theRoom = (EMRoom *) EMEntity::getHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, setRoom );

	//update stuff
	theObject->setRoom( theRoom );
	theObject->setDimensions( setDim );
	theObject->setRoomCoords( setCoords );
	theObject->setModel( setModel );
}

void EnvironmentModeler::updateAvatar( ChimeID avatarID, ChimeID setRoom, Coords setDim,
	Coords setCoords, ChimeID setModel )
{
	EMAvatar *theAvatar;
	EMRoom *theRoom;

	//get the avatar
	theAvatar = (EMAvatar *) EMEntity::getHashEntity( (EMEntity **) &avatarHash, EM_AVATAR_HASH_SIZE, avatarID );

	//get the new room
	theRoom = (EMRoom *) EMEntity::getHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, setRoom );

	//update stuff
	theAvatar->setRoom( theRoom );
	theAvatar->setDimensions( setDim );
	theAvatar->setRoomCoords( setCoords );
	theAvatar->setModel( setModel );
}

void EnvironmentModeler::removeRoom( ChimeID elementID )
{
	EMRoom *theRoom;

	//get it and remove it from the hash
	theRoom = (EMRoom *) EMEntity::removeHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, elementID );

	//deallocate, room's destructor will kill all objects and etc
	delete theRoom;
}

void EnvironmentModeler::removeDoor( ChimeID elementID )
{
	EMDoor *theDoor;
	EMRoom **rooms;

	//get it from the hash and remove it
	theDoor = (EMDoor *) EMEntity::removeHashEntity( (EMEntity **) &doorHash, EM_DOOR_HASH_SIZE, elementID );

	//get the room list
	rooms = theDoor->getLinkRooms();

	//remove it from those rooms
	rooms[0]->removeDoor( elementID );
	rooms[1]->removeDoor( elementID );

	//deallocate it
	delete theDoor;
}

void EnvironmentModeler::removeObject( ChimeID elementID )
{
	EMObject *theObject;
	EMRoom *theRoom;

	//get it from the hash, and also remove it
	theObject = (EMObject *) EMEntity::removeHashEntity( (EMEntity **) &objectHash, EM_OBJECT_HASH_SIZE, elementID );

	//pull it out of its room
	theRoom = theObject->getRoom();
	theRoom->removeObject( elementID );

	//deallocate it
	delete theObject;
}

void EnvironmentModeler::removeAvatar( ChimeID elementID )
{
	EMAvatar *theAvatar;
	EMRoom *theRoom;

	//get it from the hash, and also remove it
	theAvatar = (EMAvatar *) EMEntity::removeHashEntity( (EMEntity **) &avatarHash, EM_AVATAR_HASH_SIZE, elementID );

	//pull it out of its room
	theRoom = theAvatar->getRoom();
	theRoom->removeAvatar( elementID );

	//deallocate it
	delete theAvatar;
}

EMRoom *EnvironmentModeler::getRoom( ChimeID roomID )
{
	//pull it from the hash
	return (EMRoom *) EMEntity::getHashEntity( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE, roomID );
}

EMDoor *EnvironmentModeler::getDoor( ChimeID doorID )
{
	//pull it from the hash
	return (EMDoor *) EMEntity::getHashEntity( (EMEntity **) &doorHash, EM_DOOR_HASH_SIZE, doorID );
}

EMAvatar *EnvironmentModeler::getAvatar( ChimeID avatarID )
{
	//pull it from the hash
	return (EMAvatar *) EMEntity::getHashEntity( (EMEntity **) &avatarHash, EM_AVATAR_HASH_SIZE, avatarID );
}

EMObject *EnvironmentModeler::getObject( ChimeID objectID )
{
	//pull it from the hash
	return (EMObject *) EMEntity::getHashEntity( (EMEntity **) &objectHash, EM_OBJECT_HASH_SIZE, objectID );
}

EMModel *EnvironmentModeler::getModel( ChimeID modelID )
{
	//pull it from the hash
	return (EMModel *) EMEntity::getHashEntity( (EMEntity **) &modelHash, EM_MODEL_HASH_SIZE, modelID );
}

void EnvironmentModeler::moveAvatar( ChimeID avatarID, Coords setCoords )
{
	EMAvatar *theAvatar;

	//get the avatar
	theAvatar = (EMAvatar *) EMEntity::getHashEntity( (EMEntity **) &avatarHash, EM_AVATAR_HASH_SIZE, avatarID );

	//update the coords
	theAvatar->setRoomCoords( setCoords );

	//inform the engine
}

void EnvironmentModeler::updateModel( ChimeID modelID, char *file )
{
	EMModel *theModel;

	//get the model structure
	theModel = (EMModel *) EMEntity::getHashEntity( (EMEntity **) &modelHash, EM_MODEL_HASH_SIZE, modelID );

	//update it, inform the engine of sucessful update
	if( !theModel->updateModel( file ) )
	{
		//inform the engine
	}
}

void EnvironmentModeler::flush( void )
{
	//clear all hashes
	EMEntity::clearHash( (EMEntity **) &modelHash, EM_MODEL_HASH_SIZE );
	EMEntity::clearHash( (EMEntity **) &roomHash, EM_ROOM_HASH_SIZE );
	EMEntity::clearHash( (EMEntity **) &doorHash, EM_DOOR_HASH_SIZE );
	EMEntity::clearHash( (EMEntity **) &objectHash, EM_OBJECT_HASH_SIZE );
	EMEntity::clearHash( (EMEntity **) &avatarHash, EM_AVATAR_HASH_SIZE );

	//reset all vars
	cameraLocation = Coords( 0, 0, 0 );
	cameraFocus = Coords( 0, 0, 0 );
	cameraRoom = NULL;

	//flush the engine
}

void EnvironmentModeler::setCamera( ChimeID roomID, Coords location, Coords focus )
{
	//set camera paramters
	cameraLocation = location;
	cameraFocus = focus;
	cameraRoom = getRoom( roomID );
}