/*
 * JdbcUserDAO.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

import java.sql.*;
import psl.chime4.server.data.sql.*;

/**
 * Manages the persistence of <code>User</code> objects to a relational
 * database via JDBC.
 *
 * @author Mark Ayzenshtat
 * @see psl.chime4.server.auth.User
 */
public class JdbcUserDAO extends AbstractJdbcDAO
		implements UserDAO {
	// table names
	private static final String kTableUsers = "Users";
	
	// column names
	private static final String kColUsersID = "ID";
	private static final String kColUsersUserName = "UserName";
	private static final String kColUsersPassword = "Password";
	private static final String kColUsersPublicKey = "PublicKey";
	private static final String kColUsersAccessModes = "AccessModes";
		
	/*
	 * Package-scope constructor to ensure instantiation through factory.
	 */
	JdbcUserDAO() {
		super();
	}

	/**
	 * Ensures that data tables exist.  If they do not exist, it is expected
	 * that they will after this method completes.
	 */
	protected void ensureTablesExist() throws DataAccessException {
		Connection conn = null;
		Statement stmt = null;
		
		// get a connection
		conn = getConnection();
				
		try {
			stmt = conn.createStatement();			
			
			// attempt to create users table
			try {
				stmt.execute(buildCreateUsersTableStatement());
			} catch (SQLException ex2) {
				// if an exception is thrown, it means the table already exists				
			}
		} catch (SQLException ex) {			
			throw new
				DataAccessException("Error while attempting to create tables.", ex);
		} finally {
			cleanUp(conn, stmt, null);
		}		
	}

	private String buildCreateUsersTableStatement() {
		// table columns...
		String[] columns = {
			kColUsersID, kColUsersUserName, kColUsersPassword,
			kColUsersPublicKey, kColUsersAccessModes
		};
		
		// ...and their corresponding types
		String[] types = {
			"integer PRIMARY KEY", "varchar(200)", "varchar(200)",
			"varbinary(4096)", "integer"
		};
		
		return SqlHelper.create(kTableUsers, columns, types);
	}	
	
	/**
	 * Loads the persistent object with the given ID from the
	 * backing data store.
	 *
	 * @param iID the ID
	 * @return the loaded persistent object
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public Persistent load(int iID) throws DataAccessException {
		return null;
	}
	
	/**
	 * Saves the given persistent object to the backing data store.
	 *
	 * @param iR the persistent object to save
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public void store(Persistent iR) throws DataAccessException {
	}
	
	/**
	 * Creates a persistent object in the backing data store.
	 *
	 * @return the ID that uniquely identifies the created persistent object
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public int create() throws DataAccessException {
		return 0;
	}

	/**
	 * Deletes a persistent object from the backing data store.
	 *
	 * @param iID the ID that uniquely identifies the persistent object
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public void delete(int iID) throws DataAccessException {
	}
}