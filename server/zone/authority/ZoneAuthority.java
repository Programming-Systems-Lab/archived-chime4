
package psl.chime4.server.zone.authority;

import psl.chime4.server.auth.NetworkNode;
import psl.chime4.server.zone.Zone;



public interface ZoneAuthority {

    public void handleZoneTransferRequest(Zone[] list, NetworkNode source,
					  NetworkNode newPrevNeighbor,
					  NetworkNode newNextNeighbor);


    public void handleZoneTransferRejection(Zone[] list, NetworkNode source);


    public void handleZoneTransferAcceptance(Zone[] list, NetworkNode source);



} // end ZoneAuthority interface
