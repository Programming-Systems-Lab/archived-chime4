package psl.chime4.server.cwm.world.wms;

import psl.chime4.server.cwm.world.persist.ObjectID;

/**
 * Represents an avatar in the world. An avatar is an object that can move 
 * around the world and initiate actions.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class Avatar extends WorldObject
{
   /** id of the room this avatar is in **/
   private ObjectID currentLocation;
   
   /**
    * Get the ID of the room the avatar is currently in.
    *
    * @return id of the room the avatar is currently in
    **/
   public ObjectID getCurrentLocation()
   {
      return currentLocation;
   }
   
   /**
    * Set the id of the room that the avatar is currently in.
    *
    * @param loc id of the room that the avatar is currently in
    * @throws IllegalArgumentException
    *         if <code>loc</code> is now owned by a Room
    **/
   public void setCurrentLocation(ObjectID loc)
   {
      if ((loc != null) && (loc.getOwnerType() != Room.class))
      {
         String msg = "avatars can only exist in rooms";
         throw new IllegalArgumentException(msg);
      }
      
      this.currentLocation = loc;
   }
}