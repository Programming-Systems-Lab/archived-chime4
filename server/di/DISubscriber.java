package psl.chime4.server.di;

public class DISubscriber
{
	private Object value;

	public DISubscriber( Object setValue )
	{
		value = setValue;
	}

	public Object getIdentValue()
	{
		return value;
	}
}