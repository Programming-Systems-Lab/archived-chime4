
package psl.chime4.server.auth;





/**
 * Represents any uniquely accessible entity on a CHIME network.  This 
 * includes CHIME servers, zone server, authentication servers, and clients.
 * Any machine with its own IP address is a network node.
 *
 * This object only provides node identification information.  It does
 * not provide communication abilities.
 *
 * @author Gregory Estren (gce3@columbia.edu)
 * @version 1.0
 **/

public class NetworkNode {


    /** 
     * Internet hostname for this node
     **/
    private String hostname;


    /**
     * IP address for this node 
     **/
    private String ipaddress;


    /**
     * CHIME-level username for this node.  Usernames are expected to
     * be unique across the network (although this is a policy decision,
     * it is not built into the design
     **/
    private String username;


    /**
     * Authorization ticket for this node, if available.
     **/
    private AuthTicket authorization;





    /**
     * Creates a new network node.
     *
     * @param username - CHIME-level username that identifies this node.
     * @param auth     - authorization ticket for the node, or null if
     *                   no ticket is available
     **/
    public NetworkNode(String username, AuthTicket auth) {
	this.username = username;
	this.authorization = auth;
    }



    /**
     * Returns the authorization ticket for this node, or null if no
     * such ticket is available.
     **/
    public AuthTicket getAuthTicket() {
	return authorization;
    }



    /**
     * Sets the authorization ticket for this node.
     **/
    public void setAuthTicket(AuthTicket auth) {
	this.authorization = auth;
    }



    /**
     * Returns the CHIME-level network username for this node, or null
     * if no username has been set.
     **/
    public String getUsername() {
	return username;
    }



    /**
     * Sets the CHIME-level network username for this node.
     **/
    public void setUsername(String name) {
	this.username = name;
    }



    /** 
     * Returns the internet hostname for this node, or null if no hostname
     * has been set.
     **/
    public String getHostname() {
	return hostname;
    }



    /**
     * Sets the internet hostname for this node.
     **/
    public void setHostname(String name) {
	this.hostname = name;
    }



    /**
     * Returns the internet IP address for this node, or null if no address
     * has been set.
     **/
    public String getIPAddress() {
	return ipaddress;
    }



    /**
     * Sets the internet IP address for this node.
     **/
    public void setIPAddress(String address) {
	this.ipaddress = address;
    }





    /**
     * Returns the "current server", same as 127.0.0.1 (localhost)
     **/
    public static NetworkNode localServer() {
	return new NetworkNode("localhost", null);
    }


} // end NetworkNode class




