
package psl.chime4.server.zone.schemes;

import psl.chime4.server.auth.NetworkNode;
import psl.chime4.server.zone.*;
import psl.chime4.server.zone.authority.NetworkAuthority;
import psl.chime4.server.zone.metrics.*;


public class DefaultNetworkAuthority implements NetworkAuthority,
                                                MessageDefinitions {

    private NetworkMetrics metrics;
    private ZoneSettings zoneLayout;

    private NetworkNode freeServer = null;

    private int peerCheck = 0;
    private boolean requestingBackup = false;


    public DefaultNetworkAuthority(NetworkMetrics m, ZoneSettings zs) {
	this.metrics = m;
	this.zoneLayout = zs;
    }





    public void monitorNetwork() {

	// Check if we are the only host on the network and should therefore
	// promote ourselves to a zone server
	if (metrics.zoneServiceEnabled() == false) {
	    peerCheck++;	
	    if ((metrics.getServerList().getLiveServerCount()==0) && 
		(peerCheck >= 10))
		startProactiveZoneService();
	}


	else {   // if we are currently providing zone services
	    
	    ensureBackupAvailable();
	    
	}
    }





    /**
     * Upgrade the local server to a zone server, assuming that there are
     * no other zone servers on the network.
     **/
    private void startProactiveZoneService() {
	Zone z1 = new Zone();
	Zone z2 = new Zone();
	Zone z3 = new Zone();

	zoneLayout.addPrimaryZone(z1, true);
	zoneLayout.addPrimaryZone(z2, true);
	zoneLayout.addPrimaryZone(z3, true);

	zoneLayout.setNextNeighbor(null);
	zoneLayout.setPreviousNeighbor(null);

	metrics.enableZoneService();
    }





    /**
     * Check that this server is currently being covered by active (alive)
     * primary and secondary backup servers.  If not, request as appropriate.
     **/
    private void ensureBackupAvailable() {
	Zone[] primaryZones = zoneLayout.getPrimaryZones();

	if (primaryZones.length == 0)   // not doing primary coverage
	    return;

	// We may assume that all zones under our control share the same
	// backup servers.  So we need only look at the info for the first
	// element in the list.
	NetworkNode backup1 = primaryZones[0].getFirstBackupServer();
	NetworkNode backup2 = primaryZones[0].getSecondBackupServer();
      

	// See if we need to request new backup services (only request one
	// at a time)
	ServerTracker sl = metrics.getServerList();
	if ((backup1 == null) || 
	    (sl.isServerAlive(backup1.getIPAddress()) == false)) {

	    if (requestingBackup == false) {
		NetworkNode backup = getFreeZoneServer();
		if (backup != null) {
		    PeerCommunicator.requestPrimaryBackup(primaryZones,backup);
		    requestingBackup = true;
		}
	    }
	}
	    

	else if ((backup2 == null) ||
		 (sl.isServerAlive(backup2.getIPAddress()) == false)) {

	    if (requestingBackup == false) {
		NetworkNode backup = getFreeZoneServer();
		if (backup != null) {
		    PeerCommunicator.requestSecondaryBackup(primaryZones, 
							    backup);
		    requestingBackup = true;
		}
	    }
	}
       
	else {
	    requestingBackup = false;
	}
    }





    /**
     * Given a CHIME server that wants to be placed under this server's
     * zone responsibility, this method handles that request.  Via 
     * internal criteria, it either a) issues a rejection to the CHIME
     * server or b) accepts the CHIME server and places it on the Street
     * under one of the local server's zones.
     *
     * @param chimeServer - identification information for a CHIME server
     *                      that has requested zone coverage from this
     *                      zone server
     **/
    public void handleChimeServerRequest(NetworkNode chimeServer) {
    }




    /**
     * Given a zone server that has lost contact with the rest of the
     * network, this method internally reconfigures the local server's
     * settings and zone responsibilities as deemed appropriate.
     *
     * @param zoneServer - identification information for a zone server
     *                     that has lost contact with the network, as
     *                     decided by the local system.
     **/
    public void handleDroppedZoneServer(NetworkNode zoneServer) {
    }



    /**
     * Given a CHIME server under the local server's zone responsibility that
     * has lost contact with the network (specifically, has lost contact
     * with the local server), this method reconfigures internal settings and
     * network setup as appropriate.
     *
     * @param chimeServer - identification information for a CHIME server
     *                      that has lost contact with the network, as
     *                      decided by the local system.
     **/
    public void handleDroppedChimeServer(NetworkNode chimeServer) {
    }





    /**
     * Returns a link to a zone server which is currently unoccupied (not
     * performing any zone duties).  If no such server exists, then this
     * method sends out a request to the network for a new server to
     * be instantiated (from a CHIME server) and returns null.  It will
     * return null until that server has been upgraded and is ready to go.
     **/
    private NetworkNode getFreeZoneServer() {

	if (freeServer != null) {
	    NetworkNode ret = freeServer;
	    freeServer = null;  // we assume that the method's caller will
	                        // utilize the server and make it no longer
	                        // free
	    return ret;

	}
	else {
	    PeerCommunicator.sendBroadcast(REQUEST_ZONE_SERVER_CANDIDATE);
	    return null;
	}
    }





    /**
     * If we're looking for a free zone server, then upgrade this chime
     * server.  Otherwise, ignore the zone service offer, since we already
     * have someone else we can use.
     **/
    public void handleZoneServiceOffer(NetworkNode chimeServer) {
	if (freeServer == null) {
	    PeerCommunicator.sendMessage(START_ZONE_SERVICE, chimeServer);
	    freeServer = chimeServer;
	}
    }




} // end DefaultNetworkAuthority class

