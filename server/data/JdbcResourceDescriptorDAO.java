/*
 * JdbcResourceDescriptorDAO.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

import java.sql.*;
import java.util.*;
import psl.chime4.server.data.sql.*;

/** 
 * Manages the persistence of <code>ResourceDescriptor</code> objects to a
 * relational database through JDBC.
 *
 * @author Mark Ayzenshtat 
 */
public class JdbcResourceDescriptorDAO implements ResourceDescriptorDAO {	
	// table names
	private static final String kTableRDs = "ResourceDescriptors";
	private static final String kTableMoreData = "RDAdditionalData";
	
	// column names
	private static final String kColRDsID = "ID";
	private static final String kColRDsTimeCreated = "TimeCreated";
	private static final String kColRDsTimeLastModified = "TimeLastModified";
	private static final String kColRDsType = "Type";
	private static final String kColRDsProtocol = "Protocol";
	private static final String kColRDsPath = "Path";
	private static final String kColRDsContentType = "ContentType";
	private static final String kColRDsSize = "Size";
	private static final String kColRDsHasMoreData = "HasAdditionalData";
	
	private static final String kColMoreDataID = "ID";
	private static final String kColMoreDataValue = "Data";
	private static final String kColMoreDataOrder = "DataOrder";
	
	private static final String[] kZeroStringArray = new String[0];
	
	private ConnectionSource mConnectionSource;
	
	/*
	 * Package-scope constructor to ensure instantiation through factory.
	 */
	JdbcResourceDescriptorDAO() {
		// get a connection source from the factory
		mConnectionSource = ConnectionSourceFactory.getInstance()
			.getConnectionSource();
		
		// make sure DB tables exist
		try {
			ensureTablesExist();
		} catch (DataAccessException ex) {
			throw new RuntimeException("Could not acquire tables.", ex);
		}
	}
	
	/**
	 * Loads the resource descriptor with the given ID from the 
	 * backing data store.
	 *
	 * @param iID the ID
	 * @return the loaded resource descriptor
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public Persistable load(int iID) throws DataAccessException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// milk the connection source
		conn = getConnection();
				
		// load the ResourceDescriptor
		try {
			stmt = conn.createStatement();			
			rs = stmt.executeQuery(buildRDsQuery(iID));			
			rs.next();
			
			// instantiate a protocol-specific ResourceDescriptor subclass
			String protocol = rs.getString(kColRDsProtocol);
			ResourceDescriptor rd = ResourceDescriptorFactory
				.getInstance().newRD(protocol);
			
			// copy values from the result set into the ResourceDescriptor object			
			rd.setPersistenceID(iID);
			rd.setTimeCreated(rs.getTimestamp(kColRDsTimeCreated));
			rd.setTimeLastModified(rs.getTimestamp(kColRDsTimeLastModified));
			rd.setType(rs.getString(kColRDsType));
			rd.setProtocol(rs.getString(kColRDsProtocol));
			rd.setPath(rs.getString(kColRDsPath));
			rd.setContentType(rs.getString(kColRDsContentType));
			rd.setSize(rs.getInt(kColRDsSize));
			
			// copy additional data, if it exists
			boolean hasMoreData = rs.getBoolean(kColRDsHasMoreData);
			if (hasMoreData) {
				List additionalDataList = new ArrayList(5);
				
				rs = stmt.executeQuery(buildMoreDataQuery(iID));
				while (rs.next()) {
					additionalDataList.add(rs.getString(kColMoreDataValue));
				}
				
				String[] additionalData = (String[]) 
					additionalDataList.toArray(kZeroStringArray);
				rd.setAdditionalData(additionalData);
			}
			
			return rd;
		} catch (SQLException ex) {			
			throw new
				DataAccessException("Error loading ResourceDescriptor.", ex);
		} finally {
			cleanUp(conn, stmt, rs);
		}
	}
	
	/**
	 * Saves the given resource descriptor to the backing data store.
	 *
	 * @param iP the resource descriptor to save
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 * @exception ClassCastException if the supplied <code>Persistable</code>
	 * object is not a <code>ResourceDescriptor</code>
	 */
	public void store(Persistable iP) throws DataAccessException {
		// ClassCastException will be thrown here if cast fails
		ResourceDescriptor r = (ResourceDescriptor) iP;
		
		Connection conn = null;
		Statement stmt = null;
		
		// get a connection
		conn = getConnection();
				
		try {
			stmt = conn.createStatement();
			
			String[] moreData = r.getAdditionalData();
			boolean hasMoreData = moreData.length > 0;
			
			// store resource descriptor
			stmt.executeUpdate(buildRDsStatement(r, hasMoreData));
			
			// store additional data if necessary			
			if (hasMoreData) {
				stmt.executeUpdate(buildMoreDataStatement(moreData));
			}      
		} catch (SQLException ex) {			
			throw new
				DataAccessException("Error storing resource descriptor.", ex);
		} finally {
			cleanUp(conn, stmt, null);
		}		
	}

