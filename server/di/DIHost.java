package psl.chime4.server.di;

public class DIHost
{
	private DIZone zone;
	private DIType hostID;
	private DIType hostType;
	private String info;

    public DIHost( DIType setIdent, DIType setType, String setInfo, int length, DIZone setZone )
    {
		hostID = setIdent;
		hostType = setType;
		info = setInfo;
		zone = setZone;
    }

    public void setHostType( DIType setType )
    {
		hostType = setType;
    }

    public void setHostInfo( String setInfo )
    {
		info = setInfo;
    }

    public void setHostID( DIType setIdent )
    {
		hostID = setIdent;
    }

    public void setHostZone( DIZone setZone )
    {
		zone = setZone;
	}

    public DIType getHostType()
    {
		return hostType;
    }

    public String getHostInfo()
    {
		return info;
    }

    public DIZone getHostZone()
    {
		return zone;
    }

    public DIType getHostID()
    {
		return hostID;
    }
}
