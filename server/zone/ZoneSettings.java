
package psl.chime4.zone;


import java.util.*;





/**
 * Holds all information regarding the list of zones under a zone server's 
 * domain.  This consists of which zones a server controls, the type
 * of responsibility it has (primary, first backup, normal backup), and
 * servers covering neighboring zone areas.  Provides the ability to
 * query and modify a server's settings.
 *
 * This class exclusively provides internal services to the zone server.
 * See the @see ZoneServer class definition for information about interfacing
 * a zone server to the "outside world".
 *
 * @author Gregory Estren (gce3@columbia.edu)
 * @version 1.0
 **/


public class ZoneSettings {


    /**
     * List of current zones under primary responsibility of this server 
     **/
    private ArrayList primaryList();

    /**
     * List of current zones under "first backup" responsibility of this
     * server
     **/
    private ArrayList firstBackupList();

    /**
     * List of current zones under general backup responsibility of this
     * server
     **/
    private ArrayList generalBackupList();

    /**
     * "Next" zone server, i.e. the zone server with a responsibility that
     * directly borders the responsibility area for this server.  When
     * users walk off the current server's area (off the end), they are
     * redirected to the next neighbor.
     **/
    private NetworkNode nextNeighbor;

    /**
     * "Previous" zone server.  Same concept as the next zone server, except
     * borders the beginning of the current area instead of the end.
     **/
    private NetworkNode lastNeighbor;





    /**
     * Creates a new setup with no zone responsibilities or any other
     * settings.
     **/
    public ZoneSettings() {
	primaryList = new ArrayList();
	firstBackupList = new ArrayList();
	generalBackupList = new ArrayList();
	nextNeighbor = null;
	lastNeighbor = null;
    }





    /**
     * Allocates primary responsibility for the following set of zones
     * to this zone server.  This responsibility is added to any
     * pre-existing zone responsibility that the server may have.
     *
     * @param zones - set of zones that this server should take responsibility
     *                for.  No other server should serve primary 
     *                responsibility for these zones.
     * @param beginning - if true, adds this to the beginning of the current
     *                    list.  If false, adds it to the end of the list.
     **/
    public void addPrimaryZones(Zone[] zones, boolean beginning) {
	if (zones == null)
	    return;

	if (beginning == true) 
	    primaryList.add(0, Arrays.asList(zones));
	else
	    primaryList.add(Arrays.asList(zones));
    }





    /**
     * Adds primary responsibility for the given zone to this zone server.
     *
     * @param zone - zone that this server should take responsibility
     *               for.  No other server should serve primary 
     *               responsibility for this zone.
     * @param beginning - if true, adds this to the beginning of the current
     *                    list.  If false, adds it to the end of the list.
     *
     **/
    public void addPrimaryZone(Zone zone, boolean beginning) {
	if (zone == null)
	    return;
	else if (beginning == true)
	    primaryList.add(0, zone);
	else
	    primaryList.add(zone);
    }





    /**
     * Removes primary responsibility for the given zone.  If this server
     * did not already have primary responsibility for the zone (even if
     * it had backup responsibility), nothing is done.
     **/
    public void removePrimaryZone(Zone zone) {
	primaryList.remove(zone);
    }





    /**
     * Returns an ordered list (from beginning to end) of zones
     * under this server's primary responsibility.  
     *
     * @return ordered array of primary zone responsibility.  This array
     * is guaranteed not to be null, although it may be zero-length.
     **/
    public Zone[] getPrimaryZones() {
	return (Zone []) primaryList.toArray();
    }





