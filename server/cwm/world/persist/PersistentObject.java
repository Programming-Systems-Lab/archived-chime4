package psl.chime4.server.cwm.world.persist;

import psl.chime4.server.data.Persistent;

/**
 * An abstract implementation of the Persistent interface.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class PersistentObject implements Persistent
{
   /** the persistence id **/
   private int persistenceID;
   
   /** the object ID for this object **/
   private ObjectID objID;
   
   public int getPersistenceID()
   {
      return persistenceID;
   }
   
   public void setPersistenceID(int persistenceID)
   {
      this.persistenceID = persistenceID;
      objID = new ObjectID(persistenceID, getClass());
   }
   
   /**
    * Get the object ID associated with this object.
    *
    * @return ObjectID for this object
    **/
   public ObjectID getObjectID()
   {
      return objID;
   }
   
   /**
    * Set the object ID object associated with this persistent object.
    *
    * @param objID the object ID available for this object
    * @throws IllegalArgumentException
    *         if <code>objID</code> is <code>null</code>
    **/
   public void setObjectID(ObjectID persistID)
   {
      // check for null
      if (objID == null)
      {
         String msg = "objID cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.objID = objID;
   }
}


