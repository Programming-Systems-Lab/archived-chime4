package psl.chime4.server.rdf;


/**
 * A ResourceDescriptor is something that describes some kind of resource
 * in the Metaverse. A resource can be anything; an HTML file, a database, or
 * even an object in the real world. A ResourceDescriptor, which contains
 * metadata about the resource, might be specific both for a type or for
 * an individual entity.
 *
 * All ResourceDescriptors are by default Persistable.
 *
 * @author Mark Ayzenshtat
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface ResourceDescriptor extends psl.chime4.data.Persistable
{
   /**
    * All ResourceDescriptors are identified by a globally unique URI that
    * identifies the Resource they are describing.
    *
    * @return URI addressing the resource being described
    **/
   public String getURI();
   
   /**
    * Change the URI that a ResourceDescriptor is describing.
    *
    * @param uri new URI of the resource being described by this 
    *        ResourceDescriptor
    **/
   public void setURI(String uri);
   
   /**
    * A ResourceDescriptor is considered 'complete' when all of its 
    * metadata fields have been filled in by the DataServer.
    *
    * @return <code>true</code> if the DataServer has filled in all of the
    *         metadata fields of the ResourceDescriptor
    **/
   public boolean isComplete();
   
   /**
    * Change the 'complete' status of the ResourceDescriptor.
    *
    * @param complete the new complete status of the ResourceDescriptor
    **/
   public void setComplete(boolean complete);
   
   /**
    * Get the resource-type described by the resource.
    *
    * @return resource-type constant for the resource
    **/
   public int getResourceType();
   
   /**
    * Set the resource-type that describes the resource.
    *
    * @param resourceType resource-type constant describing this resource
    **/
   public void setResourceType(int resourceType);
   
}