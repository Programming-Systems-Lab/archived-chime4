/*
 * JdbcDAOFactory.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.data;

// jdk imports
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

// non-jdk imports
import psl.chime4.metadata.*;
import psl.chime4.vem.*;

/**
 * A factory for creating JDBC-implemented <code>DataAccessObject</code>s.
 *
 * @author Mark Ayzenshtat 
 */
public class JdbcDAOFactory implements DAOFactory {
	// a mapping of Persistable subclasses to DataAccessObject instances
	private Map mDAOClassMap;
	
	// package-scope constructor
	JdbcDAOFactory() {
		mDAOClassMap = new HashMap(20);
		
		bindPersistableToDAO(ResourceDescriptor.class,
			JdbcResourceDescriptorDAO.class);
		
		bindPersistableToDAO(VemMap.class, JdbcVemMapDAO.class);
	}
	
	/**
	 * Binds a class of <code>Persistable</code> objects to a class of
	 * <code>DataAccessObject</code> instances.
	 *
	 * @param iPersistableClass the class of Persistable objects
	 * @param iDAOClass the class of DataAccessObject instances
	 */
	public void bindPersistableToDAO(Class iPersistableClass, Class iDAOClass) {
		DataAccessObject dao;
		
		// instantiate the DAO
		try {
			dao = (DataAccessObject) iDAOClass.newInstance();
		} catch(InstantiationException ex) {
			throw new RuntimeException(
				"Cannot instantiate a ResourceDescriptorDAO subclass.", ex
			);
		} catch(IllegalAccessException ex) {
			throw new RuntimeException(
				"Cannot instantiate a ResourceDescriptorDAO subclass.", ex
			);
		}
		
		// put the DAO instance in the map		
		mDAOClassMap.put(iPersistableClass, dao);
	}
	
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
	public DataAccessObject getDAO(Class iPersistableClass) {
		if (!Persistable.class.isAssignableFrom(iPersistableClass)) {
			throw new java.lang.IllegalArgumentException(
				"Supplied class must inherit from Persistable.");
		}
		
		DataAccessObject dao = (DataAccessObject) 
			mDAOClassMap.get(iPersistableClass);
		
		if (dao == null) {
			throw new IllegalArgumentException("No record of class: " + 
				iPersistableClass);
		}
		
		return dao;
	}
}