/*
 * DataServer.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.data;

import psl.chime4.metadata.ResourceDescriptor;

/**
 * The <code>DataServer</code> class represents the central access point for
 * the public API, through which other components like the librarian, world
 * manager, and zone manager communicate with the data server.  Each CHIME
 * server contains exactly one data server, created and destroyed by the
 * world manager.
 *
 * @author Mark Ayzenshtat
 */
public class DataServer {
	private DAOFactory mDAOFactory;
	
	public DataServer() {	
		mDAOFactory = new JdbcDAOFactory();
	}
	
	/**
	 * Initializes this data server.
	 */
	public void startup() {
		// TODO: Implement me.
	}
	
	/**
	 * Shuts down this data server.
	 */
	public void shutdown() {
		// TODO: Implement me.
	}
	
	/**
	 * Retrieves this data server's DAO factory.  The DAO factory enables client
	 * code to obtain data access objects with which to read/write persistable
	 * objects from/to the underlying data store.
	 *
	 * @return this data server's DAO factory
	 */
	public DAOFactory getDAOFactory() {
		return mDAOFactory;
	}
	
	public void completeMetadata(ResourceDescriptor iRD) {
		// TODO: Implement me.
	}
}