    /**
     * Allocates backup responsibility for the following set of zones
     * to this zone server.  Two levels of backup responsibility are
     * available:  first level and "general" level.  First level backup
     * systems have the added responsibility of taking over as primary
     * servers in case the pre-existing primary server for a zone drops
     * from the network.  "General" backup systems have no responsibility
     * beyond general backup support.
     *
     * @param zones - set of zones that this server should take responsibility
     *                for.  No other server should serve the same level
     *                of backup responsibility for these zones.
     *
     * @param firstBackup - if true, set this server for first backup
     *                      responsibility.  If false, set this server
     *                      for general backup responsibility.
     * @param beginning - if true, adds this to the beginning of the current
     *                    list.  If false, adds it to the end of the list.
     **/
    public void setBackupZones(Zone[] zones, boolean firstBackup,
			       boolean beginning) {
	if (zones == null)
	    return;

	if (firstBackup == true) {
	    if (beginning == true)
		firstBackupList.add(0, Arrays.asList(zones));
	    else
		firstBackupList.add(Arrays.asList(zones));
	}
	else {
	    if (beginning == true)
		generalBackupList.add(0, Arrays.asList(zones));
	    else
		generalBackupList.add(Arrays.asList(zones));
	}

    }
    




    /**
     * Adds backup responsibility for the specified zone.  See the
     * setBackupZones method for more information about backup zone
     * responsibility.
     *
     * @param zones - zone that this server should take responsibility
     *                for.  No other server should serve the same level
     *                of backup responsibility for this zone.
     *
     * @param firstBackup - if true, set this server for first backup
     *                      responsibility.  If false, set this server
     *                      for general backup responsibility.
     * @param beginning - if true, adds this to the beginning of the current
     *                    list.  If false, adds it to the end of the list.
     **/
    public void addBackupZone(Zone zone, boolean firstBackup,
			      boolean beginning) {
	if (zone == null)
	    return;

	else if (firstBackup == true) {
	    if (beginning == true)
		firstBackupList.add(0, zone);
	    else
		firstBackupList.add(zone);
	}

	else {
	    if (beginning == true)
	       generalBackupList.add(0, zone);
	else
	    generalBackupList.add(zone);
	}
    }





    /**
     * Removes this server's backup responsibility for the specified zone.
     * If the server does not already have some backup responsibility
     * for the zone, nothing is done.
     **/
    public void removeBackupZone(Zone zone) {
	if (firstBackupList.remove(zone) == false)
	    generalBackupList.remove(zone);
    }





    /**
     * Returns an ordered list (from beginning to end) of zones
     * under this server's backup responsibility.  
     *
     * @param firstBackup - if true, return first backup list.  If false,
     *                      return general backup list.
     *
     * @return ordered array of backup zone responsibility.  This array
     * is guaranteed not to be null, although it may be zero-length.
     **/
    public Zone[] getBackupZones(boolean firstBackup) {
	if (firstBackup == true)
	    return (Zone []) firstBackupList.toArray();
	else
	    return (Zone []) generalBackupList.toArray();
    }





    /**
     * Sets this server's "next neighbor".  This corresponds to the server
     * that manages the zone directly bordering (and after) the current
     * server's range.  
     *
     * @param - network node to set as next neighbor, or null to unset
     *          any next neighbor value.
     **/
    public void setNextNeighbor(NetworkNode zoneServer) {
	nextNeighbor = zoneServer;
    }





    /**
     * Sets this server's "previous neighbor".  This corresponds to the server
     * that manages the zone directly bordering (and before) the current
     * server's range.  
     *
     * @param - network node to set as previous neighbor, or null to unset
     *          any next neighbor value.
     **/
    public void setPreviousNeighbor(NetworkNode zoneServer) {
	previousNeighbor = zoneServer;
	
    }





    /**
     * Returns the currently set "next neighbor" network node, or null if
     * no such neighbor is set.
     **/
    public NetworkNode getNextNeighbor() {
	return nextNeighbor;
    }





    /**
     * Returns the currently set "previous neighbor" network node, or null if
     * no such neighbor is set.
     **/
    public NetworkNode getPreviousNeighbor() {
	return previousNeighbor;
    }



} // end ZoneSettings class

