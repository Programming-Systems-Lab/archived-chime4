/*
 * GenericResourceDescriptor.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.data;

/**
 * Describes a resource accessible via a generic or unspecified protocol.
 *
 * @author Mark Ayzenshtat
 * @see ResourceDescriptor
 */
public class GenericResourceDescriptor extends ResourceDescriptor {
	private static final String[] kZeroStringArray = new String[0];
	
	/**
	 * Instantiates an empty generic resource descriptor.  This constructor
	 * intentionally has package scope so that we can make use of a resource
	 * descriptor factory.
	 */
	GenericResourceDescriptor() {
		super();
	}

	/**
	 * Retrieves any additional data this resource descriptor has.  This method
	 * intentionally has package scope and should only be called by <code>
	 * ResourceDescriptorDAO</code> implementations.
	 *
	 * @return any additional data this resource descriptor has
	 */
	String[] getAdditionalData() {
		return kZeroStringArray;
	}
	
	/**
	 * Assigns any additional data this resource descriptor has.  This method
	 * intentionally has package scope and should only be called by <code>
	 * ResourceDescriptorDAO</code> implementations.
	 *
	 * @param iData any additional data this resource descriptor has
	 */
	void setAdditionalData(String[] iData) {
		// no additional data, so do nothing
	}
}