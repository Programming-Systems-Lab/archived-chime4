#ifndef __CONSOLE_WINDOW_H__
#define __CONSOLE_WINDOW_H__

class ConsoleWindow
{
	public:
		ConsoleWindow( iAws *aws );
		~ConsoleWindow( void );

		void Show( void );
		void Hide( void );
		void Move( int x, int y );
		
		bool HandleEvent( iEvent &event );

	private:
		iAwsWindow *theWindow;
};

#endif