package psl.chime4.server.rdf;

/**
 * Abstract implementation of the ResourceDescriptor interface that makes
 * it easier to implement the interface.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class AbstractResourceDescriptor 
   implements ResourceDescriptor
{
   /** the persistableID **/
   private int persistenceID_;
   
   /** String containing the URI **/
   private String uri_;
   
   /** the resource type constant **/
   private String typeID_;
   
   /**
    * Protected default constructor.
    **/
   protected AbstractResourceDescriptor()
   {
      persistenceID_ = -1;
      uri_ = null;
      typeID_ = null;
   }
   
      
   /**
    * Get the persistence ID for this ResourceDescriptor so it can be
    * stored in the dataserver.
    *
    * @return persistence ID assigned by the dataserver, -1 if unknown
    **/
   public int getPersistenceID()
   {
      return persistenceID_;
   }
   
   /**
    * Set the persistence ID for the ResourceDescriptor so it can be stored
    * in the dataserver.
    *
    * @param persistenceID persistence id used by the dataserver 
    **/
   public void setPersistenceID(int persistenceID)
   {
      this.persistenceID_ = persistenceID;
   }
   
   /**
    * Get the URI that this ResourceDescriptor is describing.
    *
    * @return uri for the resource pointed to by this ResourceDescriptor,
    *             empty string if unknown
    **/
   public String getURI()
   {
      return uri_;
   }
   
   /**
    * Set the URI that this ResourceDescriptor is describing.
    *
    * @param uri URI for the Resource this ResourceDescriptor describes
    **/
   public void setURI(String uri)
   {
      this.uri_ = uri;
   }
   
   /**
    * Get the type identifier for this resource. This string uniquely 
    * identifies the type of resource within the Chime system. 
    *
    * @return resource-type constant describing the resource
    **/
   public String getTypeIdentifier()
   {
      return typeID_;
   }
   
   /**
    * Set the resource type identifier that describes this resource.
    *
    * @param typeID type identifier for this resource
    **/
   public void setTypeIdentifier(String typeID)
   {
      this.typeID_ = typeID;
   }
}