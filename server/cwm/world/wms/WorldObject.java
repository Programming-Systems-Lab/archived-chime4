package psl.chime4.server.cwm.world.wms;

import psl.chime4.server.cwm.world.persist.PersistentObject;
import psl.chime4.server.cwm.world.persist.Cacheable;
import psl.chime4.server.cwm.world.persist.ObjectID;

import java.io.Serializable;

/**
 * A WorldObject represents an actual object that exists in the world. This
 * might be a Room, Door, User or a piece of metadata.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class WorldObject extends PersistentObject
   implements Cacheable, Serializable
{
   /**
    * Get a unique integer describing this instance.
    *
    * @return instance-ID for this object
    **/
   public int getInstanceID()
   {
      return super.getPersistenceID();
   }
   
   /**
    * Two WorldObjects are equal if they have the same object id.
    *
    * @param o object to test for equality against
    * @return <code>true</code> if the objects have the same object 
    *         id else <code>false</code>
    **/
   public boolean equals(Object o)
   {
      ObjectID objID = ((PersistentObject) o).getObjectID();
      return super.getObjectID().equals(objID);
   }
   
   /**
    * Hashcode of an object is it's persistence id.
    *
    * @return persistence id of the object
    **/
   public int hashCode()
   {
      return super.getObjectID().getID();
   }
}



