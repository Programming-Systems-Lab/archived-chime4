/* 
 * VemData.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.vem;

/**
 * This class represents visualization information for any given
 * artifact in the database.
 *
 * @author Vladislav Shchogolev
 * @version $Revision$
 */

public class VemData {

	/** The data class type of the object */
	private VemType mType;
	/** The sub-class-type for this object */
	private char mSubType;
	/** The ResourceID of the 3D model representing this object */
	private int mModelID;
	/** The ResourceID of the 2D model representing this object */
	private int mImageID;

	public VemData() {
	}
	
	/**
	 * Returns the data class type for this VEM object.
	 *
	 * @return Value of property type.
	 */
	public VemType getType() {
		return mType;
	}	

	/** Setter for property type.
	 * @param type New value of property type.
	 */
	public void setType(VemType type) {
		mType = type;
	}	
	
	/** Getter for property ModelID.
	 * @return Value of property ModelID.
	 */
	public int getModelID() {
		return mModelID;
	}
	
	/** Setter for property ModelID.
	 * @param shape New value of property ModelID.
	 */
	public void setModelID(int model) {
		mModelID = model;
	}
	
	/** Getter for property imageID.
	 * @return Value of property imageID.
	 */
	public int getImageID() {
		return mImageID;
	}
	
	/** Setter for property shape2D.
	 * @param shape2D New value of property shape2D.
	 */
	public void setImageID(int image) {
		mImageID = image;
	}
	
	/** Getter for property subType.
	 * @return Value of property subType.
	 */
	public char getSubType() {
		return mSubType;
	}	

	/** Setter for property subType.
	 * @param subType New value of property subType.
	 */
	public void setSubType(char subType) {
		mSubType = subType;
	}	
	
}
