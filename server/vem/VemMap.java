/*
 *  VemMap.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.vem;

import psl.chime4.data.User;

/**
 * <p>This object stores a mapping between a user and file pattern
 * and a corresponding <code>VemData</code> object. Currently the system
 * supports two types of file patterns. It can be either:
 * (a) a complete resource URI without any wild card characters, or
 * (b) an extension pattern composed of a *, a period and the extension, 
 * (i.e. *.html)
 * </p>
 *
 * @author Vladislav Shchogolev
 * @version 1.0
 * @see psl.chime4.data.VemMapDAO
 */

public class VemMap {

	/** the four possible priorities for VEM data mappings */
	public static final int USER_EXPLICIT = 1;
	public static final int GLOBAL_EXPLICIT = 2;
	public static final int USER_PATTERN = 3;
	public static final int GLOBAL_PATTERN = 4;

	private User mUser;     // the user this map is for, or User.GLOBAL
	private String mPattern;// the filename or extension pattern to map
	private VemData mData;  // the associated visual information

    /**
	 * Default constructor.
	 *
	 * @param iU	the user for whom this map is being made (or GLOBAL_ID)
	 * @param iP	the complete URI for the object source, or a wildcard pattern
	 *				(for mapping all objects with a particular extension)
	 * @param iVD	the <code>VemData</code> describing the appearance of all
	 *				such objects.
	 */
	public VemMap(User iU, String iP, VemData iVD) {
		mUser = iU;
		mPattern = iP;
		mData = iVD;
    }

	public User getUser() {
		return mUser;
	}

	public String getPattern() {
		return mPattern;
	}

	public VemData getVemData() {
		return mData;
	}

	/**
	 *	Returns the priority of this mapping in relation to others. Used 
	 *	mainly for internal storage and retrieval.
	 */
	public int getPriority()
	{
		int r;

		if (mPattern == null || mUser == null) 
			return 0;
		
		if (mPattern.indexOf('*') < 0) {  // if not a pattern
		    r = mUser.getID()==User.GLOBAL_ID ? GLOBAL_EXPLICIT : USER_EXPLICIT;
		} else	{
		    r = mUser.getID()==User.GLOBAL_ID ? GLOBAL_PATTERN : USER_PATTERN;
		}

		return r;
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
}