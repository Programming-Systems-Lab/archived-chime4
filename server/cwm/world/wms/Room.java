package psl.chime4.server.cwm.world.wms;

import psl.chime4.server.cwm.world.persist.ObjectID;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collections;

/**
 * Represents a Room in the world. A Room is an object that can contain other
 * objects as well as doors.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class Room extends MetadataWorldObject
{
   /** set of doors in the room **/
   private Set doors;
   
   /** set of objects in the room **/
   private Set contents;
   
   /** id of the metadata backing this room **/
   private ObjectID mdata;
   
   /**
    * Add a WorldObject to the contents of this room.
    *
    * @param wObjID id of a world object
    * @throws IllegalArgumentException
    *         if <code>wObjID</code> is not owned by a WorldObject
    **/
   public void addContent(ObjectID wObjID)
   {
      if (wObjID != null)
      {
         if (wObjID.getOwnerType() != WorldObject.class)
         {
            String msg = "can only add world objects to room";
            throw new IllegalArgumentException(msg);
         }
         
         if (contents == null)
         {
            contents = new HashSet();
         }
         
         contents.add(wObjID);
      }
   }
   
   /**
    * Remove a WorldObject from the contents of this room.
    *
    * @param wObjID object id of the world object to remove
    **/
   public void removeContent(ObjectID wObjID)
   {
      if ((wObjID != null) && (contents != null))
      {
         contents.remove(wObjID);
         
         // null the contents if the last contents was gone
         if (contents.isEmpty())
         {
            contents = null;
         }
      }
   }
   
   /**
    * Return an iterator over the contents of this room. 
    *
    * @return Iterator over contents of this room
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
    * Add a door to this room.
    *
    * @param doorID object id of the door in this room
    * @throws IllegalArgumentException
    *         if <code>doorID</code> is not owned by a door
    **/
   public void addDoor(ObjectID doorID)
   {
      if (doorID != null)
      {
         if (doorID.getOwnerType() != Door.class)
         {
            String msg = "only doors can be added to rooms";
            throw new IllegalArgumentException(msg);
         }
         
         if (doors == null)
         {
            doors = new HashSet();
         }
         
         doors.add(doorID);
      }
   }
   
   /**
    * Remove a door from this room.
    *
    * @param doorID object id of the door to remove from this room
    **/
   public void removeDoor(ObjectID doorID)
   {
      if ((doorID != null) && (doors != null))
      {
         doors.remove(doorID);
         
         // null it if empty
         if (doors.isEmpty())
         {
            doors = null;
         }
      }
   }
      
   /**
    * Return an iterator over all the doors in this room.
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
}