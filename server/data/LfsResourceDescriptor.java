/*
 * LfsResourceDescriptor.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

/**
 * Describes a resource accessible via the local filesystem.
 *
 * @author Mark Ayzenshtat
 * @see ResourceDescriptor
 */
public class LfsResourceDescriptor extends ResourceDescriptor {
	private boolean mIsHidden;
	private LfsResourceDescriptor[] mContainedFiles;

	/**
	 * Instantiates an empty LFS resource descriptor.  This constructor
	 * intentionally has package scope so that we can make use of a resource
	 * descriptor factory.
	 */
	LfsResourceDescriptor() {
		super();

		mIsHidden = false;
		mContainedFiles = new LfsResourceDescriptor[0];
	}

	/**
	 * Returns whether the target resource is hidden.
	 *
	 * @return whether the target resource is hidden
	 */
	public boolean isHidden() {
		return mIsHidden;
	}

	/**
	 * Assigns whether the target resource should be hidden.
	 *
	 * @param iHidden whether the target resource should be hidden
	 */
	public void setHidden(boolean iHidden) {
		mIsHidden = iHidden;
	}

	/**
	 * If the target resource is a directory, retrieves descriptors for the
	 * files that it contains.  If the target resource is a file, the returned
	 * array is guaranteed to be non-null, but its contents are undefined.
	 *
	 * @return descriptors for the files that the target directory contains
	 */
	public LfsResourceDescriptor[] getContainedFiles() {
		return mContainedFiles;
	}

	/**
	 * If the target resource is a directory, assigns the descriptors of the
	 * files that it contains.  If the target resource is a file, this method
	 * should be treated as a no-op.
	 *
	 * @param iFiles descriptors for the files that the target directory contains
	 */
	public void setContainedFiles(LfsResourceDescriptor[] iFiles) {
		mContainedFiles = iFiles;
	}

	/**
	 * Retrieves any additional data this resource descriptor has.  This method
	 * intentionally has package scope and should only be called by <code>
	 * ResourceDescriptorDAO</code> implementations.
	 *
	 * @return any additional data this resource descriptor has
	 */
	String[] getAdditionalData() {
		// TODO: Implement this method
		return new String[0];
	}
	
	/**
	 * Assigns any additional data this resource descriptor has.  This method
	 * intentionally has package scope and should only be called by <code>
	 * ResourceDescriptorDAO</code> implementations.
	 *
	 * @param iData any additional data this resource descriptor has
	 */
	void setAdditionalData(String[] iData) {
		// TODO: Implement this method
	}
}