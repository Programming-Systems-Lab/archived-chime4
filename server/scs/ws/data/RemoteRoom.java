package psl.chime4.server.scs.ws.data;

/**
 * Represents a room on a remote server.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RemoteRoom extends Room
{
   /** host of the remote server **/
   private String host;
   
   /** port of the remote server process **/
   private int port;
   
   /** unique topic for the remote server **/
   private String topic;
   
   /**
    * Get the host of the remote server.
    *
    * @return host of the remote server
    **/
   public String getRemoteHost()
   {
      return host;
   }
   
   /**
    * Set the host of the remote server.
    *
    * @param host host of the remote server
    **/
   public void setRemoteHost(String host)
   {
      this.host = host;
   }
   
   /**
    * Get the port of the remote server.
    *
    * @return port of the remote server
    **/
   public int getRemotePort()
   {
      return port;
   }
   
   /**
    * Set the port of the remote server.
    *
    * @param port port of the remote server
    **/
   public void setRemotePort(int port)
   {
      this.port = port;
   }
   
   /**
    * Get the unique topic for the remote server.
    *
    * @return topic for the remote server
    **/
   public String getRemoteTopic()
   {
      return topic;
   }
   
   /**
    * Set the unique topic for the remote server.
    *
    * @param topic topic for the remote server
    **/
   public void setRemoteTopic(String topic)
   {
      this.topic = topic;
   }
}