package psl.chime4.server.di;

public class DIMessageBody
{
	String data;

    public DIMessageBody( String msgData )
    {
		data = msgData;
    }

    String getData()
    {
		return data;
    }
}
