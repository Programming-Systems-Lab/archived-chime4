#include "core.h"


ClientSystemDriver::ClientSystemDriver( void )
{
	graphics2D = NULL;
	graphics3D = NULL;
}

ClientSystemDriver::~ClientSystemDriver( void )
{
	
}

bool ClientSystemDriver::Initialize( int argc, char *argv[], char *configFile, iObjectRegistry *setReg )
{
	iConfigManager* Config;
	char *name;

	//set the registry
	object_reg = setReg;

	//init our config
	if( !csInitializer::SetupConfigManager( object_reg, configFile ) )
	{
		//LOG
		return false;
	}

	//manual load
	if (!csInitializer::RequestPlugins (object_reg,
  		CS_REQUEST_VFS,
		CS_REQUEST_SOFTWARE3D,
		CS_REQUEST_ENGINE,
		CS_REQUEST_REPORTER,
		CS_REQUEST_REPORTERLISTENER,
		CS_REQUEST_END))
	{
		return false;
	}

	//check our config
	Config = CS_QUERY_REGISTRY (object_reg, iConfigManager);
	if( !Config )
	{
		//LOG
		return false;
	}

	name = (char *) Config->GetStr("System.ApplicationID");

	//setup the event handler
	if( !csInitializer::SetupEventHandler( object_reg, ClientHandleEvent ) )
	{
		//LOG
		return false;
	}

	//get the VFS
	vfs = CS_QUERY_REGISTRY( object_reg, iVFS );
	if( !vfs )
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


	//setup the native window
	nw = graphics2D->GetNativeWindow();
	if( nw == NULL )
	{
		//LOG
		return false;
	}

	nw->SetTitle(CLIENT_NATIVE_WINDOW_TITLE);

	//no errors
	return true;
}