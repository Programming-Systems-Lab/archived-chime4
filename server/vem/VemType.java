package psl.chime4.vem;

/**
 * A typesafe enum for VEM types.  Objects of this type are guaranteed to
 * work with the == operator, so comparisons are cheap.
 *
 * @author Vladislav Shchogolev
 */

public class VemType {
	public static final VemType COMPONENT = new VemType("Component");
	public static final VemType CONTAINER = new VemType("Container");
	public static final VemType CONNECTOR = new VemType("Connector");

	private String mType;

	private VemType(String iType) {	   // constructor intentionally private
		mType = iType;
	}

	public String toString() {
		return mType;
	}

	public int toInt() {
		return (mType.equals("Component")? 0 : (mType.equals("Container")? 1 : 2));
	}
	
	public static VemType getTypeForCode(int i) {
		switch (i) {
			case 0: return COMPONENT;
			case 1: return CONTAINER;
			case 2: return CONNECTOR;
			default: return null;
		}
	}
}