package psl.chime4.server.di;

public class DIMessageBody
{
	byte data[];

    public DIMessageBody( String msgData )
    {
		data = msgData.getBytes();
    }

    public DIMessageBody( byte[] msgData )
    {
		data = msgData;
	}

    public String toString()
    {
		return new String( data );
    }

    public byte[] toBytes()
    {
		return data;
	}
}
