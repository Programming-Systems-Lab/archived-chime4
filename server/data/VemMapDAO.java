/*
 * VemMapDAO.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.data;

import psl.chime4.vem.*;

/**
 * This class manages a store of mappings between (User ID, file pattern) pairs
 * and corresponding VemData objects. Each pair has a unique VemData object.
 * Currently the system supports two types of file patterns. It can be either
 * (a) a complete resource URI without any wild card characters, or
 * (b) an extension pattern composed of a *, a period and the extension, (i.e. *.html)
 * <p>
 * There are 4 possibilities for the VEM in terms of choosing
 * visualization data for an object:
 * <ol>
 * <li>a user requests that a particular object appear in a certain way.
 * <li>a global setting states that a particular object appears in a certain way.
 * <li>a user setting states that objects with a certain extension appear a certain way
 * <li>a global setting state that objects with a certain extension appear a certain way
 * </ol>
 * <p>
 * These  are listed in order of precedence. Any mapping in category 1 overrides
 * a mapping in any lower category.
 *
 * @author Vladislav Shchogolev
 * @version 1.0
 */
public interface VemMapDAO
{
	/**
	 * Saves the given VEM mapping to the backing data store.
	 *
	 * @param iVM the VEM mapping to save
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public void store(VemMap iVM) throws DataAccessException;

	/**
	 * Loads the VEM mapping for the given User ID and file pattern from the
	 * backing data store.
	 *
	 * @param iU the User ID
	 * @param iPattern either a complete file name or a extension pattern
	 * (like *.html)
	 * @return the loaded resource descriptor
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public VemMap load(User iU, String iPattern) throws DataAccessException;
}
