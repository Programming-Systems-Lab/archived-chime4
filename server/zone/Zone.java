
package psl.chime4.server.zone;





/**
 *
 * A zone is simply a specified subsection of the overall Street that is
 * capable of holding CHIME servers and users.
 *
 * @author Gregory Estren (gce3@columbia.edu)
 * @version 1.0
 **/

public class Zone {


    private int zoneID;


    // 90% of a zone's data is the same as a CHIME world.  The other 10%
    // involves a couple of Street-specific parameters which will need
    // to be filled in at a later date.
    public Zone() {
	zoneID = -1;
    }


    public void setID(int id) {
	zoneID = id;
    }


    public int getID() {
	return zoneID;
    }
    
} // end Zone class






