package psl.chime4.server.cwm.world.wms;

/**
 * A RemoteRoom is a room that resides on a remote server. When the user 
 * enters a remote room a client handoff is initiated behind the screens.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RemoteRoom extends Room
{
   /** host of the event server **/
   private String host;
   
   /** port of the event server **/
   private int port;
   
   /** topic the remote server is listening to **/
   private String topic;
   
   /**
    * Get the host of the event server of the remote server hosting this
    * room.
    *
    * @return host of the event server of the remote server 
    **/
   public String getHost()
   {
      return host;
   }
   
   /**
    * Set the host of the event server of the remote server.
    *
    * @param host host of the event server of the remote server
    **/
   public void setHost(String host)
   {
      this.host = host;
   }
   
   /**
    * Get the port of the event server of the remote server.
    *
    * @return port of the event server of the remote server
    **/
   public int getPort()
   {
      return port;
   }
   
   /**
    * Set the port of the event server of the remote server.
    *
    * @param port port of the event server of the remote server
    **/
   public void setPort(int port)
   {
      this.port = port;
   }
   
   /**
    * Get the topic of the remote world server on the remote event server.
    *
    * @return topic the remote world server is listening to
    **/
   public String getTopic()
   {
      return topic;
   }
   
   /**
    * Set the topic that the remote world server is listening to.
    *
    * @param topic the topic that the remote world server is listening to
    **/
   public void setTopic(String topic)
   {
      this.topic = topic;
   }
}
