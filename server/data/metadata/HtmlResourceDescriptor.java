/*
 * HtmlResourceDescriptor.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data.metadata;

import java.util.List;
import org.jdom.*;

/**
 * A single unit of HTML metadata.
 *
 * @author Mark Ayzenshtat
 */
public class HtmlResourceDescriptor extends AbstractResourceDescriptor {
	private static final String[] kZeroStringArray = new String[0];
  
  private String[] mLinks;
  
  HtmlResourceDescriptor() {
    super();
    
    setLinks(kZeroStringArray);
  }
  
  public String[] getLinks() {
    return mLinks;
  }
  
  public void setLinks(String[] iLinks) {
    mLinks = iLinks;
  }
  
  /**
	 * Retrieves any additional data this resource descriptor has.
	 *
	 * @return any additional data this resource descriptor has
	 */
  public String[] getAdditionalData() {
    return null;
  }
  
	/**
	 * Assigns any additional data this resource descriptor has.
	 *
	 * @param iData any additional data this resource descriptor has
	 */  
  public void setAdditionalData(String[] iAddtlData) {
    
  }

  /**
   * Completes the fields of this resource descriptor with data from the
   * supplied <code>Document</code> object.  Concrete subclasses should
   * always call <code>super.completeFromDocument(iDoc)</code> before providing
   * their own additional implementation.
   *
   * @param iDoc the document
   */
  public void completeFromDocument(Document iDoc) {
		// fill in all base resource descriptor fields
    super.completeFromDocument(iDoc);
    
    Element root = iDoc.getRootElement();
		
    // set links
    List links = root.getChildren("Link");
    String[] linksArray = new String[links.size()];
    for (int i = 0; i < linksArray.length; i++) {
      linksArray[i] = ((Element) links.get(i)).getTextTrim();
    }
    setLinks(linksArray);
  }
}