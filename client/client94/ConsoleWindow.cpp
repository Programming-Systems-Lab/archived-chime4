#include "core.h"


ConsoleWindow::ConsoleWindow( iAws *aws )
{
	theWindow = aws->CreateWindowFrom("Console");
	if( !theWindow )
	{
		//LOG
	}
}

ConsoleWindow::~ConsoleWindow( void )
{
	delete theWindow;
}

void ConsoleWindow::Show( void )
{
	theWindow->Show();
}

void ConsoleWindow::Hide( void )
{
	theWindow->Hide();
}

void ConsoleWindow::Move( int x, int y )
{
	theWindow->Move( x, y );
}

bool ConsoleWindow::HandleEvent( iEvent &event )
{
	return false;
}