
package psl.chime4.server.zone;

import java.io.*;
import java.util.Hashtable;

import psl.chime4.server.auth.NetworkNode;
import psl.chime4.server.di.*;
import psl.chime4.server.util.StringParser;


public class PeerCommunicator implements MessageDefinitions {



    public static void sendMessage(String message, NetworkNode zoneServer) {
	DirectoryInterface.communicate(new DIType(zoneServer.getHostname()),
				       new DIType(message),
				       new DIMessageBody(message));
    }





    public static void initiateZoneTransfer
	(Zone[] list, NetworkNode zoneServer, NetworkNode newPrevNeighbor,
	 NetworkNode newNextNeighbor) {

	Hashtable data = new Hashtable();

	data.put(ZONE_LIST, serializeObject(list));
	data.put(SET_PREVIOUS_NEIGHBOR, serializeObject(newPrevNeighbor));
	data.put(SET_NEXT_NEIGHBOR, serializeObject(newNextNeighbor));

	String message = StringParser.generateKeyValueString(data);
	DirectoryInterface.communicate(new DIType(zoneServer.getHostname()),
				       new DIType(TRANSFER_ZONE),
				       new DIMessageBody(message));
    }





    public static void requestPrimaryBackup(Zone[] list, NetworkNode zs) {
	Hashtable data = new Hashtable();
		
	data.put(ZONE_LIST, serializeObject(list));

	String message = StringParser.generateKeyValueString(data);
	DirectoryInterface.communicate(new DIType(zs.getHostname()),
				       new DIType(REQUEST_PRIMARY_BACKUP),
				       new DIMessageBody(message));
    }





    public static void requestSecondaryBackup(Zone[] list, NetworkNode zs) {
	Hashtable data = new Hashtable();
		
	data.put(ZONE_LIST, serializeObject(list));

	String message = StringParser.generateKeyValueString(data);
	DirectoryInterface.communicate(new DIType(zs.getHostname()),
				       new DIType(REQUEST_SECONDARY_BACKUP),
				       new DIMessageBody(message));
    }





    private static String serializeObject(Object o) {
	ByteArrayOutputStream stream;
	ObjectOutputStream out;
	
	try {
	    stream = new ByteArrayOutputStream();
	    out = new ObjectOutputStream(stream);
	    out.writeObject(o);
	    out.flush();
	    
	    return stream.toString();
	}
	catch (IOException e) {
	    return null;
	}
    }





} // end PeerCommunicator class

