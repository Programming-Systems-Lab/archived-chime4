/*
 * PooledJdbcOneConnectionSource.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.data.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.prefs.Preferences;

/**
 * A <code>ConnectionSource</code> implementation that pools connections
 * obtained using <code>DriverManager.getConnection()</code> from the
 * JDBC 1.0 API.
 *
 * @author Mark Ayzenshtat
 */
public class PooledJdbcOneConnectionSource implements ConnectionSource {
	// preferences object
	private static final Preferences kPrefs =
		Preferences.userNodeForPackage(PooledJdbcOneConnectionSource.class);	
	
	// preference keys
	private static final String kKeyJdbcOneDriver = "JdbcOneDriver";
	private static final String kKeyJdbcOneURL = "JdbcOneURL";
	private static final String kKeyJdbcOneUserName = "JdbcOneUserName";
	private static final String kKeyJdbcOnePassword = "JdbcOnePassword";	
	
	// default preference values
	private static final String kDefaultJdbcOneDriver =
		"org.gjt.mm.mysql.Driver";
	private static final String kDefaultJdbcOneURL =
		"jdbc:mysql://localhost/chime4";
	private static final String kDefaultJdbcOneUserName = "sa";
	private static final String kDefaultJdbcOnePassword = "asdf";
	
	// pool constants
	private static final int kInitialPoolSize = 50;
	private static final int kPoolIncrementSize = 50;

	private String mDriverClass;
	private String mURL;
	private String mUserName;
	private String mPassword;
	private Map mConnections;
	
	/**
	 * Constructs a new <code>PooledJdbcOneConnectionSource</code>.
	 * This constructor intentionally has package scope and should only
	 * be called by <code>ConnectionSourceFactory</code>.
	 */
	PooledJdbcOneConnectionSource() {
		// load preferences
		mDriverClass = kPrefs.get(kKeyJdbcOneDriver, kDefaultJdbcOneDriver);
		mURL = kPrefs.get(kKeyJdbcOneURL, kDefaultJdbcOneURL);
		mUserName = kPrefs.get(kKeyJdbcOneUserName, kDefaultJdbcOneUserName);
		mPassword = kPrefs.get(kKeyJdbcOnePassword, kDefaultJdbcOnePassword);
		
		// load the DB driver
		try {
			Class.forName(mDriverClass);
		} catch (ClassNotFoundException ex) {
			throw new java.lang.RuntimeException("Cannot load the DB driver.", ex);
		}
		
		// initialize connection pool
		mConnections = new HashMap(kInitialPoolSize + kPoolIncrementSize);
		try {
			for (int i = 0; i < kInitialPoolSize; i++) {
				addNewConnection();
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Could not fill connection pool.", ex);
		}
	}
	
	/**
	 * Obtains a DB connection.
	 *
	 * @return the connection
	 * @exception SQLException if a database error occurs
	 */
	public synchronized Connection obtainConnection() throws SQLException {
		Iterator iterator = mConnections.keySet().iterator();
		Connection c;

		while (iterator.hasNext()) {
			c = (Connection) iterator.next();
			if (((Boolean) mConnections.get(c)).booleanValue()) {
				// if there are problems with this connection,
				// create a fresh one to take its place
				if (!isWorking(c)) {
					c = newConnection();
				}

				// mark this connection as taken
				mConnections.put(c, Boolean.FALSE);
				return c;
			}
		}

		// since no more connections are available, add
		// new ones numbering mIncrementSize to the pool
		for (int i = 0; i < kPoolIncrementSize - 1; i++) {
			addNewConnection();
		}

		return obtainConnection();
	}
	
	/**
	 * Instructs this connection source to perform clean-up on the supplied
	 * connection.
	 *
	 * @param iConn the connection to release
	 */
	public synchronized void releaseConnection(Connection iConn) throws SQLException {
		mConnections.put(iConn, Boolean.TRUE);
	}
	
	private boolean isWorking(Connection iConn) {
		try {
			iConn.setAutoCommit(true);
		} catch (SQLException ex) {
			return false;
		}
		
		return true;
	}
	
	private void addNewConnection() throws SQLException {
		Connection c = newConnection();
		mConnections.put(c, Boolean.TRUE);
	}
	
	private Connection newConnection() throws SQLException {
		return DriverManager.getConnection(mURL, mUserName, mPassword);
	}
}