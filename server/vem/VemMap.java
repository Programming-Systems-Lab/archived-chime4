/*
 *  VemMap.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.vem;

import psl.chime4.server.auth.User;
import psl.chime4.server.data.Persistent;

/**
 * <p>This object stores a mapping between a user and file pattern
 * and a corresponding <code>VemData</code> object. Currently the system
 * supports two types of file patterns. It can be either:
 * (a) a complete resource URI without any wild card characters, or
 * (b) the contentType of the object
 * </p>
 *
 * @author Vladislav Shchogolev
 * @version 1.0
 * @see psl.chime4.data.VemMapDAO
 */

public class VemMap implements Persistent {
    
    /**
     *	The four possible priorities for VEM data mapping priorities
     */
    public static final int EXPLICIT	 = 1;
    public static final int CONTENT_TYPE = 3;
    public static final int DEFAULT	 = 5;
    
    /**
     *	Priority modifiers
     */
    public static final int USER_MAP	= 0;
    public static final int GLOBAL_MAP	= 1;
    
    private int mUserID;		// the user this map is for, or User.GLOBAL
    private String mContentType;	// the content type of the object
    private String mHostAndPath;	// the host and path string for the object
					// (also called the URI or URL)
    private VemData mData;		// the associated visual information
    private int persistenceID;
    
    /**
     * Constructor.
     *
     * @param iUID	the user for whom this map is being made (or GLOBAL_ID)
     * @param iContentType
     *				the content type of the object
     * @param iHAP	the complete URI for the object
     * @param iVD	the <code>VemData</code> describing the appearance of all
     *				such objects.
     */
    public VemMap(int iUID, String iContentType, String iHAP, VemData iVD) {
	mUserID = iUID;
	mContentType = iContentType;
	mHostAndPath = iHAP;
	mData = iVD;
    }
    
    /**
     *	Secondary constructor, used when a mapping is retrieved from storage.
     *
     * @param iUID	the user for whom this map is being made (or GLOBAL_ID)
     * @param iPattern		either the content type or the URI
     * @param iPriority		the priority of the mapping
     * @param iVD	the <code>VemData</code> describing the appearance of all
     *				such objects.
     */
    public VemMap(int iUID, String iPattern, int iPriority, VemData iVD) {
	this(iUID, null, null, iVD);
	
	if (iPriority == EXPLICIT + USER_MAP ||
	iPriority == EXPLICIT + GLOBAL_MAP) {
	    mHostAndPath = iPattern;
	} else {
	    mContentType = iPattern;
	}
    }
    
    public String getPattern() {
	if (mHostAndPath != null) return mHostAndPath;
	else if (mContentType != null) return mContentType;
	else return null;
    }
    
    public int getUserID() {
	return mUserID;
    }
    
    public String getContentType() {
	return mContentType;
    }
    
    public String getHostAndPath() {
	return mHostAndPath;
    }
    
    public VemData getVemData() {
	return mData;
    }
    
    /**
     *	Returns the priority of this mapping in relation to others. Used
     *	mainly for internal storage and retrieval.
     */
    public int getPriority() {
	int modifier = (mUserID == User.GLOBAL_ID) ? GLOBAL_MAP : USER_MAP;
	
	if (mHostAndPath == null && mContentType == null)
	    return DEFAULT + modifier;
	
	else if (mHostAndPath == null)
	    return CONTENT_TYPE + modifier;
	
	else return EXPLICIT + modifier;
    }
    
    /**
     *	Given an explicit URI, returns a wildcard pattern for all
     *	resources with the same extension as the given resource.
     *
     *	@param iStr any string ending in the file name and extension of the
     *				resource.
     *	@returns A string of the form <code>*.<i>ext</i></code>
     */
    public static String getExtensionPattern(String iStr) {
	int index = iStr.lastIndexOf(".");
	return (index < 0) ? "" : "*" + iStr.substring(index);
    }
    
    /**
     * Retrieves the unique ID that serves as a key to this object.
     *
     * @return this object's ID
     */
    public int getPersistenceID() {
	return persistenceID;
    }
    
    /**
     * Assigns the unique ID that serves as a key to this object.
     *
     * @param iID this object's ID
     */
    public void setPersistenceID(int iID) {
	persistenceID = iID;
    }
    
    public boolean isExplicitMap() {
	int p = getPriority();
	return (p == EXPLICIT + USER_MAP || p == EXPLICIT + GLOBAL_MAP);
    }
    
}