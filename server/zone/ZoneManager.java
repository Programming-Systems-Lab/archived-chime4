
package psl.chime4.server.zone;


import psl.chime4.server.auth.NetworkNode;



/**
 * A zone manager is one of the two core functional components that make
 * up a zone server (the other component being a @see DataManager).  The
 * zone manager holds functionality for dealing with network organization
 * issues.  This includes allocating and removing zones, zone backup
 * responsibilities, and adding and removing zone servers.
 *
 * This class exclusively provides internal services to the zone server.
 * See the @see ZoneServer class definition for information about interfacing
 * a zone server to the "outside world".
 *
 * @author Gregory Estren (gce3@columbia.edu)
 * @version 1.0
 **/


public class ZoneManager {


    /**
     * Holds information on this server's primary and backup zone 
     * responsibilities, as well as its place on the Street (which is
     * really just identified by who its neighbors are)
     **/
    private ZoneSettings zoneSettings;


    private DataManager dataManager;




    /**
     * Creates a new zone manager object, given a repository for zone
     * settings. This repository is used throughout the zone server,
     * and thus no object should assume exclusive access to it.
     **/
    public ZoneManager(ZoneSettings zs, DataManager dm) {
	zoneSettings = zs;
	dataManager = dm;
    }





    /**
     * Promotes the specified CHIME server to a zone server.  This method
     * assumes that all decision making, etc. has already occurred.  It
     * does nothing more than perform the actual upgrade.
     **/
    public void addZoneService(NetworkNode chimeServer) {
    }





    /**
     * Demotes the specified zone server back to a regular CHIME server.
     **/
    public void removeZoneService(NetworkNode zoneServer) {
    }





    /**
     * Begins the process of offsetting current zone responsibilities to
     * an alternate zone server.  The entire process of doing this is broken
     * down into two methods and consists of the following operation:
     *
     *  1) Assign zone responsibility for the given list to the specified
     *     zone server. (initiateZoneTransfer method)
     *  2) Update remote server's "neighbor" as appropriate.  If the local
     *     server intends to go offline, then this will consist of 
     *     assigning the local server's neighbor as the remote server's
     *     neighbor.
     *  3) Receive acknowledgement and acceptance of transfer from remote
     *     server.  Once this happens, we know that the remote server
     *     is handling redundant responsibility for the specified zone list
     *     AND that its neighbor data has been updated as appropriate.
     *  4) After the acknowledgement is received, redirect all subscribing
     *     users, CHIME servers, etc. to the new server.  
     *     (completeZoneTransfer method)
     *  5) Internally eliminate zone responsibility.
     *
     *  @param list - list of zones to transfer to the given zone server.
     *                This list is assumed to be continuous and end at
     *                the border of this server's overall list.  If these
     *                conditions are not met, nothing is done.
     *  @param zoneServer - server to transfer responsibility to.  This
     *                      server MUST be the server neighboring the
     *                      current server (on the proper side) or be a 
     *                      newly commissioned zone server which needs
     *                      zone responsibility allocated to it.
     **/
    public void initiateZoneTransfer(Zone[] list, NetworkNode zoneServer) {

	Zone[] allZones = zoneSettings.getPrimaryZones();
	NetworkNode prevNeighbor = zoneSettings.getPreviousNeighbor();
	NetworkNode nextNeighbor = zoneSettings.getNextNeighbor();

	if ((list == null) || (allZones == null) || (zoneServer == null)) 
	    return;

	// If we're removing all our zones, then we should "forward on"
	// our neighboring zone server to our other neighbor.  If the
	// new server isn't already a neighbor, we assume that it will
	// fully inherit our existing previous and next neighbors
	if (list.length == allZones.length) {
	    if (zoneServer.equals(prevNeighbor))        prevNeighbor = null;
	    else if (zoneServer.equals(nextNeighbor))   nextNeighbor = null;
	}

	// Otherwise, if we're removing just some of our zones...
	else if (list.length < allZones.length) {
	    // moving to neighbor
	    if (zoneServer.equals(prevNeighbor) || 
		zoneServer.equals(nextNeighbor)) {
		prevNeighbor = null;
		nextNeighbor = null;
	    }

	    // "inserting" new zone server
	    else if (list[0].equals(allZones[0]))
		nextNeighbor = NetworkNode.localServer();
	    else if (list[list.length-1].equals(allZones[allZones.length-1]))
		prevNeighbor = NetworkNode.localServer();
	    else {  // internal transfer (which doesn't make much sense)
		nextNeighbor = NetworkNode.localServer();
		prevNeighbor = NetworkNode.localServer();
	    }
	}

	PeerCommunicator.initiateZoneTransfer(list, zoneServer, 
					      prevNeighbor, nextNeighbor);
    }





