/*
 * ResourceDescriptor.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data.metadata;

import java.util.Date;
import psl.chime4.server.data.Persistent;

/**
 * A resource descriptor describes (ho ho) the attributes of a particular
 * resource.  Conceptually, it's a URI combined with some protocol-specific 
 * information that is accessible through <code>ResourceDescriptor</code>
 * subclasses.  There is no official XML schema for resource descriptors that
 * is considered up-to-date; see <a href="http://www.suhit.com/xml/test2.xsd">
 * http://www.suhit.com/xml/test2.xsd</a> for the next best thing.  The
 * resource descriptor API is subject to change pending the release of
 * Frax 2.
 *
 * @author Mark Ayzenshtat
 */
public interface ResourceDescriptor extends Persistent {
  /**
   * Returns a fully qualified name for the target resource.
   *
   * @return a fully qualified name for the target resource
   */
  public String getName();

  /**
   * Assigns a fully qualified name for the target resource.
   *
   * @param iName a fully qualified name for the target resource
   */
  public void setName(String iName);
  
  /**
   * Returns the protocol used to retrieve this resource descriptor.
   *
   * @return the protocol used to retrieve this resource descriptor
   */
  public String getProtocol();

  /**
   * Assigns the protocol used to retrieve this resource descriptor.
   *
   * @param iProtocol the protocol used to retrieve this resource descriptor
   */  
  public void setProtocol(String iProtocol);
  
  /**
   * Returns the target file's type.
   *
   * @return the target file's type
   */
  public String getType();

  /**
   * Assigns the target file's type.
   *
   * @param iType the target file's type
   */  
  public void setType(String iType);
  
  /**
   * Returns the target file's size, in bytes.
   *
   * @return the target file's size, in bytes
   */
  public int getSize();

  /**
   * Assigns the target file's size, in bytes.
   *
   * @param iSize the target file's size, in bytes
   */
  public void setSize(int iSize);

	/**
	 * Retrieves the time that the target resource was last modified.
   *
	 * @return the time of last modification
	 */  
  public Date getWhenLastModified();
  
	/**
	 * Assigns the time that the target resource was last modified.
   *
   * @param the time of last modification
	 */  
  public void setWhenLastModified(Date iLastModified);
  
	/**
	 * Retrieves any additional data this resource descriptor has.
	 *
	 * @return any additional data this resource descriptor has
	 */
  public String[] getAdditionalData();
  
	/**
	 * Assigns any additional data this resource descriptor has.
	 *
	 * @param iData any additional data this resource descriptor has
	 */  
  public void setAdditionalData(String[] iAddtlData);
}