package psl.chime4.server.scs.persist;

import psl.chime4.server.data.Persistent;

/**
 * An object that is capable of being persisted to the persistence layer.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class PersistentObject implements Persistent
{
   private int persistenceID;
   private ObjectID oid;
   private PersistenceService perService;
   
   /**
    * Get the persistence id of the object.
    *
    * @return persistence id
    **/
   public int getPersistenceID()
   {
      return persistenceID;
   }
   
   /**
    * Set the persistence id of the object.
    *
    * @param persistenceID new persistence id of the object
    **/
   public void setPersistenceID(int persistenceID)
   {
      this.persistenceID = persistenceID;
   }
   
   /**
    * Get the object id for this object.
    *
    * @return ObjectID for this object
    **/
   public ObjectID getObjectID()
   {
      return oid;
   }
   
   /**
    * Set the object id for this object.
    *
    * @param oid ObjectID for this object
    **/
   public void setObjectID(ObjectID oid)
   {
      this.oid = oid;
   }
   
   /**
    * Get the persistence service that created this persistent object.
    *
    * @return persistence service that created this object
    **/
   public PersistenceService getPersistenceService()
   {
      return perService;
   }
   
   /**
    * Set the persistence service for this object.
    *
    * @param pService PersistenceService that created this object
    **/
   public void setPersistenceService(PersistenceService pService)
   {
      this.perService = pService;
   }
   
   /**
    * The PersistenceService must call this method immediately after an 
    * object has been created.
    *
    * @param pService PersistenceService that created the object
    **/
   public void onCreate(PersistenceService pService)
   {
      ; // do nothing
   }
   
   /**
    * The PersistenceService must call this method immediately after an 
    * object has been loaded back from the persistence layer.
    *
    * @param pService PersistenceService that loaded the object
    **/
   public void onLoad(PersistenceService pService)
   {
      ; // do nothing
   }
   
   /**
    * The PersistenceService must call this method immediately before an 
    * object is stored in the persistence layer.
    *
    * @param pService PersistenceService that is storing the object
    **/
   public void onStore(PersistenceService pService)
   {
      ; // do nothing
   }
   
   /**
    * The PersistenceService must call this method immediately before an
    * object is deleted from the persistence layer.
    *
    * @param pService PersistenceService deleting the object
    **/
   public void onDelete(PersistenceService pService)
   {
      ; // do nothing
   }
}


