/*
 * VemMapDAOFactory.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

/**
 * A factory for creating <code>VemMapDAO</code> objects
 *
 * @author  Vladislav Shchogolev
 */
public class VemMapDAOFactory {
	
	private static final VemMapDAOFactory kSingleton =
		new VemMapDAOFactory();
	
	private static final String kDefaultDAOClassName = "JdbcVemMapDAO";
	private VemMapDAO mDAO;
	
	// prevent external instantiation
	private VemMapDAOFactory() {		
		
		try {
			mDAO = (VemMapDAO)Class.forName(kDefaultDAOClassName).newInstance();
		} catch(Exception ex) {
			throw new RuntimeException(
				"Cannot instantiate a VemMapDAO object.", ex
			);
		}
	}

	/**
	 * Retrieves the singleton instance of this factory.
	 *
	 * @return the singleton instance of this factory
	 */
	public static VemMapDAOFactory getInstance() {
		return kSingleton;
	}
	
	/**
	 * Retrieves a valid <code>VemMapDAO</code> object.
	 *
	 * @return a valid <code>VemMapDAO</code> object
	 */
	public VemMapDAO getDAO() {
		return mDAO;
	}
	
}
