package psl.chime4.server.scs.ws.data;

import psl.chime4.server.scs.metadata.*;

/**
 * Represents a piece of metadata in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class MetadataWorldObject extends WorldObject
{
   private Metadata mData;
   
   /**
    * Get the piece of Metadata represented by this room.
    *
    * @return metadata this object represents
    **/
   public Metadata getMetadata()
   {
      return mData;
   }
   
   /**
    * Set the piece of Metadata represented by this room.
    *
    * @param mdata the metadata this object represents
    **/
   public void setMetadata(Metadata mData)
   {
      this.mData = mData;
   }
}