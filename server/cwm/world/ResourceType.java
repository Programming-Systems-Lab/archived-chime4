package psl.chime4.server.cwm.world;

/**
 * A ResourceType describes the type of a resource.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ResourceType
{
   /** integer describing the resource **/
   private int typeID;
   
   /** the sanitized MIME-type of the resource **/
   private String mimeType;
   
   /**
    * Private constructor. Use factory methods to create objects of this
    * type.
    *
    * @param typeID   type-id uniquely identifying this resource type
    * @param mimeType sanitized mime-type of this resource eg 'text/html'
    **/
   private ResourceType(int typeID, String mimeType)
   {
      this.typeID = typeID;
      this.mimeType = mimeType;
   }
   
   /**
    * Get a universally-unique integer describing this type.
    *
    * @return type id for this resource type
    **/
   public int getTypeID()
   {
      return typeID;
   }
   
   /**
    * Get the sanitized mime-type of this resource.
    *
    * @return mime type of this file
    **/
   public String getMimeType()
   {
      return mimeType;
   }
   
   /**
    * Create a ResourceType object. 
    *
    * @param mimeType this is a sanitized mime type describing the resource.
    *                 This mime-type can only contain basic mime-type 
    *                 information. E.g. "text/html; charset=iso-8859-1" must
    *                 be sanitized to "text/html".
    **/
   public static ResourceType createResourceType(String mimeType)
   {
      ; // do nothing
   }
   
   /** 
    * Constants describing the different types of resources.
    **/
   public static final ResourceType MP3_SOUND_FILE = 
      new ResourceType(1000, "audio/mp3");
   
   public static final int GIF_IMAGE_FILE = 
      new ResourceType(2000, "image/gif");
   public static final int JPEG_IMAGE_FILE = 
      new ResourceType(2001, "image/jpeg");  
}