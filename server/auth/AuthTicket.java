
package psl.chime4.auth;


import java.util.Date;





/**
 * Encapsulates a "ticket" that holds an entity's authentication information
 * as specified by an authentication server.  Information within the ticket
 * is guaranteed to be as secure and trustworthy as the issuing authentication
 * server, no matter who is in possession of the ticket.  Any party is able
 * to read the ticket, but only the specified authentication server can
 * issue or change the ticket.
 *
 * In order for clients to ensure ticket security, they should have some
 * means for independently obtaining the authority's decryption key (which
 * will be its public key in a public key system) in a trusted manner.  So
 * long as this key itself is trusted, all data can be trusted as well.
 *
 * @author Greg Estren (gce3@columbia.edu)
 * @version 1.0
 **/

public class AuthTicket {

    /**
     * Valid services.  Any given network node may be authorized to provide
     * any combination of these services.  Use the @see isAuthorized
     * method to validate services.
     **/
    public static int CLIENT = 0;
    public static int CHIMESERVER = 1;
    public static int ZONESERVER = 2;
    public static int AUTHSERVER = 3;



    private NetworkNode authority;
    private byte[] decryptionKey;
    private byte[] ciphertext;
    private String plaintext;

    private String userID;
    private String authorityID;
    private Date startDate;
    private Date expireDate;
    private boolean[] validServices = new boolean[10];





    /**
     * Creates a new ticket with the specified public key of the specified
     * network system.  
     **/
    public AuthTicket(NetworkNode authority, byte[] authKey, byte[] data) {
	this.authority = authority;
	this.decryptionKey = authKey;
	this.ciphertext = data;

	decryptData();
    }





    /**
     * Decrypts ciphertext with current settings and sets all relevant
     * data
     **/
    private void decryptData() {

	plaintext = AuthUtil.decrypt(ciphertext, decryptionKey);

	userID = null;
	authorityID = null;
	startDate = null;
	expireDate = null;
	validServices = null;
	for (int i=0; i < validServices.length; i++)
	    validServices[i] = false;

	if (plaintext == null)   // decryption error, unset all data
	    return;
	
	else { 
	    // given plaintext, set all data appropriately
	}

    }





    /**
     * Sets the public key of the ticket's issuing authority.  This information
     * is necessary to decode the ticket.
     **/
    public void setAuthKey(byte[] authKey) {
	this.decryptionKey = authKey;
	decryptData();
    }





    /**
     * Returns the network-level user identification (username) that this
     * ticket applies to.  Returns null if information cannot be 
     * accessed (if the required decryption key is missing or wrong).
     **/
    public String getUserID() {
	return userID;
    }





    /**
     * Returns the date and time that this ticket's period of validity 
     * starts.  Returns null if this information cannot be accessed.
     **/
    public Date getStartDate() {
	return startDate;
    }





    /**
     * Returns the date and time that this ticket's period of validity
     * ends.  Returns null if this information cannot be accessed.
     **/
    public Date getExpirationDate() {
	return expireDate;
    }





    /**
     * Returns true if the user for this ticket is authorized to perform
     * the specified network role. Returns false if the user is unauthorized
     * for that role.  Returns false if this information cannot be
     * accessed.
     *
     * @param service - one of AuthTicket.CLIENT, AuthTicket.CHIMESERVER,
     *                  AuthTicket.ZONESERVER, or AuthTicket.AUTHSERVER
     **/
    public boolean isAuthorized(int service) {
	if (service > validServices.length-1)
	    return false;
	else if (service < 0)
	    return false;
	else
	    return validServices[service];
    }





    /**
     * Returns the user identification of the issuing authority, as encoded
     * within the ticket.  Returns null if this information cannot be
     * accessed.
     **/
    public String getAuthorityUserID() {
	return authorityID;
    }



} // end AuthTicket class












