package psl.chime4.server.scs.ws.event;

import psl.chime4.server.ces.*;

/**
 * A WorldLoginEvent sent by the client to the world to login. Once a client
 * has logged into the world they are not yet part of the world; instead they
 * can now use any service exported by that world service.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class WorldLoginEvent extends WorldEvent
{
   /** username of the client logging in **/
   public String Username;
   
   /** client's ticket; proof of the client's identity **/
   public byte[] Ticket;
   
   /** unique topic; this is how the world service responds to the login **/
   public String UniqueTopic;
   
   /**
    * Basic constructor to initialize all fields.
    *
    * @param username name of the client logging in
    * @param ticket   proof of the client's identity
    * @param topic    unique topic that the client is listening to
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    **/
   public WorldLoginEvent(String username, byte[] ticket, String topic)
   {
      super(EventConstants.WorldLogin, EventConstants.WorldLoginUri);
      
      if ((username == null) || (ticket == null) || (topic == null))
      {
         String msg = "no param can be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.Username = username;
      this.Ticket = ticket;
      this.UniqueTopic = topic;
   }
   
   /**
    * Initialize the WorldLoginEvent from a Ces Event.
    *
    * @param event Ces Event storing the information
    * @throws IllegalArgumentException
    *         if <code>event</code> is <code>null</code>
    **/
   public WorldLoginEvent(Event event)
   {
      super(event);
      
      this.Username = event.getString("client.name");
      this.Ticket = event.getByteArray("client.ticket");
      this.UniqueTopic = event.getString("client.uniqueTopic");
   }
   
   public Event toCesEvent(Event event)
   {
      event = super.toCesEvent(event);
      
      event.put("client.name", Username);
      event.put("client.ticket", Ticket);
      event.put("client.uniqueTopic", UniqueTopic);
      return event;
   }
}
