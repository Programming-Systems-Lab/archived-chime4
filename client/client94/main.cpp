#include "core.h"

//global data
ClientApp *gApp = NULL;

// Define the skin for windowing system
CSWS_SKIN_DECLARE_DEFAULT( DefaultSkin );

//load the engine
CS_IMPLEMENT_APPLICATION


int main( int argc, char *argv[] )
{
	iObjectRegistry *object_reg;

	// Initialize the random number generator
	srand( time (NULL) );
	
	//create the object registry
	object_reg = csInitializer::CreateEnvironment( argc, argv );
	if( !object_reg )
	{
		//LOG
		return -1;
	}

	//init application
	gApp = new ClientApp( object_reg, DefaultSkin );
	if( !gApp->Initialize( argc, argv ) )
	{
		//log
		return -1;
	}

	//start the main loop
	gApp->Start();

	//cleanup
	delete gApp;
	
	//no errors
	return 0;
}