package psl.chime4.server.scs.jc;

/**
 * This class stores pertinent information about the client.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public final class ClientState
{
   public String UniqueTopic;  
   public String Username;
   public String Password;
   
   public String EventServer;
   public int EventServerPort;
   /** topic of the world the client is currently logged into **/
   public String WorldTopic;
}
