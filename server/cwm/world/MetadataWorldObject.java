package psl.chime4.server.cwm.world;

import psl.chime4.server.cwm.Metadata;

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
   private Metadata mdata;

   /**
    * Get the piece of metadata this object represents.
    *
    * @return underlying Metadata object
    **/
   public Metadata getMetadata()
   {
      return mdata;
   }
   
   /**
    * Set the underlying metadata object that this object represents in the
    * world.
    *
    * @param mdata metadata about some resource
    **/
   public void setMetadata(Metadata mdata)
   {
      this.mdata = mdata;
   }
}
