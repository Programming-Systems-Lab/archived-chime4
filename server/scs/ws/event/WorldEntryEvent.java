package psl.chime4.server.scs.ws.event;

import psl.chime4.server.ces.*;

/**
 * A request by the Client to the World Service to enter the world. 
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class WorldEntryEvent extends WorldEvent
{
   /** username of the client requesting entry **/
   public String Username;
   
   /**
    * Construct the event with the passed parameters.
    *
    * @param username name of the user sending the event
    * @throws IllegalArgumentException
    *         if <code>username</code> is <code>null</code>
    **/
   public WorldEntryEvent(String username)
   {
      super(EventConstants.WorldEntry, EventConstants.WorldEntryUri);
      
      if (username == null)
      {
         String msg = "username cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.Username = username;
   }
   
   /**
    * Construct the event from a Ces Event.
    *
    * @param event event containing the info
    **/
   public WorldEntryEvent(Event event)
   {
      super(event);
      
      this.Username = event.getString("client.name");
   }
   
   public Event toCesEvent(Event event)
   {
      event = super.toCesEvent(event);
      event.put("client.name", Username);
      return event;
   }
}