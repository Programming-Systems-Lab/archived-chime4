package psl.chime4.server.scs.metadata;

import psl.chime4.server.data.metadata.ResourceDescriptor;
import psl.chime4.server.scs.persist.*;

/**
 * Represents a piece of metadata in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public final class Metadata extends PersistentObject
{
   private boolean evaluated;
   private String url;
   private ResourceDescriptor resDes;
   
   /**
    * Get the underlying resource descriptor.
    *
    * @return underlying resource descriptor
    **/
   public ResourceDescriptor getResourceDescriptor()
   {
      return resDes;
   }
   
   /**
    * Set the underlying resource descriptor.
    *
    * @param resDes the underlying resource descriptor
    **/
   public void setResourceDescriptor(ResourceDescriptor resDes)
   {
      this.resDes = resDes;
   }
   
   /**
    * Get the URL this metadata describes.
    *
    * @return url for the resource this metadata describes
    **/
   public String getURL()
   {
      return url;
   }
   
   /**
    * Set the URL this metadata describes.
    *
    * @param url metadata this metadata describes
    **/
   public void setURL(String url)
   {
      this.url = url;
   }
   
   /**
    * Get whether this metadata has been completed evaluated.
    *
    * @return <code>true</code> if the metadata has been evaluated, else
    *         <code>false</code>
    **/
   public boolean isEvaluated()
   {
      return evaluated;
   }
   
   /**
    * Set whether this metadata has been completely evaluated.
    *
    * @param evaluated evaluation status of this metadata
    **/
   public void setEvaluated(boolean evaluated)
   {
      this.evaluated = evaluated;
   }
}