/*
 * AbstractResourceDescriptor.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data.metadata;

import java.util.Date;

/**
 * Provides a skeletal implementation of <code>ResourceDescriptor</code>.
 *
 * @author Mark Ayzenshtat
 */
public abstract class AbstractResourceDescriptor
    implements ResourceDescriptor {
  private int mPersistenceID;
  private String mName;
  private String mProtocol;
  private String mType;
  private int mSize;
  private Date mLastModified;
  
	/**
	 * Retrieves the unique ID that serves as a persistence key to this object.
	 *
	 * @return this object's persistence ID
	 */
	public int getPersistenceID() {
    return mPersistenceID;
  }
	
	/**
	 * Assigns the unique ID that serves as a persistence key to this object.
	 *
	 * @param iID this object's persistence ID
	 */
	public void setPersistenceID(int iID) {
    mPersistenceID = iID;
  }
  
  /**
   * Returns a fully qualified name for the target resource.
   *
   * @return a fully qualified name for the target resource
   */
  public String getName() {
    return mName;
  }

  /**
   * Assigns a fully qualified name for the target resource.
   *
   * @param iName a fully qualified name for the target resource
   */
  public void setName(String iName) {
    mName = iName;
  }
  
  /**
   * Returns the protocol used to retrieve this resource descriptor.
   *
   * @return the protocol used to retrieve this resource descriptor
   */
  public String getProtocol() {
    return mProtocol;
  }

  /**
   * Assigns the protocol used to retrieve this resource descriptor.
   *
   * @param iProtocol the protocol used to retrieve this resource descriptor
   */  
  public void setProtocol(String iProtocol) {
    mProtocol = iProtocol;
  }
  
  /**
   * Returns the target file's type.
   *
   * @return the target file's type
   */
  public String getType() {
    return mType;
  }

  /**
   * Assigns the target file's type.
   *
   * @param iType the target file's type
   */  
  public void setType(String iType) {
    mType = iType;
  }
  
  /**
   * Returns the target file's size, in bytes.
   *
   * @return the target file's size, in bytes
   */
  public int getSize() {
    return mSize;
  }

  /**
   * Assigns the target file's size, in bytes.
   *
   * @param iSize the target file's size, in bytes
   */
  public void setSize(int iSize) {
    mSize = iSize;
  }

	/**
	 * Retrieves the time that the target resource was last modified.
   *
	 * @return the time of last modification
	 */  
  public Date getWhenLastModified() {
    return mLastModified;
  }
  
	/**
	 * Assigns the time that the target resource was last modified.
   *
   * @param the time of last modification
	 */  
  public void setWhenLastModified(Date iLastModified) {
    mLastModified = iLastModified;
  }
}