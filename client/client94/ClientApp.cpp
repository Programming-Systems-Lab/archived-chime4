#include "core.h"
#include "ClientEventDefs.h"
#include "em/EnvironmentModeler.h"


bool ClientHandleEvent( iEvent &event )
{
	if( event.Type == csevBroadcast && event.Command.Code == cscmdProcess )
	{
		gApp->SetupFrame();
		return true;
	}
	else if( event.Type == csevBroadcast && event.Command.Code == cscmdFinalProcess)
	{
		gApp->FinishFrame();
		return true;
	}
	else
	{
		return gApp->HandleEvent( event );
	}
}

ClientApp::ClientApp( iObjectRegistry *setReg, csSkin &setSkin ): csApp( setReg, setSkin )
{
	//set defaults
	object_reg = setReg;

	//managers
	plugin_mgr = NULL;
	graphics2D = NULL;
	graphics3D = NULL;
	engine = NULL;
	VFS = NULL;
	KeyboardDriver = NULL;
	vc = NULL;
	FontServer = NULL;
	ImageLoader = NULL;
	texture_mgr = NULL;
	modelConverter = NULL;

	//menus
	ka = NULL;
	menuBar = NULL;
	clientMenu = NULL;
	connectItem = NULL;
	disconnectItem = NULL;
	videoConfigItem = NULL;
	exitItem = NULL;
	windowsMenu = NULL;
	displayItem = NULL;
	consoleItem = NULL;

	//windowing
	nw = NULL;
	aws = NULL;
	awsPrefs = NULL;
	awsCanvas = NULL;

	//config
	configFile = CLIENT_DEFAULT_CONFIG_PATH;

	//3d view
	active = false;
	view = NULL;

	//runtime
	mode = 0;
}

ClientApp::~ClientApp( void )
{
	//get all the dll's to unload
	if( graphics2D )
		graphics2D->DecRef();

	if( graphics3D )
		graphics3D->DecRef();

	if( engine )
		engine->DecRef();

	//destroy the application
//	csInitializer::DestroyApplication( object_reg );
}

bool ClientApp::Initialize( int argc, char *argv[] )
{
	//parse command line
	parseCommandLine( argc, argv );

	//init our config
	if( !csInitializer::SetupConfigManager( object_reg, configFile ) )
	{
		//LOG
		return false;
	}

	//manual load
	if (!csInitializer::RequestPlugins (object_reg,
		CS_REQUEST_REPORTER,
		CS_REQUEST_REPORTERLISTENER,
		CS_REQUEST_END))
	{
		return false;
	}

	//load plugin manager
	plugin_mgr = CS_QUERY_REGISTRY( object_reg, iPluginManager );
	if( !plugin_mgr )
	{
		//LOG
		return false;
	}

	//get the VFS
	VFS = CS_QUERY_REGISTRY( object_reg, iVFS );
	if( !VFS )
	{
		//LOG
		return false;
	}

	//setup 3D graphics
	graphics3D = CS_QUERY_REGISTRY( object_reg, iGraphics3D );
	if( !graphics3D )
	{
		//LOG
		return false;
	}

	//setup 2D graphics
	graphics2D = CS_QUERY_REGISTRY( object_reg, iGraphics2D );
	if( !graphics2D )
	{
		//LOG
		return false;
	}

	//get the engine
	engine = CS_QUERY_REGISTRY( object_reg, iEngine );
	if( !engine )
	{
		//LOG
		return false;
	}

	//get the font server
	FontServer = CS_QUERY_REGISTRY( object_reg, iFontServer );
	if( !FontServer )
	{
		//LOG
		return false;
	}

	//image loader
	ImageLoader = CS_QUERY_REGISTRY( object_reg, iImageIO );
	if( !ImageLoader )
	{
		//LOG
		return false;
	}

	//load keyboard driver
	KeyboardDriver = CS_QUERY_REGISTRY( object_reg, iKeyboardDriver );
	if( !KeyboardDriver )
	{
		//LOG
		return false;
	}

	//load the virtual clock
	vc = CS_QUERY_REGISTRY( object_reg, iVirtualClock );
	if( !vc )
	{
		//LOG
		return false;
	}

	//load the loader =)
	loader = CS_QUERY_REGISTRY( object_reg, iLoader );
	if( !loader )
	{
		//LOG
		return false;
	}

	// Find the model converter plugin
	modelConverter = CS_QUERY_REGISTRY( object_reg, iModelConverter );
	if( !modelConverter )
	{
		//LOG
		return false;
	}

	//load the cross builder
	crossBuilder = CS_QUERY_REGISTRY( object_reg, iCrossBuilder );
	if( !crossBuilder )
	{
		//LOG
		return false;
	}

	//setup the native window
	nw = graphics2D->GetNativeWindow();
	if( nw == NULL )
	{
		//LOG
		return false;
	}

	nw->SetTitle(CLIENT_NATIVE_WINDOW_TITLE);

	//open the application
	if( !csInitializer::OpenApplication( object_reg ) )
	{
		//LOG
		return false;
	}

	//init the base component
	if( !csApp::Initialize() )
	{
		//LOG
		return false;
	}
	
	//get the texture manager
	texture_mgr = graphics3D->GetTextureManager();

	//set the default palette
	texture_mgr->SetPalette();

	//init aws
	aws = CS_LOAD_PLUGIN( plugin_mgr, "crystalspace.window.alternatemanager", iAws );
	if( !aws )
	{
		//LOG
		return false;
	}

	awsCanvas = aws->CreateCustomCanvas( graphics2D, graphics3D );
	aws->SetFlag( AWSF_AlwaysRedrawWindows );
	aws->SetCanvas( awsCanvas );

	awsPrefs = aws->GetPrefMgr();

	if( !awsPrefs->Load(CLIENT_DEFAULT_AWS_DEFS) )
	{
		//LOG
		return false;
	}

	awsPrefs->SelectDefaultSkin("Normal Windows");

	//load a random texture
	loader->LoadTexture ("stone", "/lib/stdtex/misty.jpg");

	//init the camera
	InitCamera();

	//init engine
	ClientEngine::initialize( view );

	//create windows
	InitWindows();

	//create menus
	InitMenus();

	//init local
	EnvironmentModeler::initialize();

	//start threads


	//prepare the engine
	engine->Prepare();
	
	//register event handler now once everything's ready
	if( !csInitializer::SetupEventHandler( object_reg, ClientHandleEvent ) )
	{
		//LOG
		return false;
	}

	return true;
}

