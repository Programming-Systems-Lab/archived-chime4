package psl.chime4.server.cwm.world.wms;

import psl.chime4.server.cwm.world.persist.ObjectID;

/**
 * A MetadataWorldObject is an object that represents a piece of metadata 
 * in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class MetadataWorldObject extends WorldObject
{
   /** piece of metadata backed by this object **/
   private ObjectID mData;
   
   /**
    * Get the object id of the piece of metadata.
    *
    * @return object id of the metadata backing this metadata
    **/
   public ObjectID getMetadata()
   {
      return mData;
   }
   
   /**
    * Set the object id of the piece of metadata backing this world object.
    *
    * @param mData id of the metadata backing this service
    * @throws IllegalArgumentException
    *         if <code>mData</code> is not owned by a Metadata
    **/
   public void setMetadata(ObjectID objID)
   {
      if ((objID != null) && (objID.getOwnerType() != Metadata.class))
      {
         String msg = "object id must be owned by metadata";
         throw new IllegalArgumentException(msg);
      }
      
      this.mData = objID;
   }
}