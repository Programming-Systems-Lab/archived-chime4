package psl.chime4.server.scs.ws.event;

import psl.chime4.server.ces.*;

/**
 * Sent by the world to the client to indicate that they may enter the world
 * through a specific door and into a specific room.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class WorldEntrySuccessEvent extends WorldEvent
{
   /** name of the world sending the event **/
   public String WorldName;
   
   /** id of the room the client may enter **/
   public int RoomID;
   
   /** id of the door the client should pass through **/
   public int DoorID;
   
   /**
    * Construct the event from the passed parameters.
    *
    * @param worldName name of the world that accepted the entry request
    * @param roomID    room the client should enter
    * @param doorID    door the client should pass through
    * @throws IllegalArgumentException
    *         if <code>worldName</code> is <code>null</code>
    **/
   public WorldEntrySuccessEvent(String worldName, int roomID, int doorID)
   {
      super(EventConstants.WorldEntrySuccess, 
            EventConstants.WorldEntrySuccessUri);
      
      if (worldName == null)
      {
         String msg = "worldName cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.WorldName = worldName;
      this.RoomID = roomID;
      this.DoorID = doorID;
   }
   
   /**
    * Construct the event from a Ces Event.
    *
    * @param event Ces Event containing the info
    **/
   public WorldEntrySuccessEvent(Event event)
   {
      super(event);
      
      this.WorldName = event.getString("world.name");
      this.RoomID = event.getInteger("room.id");
      this.DoorID = event.getInteger("door.id");
   }
   
   public Event toCesEvent(Event event)
   {
      event = super.toCesEvent(event);
      
      event.put("world.name", WorldName);
      event.put("room.id" , RoomID);
      event.put("door.id", DoorID);
      return event;
   }
}