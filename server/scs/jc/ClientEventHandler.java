package psl.chime4.server.scs.jc;

import psl.chime4.server.ces.*;
import psl.chime4.server.scs.*;
import psl.chime4.server.scs.ws.event.*;

/**
 * Event handler used for the client.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ClientEventHandler implements EventHandler
{
   /** client who created us **/
   private Client client;
   
   /**
    * Construct a new event handler to work for a specific client.
    *
    * @param client client who wants the event handler
    * @throws IllegalArgumentException
    *         if <code>client</code> is <code>null</code>
    **/
   public ClientEventHandler(Client client)
   {
      if (client == null)
      {
         String msg = "client cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.client = client;
   }
   
   /**
    * Consume an event. If the event is passed, the handler can be sure that
    * it recieved it because it met the necessary criteria.
    *
    * @param event Event that met the handler's criteria
    */
   public void handleEvent(Event event)
   {
      // log the event
      client.LoggingService.info("CEH", "Recieved Event: " + event);
      
      // switch on the event
      WorldEvent wevent = WorldEventMapper.cesToWorld(event);
      
      switch (wevent.EventCode)
      {
         case EventConstants.WorldLoginSuccess:
            handleEvent((WorldLoginSuccessEvent) wevent);
            break;
            
         case EventConstants.WorldEntrySuccess:
            handleEvent((WorldEntrySuccessEvent) wevent);
            break;
            
         default:
            handleEvent(wevent);
            break;
      }
   }   
   
   /**
    * Called when the client recieves an event it doesn't recognize.
    *
    * @param wevent World Event
    **/
   public void handleEvent(WorldEvent wevent)
   {
      client.LoggingService.severe("CEH", "Unrecognized Event: " + wevent);
   }
   
   /**
    * Called when the client recieves a WorldLoginSuccess event.
    *
    * @param event WorldLoginSuccess event
    * @throws IllegalArgumentException
    *         if <code>event</code> is <code>null</code>
    **/
   protected void handleEvent(WorldLoginSuccessEvent event)
   {
      if (event == null)
      {
         String msg = "event cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // tell the client she's logged in
      String worldName = event.WorldName;
      client.output("Successfully logged into world " + worldName);
   }
    
   /**
    * Called when the client recieves a WorldEntrySuccess event.
    *
    * @param event WorldEntrySuccess event
    * @throws IllegalArgumentException
    *         if <code>event</code> is <code>null</code>
    **/
   protected void handleEvent(WorldEntrySuccessEvent event)
   {
      if (event == null)
      {
         String msg = "event cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // tell the client what room to enter
      String worldName = event.WorldName;
      int roomID = event.RoomID;
      int doorID = event.DoorID;
      
      client.output("Successfully entered world " + worldName + ". Please " +
         "enter room " + roomID + " through door " + doorID + " within" + 
         " the next 120 seconds.");
   }
      
}