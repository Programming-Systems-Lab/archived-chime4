package psl.chime4.server.scs.ws.event;

import psl.chime4.server.ces.*;

/**
 * Event sent by the World to the Client indicating that she cannot login 
 * into the world service.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class WorldLoginFailureEvent extends WorldEvent
{
   /** name of the world that reject the login request **/
   public String WorldName;
   
   /** reason login failed **/
   public String Reason;
   
   /**
    * Construct a WorldLoginFailureEvent with the passed parameters.
    *
    * @param worldName name of the world rejecting the login
    * @param reason    reason the login failed
    * @throws IllegalArgumentException
    *         if <code>worldName</code> is <code>false</code>
    **/
   public WorldLoginFailureEvent(String worldName, String reason)
   {
      super(EventConstants.WorldLoginFailure, 
            EventConstants.WorldLoginFailureUri);
      
      if (worldName == null)
      {
         String msg = "world name cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.WorldName = worldName;
      this.Reason = reason;
   }
   
   /**
    * Construct a WorldLoginFailureEvent from the passed Ces Event.
    *
    * @param event Event containing the event info
    **/
   public WorldLoginFailureEvent(Event event)
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