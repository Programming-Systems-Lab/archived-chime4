
package psl.chime4.server.zone;

import psl.chime4.server.auth.NetworkNode;


public class PeerCommunicator {

    public static int TRANSFER_ZONE = 0;
    public static int REJECT_ZONE_TRANSFER = 1;
    public static int ACCEPT_ZONE_TRANSFER = 2;
    public static int START_ZONE_SERVICE = 3;
    public static int STOP_ZONE_SERVICE = 4;

    public static void sendMessage(int message, NetworkNode zoneServer) {
    }



    public static void initiateZoneTransfer
	(Zone[] list, NetworkNode zoneServer, NetworkNode newPrevNeighbor,
	 NetworkNode newNextNeighbor) {
    }



} // end PeerCommunicator class
