
package psl.chime4.server.zone;

import java.io.*;
import java.util.*;

import psl.chime4.server.di.*;
import psl.chime4.server.auth.*;
import psl.chime4.server.util.StringParser;
import psl.chime4.server.zone.authority.*;
import psl.chime4.server.zone.metrics.*;
import psl.chime4.server.zone.schemes.*;





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

public class ZoneServer implements DIEventReceiver, MessageDefinitions {


    private String username = null;   // network-level user id
    private AuthTicket localAuth = null;  // local authentication ticket

    private Thread serverThread;
    private boolean runThread;

    private ZoneSettings zoneLayout;
    private DataManager dataManager;
    private ZoneManager zoneManager;

    private ZoneAuthority zoneControl;
    private BackupAuthority backupControl;
    private NetworkAuthority networkControl;
    private NetworkMetrics metrics;

    private DirectoryInterface di;  // network communication interface




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

	metrics = new NetworkMetrics();

	// When we first start up we do NOT perform any zone services.  We
	// enable service ONLY if 
	//     a) requested to by another known zone server
	//     b) we can not find any other zone servers on the network
	metrics.disableZoneService();

	zoneLayout = new ZoneSettings();
	dataManager = new DataManager();
	zoneManager = new ZoneManager(zoneLayout, dataManager);

	di = null; // INTEGRATE: NEED TO SET DIRECTORY INTERFACE HERE

	zoneControl = new DefaultZoneAuthority(zoneManager, di);
	backupControl = new DefaultBackupAuthority(zoneManager,dataManager,di);
	networkControl = new DefaultNetworkAuthority(metrics, zoneLayout);

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
		    runMainThread();
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
	metrics.disableZoneService();
    }





    /**
     * Continously analyzes the zone server's network load as well as 
     * load across its peers.  Based on this information, adjusts
     * responsibilities as deemed appropriate.  This may involve
     * adding new zone servers to the network, removing existing zone
     * servers, accepting extra zone responsibility, or offsetting current
     * zone responsibility.  This is the zone server's "main method" for
     * network balancing, and runs in its own thread.
     *
     * If we're providing zone services (i.e. the zone server is "enabled"),
     * we also send an "alive" update periodically to announce our presence
     * to the network
     **/
    private void runMainThread() {
	while (runThread) {

	    if (metrics.zoneServiceEnabled()) {
		networkControl.monitorNetwork();
		PeerCommunicator.sendBroadcast(SEND_ALIVE_UPDATE);
	    }

	    try {new Thread().sleep(1000);} catch (Exception e) {}
	}
    }





    public void receiveMessage(DIMessage msg) {

	String msgBody = msg.getBody().toString();
	Hashtable data = StringParser.parseKeyValueString(msgBody);
	NetworkNode source = new NetworkNode(msg.getSender().toString(), null);


	/**
	 *  Locally handled stuff
	 **/
	if (msg.getType().equals(STOP_ZONE_SERVICE)) 
	    stopZoneServer();
	else if (msg.getType().equals(SEND_ALIVE_UPDATE))
	    metrics.getServerList().setLiveServer(msg.getSender().toString());


	/**
	 * Handled by the network control authority
	 **/
	else if (msg.getType().equals(REQUEST_ZONE_SERVER_CANDIDATE)) 
	    ; // MUST HAVE SOME WAY TO SELECT FROM LIST OF CHIME SERVERS
	// UNDER OUR DOMAIN AND RETURN ONE OF THOSE CHIME SERVERS TO THE
	// REQUESTING ZS
	else if (msg.getType().equals(OFFER_ZONE_SERVICE)) {
	    NetworkNode cs = (NetworkNode) retrieveObject(data, CHIME_SERVER);
	    networkControl.handleZoneServiceOffer(cs);
	}


	/**
         *  Handled by zone control authority
         **/
	else if (msg.getType().equals(TRANSFER_ZONE)) { 
	    Zone[] list = (Zone[]) retrieveObject(data, ZONE_LIST);
	    NetworkNode pn=(NetworkNode) retrieveObject(data, 
							SET_PREVIOUS_NEIGHBOR);
	    NetworkNode nn = (NetworkNode) retrieveObject(data, 
							  SET_NEXT_NEIGHBOR);
	    zoneControl.handleZoneTransferRequest(list, source, pn, nn);
	}
	else if (msg.getType().equals(REJECT_ZONE_TRANSFER)) {
	    Zone[] list = (Zone[]) retrieveObject(data, ZONE_LIST);
	    zoneControl.handleZoneTransferRejection(list, source);
	}
	else if (msg.getType().equals(ACCEPT_ZONE_TRANSFER)) {
	    Zone[] list = (Zone[]) retrieveObject(data, ZONE_LIST);
	    zoneControl.handleZoneTransferAcceptance(list, source);
	}
	

	/**
         *  Handled by backup control authority
	 **/
	else if (msg.getType().equals(REQUEST_PRIMARY_BACKUP)) {
	    Zone[] list = (Zone[]) retrieveObject(data, ZONE_LIST);
	    backupControl.handlePrimaryBackupRequest(list, source);
	}
	else if (msg.getType().equals(REQUEST_SECONDARY_BACKUP)) {
	    Zone[] list = (Zone[]) retrieveObject(data, ZONE_LIST);
	    backupControl.handleSecondaryBackupRequest(list, source);
	}
	else if (msg.getType().equals(OFFER_PRIMARY_BACKUP)) {
	    Zone[] list = (Zone[]) retrieveObject(data, ZONE_LIST);
	    backupControl.handlePrimaryBackupOffer(list, source);
	}
	else if (msg.getType().equals(OFFER_SECONDARY_BACKUP)) {
	    Zone[] list = (Zone[]) retrieveObject(data, ZONE_LIST);
	    backupControl.handleSecondaryBackupOffer(list, source);
	}
	else if (msg.getType().equals(REJECT_ZONE_BACKUP)) {
	    Zone[] list = (Zone[]) retrieveObject(data, ZONE_LIST);
	    backupControl.handleBackupRejection(list, source);
	}
	else if (msg.getType().equals(ACCEPT_ZONE_BACKUP)) {
	    Zone[] list = (Zone[]) retrieveObject(data, ZONE_LIST);
	    backupControl.handleBackupAcceptance(list, source);
	}

        //    - anything data manager related also
    }





    /** Nothing needs to be done here --> interface requirement **/
    public void receiveEvent(DIEvent event) {
    }





    /** Nothing needs to be done here --> interface requirement **/
    public void receiveResult(DIHost result) {
    }





    /**
     * Given a serialized object stored in a hashtable, retrieves that
     * object, deserializes it, and returns the re-instantiated object
     *
     * @param h - hashtable consisting of string-based serialized objects
     * @param serialized - hash key for the desired object
     **/
    private static Object retrieveObject(Hashtable h, String serialized) {
	try {
	    serialized = (String) h.get(serialized);
	    ByteArrayInputStream stream;
	    stream = new ByteArrayInputStream(serialized.getBytes());
	    ObjectInputStream in = new ObjectInputStream(stream);
	    Object o = in.readObject();
	    in.close();
	    return o;
	}
	catch (Exception e) {
	    return null;
	}
    }





} // end ZoneServer class

