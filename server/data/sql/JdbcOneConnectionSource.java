/*
 * JdbcOneConnectionSource.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;

/**
 * A <code>ConnectionSource</code> implementation that uses <code>
 * DriverManager.getConnection()</code> from the JDBC 1.0 API.
 *
 * @author Mark Ayzenshtat
 */
public class JdbcOneConnectionSource implements ConnectionSource {
	// preferences object
	private static final Preferences kPrefs =
		Preferences.userNodeForPackage(JdbcOneConnectionSource.class);	
	
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

	private String mDriverClass;
	private String mURL;
	private String mUserName;
	private String mPassword;
	
	/**
	 * Constructs a new <code>JdbcOneConnectionSource</code>.  This constructor
	 * intentionally has package scope and should only be called by
	 * <code>ConnectionSourceFactory</code>.
	 */
	JdbcOneConnectionSource() {
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
	}
	
	/**
	 * Obtains a DB connection.
	 *
	 * @return the connection
	 * @exception SQLException if a database error occurs
	 */
	public Connection obtainConnection() throws SQLException {
		return DriverManager.getConnection(mURL, mUserName, mPassword);
	}
	
	/**
	 * Instructs this connection source to perform clean-up on the supplied
	 * connection.
	 *
	 * @param iConn the connection to release
	 */
	public void releaseConnection(Connection iConn) throws SQLException {
		// since this is just a simple JDBC 1.0 implementation, there's no
		// pool clean-up involved -- simply close the connection if it isn't
		// already closed
		if (!iConn.isClosed()) {
			iConn.close();
		}
	}
}