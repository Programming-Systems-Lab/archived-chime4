/*
 * ResourceDescriptor.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

import java.util.Date;

/**
 * A resource descriptor describes (ho ho) the attributes of a particular
 * resource.  Conceptually, it's a URI combined with some protocol-specific 
 * information that is accessible through <code>ResourceDescriptor</code>
 * subclasses.
 *
 * @author Mark Ayzenshtat
 */
public abstract class ResourceDescriptor implements Persistent {
	private static final Date kZeroDate = new Date(0);
	private static final String kZeroString = "";
	
	private int mID;
	private Date mTimeCreated;
	private Date mTimeLastModified;
	private String mType;
	private String mProtocol;
	private String mPath;
	private String mContentType;
	private int mSize;
	
	/**
	 * Instantiates an empty resource descriptor.  Subclass constructors
	 * should call <code>super();</code> before performing any of their
	 * own initialization.
	 */
	protected ResourceDescriptor() {
		mID = 0;
		mTimeCreated = kZeroDate;
		mTimeLastModified = kZeroDate;
		mType = kZeroString;
		mProtocol = kZeroString;
		mPath = kZeroString;
		mContentType = kZeroString;
		mSize = 0;
	}
	
	/**
	 * Retrieves the unique identifier for this resource descriptor.
	 *
	 * @return the unique identifier
	 */
	public int getPersistenceID() {
		return mID;
	}
	
	/**
	 * Assigns the unique identifier for this resource descriptor.
	 *
	 * @param the unique identifier
	 */
	public void setPersistenceID(int iID) {
		mID = iID;
	}
	
	/**
	 * Retrieves the time that the target resource was created.
	 *
	 * @return the time of creation
	 */
	public Date getTimeCreated() {
		return mTimeCreated;
	}
	
	/**
	 * Assigns the time that the target resource was created.
	 *
	 * @param the time of creation
	 */
	public void setTimeCreated(Date iTime) {
		mTimeCreated = iTime;
	}

	/**
	 * Retrieves the time that the target resource was last modified.
	 *
	 * @return the time of last modification
	 */
	public Date getTimeLastModified() {
		return mTimeLastModified;
	}
	
	/**
	 * Assigns the time that the target resource was last modified.
	 *
	 * @param the time of last modification
	 */
	public void setTimeLastModified(Date iTime) {
		mTimeLastModified = iTime;
	}
	
	/**
	 * Retrieves the target resource's type.  For compatibility reasons,
	 * this value should always corresponds to a particular FRAX plug 
	 * (e.g. html, image, dir, txt, etc.).
	 *
	 * @return the target resource's type
	 */
	public String getType() {
		return mType;
	}
	
	/**
	 * Assigns the target resource's type.  For compatibility reasons,
	 * this value should always corresponds to a particular FRAX plug 
	 * (e.g. html, image, dir, txt, etc.).
	 *
	 * @param the target resource's type
	 */
	public void setType(String iType) {
		mType = iType;
	}
	
	/**
	 * Retrieves the protocol used to locate the target resource.  For
	 * compatibility reasons, this value should always correspond to a
	 * particular FRAX protocol (e.g. HTTP, BSCW, LFS).
	 *
	 * @return the protocol
	 */
	public String getProtocol() {
		return mProtocol;
	}

	/**
	 * Assigns the protocol used to locate the target resource.  For
	 * compatibility reasons, this value should always correspond to a
	 * particular FRAX protocol (e.g. HTTP, BSCW, LFS).
	 *
	 * @param the protocol
	 */
	public void setProtocol(String iProtocol) {
		mProtocol = iProtocol;
	}
	
	/**
	 * Retrieves the path of the target resource.  Currently, to preserve
	 * backward compatibility with the MetaInfo schema, this path includes
	 * the hostname.
	 *
	 * @return the path
	 */
	public String getPath() {
		return mPath;
	}

	/**
	 * Assigns the path of the target resource.  Currently, to preserve
	 * backward compatibility with the MetaInfo schema, this path includes
	 * the hostname.
	 *
	 * @return the path
	 */	
	public void setPath(String iPath) {
		mPath = iPath;
	}
	
	/**
	 * Retrieves the content type of the target resource.  Unlike the value
	 * returned when calling <code>getType()</code>, the content type depends
	 * on the protocol used to locate the target resource.  For example,
	 * the <code>HttpResourceDescriptor</code> returns the value of the
	 * <code>Content-Type</code> header field, while the <code>
	 * LfsResourceDescriptor</code> returns <code>"FILE"</code> or <code>
	 * "DIRECTORY"</code>.
	 *
	 * @return the protocol-specific content type
	 */
	public String getContentType() {
		return mContentType;
	}

	/**
	 * Assigns the content type of the target resource, which depends
	 * on the protocol used to locate the target resource.
	 *
	 * @param the protocol-specific content type
	 */
	public void setContentType(String iContentType) {
		mContentType = iContentType;
	}
	
	/**
	 * Retrieves the size, in bytes, of the target resource.
	 *
	 * @return the resource size
	 */
	public int getSize() {
		return mSize;
	}

	/**
	 * Assigns the size, in bytes, of the target resource.
	 *
	 * @return the resource size
	 */
	public void setSize(int iSize) {
		mSize = iSize;
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