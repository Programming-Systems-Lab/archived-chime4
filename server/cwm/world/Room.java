package psl.chime4.server.cwm.world;

import psl.chime4.server.cwm.Metadata;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a Room in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class Room extends WorldObject
{
   /** list of doors in the room **/
   private List doors = new ArrayList();
   
   /** list of objects in the room **/
   private List contents = new ArrayList();
   
   /** the metadata behind this room **/
   private Metadata mdata;
   
   /**
    * Remove an object from the room. If the object is not in this room
    *this
    * method does nothing.
    *
    * @param wobj WorldObject to remove from this room
    **/
   public void removeContent(WorldObject wobj)
   {
      if (wobj != null)
      {
         contents.remove(wobj);
      }
   }
   
   /**
    * Add an object to the room.
    *
    * @param wobj WorldObject to add to this room
    **/
   public void addContent(WorldObject wobj)
   {
      if (wobj != null)
      {
         contents.add(wobj);
      }
   }
   
   /**
    * Get an Iterator over all the objects in the room. No guarantee is made
    * about the sequence of iteration.
    *
    * @return Iterator over the contents of the room.
    **/
   public Iterator contents()
   {
      return contents.iterator();
   }
   
   /**
    * Add a Door to the room.
    *
    * @param door Door to add to this room.
    **/
   public void addDoor(Door door)
   {
      if (door != null)
      {
         doors.add(door);
      }
   }
   
   /**
    * Remove a Door from the room.
    *
    * @param door Door to remove from the room.
    **/
   public void removeDoor(Door door)
   {
      if (door != null)
      {
         doors.remove(door);
      }
   }
   
   /**
    * Return an Iterator over the doors in the room.
    *
    * @return iterator over the doors in the room
    **/
   public Iterator doors()
   {
      return doors.iterator();
   }
   
   /**
    * Get the underlying metadata object this room represents.
    *
    * @return underling metadata this room represents or <code>null</code>
    **/
   public Metadata getMetadata()
   {
      return mdata;
   }
   
   /**
    * Set the underlying metadata object this room represents.
    *
    * @param mdata underlying metadata this room represents
    **/
   public void setMetadata(Metadata mdata)
   {
      this.mdata = mdata;
   }
}