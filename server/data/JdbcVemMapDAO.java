/*
 * JdbcVemMapDAO.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.data;

import java.sql.*;
import psl.chime4.vem.*;
import psl.chime4.data.sql.*;

/**
 * Implements VemMapDAO using a JDBC database connection
 *
 * @author Vladislav Shchogolev
 * @version 1.0
 */

public class JdbcVemMapDAO implements VemMapDAO 
{
	// table names
	private static final String kTableVMs = "VemMappings";	
	
	// column names 
	private static final String kColVMsUID = "UserID";
	private static final String kColVMsPattern = "Pattern";
	private static final String kColVMsPriority = "Priority";
	private static final String kColVMsType = "VemType";
	private static final String kColVMsSubType = "SubType";
	private static final String kColVMsShape = "Shape";
	private static final String kColVMsShape2D = "Shape2D";
	
	// prepared statements
	private PreparedStatement searchStmt, insertStmt;

	// connection
	private ConnectionSource mConnectionSource;
	
    JdbcVemMapDAO()
	{
		// get a connection source from the factory
		mConnectionSource = ConnectionSourceFactory.getInstance()
			.getConnectionSource();
	}

	public void store(VemMap iVM) throws DataAccessException
	{
		Connection conn = getConnection();
				
		try {
			prepareInsert(conn, iVM);
			insertStmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new DataAccessException("Error in storing VEM Mapping", sqle);
		} finally {
			cleanUp(conn); // clean up
		}
	}

	public VemMap load(User iU, String iPattern) throws DataAccessException
	{
		Connection conn = getConnection();	

		try {
			prepareSearch(conn, iU, iPattern);
			ResultSet rs = searchStmt.executeQuery();
			if (rs.next()) {	// grab first row of the table
				// populate VemData object
				VemData data = new VemData();
				data.setType(VemType.getTypeForCode(rs.getInt(kColVMsType)));
				data.setSubType((char)rs.getByte(kColVMsSubType));
				data.setShape(rs.getString(kColVMsShape));
				data.setShape2D(rs.getString(kColVMsShape2D));
				return new VemMap(iU, iPattern, data); 
			} else
				return null;
		} catch (SQLException sqle) {
			throw new DataAccessException("Error in loading VEM Mapping", sqle);
		} finally {
			cleanUp(conn); // clean up
		}
	}

	private void prepareSearch(Connection conn, User iU, String iP) 
	throws SQLException
	{
		if (searchStmt == null) {	// if first time use, create new statement
			String cols[] = { 
				kColVMsType, kColVMsSubType, kColVMsShape, kColVMsShape2D, 
				kColVMsPriority 
			};
		
			String whereClause = "(userID = 0 OR userID = '?') AND " +
				"(pattern = '?' OR pattern = '?')";
			
			// order by priority (5)
			searchStmt = conn.prepareStatement(
				SqlHelper.select(cols, kTableVMs, whereClause, "5"));
		}

		// parameter indexes refer to the ? in the whereClause string
		searchStmt.setInt(1, iU.getPersistenceID());
		searchStmt.setString(2, iP);
		searchStmt.setString(3, VemMap.getExtensionPattern(iP));
    }
	
	private void prepareInsert(Connection conn, VemMap iVM) 
	throws SQLException 
	{
		VemData vd = iVM.getVemData();
				
		if (insertStmt == null) {	 // if first time use, create new statement
			String insertCode = "INSERT INTO " + kTableVMs + 
				" VALUES ('?', '?', '?', '?', '?', '?', '?')";
			insertStmt = conn.prepareStatement(insertCode);
		}
		
		// parameter indexes refer to the ? in the insertCode string
		insertStmt.setInt(1, iVM.getUser().getPersistenceID()); // set the user ID
		insertStmt.setString(2, iVM.getPattern());  // set the URI pattern
		insertStmt.setInt(3, iVM.getPriority());    // set the priority
		insertStmt.setInt(4, vd.getType().toInt());	// vem type
		insertStmt.setByte(5, (byte)vd.getSubType());	// vem sub type
		insertStmt.setString(6, vd.getShape());	// vem shape
		insertStmt.setString(7, vd.getShape2D());	// vem shape 2d
	}
	
	private Connection getConnection() throws DataAccessException {
		try {
			return mConnectionSource.obtainConnection();
		} catch (SQLException ex) {
			throw new DataAccessException("Could not obtain connection.", ex);
		}	
	}
	
	private void cleanUp(Connection conn) throws DataAccessException {
		try {				
			mConnectionSource.releaseConnection(conn);
		} catch (SQLException ex) {
			throw new 
				DataAccessException("Could not dispose of connection.", ex);
		}
	}
}