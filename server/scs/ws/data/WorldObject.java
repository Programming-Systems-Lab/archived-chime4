package psl.chime4.server.scs.ws.data;

import psl.chime4.server.scs.persist.*;

/**
 * Represents an object in the world.
 *
 * @todo Override hashCode() and equals() in this class.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class WorldObject extends PersistentObject
{
   /**
    * Get the instance id for this world object.
    *
    * @return instance id for this world object
    **/
   public int getInstanceID()
   {
      return super.getPersistenceID();
   }
}