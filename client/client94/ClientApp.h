#ifndef __CLIENT_APP_H__
#define __CLIENT_APP_H__

//dependencies
#include "DisplayWindow.h"
#include "ConsoleWindow.h"

//functions
bool ClientHandleEvent( iEvent &event );

//declaration
class ClientApp: public csApp
{
	public:
		ClientApp( iObjectRegistry *setReg, csSkin &setSkin );
		virtual ~ClientApp( void );

		bool Initialize( int argc, char *argv[] );

		void InitCamera( void );
		void InitWindows( void );
		void InitMenus( void );

		void Start( void );
		void ShutDown( void );

		void SetMode( int theMode );
		int GetMode( void );

		void Start3D( void );
		void Stop3D( void );

		void SetupFrame( void );
		void FinishFrame( void );

		iGraphics2D *GetG2D( void );
		iGraphics3D *GetG3D( void );
		iAws *GetAWS( void );

		bool LoadModel( char *file, char *name );

		bool HandleEvent( iEvent &event );

	private:
		void parseCommandLine( int argc, char *argv[] );

		//flow control
		bool active;

		//windows
		DisplayWindow *display;
		ConsoleWindow *console;

		//menus
		csKeyboardAccelerator *ka;
		csMenu *menuBar;
		csMenu *clientMenu;
		csMenuItem *connectItem;
		csMenuItem *disconnectItem;
		csMenuItem *videoConfigItem;
		csMenuItem *exitItem;
		csMenu *windowsMenu;
		csMenuItem *displayItem;
		csMenuItem *consoleItem;

		//windowing
		iNativeWindow *nw;
		iAws *aws;
		iAwsPrefManager *awsPrefs;
		iAwsCanvas *awsCanvas;

		//managers
		iTextureManager* texture_mgr;
		iGraphics2D *graphics2D;
		iGraphics3D *graphics3D;
		iEngine *engine;
		iLoader *loader;
		iModelConverter *modelConverter;
		iCrossBuilder *crossBuilder;

		//config
		char *configFile;

		//view
		csView *view;

		//runtime
		int mode;
};

#endif