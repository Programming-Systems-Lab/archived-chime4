package psl.chime4.server.scs.jc;

import psl.chime4.server.scs.service.*;
import psl.chime4.server.scs.persist.*;

import java.util.IdentityHashMap;

/**
 * A simple implementation of the persistence service that stores all 
 * objects in memory.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class SimplePersistenceService extends AbstractService 
   implements PersistenceService
{
   int objectID = 0;
   private IdentityHashMap objects = new IdentityHashMap();
   
   /**
    * Construct a new PersistentObject of the given type.
    *
    * @param type the type of object to construct
    * @return PersistentObject of the given type
    * @throws IllegalArgumentException
    *        if <code>type</code> is <code>null</code>
    * @throws ServiceException
    *        if something goes wrong
    */
   public PersistentObject create(Class type) throws ServiceException
   {
      // increment the objectID counter
      objectID += 1;
      
      try
      {
         // instantiate the class
         PersistentObject pobj = (PersistentObject) type.newInstance();
         ObjectID oid = new ObjectID(objectID, type);
         pobj.setObjectID(oid);
         pobj.setPersistenceID(objectID);
         pobj.setPersistenceService(this);
         
         // put it in the map
         objects.put(oid, pobj);
         
         return pobj;
      }
      catch (Exception e)
      {
         String msg = "couldn't create object of type: " + type;
         throw new ServiceException(msg, e);
      }
   }
   
   /**
    * Delete the object with the given id from the persistent layer.
    *
    * @param id id of the object to delete
    * @throws IllegalArgumentException
    *        if <code>id</code> is <code>null</code>
    * @throws ServiceException
    *        if the request fails
    */
   public void delete(ObjectID id) throws ServiceException
   {
      objects.remove(id);
   }
   
   /**
    * Load an object with the given id from the persistence layer.
    *
    * @param id object for the id
    * @return PersistentObject mapped to <code>id</code>
    * @throws IllegalArgumentException
    *        if <code>id</code> is <code>null</code>
    * @throws ServiceException
    *        if the request fails
    */
   public PersistentObject load(ObjectID id) throws ServiceException
   {
      return (PersistentObject) objects.get(id);
   }
   
   /**
    * Store an object in the persistence service.
    *
    * @param pobj PersistentObject to write to the persistence layer
    * @throws ServiceException
    *        if the request fails
    */
   public void store(PersistentObject pobj) throws ServiceException
   {
      objects.put(pobj.getObjectID(), pobj);
   }
   
}