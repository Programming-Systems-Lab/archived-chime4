#include "core.h"


csView *ClientEngine::view = NULL;
iSector *ClientEngine::defaultRoom = NULL;
iMeshObjectFactory *ClientEngine::defaultFactory = NULL;
iEngine *ClientEngine::engine = NULL;


void ClientEngine::initialize( csView *setView )
{
	iMeshObjectType *t;

	//set defaults
	view = setView;
	engine = view->GetEngine();

	//get a meshobject type
	t = engine->GetThingType();

	//create the factory
	defaultFactory = t->NewFactory();

	//create an empty starting room
	defaultRoom = engine->CreateSector( "default" );

	view->GetCamera()->SetSector( defaultRoom );
}

void ClientEngine::addRoom( EMRoom *room )
{
	char buff[50];
	iSector *tempSector;
	iMeshWrapper *wrap;
	iMeshFactoryWrapper *factory;
	iThingState *tempState;
	iPolygon3D* p;
	csVector3 coords[8];
	Coords dimensions;
	iMaterialWrapper* tm = engine->GetMaterialList()->FindByName("stone");
	iStatLight* light;

	//make it a string
	sprintf( buff, "%d", room->getID() );

	//create a new sector with the room id
	tempSector = engine->CreateSector( buff );

	//format the model string
	sprintf( buff, "%d", room->getModel()->getID() );

	//get dimensions
	dimensions = room->getDimensions();

	//get the factory
	factory = engine->GetMeshFactories()->FindByName( buff );
	if( factory )
	{
		//now put in the model
		wrap = engine->CreateMeshWrapper( factory, buff, tempSector );

		wrap->DecRef();
	}
	else
	{
		//model's not ready, so we'll make the proxy instead
		wrap = engine->CreateSectorWallsMesh( tempSector, "walls" );

		tempState = SCF_QUERY_INTERFACE( wrap->GetMeshObject(), iThingState );

		//setup coords
		coords[0].Set( dimensions.x, 0, dimensions.y );
		coords[1].Set( dimensions.x, 0, 0 );
		coords[2].Set( 0, 0, 0 );
		coords[3].Set( 0, 0, dimensions.y );
		
		coords[4].Set( dimensions.x, dimensions.z, dimensions.y );
		coords[5].Set( dimensions.x, dimensions.z, 0 );
		coords[6].Set( 0, dimensions.z, 0 );
		coords[7].Set( 0, dimensions.z, dimensions.y );

		//floor
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[0] );
		p->CreateVertex( coords[1] );
		p->CreateVertex( coords[2] );
		p->CreateVertex( coords[3] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );
  
		//ceiling
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[7] );
		p->CreateVertex( coords[6] );
		p->CreateVertex( coords[5] );
		p->CreateVertex( coords[4] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );

		//left wall
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[2] );
		p->CreateVertex( coords[6] );
		p->CreateVertex( coords[7] );
		p->CreateVertex( coords[3] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );

		//right wall
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[0] );
		p->CreateVertex( coords[4] );
		p->CreateVertex( coords[5] );
		p->CreateVertex( coords[1] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );

		//front wall
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[3] );
		p->CreateVertex( coords[7] );
		p->CreateVertex( coords[4] );
		p->CreateVertex( coords[0] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );

		//back wall
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[1] );
		p->CreateVertex( coords[5] );
		p->CreateVertex( coords[6] );
		p->CreateVertex( coords[2] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );

		wrap->DeferUpdateLighting( CS_NLIGHT_STATIC|CS_NLIGHT_DYNAMIC, 10 );

		//get rid of our references
		tempState->DecRef();
		wrap->DecRef();
	}

	//setup lights
	light = engine->CreateLight( NULL, csVector3( 5, 5, 5 ), 
		20, csColor( 1, 0, 0 ), false );
	tempSector->GetLights()->Add( light->QueryLight() );
	light->DecRef();

	//shine lights
	tempSector->ShineLights();

	//prepare new meshes
	engine->PrepareMeshes();
}

void ClientEngine::addDoor( EMDoor *door )
{

}

