package psl.chime4.server.rdf;

/**
 * An HTMLResourceDescriptor describes an HTML file. It contains all
 * relevant metadata about the HTML file but none of its content.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class HTMLFileResourceDescriptor extends FileResourceDescriptor
{
   /** the title of the HTML file **/
   private String title_;
   
   /** array of meta values for the HTML file **/
   private String[] metaValues_;
   
   /** list of links within the HTML file, these must all be complete **/
   private String[] links_;
   
   /** list of complete image uris within the HTML file **/
   private String[] images_;
   
   /** time that the the HTML file expires **/
   private long timeExpires_;
   
   /**
    * Default constructor demanded by the Javabean Spec.
    **/
   public HTMLFileResourceDescriptor()
   {
      title_ = "";
      metaValues_ = null;
      links_ = null;
      images_ = null;
      timeExpires_ = -1;
   }
   
   /**
    * Get the title of the HTML file.
    *
    * @return title of the HTML file, empty string if none
    **/
   public String getTitle()
   {
      return title_;
   }
   
   /**
    * Set the title of the HTML file.
    *
    * @param title of the HTML file.
    **/
   public void setTitle(String title)
   {
      this.title_ = title;
   }
   
   /**
    * Get the meta keywords that may be included in the <head> element of the
    * HTML file. The is an alternating array where each keyword is followed
    * by its value. 
    *
    * For example, if you are looking at the home page of the U.S. Fish &
    * Wildlife Service (http://www.fws.gov/index.html) its meta tags look 
    * like this:
    *
    * <meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
    * <META NAME="DESCRIPTION" CONTENT="Home Page of the U. S. Fish
    * and Wildlife Service, a bureau in the Department of the Interior.  
    * Our mission is, working with others, to conserve, protect and enhance 
    * fish, wildlife, and plants and their habitats for the continuing 
    * benefit of the American people.">
    * <meta name="keywords" content="fish, wildlife, animals, birds, habitat,
    * endangered, species, wetland, refuge, publication, conservation, 
    * environment, protect, mammal, insect, plant, hunt, recreation, 
    * hatchery, partner">
    * <meta name="author" content="charlie grymes">
    * <meta name="version" content="last revised 4/1/2002">
    *
    * This would be turned into the following Java array of String objects:
    *
    * { "NAME", "Home Page of the U.S. Fish...", "keywords", 
    *   "fish, wildlife, animals...", "author", "charlie grymes", "version"
    *   "last revised 4/1/2002"
    * }
    * 
    * Note that the special http-equiv meta-values like 
    * content-type, label, pragma, and expires are ignored and not included
    * in this array. This array includes meta-tags that only describe the
    * content of the HTML file.
    *
    * @return array of meta name/value pairs from the HTML file or 
    *         <code>null</code> if the HTML file contains no meta tags 
    **/
   public String[] getMetaValues()
   {
      return metaValues_;
   }
   
   /**
    * Set the meta keywords associated with this HTML file. This array must
    * be in the format described in the <code>getMetaValues</code>  nethod.
    *
    * @param metaKeywords meta keywords associated with HTML or 
    *        <code>null</code>
    **/
   public void setMetaValues(String[] metaValues)
   {
      this.metaValues_ = metaValues;
   }
   
   /**
    * Get all of the links contained within an HTML file. All of these links
    * must be well-formed URIs and they must be absolute -- not relative 
    * URIs. (It's the responsibility of the Metadata Generator to transform 
    * any relative URIs contained in the file into absolute URIs. This 
    * includes filling in the protocol when called for.)
    *
    * Since this is an HTML file it may contain links of the following URI
    * types: file://, mailto:, http://, gopher://, WAIS://, news://, 
    * telnet://
    * 
    * As defined, all Strings contained within this array must be in the
    * general form of a URI ie:
    * [protocol]://host.domain [:port]/path/filename
    *
    * @return absolute URIs within the HTML file to other files or 
    *         <code>null</code> if the HTML file contains no links. 
    **/
   public String[] getLinks()
   {
      return links_;
   }
   
   /**
    * Set the links contained within the HTML file.
    *
    * @param links Links contained within the HTML file
    **/
   public void setLinks(String[] links)
   {
      this.links_ = links;
   }
   
   /**
    * Get all the link-URIs of the images that are embedded within the HTML
    * file. For most HTML files, this will be the values of the SRC attribute
    * for every IMG tag in the document. Every URI must be absolute -- 
    * relative URIs are not allowed. (It's the responsibility of the Metadata
    * Generator to transform any relative URIs into absolute URIs). 
    *
    * For example if the HTML file contains the following tags:
    * ...
    * <img src="http://www.meiner.com/yahoo.gif" border="0">
    * ...
    * <imag src="images/mail.png" border="0">
    * ...
    * 
    * The array would look like:
    *
    * { "http://www.meiner.com/yahoo.gif", 
    *   "http://www.meiner.com/images/mail.png" }
    *
    * @return Absolute URIs of any images embedded in the HTML file or 
    *         <code>null</code> if the html file contains no images.
    **/
   public String[] getImages()
   {
      return images_;
   }
   
   /**
    * Set the list of URIs of images embedded within the HTML file. See
    * <code>getImages</code> for the format of this array.
    *
    * @param images Array of URIs of images embedded within the HTML file
    **/
   public void setImages(String[] images)
   {
      this.images_ = images;
   }
   
   /**
    * Get the time this HTML file expires. This is usually the value of
    * the meta attribute, EXPIRES. So if the HTML file contains the tag:
    *
    * <meta HTTP-EQUIV="EXPIRES" CONTENT="Mon, 01 Jan 1990 01:00:00 GMT">
    *
    * Then the value returned by this method would be 0101110110101.
    *
    * @return time this HTML file expires or -1 if it's unknown.
    **/
   public long getTimeExpires()
   {
      return timeExpires_;
   }
   
   /**
    * Set the time this HTML file expires.
    *
    * @param time the HTML file expires.
    **/
   public void setTimeExpires(long timeExpires)
   {
      this.timeExpires_ = timeExpires;
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