	/**
	 * Creates a resource descriptor entry with the backing data store.
	 *
	 * @return the ID that uniquely identifies the created resource descriptor
	 * @exception DataAccessException if this operation cannot complete
	 * due to a failure in the backing store
	 */
	public int create() throws DataAccessException {
		// TODO: Implement me
		return 0;
	}
	
	public void delete(int iID) throws DataAccessException {
		// TODO: Implement me
	}

	private void ensureTablesExist() throws DataAccessException {
		Connection conn = null;
		Statement stmt = null;
		
		// get a connection
		conn = getConnection();
				
		try {
			stmt = conn.createStatement();			
			
			// attempt to create resource descriptors table
			try {
				stmt.execute(buildCreateRDsTableStatement());
			} catch (SQLException ex2) {
				// if an exception is thrown, it means the table already exists				
			}
			
			// attempt to create additional data table
			try {
				stmt.execute(buildCreateMoreDataTableStatement());
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
	
	/*
	 * Retrieves a connection from the connection source.
	 */
	private Connection getConnection() throws DataAccessException {		
		try {
			return mConnectionSource.obtainConnection();
		} catch (SQLException ex) {
			throw new DataAccessException("Could not obtain connection.", ex);
		}
	}

	/*
	 * Safely disposes of a <code>Connection</code>, <code>Statement</code>,
	 * and <code>ResultSet</code>.
	 */
	private void cleanUp(Connection iConn, Statement iStmt, ResultSet iRS) 
			throws DataAccessException {		
		try {
			if (iRS != null) {
				iRS.close();
			}
			
			if (iStmt != null) {
				iStmt.close();
			}
			
			if (iConn != null) {
				mConnectionSource.releaseConnection(iConn);
			}
		} catch (SQLException ex) {
			throw new 
				DataAccessException("Could not dispose of connection.", ex);
		}		
	}

	// query/statement builders -- methods that neatly assemble the SQL
	// statements that we're sending to the DB
	private String buildRDsQuery(int iID) {
		String[] columns = {
			kColRDsTimeCreated, kColRDsTimeLastModified, kColRDsType,
			kColRDsProtocol, kColRDsPath, kColRDsContentType, kColRDsSize,
			kColRDsHasMoreData
		};

		String whereClause = (new StringBuffer(kColRDsID)).append(" = ")
			.append(iID).toString();
		
		return SqlHelper.select(columns, kTableRDs, whereClause);
	}
	
	private String buildRDsStatement(ResourceDescriptor iRD, 
			boolean iHasMoreData) {
		String[] moreData = iRD.getAdditionalData();
		
		String[] columns = {
			kColRDsTimeCreated, kColRDsTimeLastModified, kColRDsType,
			kColRDsProtocol, kColRDsPath, kColRDsContentType, kColRDsSize,
			kColRDsHasMoreData			
		};
		
		String[] values = {
			SqlHelper.wrapInQuotes(
				new Timestamp(iRD.getTimeCreated().getTime()).toString()),
			SqlHelper.wrapInQuotes(
				new Timestamp(iRD.getTimeLastModified().getTime()).toString()),
			SqlHelper.prepareString(iRD.getType()), 
			SqlHelper.prepareString(iRD.getProtocol()),
			SqlHelper.prepareString(iRD.getPath()),
			SqlHelper.prepareString(iRD.getContentType()),
			String.valueOf(iRD.getSize()),
			String.valueOf((iHasMoreData) ? 1 : 0)
		};
		
		String whereClause = (new StringBuffer(kColRDsID)).append(" = ")
			.append(iRD.getPersistenceID()).toString();
		
		return SqlHelper.update(kTableRDs, columns, values, whereClause);
	}
	
	private String buildMoreDataStatement(String[] iAdditionalData) {
		// TODO: Implement me
		return null;
	}
	
	private String buildMoreDataQuery(int iID) {
		String[] columns = { kColMoreDataValue };
		String whereClause = (new StringBuffer(kColMoreDataID)).append(" = ")
			.append(iID).toString();
		
		return SqlHelper.select(columns, kTableMoreData, 
			whereClause, kColMoreDataOrder);
	}
	
	private String buildCreateRDsTableStatement() {
		// table columns...
		String[] columns = {
			kColRDsID, kColRDsTimeCreated, kColRDsTimeLastModified, kColRDsType,
			kColRDsProtocol, kColRDsPath, kColRDsContentType, kColRDsSize,
			kColRDsHasMoreData
		};
		
		// ...and their corresponding types
		String[] types = {
			"integer PRIMARY KEY", "datetime", "datetime", "varchar(20)",
			"varchar(20)", "varchar(200)", "varchar(50)", "integer", "char"		
		};
		
		return SqlHelper.create(kTableRDs, columns, types);
	}
	
	private String buildCreateMoreDataTableStatement() {
		// table columns...
		String[] columns = {
			kColMoreDataID, kColMoreDataValue, kColMoreDataOrder
		};
		
		// ...and their corresponding types
		String[] types = {
			"integer PRIMARY KEY", "varchar(255)", "integer"
		};
		
		return SqlHelper.create(kTableMoreData, columns, types);
	}
}