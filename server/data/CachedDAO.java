/*
 * CachedDAO.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

import java.util.Map;
import java.util.HashMap;

/**
 * TODO: Write this sheeit.
 *
 * @author Mark Ayzenshtat 
 */
public class CachedDAO implements DataAccessObject {
	private DataAccessObject mSourceDAO;
	private Map mCache;
	
	public CachedDAO(DataAccessObject iSourceDAO) {
		if (iSourceDAO == null) {
			throw new NullPointerException("Supplied DAO must be non-null.");
		}
		
		mSourceDAO = iSourceDAO;
		mCache = new HashMap();		
	}
	
	/**
	 * Loads the persistable object with the given ID from the
	 * backing data store.
	 *
	 * @param iID the ID
	 * @return the loaded persistable object
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public Persistable load(int iID) throws DataAccessException {
		Integer integerID = new Integer(iID);
		
		Persistable p = (Persistable) mCache.get(integerID);
		if (p == null) {
			// the object we're trying to load is not locally cached,
			// so get it from the source DAO and place it in the cache
			p = mSourceDAO.load(iID);
			mCache.put(integerID, p);
		}
		
		return p;
	}
	
	/**
	 * Saves the given persistable object to the backing data store.
	 *
	 * @param iR the persistable object to save
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public void store(Persistable iR) throws DataAccessException {
		// store the persistable object in the source DAO
		mSourceDAO.store(iR);
		
		// store the persistable object in the cache
		mCache.put(new Integer(iR.getPersistenceID()), iR);
	}
	
	/**
	 * Creates a persistable object in the backing data store.
	 *
	 * @return the ID that uniquely identifies the created persistable object
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public int create() throws DataAccessException {
		return mSourceDAO.create();
	}

	/**
	 * Deletes a persistable object from the backing data store.
	 *
	 * @param iID the ID that uniquely identifies the persistable object
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public void delete(int iID) throws DataAccessException {
		// delete the persistable object from the source DAO
		mSourceDAO.delete(iID);
		
		// delete the persistable object from the cache
		mCache.remove(new Integer(iID));
	}
}