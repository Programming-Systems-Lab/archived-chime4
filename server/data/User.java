/*
 * User.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

 package psl.chime4.data;

/**
 * A CHIME user.
 * 
 * @author Mark Ayzenshtat
 * @version 1.0
 */

public class User implements Persistable {
	private static final String kZeroString = "";
	public static final int GLOBAL_ID = 0;

	private int mID;
	private String mUserName;
	private String mPassword;

	/**
	 * Instantiates an empty <code>User</code> object.  This constructor
	 * intentionally has package scope so that we can make use of a factory.
	 */
	User() {
		mID = 0;
		mUserName = kZeroString;
		mPassword = kZeroString;
	}

	/**
	 * Retrieves the unique identifier for this user.
	 *
	 * @return the unique identifier for this user
	 */
	public int getPersistenceID() {
		return mID;
	}

	/**
	 * Assigns the unique identifier for this user.
	 *
	 * @param the unique identifier for this user
	 */
	public void setPersistenceID(int iID) {
		mID = iID;
	}

	/**
	 * Retrieves the name of this user.
	 *
	 * @return the name of this user
	 */
	public String getUserName() {
		return mUserName;
	}

	/**
	 * Assigns the name of this user.
	 *
	 * @param the name of this user
	 */
	public void setUserName(String iUserName) {
		mUserName = iUserName;
	}

	/**
	 * Retrieves this user's password.
	 *
	 * @return this user's password
	 */
	public String getPassword() {
		return mPassword;
	}

	/**
	 * Assigns this user's password.
	 *
	 * @param this user's password
	 */
	public void setPassword(String iPassword) {
		mPassword = iPassword;
	}
}