void ClientEngine::addObject( EMObject *object )
{
	char buff[50];
	iSector *sector;
	EMRoom *room;
	iMeshWrapper *wrap;
	iMeshFactoryWrapper *factory;
	iThingState *tempState;
	iPolygon3D* p;
	csVector3 coords[8];
	Coords dimensions;
	Coords pos;
	iMaterialWrapper* tm = engine->GetMaterialList()->FindByName("stone");

	//get the room
	room = object->getRoom();

	//make it a string
	sprintf( buff, "%d", room->getID() );

	//find the sector that this object should be in
	sector = engine->FindSector( buff );

	//format the model string
	sprintf( buff, "%d", object->getModel()->getID() );

	//get dimensions/position
	dimensions = object->getDimensions();
	pos = object->getRoomCoords();

	//get the factory
	factory = engine->GetMeshFactories()->FindByName( buff );
	if( factory )
	{
		//now put in the model
		//wrap = engine->CreateMeshWrapper( factory, buff, tempSector );

		//wrap->DecRef();
	}
	else
	{
		//model's not ready, so we'll make the proxy instead
		sprintf( buff, "%d", object->getID() );

		//make the wrapper object
		wrap = engine->CreateMeshWrapper( defaultFactory->NewInstance(), buff, sector );

		tempState = SCF_QUERY_INTERFACE( wrap->GetMeshObject(), iThingState );
		tempState->SetMovingOption( CS_THING_MOVE_OFTEN );

		//setup coords
		coords[0].Set( dimensions.x/2, -dimensions.z/2, dimensions.y/2 );
		coords[1].Set( dimensions.x/2, -dimensions.z/2, -dimensions.y/2 );
		coords[2].Set( -dimensions.x/2, -dimensions.z/2, -dimensions.y/2 );
		coords[3].Set( -dimensions.x/2, -dimensions.z/2, dimensions.y/2 );
		
		coords[4].Set( dimensions.x/2, dimensions.z/2, dimensions.y/2 );
		coords[5].Set( dimensions.x/2, dimensions.z/2, -dimensions.y/2 );
		coords[6].Set( -dimensions.x/2, dimensions.z/2, -dimensions.y/2 );
		coords[7].Set( -dimensions.x/2, dimensions.z/2, dimensions.y/2 );

		//floor
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[3] );
		p->CreateVertex( coords[2] );
		p->CreateVertex( coords[1] );
		p->CreateVertex( coords[0] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );
  
		//ceiling
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[4] );
		p->CreateVertex( coords[5] );
		p->CreateVertex( coords[6] );
		p->CreateVertex( coords[7] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );

		//left wall
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[3] );
		p->CreateVertex( coords[7] );
		p->CreateVertex( coords[6] );
		p->CreateVertex( coords[2] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );

		//right wall
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[1] );
		p->CreateVertex( coords[5] );
		p->CreateVertex( coords[4] );
		p->CreateVertex( coords[0] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );

		//front wall
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[0] );
		p->CreateVertex( coords[4] );
		p->CreateVertex( coords[7] );
		p->CreateVertex( coords[3] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );

		//back wall
		p = tempState->CreatePolygon();
		p->SetMaterial(tm);
		p->CreateVertex( coords[2] );
		p->CreateVertex( coords[6] );
		p->CreateVertex( coords[5] );
		p->CreateVertex( coords[1] );
		p->SetTextureSpace( p->GetVertex (0), p->GetVertex (1), 3 );

		//set position
		wrap->GetMovable()->SetPosition( csVector3( 0, 5, 0 ) );
		wrap->GetMovable()->UpdateMove();
		wrap->DeferUpdateLighting( CS_NLIGHT_STATIC|CS_NLIGHT_DYNAMIC, 10 );

		//get rid of our references
		tempState->DecRef();
		wrap->DecRef();
	}

	//prepare new meshes
	engine->PrepareMeshes();
}

void ClientEngine::addAvatar( EMAvatar *avatar )
{

}

void ClientEngine::setCamera( float x, float y, float z )
{
	iCamera *camera;

	camera = view->GetCamera();

	camera->GetTransform().SetOrigin( csVector3( x, y, z ) );
}

void ClientEngine::offsetCamera( float x, float y, float z )
{
	iCamera *camera;

	camera = view->GetCamera();

	camera->Move( csVector3( x, y, z ) );
}

void ClientEngine::setCameraRotate( float x, float y, float z )
{
	iCamera *camera;

	camera = view->GetCamera();
	
}

void ClientEngine::offsetCameraRotate( float x, float y, float z )
{
	iCamera *camera;

	camera = view->GetCamera();

	camera->GetTransform().RotateThis( csVector3( 1, 0, 0 ), x );
	camera->GetTransform().RotateThis( csVector3( 0, 1, 0 ), y );
	camera->GetTransform().RotateThis( csVector3( 0, 0, 1 ), z );
}

void ClientEngine::setCameraFocus( float x, float y, float z )
{
	iCamera *camera;

	camera = view->GetCamera();

	camera->GetTransform().LookAt( csVector3( x, y, z ), csVector3( 0, 1, 0 ) );
}

void ClientEngine::moveToRoom( EMRoom *room, float x, float y, float z )
{
	char buff[50];
	iSector *tempSector;

	//make the sector name
	sprintf( buff, "%d", room->getID() );

	//get the sector
	tempSector = engine->GetSectors()->FindByName( buff );

	//invalid
	if( tempSector == NULL )
	{
		//LOG
		return;
	}

	//change rooms
	view->GetCamera()->SetSector( tempSector );

	//move the camera
	setCamera( x, y, z );
}

void ClientEngine::flush( void )
{

}