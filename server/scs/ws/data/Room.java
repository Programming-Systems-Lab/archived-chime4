package psl.chime4.server.scs.ws.data;

import java.util.*;

/**
 * Represents a Room in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class Room extends MetadataWorldObject
{
   private Set doors = new HashSet();
   private Set contents = new HashSet();
   
   /** the last time the room's structure changed **/
   private long structureTime = System.currentTimeMillis();
   
   /**
    * Add a piece of content to the room.
    *
    * @param wobj World object to add to the room
    * @throws IllegalArgumentException
    *         if <code>wobj</code> is <code>null</code>
    **/
   public void addContent(LocatedWorldObject wobj)
   {
      if (wobj == null)
      {
         String msg = "wobj cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      synchronized(contents)
      {         
         contents.add(wobj);
      }
      
      // update the world object's current location
      wobj.setCurrentLocation(this);
   }
   
   /**
    * Remove a piece of content from the room.
    *
    * @param wobj World object to remove from this room
    **/
   public void removeContent(LocatedWorldObject wobj)
   {
      if ((wobj != null) && (contents != null))
      {
         synchronized(contents)
         {
            if (contents.remove(wobj))
            {
               wobj.setCurrentLocation(null);
            }
         }
      }
   }
   
   /**
    * Return an iterator over all the contents in the room.
    *
    * @return iterator over the contents in the room
    **/
   public Iterator contents()
   {
      if (contents == null)
      {
         return Collections.EMPTY_SET.iterator();
      } else
      {
         return contents.iterator();
      }
   }
   
   /**
    * Add a door to the room.
    *
    * @param door door to add to the room
    * @throws IllegalArgumentException
    *         if <code>door</code> is <code>null</code>
    **/
   public void addDoor(Door door)
   {
      if (door == null)
      {
         String msg = "door cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure the door leads from us
      door.setSource(this);
      
      synchronized(doors)
      {
         doors.add(door);
      }
      
      // timestamp this change in the room's structure
      structureChanged();
   }
   
   /**
    * Remove a door from the room.
    *
    * @param door door to the remove from the room
    **/
   public void removeDoor(Door door)
   {
      if ((door != null) && (doors != null))
      {
         synchronized(doors)
         {
            if (doors.remove(door))
            {
               door.setSource(null);
               structureChanged();
            }
         }
      }
   }
   
   /**
    * Return an iterator over all the doors in the room.
    *
    * @return iterator over all the doors in the room
    **/
   public Iterator doors()
   {
      if (doors == null)
      {
         return Collections.EMPTY_SET.iterator();
      } else
      {
         return doors.iterator();
      }
   }
   
   /**
    * Called when the room's structure has changed. Updates the time changed.
    **/
   protected void structureChanged()
   {
      structureTime = System.currentTimeMillis();
   }
}
      
 

