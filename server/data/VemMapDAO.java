/*
 * VemMapDAO.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

import psl.chime4.server.auth.User;
import psl.chime4.server.vem.*;

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
 * These are listed in order of precedence. Any mapping in category 1 overrides
 * a mapping in any lower category.
 *
 * @author Vladislav Shchogolev
 * @version $Revision$
 */
public interface VemMapDAO extends DataAccessObject
{
	/**
	 * Loads the VEM mapping for the given User ID and file pattern from the
	 * backing data store.
	 *
	 * @param iU	the User ID
	 * @param iType	the content type of the object
	 * @param iURI	the object's URI (Host and Path)
	 * @param iVT	the object's VemType (room, door, object)
	 *
	 * @return the persistence ID of the mapping for this search
	 *			or -1 if the search did not find any results
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public int search(int iUID, String iType, String iURI, VemType iVT) 
		throws DataAccessException;
}
