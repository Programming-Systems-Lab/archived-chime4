package psl.chime4.server.di;

import psl.chime4.server.cebs.*;

public class DICebsHandler implements EventHandler
{
	private DIZone zone;
	private DIType ident;

	public DICebsHandler( DIZone theZone )
	{
		ident = DirectoryInterface.getIdent();
		zone = theZone;
	}

	public void handleEvent( Event event )
	{

	}

	public void setZone( DIZone theZone )
	{
		zone = theZone;
	}
}