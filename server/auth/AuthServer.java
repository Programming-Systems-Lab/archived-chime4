
package psl.chime4.auth;



import java.util.Vector;
import java.util.Hashtable;




/**
 * Encapsulates the authentication server component of a broader CHIME server.
 * Any CHIME server can provide authentication services.  These services
 * may be used for local of network-level authentication, depending on the
 * organizational trust structure for the network.  
 *
 * The authentication server accepts user logins, where a user may be any
 * type of entity that has a login/password combination (clients, 
 * CHIME servers, zone servers, auth servers, etc.).  It issues encrypted
 * tickets that verify a system's authority and access privileges.  These
 * tickets may be distributed around the network and independently verified
 * without needing to make queries to the authentication server.
 *
 * @author Greg Estren (gce3@columbia.edu)
 * @version 1.0
 **/

public class AuthServer {


    /**
     * List of other authentication servers on the network trusted by this
     * one
     **/
    private Vector trustedPeers;

    private Hashtable userList;

    private byte[] privateKey = null;
    private byte[] publicKey = null;



    public AuthServer() {
	trustedPeers = new Vector();
	userList = new Hashtable();
    }





    /**
     * Starts a new authentication service on the current CHIME server.  This
     * service runs in its own thread.
     **/
    public void startAuthServer() {
    }





    /**
     * Stops authentication service on this CHIME server, after closing down
     * responsibilities as appropriate
     **/
    public void stopAuthServer() {
    }





    /**
     * Authenticates a given network entity, identified by the specified
     * user ID.  
     *
     * @param userid - network-level identify of the specified entity
     * @param authCode - user's "proof" of authenticity.  This should
     *                   be the user's login password encrypted with
     *                   the generateAuthCode method in @see AuthUtil
     *
     * @return authentication ticket for the specified user
     * @exception AuthFailedException if the user could not be successfully
     *            authenticated with the given information.
     **/
    public AuthTicket authenticateUser(String userid, byte[] authCode)
	throws AuthFailedException {

	Hashtable userInfo = (Hashtable) userList.get(userid);
	if (userInfo == null)
	    throw new AuthFailedException();
	else {
	    String password = (String) userInfo.get("password");
	    if (password.equals(authCode)) {
		String ticketMsg = "good";
		byte[] ciphertext = AuthUtil.encrypt(ticketMsg, privateKey);
		AuthTicket ticket = new AuthTicket(NetworkNode.localServer(),
						   publicKey, ciphertext);
		return ticket;
		
	    }
	    else // authentication failed due to incorrect auth code
		throw new AuthFailedException();
	}
    }





    /**
     * Main method of the authentication server.  Runs continuously listening
     * for network events and issues appropriate responses / takes appropriate
     * action as a result
     **/
    private void processNetworkEvents() {
    }





    /**
     * Propagates a newly issued ticket to all fellow (trusted) authentication
     * servers on the network, so that they're aware of the user's 
     * authentication as well.
     **/
    private void propagateAuthTicket(AuthTicket ticket) {
    }





    /**
     * Receives an authentication ticket issued by a fellow (trusted)
     * authentication server.  This notifies the local server about the
     * user's ability to be on the system, presense of new trusted 
     * authentication servers, etc.
     **/
    private void receiveTicket(AuthTicket ticket, NetworkNode authServer) {
    }





    /**
     * Queries the presence of other authentication servers on the network.
     **/
    private void queryPeers() {
    }





    /**
     * Broadcasts this authentication server's presence to all other
     * auth servers on the network.
     **/
    private void broadcastPresence() {
	// Send "hi" + authCode (encrypted with local private key)
    }





    /**
     * Given the specified peer that has broadcast its presence as an
     * authentication server, internally stores that peer as a trusted
     * server of ignores it (chooses not to trust it)
     *
     * Note that authCode is encrypted with the remote server's private
     * key, not the local server's key.
     **/
    private void handlePeerBroadcast(NetworkNode peer, byte[] authCode) {
	try {
	    authenticateUser(peer.getUsername(), authCode);
	    trustedPeers.addElement(peer);
	}
	catch (AuthFailedException e) {
	    // if authentication fails, simply don't add the user to
	    // the internal trusted list.  This requires no action.
	}
    }





    /**
     * Returns a list of all servers currently on the network trusted by the 
     * local machine to provide authentication services.
     **/
    private NetworkNode[] getTrustedAuthServers() {
	return (NetworkNode[]) trustedPeers.toArray();
    }



} // end AuthServer class

