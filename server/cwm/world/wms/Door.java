package psl.chime4.server.cwm.world.wms;

import psl.chime4.server.cwm.world.persist.ObjectID;

/**
 * A door represents a connection from one room to another room.
 *
 * @author Azubuko Obele
 * @version 0.2
 **/
public class Door extends WorldObject
{
   /** object id of the source room **/
   private ObjectID sourceRoom;
   
   /** object id of the destination room **/
   private ObjectID destinationRoom;
   
   /**
    * Get the object id of the room this door leads from.
    *
    * @return object id that this door leads from or <code>null</code>
    **/
   public ObjectID getSourceRoom()
   {
      return sourceRoom;
   }
   
   /**
    * Set the object id of the room that this door leads from.
    *
    * @param objID object id of the room that this door leads from
    * @throws IllegalArgumentException
    *         if <code>objID</code> does not belong to an object of 
              type {@link Room}
    **/
   public void setSourceRoom(ObjectID objID)
   {
      // check for type compatibility
      if ((objID != null) && (objID.getOwnerType() != Room.class))
      {
         String msg = "only objects of type room can be door sources";
         throw new IllegalArgumentException(msg);
      }
   
      this.sourceRoom = objID;
   }
   
   /**
    * Get the object id of the room this door leads to.
    *
    * @return object id of the room this door leads to
    **/
   public ObjectID getDestinationRoom()
   {
      return destinationRoom;
   }
   
   /**
    * Set the object id of the room that this door leads to.
    *
    * @param objID object id of the room this door leads to
    * @throws IllegalArgumentException
    *         if <code>objID</code> does not lead to an object of 
    *         type {@link Room}
    **/
   public void setDestinationRoom(ObjectID objID)
   {
      if ((objID != null) && (objID.getOwnerType() != Room.class))
      {
         String msg = "doors can only lead to rooms";
         throw new IllegalArgumentException(msg);
      }
      
      this.destinationRoom = objID;
   }
}
      
