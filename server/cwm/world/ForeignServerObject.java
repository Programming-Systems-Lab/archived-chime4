package psl.chime4.server.cwm.world;

/**
 * This is a special type of Room which represents a foreign server. A 
 * foreign server is a world that is being hosted on another computer. In
 * order for the client to enter this room a special user-handoff must be
 * negotiated with the foreign server and the client.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ForeignServerObject extends Room
{
   /** the event server to contact to talk to the Zone server **/
   private String eventServerAddress;
   
   /** the topic to publish to send a message to that zone server **/
   private String topic;
   
   /**
    * Get the address of the event server that the Zone Server that is 
    * hosting this part of the Street.
    *
    * @return address of the event server to the Zone Server
    **/
   public String getEventServerAddress()
   {
      return eventServerAddress;
   }
   
   /**
    * Set the address of the event server that the Zone Server is 
    * connected to.
    *
    * @param eventServer the address of the event server of the Zone Server
    **/
   public void setEventServerAddress(String eventServer)
   {
      this.eventServerAddress = eventServer;
   }
   
   /**
    * The topic that the Zone Server is guaranteed to be listening to on
    * the event server it is connected to.
    *
    * @return topic that the zone server is listening to
    **/
   public String getZoneServerTopic()
   {
      return topic;
   }
   
   /**
    * Set the topic that the Zone Server is guaranteed to be to on the event
    * server that is connected to.
    *
    * @param topic topic that the zone server is listening to
    **/
   public void setZoneServerTopic(String topic)
   {
      this.topic = topic;
   }
}