/*
 * DataServer.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

import psl.chime4.server.librarian.LibrarianRequest;

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
	 * code to obtain data access objects with which to read/write persistent
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
	
	/**
	 * Carries out a librarian search.  The data server first checks its own
	 * data store before contacting FRAX and other CHIME servers (via service
	 * discovery) as needed.
	 *
	 * @param iReq an object that encapsulates the search constraints
	 */
	public void doLibrarianSearch(LibrarianRequest iReq) {
		
	}
}