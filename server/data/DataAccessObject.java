/*
 * DataAccessObject.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

/**
 * Manages the persistence of <code>Persistable</code> objects.
 * Concrete implementations of this interface will communicate with some kind
 * of underlying data store.  However, if client code is written to the
 * interface and not to the implementation, the user shouldn't care what the
 * backing data store is.  In this manner, we can decouple data access logic 
 * from client code that depends on it.  Use the <code>DAOFactory</code> API
 * to acquire functional <code>DataAccessObject</code> instances.<br>
 * <br>
 * This object conforms to the J2EE Data Access Object
 * <a href="http://java.sun.com/blueprints/patterns/j2ee_patterns/data_access_object/">
 * design pattern</a>.
 *
 * @author Mark Ayzenshtat 
 */
public interface DataAccessObject {
	/**
	 * Loads the persistable object with the given ID from the
	 * backing data store.
	 *
	 * @param iID the ID
	 * @return the loaded persistable object
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public Persistable load(int iID) throws DataAccessException;
	
	/**
	 * Saves the given persistable object to the backing data store.
	 *
	 * @param iR the persistable object to save
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public void store(Persistable iR) throws DataAccessException;
	
	/**
	 * Creates a persistable object in the backing data store.
	 *
	 * @return the ID that uniquely identifies the created persistable object
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public int create() throws DataAccessException;

	/**
	 * Deletes a persistable object from the backing data store.
	 *
	 * @param iID the ID that uniquely identifies the persistable object
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public void delete(int iID) throws DataAccessException;
}