package psl.chime4.server.scs.persist;


/**
 * An ObjectID unique identifies an object to the persistence service.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public final class ObjectID
{
   private int objID;
   private Class ownerType;
   
   /**
    * Construct a new ObjectID with the given id and owner type.
    *
    * @param objID id for the new object
    * @param type  the type of the new object
    * @throws IllegalArgumentException
    *         if <code>type</code> is <code>null</code>
    **/
   public ObjectID(int objID, Class type)
   {
      if (type == null)
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.objID = objID;
      this.ownerType = type;
   }
   
   /**
    * Get the unique integer id for this object id.
    *
    * @return unique integer id for this object id
    **/
   public int getID()
   {
      return objID;
   }
   
   /**
    * Get the type of the owner for this object id.
    *
    * @return type of the owner for this object
    **/
   public Class getOwnerType()
   {
      return ownerType;
   }
}