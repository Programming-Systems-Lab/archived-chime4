#ifndef __DISPLAY_WINDOW_H__
#define __DISPLAY_WINDOW_H__

class DisplayWindow
{
	public:
		DisplayWindow( iAws *aws, csView *view, iGraphics2D *set2D );
		~DisplayWindow( void );

		void Draw( void );
		void Show( void );
		void Hide( void );
		void Move( int x, int y );
		
		bool HandleEvent( iEvent &event );

	private:
		iAwsWindow *theWindow;
		iGraphics2D *graphics2D;
};

#endif