package psl.chime4.server.scs.ws.event;

import psl.chime4.server.ces.*;

/**
 * Sent by the World Service to the Client indicating that she may not enter
 * the world. 
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class WorldEntryFailureEvent extends WorldEvent
{
   /** name of the world that rejected entry **/
   public String WorldName;
   
   /** reason entry was rejected **/
   public String Reason;
   
   /**
    * Construct the event from the passed parameters.
    *
    * @param worldName name of the world denying entry
    * @param reason    reason the world is being denied entry
    * @throws IllegalArgumentException
    *         if <code>worldName</code> is <code>null</code>
    **/
   public WorldEntryFailureEvent(String worldName, String reason)
   {
      super(EventConstants.WorldEntryFailure, 
            EventConstants.WorldEntryFailureUri);
      
      if (worldName == null)
      {
         String msg = "worldName cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.WorldName = worldName;
      this.Reason = reason;
   }
   
   /**
    * Construct the event from the passed Ces event.
    *
    * @param event event containing the info
    **/
   public WorldEntryFailureEvent(Event event)
   {
      super(event);
      
      this.WorldName = event.getString("world.name");
      this.Reason = event.getString("reason");
   }
   
   public Event toCesEvent(Event event)
   {
      event = super.toCesEvent(event);
      event.put("world.name", WorldName);
      event.put("reason", Reason);
      return event;
   }
}