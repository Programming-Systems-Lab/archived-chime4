package psl.chime4.server.cwm;

import psl.chime4.server.data.ResourceDescriptor;

/**
 * This class represents a piece of Metadata about some object that exists
 * within the system. It might represent metadata about an html file, an 
 * mp3 file, or even a real object in meatspace.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class Metadata 
{
   /** true if the metadata has been completed **/
   private boolean completed = false;
   
   /** the actual piece of metadata **/
   private ResourceDescriptor resDesc;
   
   /**
    * Determine whether a piece of Metadata has been completed. If a Metadata
    * object has been completed then all possible metadata about the resource
    * has been discovered and is stored inside the ResourceDescriptor object.
    * If it's incomplete, only the URI of the Metadata object is known.
    *
    * @return <code>true</code> if the metadata is complete else 
    *         <code>false</code>
    **/
   public boolean isComplete()
   {
      return completed;
   }
   
   /**
    * Set the completion status for a piece of metadata. 
    *
    * @param complete completion status for the metadata.
    **/
   public void setComplete(boolean complete)
   {
      this.completed = complete;
   }
   
   /**
    * Get the underlying ResourceDescriptor of the Metadata object. The 
    * ResourceDescriptor actually contains the metadata which describes a 
    * resource.
    *
    * @return ResourceDescriptor containing the metadata.
    **/
   public ResourceDescriptor getResourceDescriptor()
   {
      return resDesc;
   }
   
   /**
    * Set the underlying ResourceDescriptor of the Metadata object. 
    *
    * @param resDesc ResourceDescriptor containing the metadata
    **/
   public void setResourceDescriptor(ResourceDescriptor resDesc)
   {
      this.resDesc = resDesc;
   }
}