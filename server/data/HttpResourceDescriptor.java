/*
 * HttpResourceDescriptor.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

import java.util.Date;

/**
 * Describes a resource accessible via the HTTP protocol.
 *
 * @author Mark Ayzenshtat
 * @see ResourceDescriptor
 */
public class HttpResourceDescriptor extends ResourceDescriptor {
	private static final Date kZeroDate = new Date(0);
	
	private Date mTimeExpires;
	private Date mTimeDelivered;
	
	/**
	 * Instantiates an empty HTTP resource descriptor.  This constructor
	 * intentionally has package scope so that we can make use of a resource
	 * descriptor factory.
	 */
	HttpResourceDescriptor() {
		super();
		
		mTimeExpires = kZeroDate;
		mTimeDelivered = kZeroDate;
	}
	
	/**
	 * Retrieves the time that the target resource expires.
	 *
	 * @return the expiration time
	 */
	public Date getTimeExpires() {
		return mTimeExpires;
	}
	
	/**
	 * Assigns the time that the target resource expires.
	 *
	 * @param the expiration time
	 */
	public void setTimeExpires(Date iTime) {
		mTimeExpires = iTime;
	}

	/**
	 * Retrieves the time that the target resource was delivered.
	 *
	 * @return the delivery time
	 */
	public Date getTimeDelivered() {
		return mTimeDelivered;
	}
	
	/**
	 * Assigns the time that the target resource was delivered.
	 *
	 * @param the delivery time
	 */
	public void setTimeDelivered(Date iTime) {
		mTimeDelivered = iTime;
	}

	/**
	 * Retrieves any additional data this resource descriptor has.  This method
	 * intentionally has package scope and should only be called by <code>
	 * ResourceDescriptorDAO</code> implementations.
	 *
	 * @return any additional data this resource descriptor has
	 */
	String[] getAdditionalData() {
		String[] data = new String[2];
		
		data[0] = String.valueOf(mTimeExpires.getTime());
		data[1] = String.valueOf(mTimeDelivered.getTime());
		
		return data;
	}
	
	/**
	 * Assigns any additional data this resource descriptor has.  This method
	 * intentionally has package scope and should only be called by <code>
	 * ResourceDescriptorDAO</code> implementations.
	 *
	 * @param iData any additional data this resource descriptor has
	 */
	void setAdditionalData(String[] iData) {
		try {
			mTimeExpires.setTime(Long.parseLong(iData[0]));
			mTimeDelivered.setTime(Long.parseLong(iData[1]));
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Could not decode additional data.");
		}
	}
}