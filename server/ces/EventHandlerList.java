package psl.chime4.server.ces;

import java.util.List;
import java.util.ArrayList;

/**
 * An EventHandlerList groups many other EventHandlers into a single 
 * EventHandler. It can make many EventHandler objects look like a single 
 * object.
 *
 * This class is not threadsafe.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class EventHandlerList implements EventHandler
{
   /** list of event handlers **/
   private List eventHandlerList = new ArrayList();
   
   /**
    * Add an EventHandler.
    *
    * @param eventHandler EventHandler to add to the list
    * @throws IllegalArgumentException
    *         if <code>eventHandler</code> is <code>null</code>
    **/
   public void addEventHandler(EventHandler eventHandler)
   {
      // check for null
      if (eventHandler == null)
      {
         String msg = "null handlers not allowed";
         throw new IllegalArgumentException(msg);
      }
      
      eventHandlerList.add(eventHandler);
   }
   
   /**
    * Remove an EventHandler from the list.
    *
    * @param eventHandler EventHandler to remove from the list.
    **/
   public void removeEventHandler(EventHandler eventHandler)
   {
      // check for null
      if (eventHandler != null)
      {
         eventHandlerList.remove(eventHandler);
      }
   }
   
   /**
    * Determine whether there are any EventHandlers in the list.
    *
    * @return <code>true</code> if there are no EventHandlers in the list
    *         else <code>false</code>
    **/
   public boolean isEmpty()
   {
      return eventHandlerList.isEmpty();
   }
   
   /**
    * Forward the passed event to all EventHandlers in the list.
    *
    * @param event Event for forward to alll handlers in the list
    **/
   public void handleEvent(Event event)
   {
      // make sure the list is not empty
      if (!eventHandlerList.isEmpty())
      {
         // loop through it and pass it
         for (int i = 0; i < eventHandlerList.size(); ++i)
         {
            EventHandler handler = (EventHandler) eventHandlerList.get(i);
            handler.handleEvent(event);
         }
      }
   }
}
         
      