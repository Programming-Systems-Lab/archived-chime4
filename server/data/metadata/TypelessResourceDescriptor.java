/*
 * TypelessResourceDescriptor.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data.metadata;

/**
 * Describes a resource of a generic or unspecified type.
 *
 * @author Mark Ayzenshtat
 * @see ResourceDescriptor
 */
public class TypelessResourceDescriptor extends AbstractResourceDescriptor {
	private static final String[] kZeroStringArray = new String[0];
	
	/**
	 * Instantiates an empty typeless resource descriptor.  This constructor
	 * intentionally has package scope so that we can make use of a resource
	 * descriptor factory.
	 */
	TypelessResourceDescriptor() {
		super();
	}

	/**
	 * Retrieves any additional data this resource descriptor has.
	 *
	 * @return any additional data this resource descriptor has
	 */
	public String[] getAdditionalData() {
		return kZeroStringArray;
	}
	
	/**
	 * Assigns any additional data this resource descriptor has.
	 *
	 * @param iData any additional data this resource descriptor has
	 */
	public void setAdditionalData(String[] iData) {
		// no additional data, so do nothing
	}
}