void ClientApp::InitCamera( void )
{
	//turn of light caching
	engine->SetLightingCacheMode( false );

	//set ambient light
	engine->SetAmbientLight(csColor(0.3, 0.3, 0.3));

	view = new csView (engine, graphics3D);
	//view->SetRectangle( 0, 0, 320, 240 );
}

void ClientApp::InitMenus( void )
{
	//set it to single buffering, much faster
	GetG2D()->DoubleBuffer( false );

	//setup keyboard accelerators
	ka = new csKeyboardAccelerator( this );

	//setup the menu bar
	menuBar = new csMenu( this, csmfsBar, 0 );

	//client menu
	clientMenu = new csMenu( NULL );

	new csMenuItem( menuBar, "~Client", clientMenu );
	connectItem = new csMenuItem( clientMenu, "~Connect", CLIENT_MENU_CONNECT );
	disconnectItem = new csMenuItem( clientMenu, "~Disconnect", CLIENT_MENU_DISCONNECT );
	new csMenuItem( clientMenu );
	videoConfigItem = new csMenuItem( clientMenu, "~Video Config", CLIENT_MENU_VIDEO_CONFIG );
	new csMenuItem( clientMenu );
	exitItem = new csMenuItem( clientMenu, "~Exit", CLIENT_MENU_EXIT );

	//windows menu
	windowsMenu = new csMenu( NULL );

	new csMenuItem( menuBar, "~Windows", windowsMenu );
	displayItem = new csMenuItem( windowsMenu, "~Display", CLIENT_MENU_DISPLAY );
	consoleItem = new csMenuItem( windowsMenu, "~Console", CLIENT_MENU_CONSOLE );

	//make the menu big
	menuBar->SetRect( 0, 0, graphics2D->GetWidth(), CLIENT_MENU_HEIGHT );
}

void ClientApp::InitWindows( void )
{
	//display window
	display = new DisplayWindow( aws, view, graphics2D );
	display->Move( 160, CLIENT_MENU_HEIGHT+2 );
	display->Show();
}

void ClientApp::Start( void )
{
	//start the client loop
	csDefaultRunLoop( object_reg );
}

void ClientApp::ShutDown( void )
{
	//stop all threads

	//shutdown local
	EnvironmentModeler::shutdown();

	//close the program
	csApp::ShutDown();
}

void ClientApp::SetMode( int theMode )
{
	mode = theMode;
}

int ClientApp::GetMode( void )
{
	return mode;
}

void ClientApp::Start3D( void )
{
	active = false;
}

void ClientApp::Stop3D( void )
{
	active = true;
}

