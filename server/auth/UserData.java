
package psl.chime4.server.auth;


import psl.chime4.server.data.Persistent;




/**
 * Encapsulates a user registration record.  A user registration consists
 * of a string-based username, password, public key, and set of allowed
 * access modes.
 *
 * @author Greg Estren (gce3@columbia.edu)
 * @version 1.0
 **/

public class UserData implements Persistent {

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
    private byte[] publicKey;
    private String userID;
    private String password;
    private int validServices;





    /**
     * Creates a new blank user data entry
     **/
    public UserData() {
	persistenceID = -1;
	publicKey = null;
	userID = null;
	password = null;
	validServices = 0;
    }



    /**
     * Sets the specified public key for this user entry
     **/
    public void setPublicKey(byte[] key) {
	publicKey = key;
    }



    /**
     * Returns the user entry's public key listing, or null if no such
     * value exists
     **/
    public byte[] getPublicKey() {
	return publicKey;
    }



    /**
     * Sets the unique user id for this entry
     **/
    public void setUserID(String id) {
	userID = id;
    }



    /**
     * Returns the user id for this entry, or null if no such value exists
     **/
    public String getUserID() {
	return userID;
    }



    /**
     * Sets the user login password for this entry.
     **/
    public void setPassword(String pwd) {
	password = pwd;
    }



    /**
     * Returns the user login password for this entry, or null if no such
     * value exists
     **/
    public String getPassword() {
	return password;
    }



    /**
     * Returns an enumeration of all valid network services that this
     * user is allowed to provide.  If this method returns R, then for
     * any given service S, that service is allowed if R & S is true.
     **/
    public int getValidServices() {
	return validServices;
    }



    /**
     * Adds a specified service to the set of valid network services that
     * this user can provide.  
     **/
    public void addValidService(int service) {
	validServices |= service;
    }



    /**
     * Removes the specified service from the set of valid network services
     * that this user can provide.
     **/
    public void removeValidService(int service) {
	if (hasValidService(service))
	    validServices ^= service;
    }



    /**
     * Removes all valid network services for this user
     **/
    public void removeAllServices() {
	validServices = 0;
    }



    /**
     * Adds all network services to this user entry.
     **/
    public void addAllServices() {
	validServices |= CLIENT;
	validServices |= CHIMESERVER;
	validServices |= ZONESERVER;
	validServices |= AUTHSERVER;
    }



    /**
     * Returns true if this user is allowed to provide the specified service,
     * false otherwise
     **/
    public boolean hasValidService(int service) {
	return ((validServices & service) != 0);
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


    
} // end UserData class
