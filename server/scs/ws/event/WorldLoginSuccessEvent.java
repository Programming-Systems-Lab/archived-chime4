package psl.chime4.server.scs.ws.event;

import psl.chime4.server.ces.*;

/**
 * Event sent by the WorldService to the client when they have successfully
 * logged into the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class WorldLoginSuccessEvent extends WorldEvent
{
   /** username of the world that has granted login privs **/
   public String WorldName;
   
   /**
    * Construct a WorldLoginSuccessEvent.
    *
    * @param worldName name of the world sending the event
    * @throws IllegalArgumentException
    *         if <code>worldName</code> is <code>null</code>
    **/
   public WorldLoginSuccessEvent(String worldName)
   {
      super(EventConstants.WorldLoginSuccess, 
            EventConstants.WorldLoginSuccessUri);
      
      if (worldName == null)
      {
         String msg = "worldName cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.WorldName = worldName;
   }
   
   /**
    * Construct a WorldLoginSuccessEvent from a Ces Event.
    *
    * @param event Ces Event containing the event info
    **/
   public WorldLoginSuccessEvent(Event event)
   {
      super(event);
      
      this.WorldName = event.getString("world.name");
   }
   
   public Event toCesEvent(Event event)
   {
      event = super.toCesEvent(event);
      event.put("world.name", WorldName);
      return event;
   }
}
