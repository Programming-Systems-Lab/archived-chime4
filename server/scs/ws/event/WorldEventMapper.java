package psl.chime4.server.scs.ws.event;

import psl.chime4.server.ces.*;

/**
 * Provides the mapping between CES Events and WorldEvents.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class WorldEventMapper 
{
   /**
    * Map a CES Event into a WorldEvent.
    *
    * @param event CES event containing information
    * @return WorldEvent equivalent to <code>event</code>
    * @throws IllegalArgumentException
    *         if <code>event</code> is <code>null</code> or it is an 
    *         unrecognized event
    **/
   public static WorldEvent cesToWorld(Event event)
   {
      if (event == null)
      {
         String msg = "event cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      int eventCode = event.getInteger("event.code");
 
      switch (eventCode)
      {
         case EventConstants.WorldLogin:
            return new WorldLoginEvent(event);
            
         case EventConstants.WorldLoginSuccess:
            return new WorldLoginSuccessEvent(event);
                     
         case EventConstants.WorldLoginFailure:
            return new WorldLoginFailureEvent(event);
            
         case EventConstants.WorldEntry:
            return new WorldEntryEvent(event);
         
         case EventConstants.WorldEntrySuccess:
            return new WorldEntrySuccessEvent(event);
         
         case EventConstants.WorldEntryFailure:
            return new WorldEntryFailureEvent(event);
            
         default:
            String msg = "unrecognized event: " + event;
            throw new IllegalArgumentException(msg);
      }
   }
}

