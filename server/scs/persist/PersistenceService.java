package psl.chime4.server.scs.persist;

import psl.chime4.server.scs.service.*;

/**
 * The PersistenceService is responsible for the creation and storage of all
 * objects on the server. It is the primary interface to the persistence 
 * layer.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface PersistenceService extends Service
{
   /**
    * Construct a new PersistentObject of the given type.
    *
    * @param type the type of object to construct
    * @return PersistentObject of the given type
    * @throws IllegalArgumentException
    *         if <code>type</code> is <code>null</code>
    * @throws ServiceException
    *         if something goes wrong
    **/
   public PersistentObject create(Class type) throws ServiceException;
   
   /**
    * Load an object with the given id from the persistence layer.
    *
    * @param id object for the id
    * @return PersistentObject mapped to <code>id</code>
    * @throws IllegalArgumentException
    *         if <code>id</code> is <code>null</code>
    * @throws ServiceException
    *         if the request fails
    **/
   public PersistentObject load(ObjectID id) throws ServiceException;
   
   /**
    * Store an object in the persistence service. 
    *
    * @param pobj PersistentObject to write to the persistence layer
    * @throws ServiceException
    *         if the request fails
    **/
   public void store(PersistentObject pobj) throws ServiceException;
   
   /**
    * Delete the object with the given id from the persistent layer.
    *
    * @param id id of the object to delete
    * @throws IllegalArgumentException
    *         if <code>id</code> is <code>null</code>
    * @throws ServiceException
    *         if the request fails
    **/
   public void delete(ObjectID id) throws ServiceException;
}

