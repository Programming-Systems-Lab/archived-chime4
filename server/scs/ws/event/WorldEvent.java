package psl.chime4.server.scs.ws.event;

import psl.chime4.server.ces.*;

/**
 * Superclass for all events handled by the World Service.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class WorldEvent 
{
   /** unique code for the event **/
   public int EventCode;
   
   /** unique uri for the event **/
   public String EventUri;
   
   /**
    * Construct the WorldEvent from a Ces Event.
    *
    * @param code the event code
    * @param uri  uri for the event
    * @throws IllegalArgumentException
    *         if <code>uri</code> is <code>null</code>
    **/
   protected WorldEvent(int code, String uri)
   {
      this.EventCode = code;
      this.EventUri = uri;
   }
   
   /**
    * Initialized this event from the passed Ces Event.
    *
    * @param event Ces event containing the information
    * @throws IllegalArgumentException
    *         if <code>event</code> is <code>null</code>
    **/
   protected WorldEvent(Event event)
   {
      if (event == null)
      {
         String msg = "event cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.EventCode = event.getInteger("event.code");
      this.EventUri = event.getString("event.uri");
   }
      
   
   /**
    * Convert the this WorldEvent into a Ces Event suitable for 
    * transport.
    *
    * @param event Event into which to store the information
    * @return Ces Event equivalent to this World Event
    **/
   public Event toCesEvent(Event event)
   {
      event.put("event.code", EventCode);
      event.put("event.uri", EventUri);
      return event;
   }
}