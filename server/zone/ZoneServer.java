
package psl.chime4.zone;

import java.io.*;
import psl.chime4.server.auth.AuthTicket;
import psl.chime4.server.auth.NetworkNode;



/**
 * Encapsulates the "zone server" component of a broader CHIME server.  Also
 * acts as the managing component that directs functionality for other
 * objects related to zone service.  
 *
 * Note that any CHIME server can (dynamically) provide zone server
 * functionality at any time.  This functionality operates entirely 
 * independently of CHIME server responsibilities, and for all intensive
 * purposes can be thought of as a fully distinct conceptual component.
 *
 * @author Gregory Estren (gce3@columbia.edu)
 * @version 1.0
 **/

public class ZoneServer {


    private String username = null;   // network-level user id
    private AuthTicket localAuth = null;  // local authentication ticket

    private Thread serverThread;
    private boolean runThread;





    /**
     * Starts a new zone server as its own Java process
     **/
    public static void main(String[] argv) {

	// Load up and run our zone server
	ZoneServer server = new ZoneServer();
	server.startZoneServer();
	System.out.println("Zone server running.");


	// Stop the server when the keyboard user presses enter
	System.out.println("Press [enter] to stop.");
	BufferedReader in = new BufferedReader(new 
	    InputStreamReader(System.in));
	try {
	    in.readLine();
	} 
	catch (IOException e) {
	    e.printStackTrace();
	}

	server.stopZoneServer();
    }





    /**
     * Creates a new zone server. Duh!
     **/
    public ZoneServer() {
	serverThread = null;
	runThread = true;
    }





    /**
     * Starts zone service on the local machine.  A CHIME server calls
     * this method when it chooses to become a zone server.  It is 
     * assumed that the CHIME server is permitted and expected to provide
     * this service from the rest of the network.
     **/
    public void startZoneServer() {
	serverThread = new Thread() {
		public void run() {
		    processNetworkEvents();
		}
	    };
	serverThread.start();
    }





    /**
     * Stops zone service on the local machine.  A CHIME server calls this
     * method when it can no longer perform zone server responsibilities or
     * when it is ordered to halt such responsibilities from the network.
     * This method will first unload all zone responsibilities (users,
     * CHIME worlds, etc.) to its peers before shutting down service.
     **/
    public void stopZoneServer() {
	runThread = false;
    }





    /**
     * Listens for incoming network events and calls appropriate methods,
     * issues appropriate responses, etc. as necessary.  This is the
     * zone server's "main" method.  It runs continously and directs the
     * general behavior of the server.  This method directs one of two
     * threads that run a zone server (see the monitorNetwork method for
     * information on the other thread).
     **/
    private void processNetworkEvents() {
	while (runThread) {
	    try {new Thread().sleep(1000);} catch (Exception e) {}
	}
    }





    /**
     * Continously analyzes the zone server's network load as well as 
     * load across its peers.  Based on this information, adjusts
     * responsibilities as deemed appropriate.  This may involve
     * adding new zone servers to the network, removing existing zone
     * servers, accepting extra zone responsibility, or offsetting current
     * zone responsibility.  This is the zone server's "main method" for
     * network balancing, and runs in its own thread.
     **/
    private void monitorNetwork() {
    }





    /**
     * Searches across the network for current CHIME servers that are
     * eligible to become zone servers, selects one such server, 
     * promotes it to a zone server, and assigns zone responsibility to
     * the new server.  This method assumes that the need for such a server
     * has already been identified elsewhere and that the local server has
     * responsibility for fulfilling that need.
     **/
    private void addNewZoneServer() {
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
    private void handleChimeServerRequest(NetworkNode chimeServer) {
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
    private void handleDroppedZoneServer(NetworkNode zoneServer) {
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
    private void handleDroppedChimeServer(NetworkNode chimeServer) {
    }




} // end ZoneServer class


