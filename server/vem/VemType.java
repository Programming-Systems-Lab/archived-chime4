package psl.chime4.server.vem;

/**
 * A typesafe enum for VEM types.  Objects of this type are guaranteed to
 * work with the == operator, so comparisons are cheap.
 * <P>The VemType divides world objects into 3 categories. For example, a Door
 * will typically be a Connector, while a Room is a Container and an object
 * in a room is a Component.
 *
 * @author Vladislav Shchogolev
 * @version $Revision$
 */

public class VemType {
	public static final VemType COMPONENT = new VemType(0);
	public static final VemType CONTAINER = new VemType(1);
	public static final VemType CONNECTOR = new VemType(2);

	private int mType;

	private VemType(int iType) {	   // constructor intentionally private
		mType = iType;
	}

	public String toString() {
		String[] labels = {"Component", "Container", "Connector"};
		return labels[mType];
	}

	public int toInt() {
		return mType;
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