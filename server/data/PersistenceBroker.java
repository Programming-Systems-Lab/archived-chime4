/*
 * PersistenceBroker.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

/**
 * The broker is the brain of the persistence layer.  It enables the public API
 * to transparently access multiple data sources, including the local data
 * store, the data stores driving other CHIME worlds, and FRAX.  It also
 * enables client code to cache the data obtained from these sources in the
 * local data store according to some intelligent persistence scheme.
 *
 * @author Mark Ayzenshtat
 */
public class PersistenceBroker {
	private DataServer mDataServer;
	private PersistenceScheme mScheme;
	
	public PersistenceBroker(DataServer iDataServer) {
		this(iDataServer, new NaivePersistenceScheme(iDataServer));
	}
	
	public PersistenceBroker(DataServer iDataServer, PersistenceScheme iScheme) {
		mDataServer = iDataServer;
		mScheme = iScheme;
	}
	
	public void smartPersistObject(Persistent iP) throws DataAccessException {
		mScheme.smartStore(iP);
	}
	
	public void smartPersistObjects(Persistent[] iPs)
			throws DataAccessException {
		mScheme.smartStore(iPs);
	}
}