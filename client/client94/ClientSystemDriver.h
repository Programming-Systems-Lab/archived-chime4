#ifndef __CLIENT_SYSTEM_DRIVER_H__
#define __CLIENT_SYSTEM_DRIVER_H__

class ClientSystemDriver
{
	public:
		ClientSystemDriver( void );
		~ClientSystemDriver( void );

		bool Initialize( int argc, char *argv[], char *configFile, iObjectRegistry *setReg );

	private:
		iObjectRegistry *object_reg;
		
		//graphics
		iGraphics2D *graphics2D;
		iGraphics3D *graphics3D;
		iEngine *engine;
		iVFS *vfs;

};

#endif