    /**
     * Receive a request from another zone server that wishes to transfer
     * zone responsibilities to the local server and accept or reject
     * accordingly.
     **/
    public void handleZoneTransferRequest(Zone[] list, NetworkNode zoneServer,
					  NetworkNode newPrevNeighbor,
					  NetworkNode newNextNeighbor) {

	//	if (do not want responsibility) {
	//         PeerCommunicator.sendMessage
	//         (PeerCommunicator.REJECT_ZONE_TRANSFER, zoneServer);
	//	}

	if (zoneServer.equals(zoneSettings.getNextNeighbor()))
	    zoneSettings.addPrimaryZones(list, false); // add at end
	else
	    zoneSettings.addPrimaryZones(list, true);  // add at beginning

	PeerCommunicator.sendMessage(PeerCommunicator.ACCEPT_ZONE_TRANSFER,
				     zoneServer);
	dataManager.describeZoneOrganization();
    }





    /**
     * Complete the process of offsetting current zone responsibilities to
     * an alternate zone server.  See the @see initiateZoneTransfer method
     * for a complete description of how this process works.
     *
     * @param list - list of zones to be transferred.
     * @param zoneServer - server to transfer responsibility to
     **/
    public void completeZoneTransfer(Zone[] list, NetworkNode zoneServer) {
	// 1) Subscribe to new primary server (so users still receive
	//    appropriate data) as a backup before they have unsubscribed.  
	//    This feature not currently implemented.

	if ((list == null) || (list.length == 0) || (zoneServer == null))
	    return;

	// Update neighbor information as necessary (if inserting a new
	// zone server in between local server and an existing neighbor)
	Zone[] allZones = zoneSettings.getPrimaryZones();
	if (!((zoneServer.equals(zoneSettings.getNextNeighbor()) || 
	       (zoneServer.equals(zoneSettings.getPreviousNeighbor()))))) {

	    if (list[0].equals(allZones[0]))
		zoneSettings.setPreviousNeighbor(zoneServer);
	    else if (list[list.length-1].equals(allZones[allZones.length-1]))
		zoneSettings.setNextNeighbor(zoneServer);
	}

	// Internally remove our zone data
	for (int i=0; i < list.length; i++)
	    zoneSettings.removePrimaryZone(list[i]);

	// Notify our subscribers about the change
	dataManager.describeZoneOrganization();
    }





    /**
     * Splits the specified zone (assumed to be under this server's 
     * primary responsibility) into two zones and communicates information
     * appropriately.
     *
     * @param - zone to split.  If zone is not under primary coverage of
     *          this server, nothing is done.
     **/
    public void splitZone(Zone zone) {
	// 1) Check if zone is in primary coverage
	// 2) Split & rename and update coverage list in zoneSettings
	// 3) Have the data manager figure out who is in what new zone
	//    (from the old zone) and tell them their new zone
    }





    /**
     * Merges the two zones together, under the assumptions that both
     * zones are under this server's primary responsibility and that they
     * currently exist sequentially next to each other (zone 1 comes
     * immediately before zone 2)
     *
     * @param zone1, zone2 - zones to merge.  If either zone is not under
     *                       this server's primary responsibility or zones
     *                       are not sequential, nothing is done
     **/
    public void mergeZones(Zone zone1, Zone zone2) {
	// 1) Check that zone1 and zone2 appear in primary next to each other
	// 2) Merge together with new name and update primary list
	// 3) Using data manager, communicate to users in both zones their
	//    new zone name.
    }



} // end ZoneManager class


















