/*
 * DAOFactory.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

/**
 * A factory for creating <code>DataAccessObject</code>s.
 *
 * @author Mark Ayzenshtat 
 */
public interface DAOFactory {
	/**
	 * Retrieves a valid <code>DataAccessObject</code> instance that manages
	 * the supplied class of <code>Persistable</code> objects.
	 *
	 * @return a valid <code>DataAccessObject</code> instance	 
	 * @exception IllegalArgumentException if the supplied class does not
	 * inherit from <code>Persistable</code>
	 * @exception IllegalArgumentException if this <code>DAOFactory</code>
	 * does not have any account of the supplied class of <code>Persistable
	 * </code>objects
	 */
	public DataAccessObject getDAO(Class iPersistableClass);
	
	/**
	 * Binds a class of <code>Persistable</code> objects to a class of
	 * <code>DataAccessObject</code> instances.
	 *
	 * @param iPersistableClass the class of Persistable objects
	 * @param iDAOClass the class of DataAccessObject instances
	 */
	public void bindPersistableToDAO(Class iPersistableClass, Class iDAOClass);
}