package psl.chime4.server.rdf;

/**
 * A FileResourceDescriptor is a ResourceDescriptor that specifically 
 * contains describes a file identified by a globally unique URI.
 *
 * @author Mark Ayzenshtat
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class FileResourceDescriptor implements ResourceDescriptor
{
   /** the persistableID **/
   private int persistableID_;
   
   /** String containing the URI **/
   private String uri_;
   
   /** the resource type constant **/
   private int resourceType_;
   
   /** whether the FileResourceDescriptor has been completed **/
   private boolean completed_;
   
   /** the time the file was created **/
   private long timeCreated_;
   
   /** last time the file was modified **/
   private long timeModified_;
   
   /** protocol used to retrieve thes file **/
   private String protocol_;
   
   /** the size of the file in bytes **/
   private int size_;
   
   /** the type of the file (eg. html, gif, dir, txt) **/
   private String fileType_;
   
   /** content-type of the file **/
   private String contentType_;
   
   /**
    * Create a non-initialized FileResourceDescriptor.
    **/
   protected FileResourceDescriptor()
   {
      persistableID_ = -1;
      uri_ = "";
      completed_ = false;
      timeCreated_ = -1;
      timeModified_ = -1;
      protocol_ = "";
      size_ = -1;
      fileType_ = "";
      contentType_ = "";
   }
   
   /**
    * Construct a base-initialized FileResourceDescriptor.
    *
    * @param persistableID the unique ID assigned to this ResourceDescriptor
    *                      by the DataServer
    * @param uri           the uri of the resource 
    * @param completed     whether the metadata fields have been completed
    * @param timeCreated   when the file was created
    * @param timeModified  last time the file was modified
    * @param protocol      protocol that makes the file available
    * @param size          size of the file in bytes
    * @param fileType      the type of the file
    * @param contentType   the type of the contents of the file
    **/
   protected FileResourceDescriptor(int persistableID, String uri, 
                                    int resourceType_, boolean completed, 
                                    long timeCreated, long timeModified, 
                                    String protocol, int size, 
                                    String fileType, String contentType)
   {
      this.persistableID_ = persistableID;
      this.uri_ = uri;
      this.completed_ = completed;
      this.timeCreated_ = timeCreated;
      this.timeModified_ = timeModified;
      this.protocol_ = protocol;
      this.size_ = size;
      this.fileType_ = fileType;
      this.contentType_ = contentType;
   }
   
   /**
    * Get the persistableID for this ResourceDescriptor so it can be
    * stored in the dataserver.
    *
    * @return persistableID assigned by the dataserver, -1 if unknown
    **/
   public int getPersistenceID()
   {
      return persistableID_;
   }
   
   /**
    * Set the persistableID for the ResourceDescriptor so it can be stored
    * in the dataserver.
    *
    * @param persistableID persistable id used by the dataserver 
    **/
   public void setPersistenceID(int persistableID)
   {
      this.persistableID_ = persistableID;
   }
   
   /**
    * Get the resource type constant that describes the resource.
    *
    * @return resource-type constant describing the resource
    **/
   public int getResourceType()
   {
      return resourceType_;
   }
   
   /**
    * Set the resource type constant that describes the resource.
    *
    * @param resourceType resource type constant that describes the resource
    **/
   public void setResourceType(int resourceType)
   {
      this.resourceType_ = resourceType;
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
    * Get the completed status for the ResourceDescriptor. The 
    * ResourceDescriptor is completed if the DataServer has filled in all
    * its metadata fields.
    *
    * @return completed status of the ResourceDescriptor
    **/
   public boolean isComplete()
   {
      return completed_;
   }
   
   /**
    * Set the completed status for the ResourceDescriptor.
    *
    * @param completed <code>true</code> iff the ResourceDescriptor has been
    *                  completed by the DataServer.
    **/
   public void setComplete(boolean completed)
   {
      this.completed_ = completed;
   }
   
   /**
    * Get the time that the file was created.
    *
    * @return time since the file was created, -1 if its unknown
    **/
   public long getTimeCreated()
   {
      return timeCreated_;
   }
   
   /**
    * Set the time that the file was created.
    *
    * @param timeCreated the file was created, -1 if its unknown.
    **/
   public void setTimeCreated(long timeCreated)
   {
      this.timeCreated_ = timeCreated;
   }
   
   /**
    * Get the time the file was last modified.
    *
    * @return time the file was last modified, -1 if its unknown
    **/
   public long getTimeModified()
   {
      return timeModified_;
   }
   
   /**
    * Set the time the file was last modified.
    *
    * @param timeModified the time this file was last modified
    **/
   public void setTimeModified(long timeModified)
   {
      this.timeModified_ = timeModified;
   }
   
   /**
    * Get the protocol that exports the file. 
    *
    * @return protocol that exports the file, empty string if unknown
    **/
   public String getProtocol()
   {
      return this.protocol_;
   }
   
   /**
    * Set the protocol that exports the file.
    *
    * @param protocol Protocol that exports the file.
    **/
   public void setProtocol(String protocol)
   {
      this.protocol_ = protocol;
   }
   
   /**
    * Get the number of bytes in the file.
    *
    * @return number of bytes in the file, -1 if unknown
    **/
   public int getSize()
   {
      return size_;
   }
   
   /**
    * Set the number of bytes in the file.
    *
    * @param size number of bytes in the file.
    **/
   public void setSize(int size)
   {
      this.size_ = size;
   }
   
   /**
    * Get the type of the file which may be like html, mp3, txt, gif, dir.
    *
    * @return type of the file, empty string if uknown
    **/
   public String getFileType()
   {
      return fileType_;
   }
   
   /**
    * Set the type of the file.
    *
    * @param fileType type of the file
    **/
   public void setFileType(String fileType)
   {
      this.fileType_ = fileType;
   }
   
   /**
    * Get the content-type of the file which may be additional information
    * that depends on the protocol. 
    *
    * The value of this method supersedes for the value returned by 
    * <code>getFileType</code> because this method more accurately describes
    * the contents of the file including protocol and encoding specific 
    * information. For example, if we are talking about a HTML file then
    * the value returned by <code>getFileType</code> might be "html" but the
    * value returned by this method might be "text/html;charset=iso-8859-1"
    * -- the full mime-type.
    *
    * @return content type of the file, empty string if unknown
    **/
   public String getContentType()
   {
      return contentType_;
   }
   
   /**
    * Set the content-type of the file.
    *
    * @param contentType the new content type of the file
    **/
   public void setContentType(String contentType)
   {
      this.contentType_ = contentType_;
   }
   
   
   /**
    * Retrieves any additional data this resource descriptor has.  This method
    * intentionally has package scope and should only be called by <code>
    * ResourceDescriptorDAO</code> implementations.
    *
    * @return any additional data this resource descriptor has
    */
    abstract String[] getAdditionalData();
	
   /**
    * Assigns any additional data this resource descriptor has.  This method
    * intentionally has package scope and should only be called by <code>
    * ResourceDescriptorDAO</code> implementations.
    *
    * @param iData any additional data this resource descriptor has
    */
   abstract void setAdditionalData(String[] iData);
}