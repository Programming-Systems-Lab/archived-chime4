
package psl.chime4.server.zone;

import psl.chime4.server.auth.NetworkNode;


public class PeerCommunicator {

    public static int TRANSFER_ZONE = 0;
    public static int REJECT_ZONE_TRANSFER = 1;
    public static int ACCEPT_ZONE_TRANSFER = 2;
    public static int START_ZONE_SERVICE = 3;
    public static int STOP_ZONE_SERVICE = 4;
    public static int REQUEST_PRIMARY_BACKUP = 5;
    public static int REQUEST_SECONDARY_BACKUP = 6;
    public static int OFFER_PRIMARY_BACKUP = 7;
    public static int OFFER_SECONDARY_BACKUP = 8;
    public static int REJECT_ZONE_BACKUP = 9;
    public static int ACCEPT_ZONE_BACKUP = 10;

    public static void sendMessage(int message, NetworkNode zoneServer) {
    }



    public static void initiateZoneTransfer
	(Zone[] list, NetworkNode zoneServer, NetworkNode newPrevNeighbor,
	 NetworkNode newNextNeighbor) {
    }



    public static void requestPrimaryBackup(Zone[] list, NetworkNode zs) {
    }


    public static void requestSecondaryBackup(Zone[] list, NetworkNode zs) {
    }


} // end PeerCommunicator class