void ClientApp::SetupFrame( void )
{
	csTicks elapsed_time = vc->GetElapsedTicks ();

	// Now rotate the camera according to keyboard state
	float speed = (elapsed_time / 1000.0) * (0.03 * 20)*2;

	//handle inputs
	iCamera* c = view->GetCamera();
	if (KeyboardDriver->GetKeyState (CSKEY_RIGHT))
		c->GetTransform ().RotateThis (CS_VEC_ROT_RIGHT, speed);
	if (KeyboardDriver->GetKeyState (CSKEY_LEFT))
		c->GetTransform ().RotateThis (CS_VEC_ROT_LEFT, speed);
	if (KeyboardDriver->GetKeyState (CSKEY_PGUP))
		c->GetTransform ().RotateThis (CS_VEC_TILT_UP, speed);
	if (KeyboardDriver->GetKeyState (CSKEY_PGDN))
		c->GetTransform ().RotateThis (CS_VEC_TILT_DOWN, speed);
	if (KeyboardDriver->GetKeyState (CSKEY_UP))
		c->Move(CS_VEC_FORWARD * 4 * speed);
	if (KeyboardDriver->GetKeyState (CSKEY_DOWN))
		c->Move(CS_VEC_BACKWARD * 4 * speed);

	//do 2d drawing
	if( !graphics3D->BeginDraw( CSDRAW_2DGRAPHICS ) )
		return;

	//clear everything
	graphics2D->DrawBox( 0, CLIENT_MENU_HEIGHT+2, graphics2D->GetWidth(), graphics2D->GetHeight(), 0x444444 );

	//draw csws stuff
 	Redraw();

	//draw windows
	display->Draw();

	//redraw aws
	aws->Redraw();
	aws->Print(graphics3D);
}

void ClientApp::FinishFrame( void )
{
	//push it out to the screen
	graphics3D->FinishDraw();
	graphics3D->Print( NULL );

	//invalidate the menu
	menuBar->Invalidate( true );
}

iGraphics2D *ClientApp::GetG2D( void )
{
	return graphics2D;
}

iGraphics3D *ClientApp::GetG3D( void )
{
	return graphics3D;
}

iAws *ClientApp::GetAWS( void )
{
	return aws;
}

bool ClientApp::LoadModel( char *file, char *name )
{
	iDataBuffer *buf;
	iModelData *model;
	iMeshFactoryWrapper *wrap;
	iMaterialWrapper *defaultMaterial;

	//read in the file
	buf = VFS->ReadFile( file );
	if( !buf )
		return false;

	//load the converter
	model = modelConverter->Load( buf->GetUint8(), (uint32) buf->GetSize() );

	//clear the buffer
	buf->DecRef();

	//make it all into one big blob
	csModelDataTools::SplitObjectsByMaterial( model );
	csModelDataTools::MergeObjects( model, false );

	//make the sprite factory
	wrap = crossBuilder->BuildSpriteFactoryHierarchy( model, engine, defaultMaterial );

	//clear the model data
	model->DecRef();

	//set the name
	wrap->QueryObject()->SetName( name );

	return true;
}

bool ClientApp::HandleEvent( iEvent &event )
{
	//do interface handling
	if( aws->HandleEvent( event ) )
		return true;

	if( csApp::HandleEvent( event ) )
		return true;

	//find another handler
	switch( event.Type )
	{
		case csevKeyDown:
			if( event.Key.Char == 'a' )
			{
				if( !active )
				{	
					active = true;
					EnvironmentModeler::placeRoom( 100, Coords( 100, 50, 10 ), 100 );
					EnvironmentModeler::setCamera( 100, Coords( 0, 0, 0 ) );
					view->GetCamera()->GetTransform().LookAt( csVector3( 0, 0, 1 ), csVector3( 0, 1, 0 ) );
					EnvironmentModeler::placeObject( 200, 100, Coords( 2, 2, 2 ), Coords(20, 20, 5), Coords(0, 0, 0), 200 );
				}
			}
			break;

		case csevCommand:
			//check the upper 8 bits
			switch( ( event.Command.Code & 0xFF000000 ) )
			{
				case CLIENT_MENU_EVENT:
					switch( event.Command.Code )
					{
						case CLIENT_MENU_EXIT:
							ShutDown();
							return true;
					}
					return false;

				case CLIENT_DISPLAY_WINDOW_EVENT:
					return display->HandleEvent( event );

				case CLIENT_CONSOLE_WINDOW_EVENT:
					//return console->HandleEvent( event );
					break;

				default:
					break;
			}
			break;
	}

	return false;
}

void ClientApp::parseCommandLine( int argc, char *argv[] )
{

}