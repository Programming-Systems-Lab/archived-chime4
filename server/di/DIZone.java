package psl.chime4.server.di;

public class DIZone
{
	public static final int MODE_KNOWN = 0;
	public static final int MODE_INFRASTRUCTURE = 1;
	public static final int MODE_LEAF = 2;

	private DICebsHandler handler;
	private String address;
	private int port;
	private int mode;

    public DIZone( String setAddress, int setPort, int setMode )
    {
		handler = null;
		address = setAddress;
		port = setPort;
		mode = setMode;
    }

    public void setHandler( DICebsHandler eventHandler )
    {
		handler.setZone( this );
		handler = eventHandler;
	}

	public DICebsHandler getHandler()
	{
		return handler;
	}

	public DIType getIdent()
	{
		return new DIType(address + ":" + port);
	}

	public String getAddress()
	{
		return address;
	}

	public int getPort()
	{
		return port;
	}

	public int getMode()
	{
		return mode;
	}
}
