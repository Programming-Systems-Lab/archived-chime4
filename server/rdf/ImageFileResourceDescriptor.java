package psl.chime4.server.rdf;

/**
 * An ImageFileResourceDescriptor contains metadata about a file containing
 * two-dimensional visual data.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ImageFileResourceDescriptor extends FileResourceDescriptor
{
   /** the width of the image in pixels **/
   private int width_;
   
   /** the height of the image in pixels **/
   private int height_;
   
   /** a short description of the image **/
   private String shortDesc_;
   
   /**
    * Default constructor demanded by Javabean API.
    **/
   public ImageFileResourceDescriptor()
   {
      width_ = -1;
      height_ = -1;
      shortDesc_ = null;
   }
   
   /**
    * Get the width of the image in pixels.
    *
    * @return width of the image in computer pixels or -1 if unknown
    **/
   public int getImageWidth()
   {
      return width_;
   }
   
   /**
    * Set the width of the image in pixels.
    *
    * @param width Width of the image in computer pixels or -1 if unknown
    **/
   public void setImageWidth(int width)
   {
      this.width_ = width;
   }
   
   /**
    * Get the height of the image in pixels.
    *
    * @return height of the image in computer pixels or -1 if unknown
    **/
   public int getImageHeight()
   {
      return height_;
   }
   
   /**
    * Set the height of the image in pixels.
    *
    * @return height Height of the image in computer pixels or -1 if unknown
    **/
   public void setImageHeight(int height)
   {
      this.height_ = height;
   }
   
   /**
    * Get a short description of the image.
    *
    * @return short description of the image or <code>null</code> if none
    *         is available
    **/
   public String getShortDescription()
   {
      return shortDesc_;
   }
   
   /**
    * Set the short description of the image.
    *
    * @param shortDesc Short description of the image
    **/
   public void setShortDescription(String shortDesc)
   {
      this.shortDesc_ = shortDesc;
   }
   
   /**
    * Retrieves any additional data this resource descriptor has.  This method
    * intentionally has package scope and should only be called by <code>
    * ResourceDescriptorDAO</code> implementations.
    *
    * @return any additional data this resource descriptor has
    */
   public String[] getAdditionalData()
   {
      return null; // STUB
   }   
   
   /**
    * Assigns any additional data this resource descriptor has.  This method
    * intentionally has package scope and should only be called by <code>
    * ResourceDescriptorDAO</code> implementations.
    *
    * @param iData any additional data this resource descriptor has
    */
   public void setAdditionalData(String[] iData)
   {
      ; // STUB
   }   
}