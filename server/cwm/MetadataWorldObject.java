package psl.chime4.server.cwm;

import psl.chime4.server.data.ResourceDescriptor;

/**
 * Represents a piece of Metadata like a gif image or an mp3 file which 
 * exists in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class MetadataWorldObject extends WorldObject
{
   /** piece of metadata this object represents **/
   private ResourceDescriptor resDesc;

   /**
    * Get the piece of metadata this object represents.
    *
    * @return underlying Metadata object
    **/
   public ResourceDescriptor getMetadata()
   {
      return resDesc;
   }
   
   /**
    * Set the underlying metadata object that this object represents in the
    * world.
    *
    * @param resDesc metadata about some resource
    **/
   public void setMetadata(ResourceDescriptor resDesc)
   {
      this.resDesc = resDesc;
   }
}
