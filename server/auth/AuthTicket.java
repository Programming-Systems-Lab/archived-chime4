
package psl.chime4.server.auth;


import java.util.Date;
import psl.chime4.server.data.Persistent;




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

public class AuthTicket implements Persistent {
    
    /**
     * Valid services.  Any given network node may be authorized to provide
     * any combination of these services.  Use the @see isAuthorized
     * method to validate services.
     **/
    public static int CLIENT = 1;
    public static int CHIMESERVER = 2;
    public static int ZONESERVER = 4;
    public static int AUTHSERVER = 8;
    
    private int persistenceID;
    private NetworkNode authority;
    private byte[] decryptionKey;
    private byte[] ciphertext;
    private String plaintext;
    
    private String userID;
    private String authorityID;
    private Date startDate;
    private Date expireDate;
    private int validServices;
	
	
	
	
	
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
	validServices = 0;
	
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
	return ((validServices & service) != 0);
    }
    
    
    
    
    
    /**
     * Returns the user identification of the issuing authority, as encoded
     * within the ticket.  Returns null if this information cannot be
     * accessed.
     **/
    public String getAuthorityUserID() {
	return authorityID;
    }




    
    /**
     * Retrieves the unique ID that serves as a key to this object.
     *
     * @return this object's ID
     */
    public int getPersistenceID() {
	return persistenceID;
    }




    
    /**
     * Assigns the unique ID that serves as a key to this object.
     *
     * @param iID this object's ID
     */
    public void setPersistenceID(int iID) {
	persistenceID = iID;
    }




    
} // end AuthTicket class
