package psl.chime4.server.di;

public class DIEvent
{
	private int eventType;

    public DIEvent( int type )
    {
		eventType = type;
    }

    public int getType()
    {
		return eventType;
	}
}
