package psl.chime4.server.cwm.world.persist;

/**
 * Represents a unique identifier for an object in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public final class ObjectID 
{
   /** the integer id **/
   private int objID;
   
   /** the type of the object that owns this id **/
   private Class ownerType;
   
   /**
    * Construct the object id.
    *
    * @param objID the unique integer id
    * @param type  Class object that defines the type
    * @throws IllegalArgumentException
    *         if <code>type</code> is <code>null</code>
    **/
   public ObjectID(int objID, Class type)
   {
      if (type == null)
      {
         String msg = "type cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.ownerType = type;
      this.objID = objID;
   }

   /**
    * Get the integer object ID.
    *
    * @return integer object ID
    **/
   public int getID()
   {
      return objID;
   }
   
   /**
    * Get the Class object for the object that owns this persistence ID.
    *
    * @return Class object for type of object that owns this persistence ID
    **/
   public Class getOwnerType()
   {
      return ownerType;
   }
   
   /**
    * Two Object IDs are equal if they have same integer id.
    *
    * @param o object to test for equality against
    * @return <code>true</code> if <code>o</code> has the same integer id
    **/
   public boolean equals(Object o)
   {
      if (o == null)
      {
         return false;
      } else
      {
         return ((ObjectID) o).objID == objID;
      }
   }
   
   /**
    * The hashcode for a persistence ID object is simply its persistence ID
    * which is guaranteed to be unique for all objects in memory.
    *
    * @return the persistence ID of the object
    **/
   public int hashCode()
   {
      return objID;
   }
}