#include "core.h"


DisplayWindow::DisplayWindow( iAws *aws, csView *view, iGraphics2D *set2D )
{
	graphics2D = set2D;

	theWindow = aws->CreateWindowFrom("Display");
	
	if( !theWindow )
	{
		//LOG
	}

	//set the window's view to that of the main camera
	theWindow->SetEngineView( view );
}

DisplayWindow::~DisplayWindow( void )
{
	delete theWindow;
}

void DisplayWindow::Draw( void )
{
	csRect myRect;

	myRect = theWindow->Frame();

	//clear the window frame
	graphics2D->DrawBox( myRect.xmin, myRect.ymin, myRect.Width(), myRect.Height(), 0 );
}

void DisplayWindow::Show( void )
{
	theWindow->Show();
}

void DisplayWindow::Hide( void )
{
	theWindow->Hide();
}

void DisplayWindow::Move( int x, int y )
{
	theWindow->Move( x, y );
}

bool DisplayWindow::HandleEvent( iEvent &event )
{
	return false;
}