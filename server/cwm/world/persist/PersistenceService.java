package psl.chime4.server.cwm.world.persist;

/**
 * A PersistenceService is responsible for storing persistent objects between
 * server startup and shutdown. The PersistenceService also acts as an object
 * cache. A PersistenceService will use a CachingStrategy object to determine
 * whether to cache an object.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class PersistenceService
{
   /**
    * Create a new PersistentObject of the given type.
    *
    * @param type type of persistent object to create
    * @throws IllegalArgumentException
    *         if <code>type</code> is <code>null</code>
    * @throws PersistenceServiceException
    *         if a new object could not be created
    **/
   public abstract PersistentObject createPersistentObject(Class type) 
      throws PersistenceServiceException;
   
   /**
    * Retrieve a PersistentObject from the persistence layer. 
    *
    * @param objID persistence id of the object to retrieve
    * @throws IllegalArgumentException
    *         if <code>objID</code> is <code>null</code>
    * @throws PersistenceServiceException
    *         if the object could not be retrieved
    **/
   public abstract PersistentObject 
      loadPersistentObject(ObjectID objID) 
      throws PersistenceServiceException;
   
   /**
    * Store an object in the persistence service. 
    *
    * @param persistObj PersistentObject to store 
    * @throws IllegalArgumentException
    *         if <code>persistObj</code> is <code>null</code>
    * @throws PersistenceServiceException
    *         if the object cannot be stored
    **/
   public abstract void storePersistentObject(PersistentObject obj)
      throws PersistenceServiceException;
   
   /**
    * Delete an object from the persistence service.
    *
    * @param objID persistence id of the object to delete
    * @throws IllegalArgumentException
    *         if <code>objID</code> is <code>null</code>
    * @throws PersistenceServiceException
    *         if the object cannot be deleted
    **/
   public abstract void deletePersistentObject(ObjectID objID) 
      throws PersistenceServiceException;
} 