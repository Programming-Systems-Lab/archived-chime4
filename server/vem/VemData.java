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
 * @version 1.0
 */

public class VemData {

	/** The data class type of the object */
	private VemType mType;
	/** The sub-class-type for this object */
	private char mSubType;
	/** The filename of the 3D model representing this object */
	private String mShape;
	/** The filename of the 2D model representing this object */
	private String mShape2D;

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
	
	/** Getter for property shape.
	 * @return Value of property shape.
	 */
	public String getShape() {
		return mShape;
	}
	
	/** Setter for property shape.
	 * @param shape New value of property shape.
	 */
	public void setShape(String shape) {
		mShape = shape;
	}
	
	/** Getter for property shape2D.
	 * @return Value of property shape2D.
	 */
	public String getShape2D() {
		return mShape2D;
	}
	
	/** Setter for property shape2D.
	 * @param shape2D New value of property shape2D.
	 */
	public void setShape2D(String shape2D) {
		mShape2D = shape2D;
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
