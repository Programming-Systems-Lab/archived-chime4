
package psl.chime4.server.zone.authority;

import psl.chime4.server.auth.NetworkNode;



public interface NetworkAuthority {


    public void monitorNetwork();

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
    public void handleChimeServerRequest(NetworkNode chimeServer);



    /**
     * Given a zone server that has lost contact with the rest of the
     * network, this method internally reconfigures the local server's
     * settings and zone responsibilities as deemed appropriate.
     *
     * @param zoneServer - identification information for a zone server
     *                     that has lost contact with the network, as
     *                     decided by the local system.
     **/
    public void handleDroppedZoneServer(NetworkNode zoneServer);



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
    public void handleDroppedChimeServer(NetworkNode chimeServer);


    /**
     * Given a CHIME server that is offering (available) to provide zone
     * services, react appropriately.
     **/
    public void handleZoneServiceOffer(NetworkNode chimeServer);

} // end NetworkAuthority interface





