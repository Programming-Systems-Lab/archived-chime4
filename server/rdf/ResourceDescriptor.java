package psl.chime4.server.rdf;


import psl.chime4.server.data.Persistable;

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
public interface ResourceDescriptor extends Persistable
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
    * Return the resource type identifier. The type identifier is a string 
    * that uniquely identifies a given type of resource. 
    *
    * @return resource type identifier
    **/
   public String getTypeIdentifier();
   
   /**
    * Set the resource type identfier.
    *
    * @param typeID resource type identifier string
    **/
   public void setTypeIdentifier(String typeID);
}