
package psl.chime4.server.zone.schemes;

import psl.chime4.server.auth.NetworkNode;
import psl.chime4.server.di.*;
import psl.chime4.server.zone.*;
import psl.chime4.server.zone.authority.ZoneChangeAuthority;





public class DefaultZoneChangeAuthority implements ZoneChangeAuthority,
						   MessageDefinitions {

    
    private ZoneManager zoneManager;
    private DirectoryInterface di;



    public DefaultZoneChangeAuthority(ZoneManager zs, DirectoryInterface di) {
	this.zoneManager = zs;
	this.di = di;
    }





    public void handleZoneTransferRequest(Zone[] list, NetworkNode source,
					  NetworkNode newPrevNeighbor,
					  NetworkNode newNextNeighbor) {

	// automatically accept the request
	zoneManager.handleZoneTransferRequest(list, source, newPrevNeighbor,
					      newNextNeighbor);
    }




    public void handleZoneTransferRejection(Zone[] list, NetworkNode source) {
    }




    public void handleZoneTransferAcceptance(Zone[] list, NetworkNode source) {
	zoneManager.completeZoneTransfer(list, source);
    }





}   // end DefaultZoneChangeAuthority class

