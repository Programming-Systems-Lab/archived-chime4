
package psl.chime4.zone;





/**
 * A data manager is one of the core functional components that make up
 * a zone server (the other component being a @see ZoneManager).  The data
 * manager holds functionality for defining and providing access to portions
 * of the Street within the zone server's zones.  This includes placing
 * CHIME servers on the Street, allowing users to move and communicate, etc.
 *
 * This class exclusively provides internal services to the zone server.
 * See the @see ZoneServer class definition for information about interfacing
 * a zone server to the "outside world".
 *
 * @author Gregory Estren (gce3@columbia.edu)
 * @version 1.0
 **/

public class DataManager {




    
    /**
     * Subscribes the given user to receive notifications about zone events.
     * The concept of a user is generic, it can be a real user (client) or
     * a CHIME server, zone server, or any other type of server.
     **/
    public void addUser(NetworkNode user) {
    }





    /** Unsubscribes the given user from zone event notifications. 
     *
     **/
    public void removeUser(NetworkNode user) {
    }
    
} // end DataManager class






