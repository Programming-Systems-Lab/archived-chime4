
package psl.chime4.server.auth;





/**
 * This exception is thrown when authentication attempts fail.  
 *
 * @author Greg Estren (gce3@columbia.edu)
 * @version 1.0
 **/

public class AuthFailedException extends Exception {
    

    private String userID = null;
    private String authID = null;


    public AuthFailedException() {
	super("User Authentication failed.");
    }


    public AuthFailedException(String user, String auth) {
	super("Authentication failed for user " + user + " from " +
		   "authentication source " + auth);

	userID = user;
	authID = auth;

    }



    public String getUserID() {
	return userID;
    }


    public String getAuthID() {
	return authID;
    }
    
} // end AuthFailedException
