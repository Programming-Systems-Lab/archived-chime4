
package psl.chime4.server.zone.metrics;

import java.util.*;


public class ServerTracker {

    
    private Hashtable liveServers;

    // number of seconds since a last "alive" status has been received for
    // a server to no longer be considered alive
    private static int TIME_THRESHOLD = 10;
    
    public ServerTracker() {
	liveServers = new Hashtable();
    }


    /**
     * When an "I'm alive" message is received from a server,
     * this method should be called to update its live status.
     **/
    public void setLiveServer(String uniqueid) {
	liveServers.put(uniqueid, new Date());
    }



    public int getLiveServerCount() {
	purgeServerList();
	return liveServers.size();
    }



    public boolean isServerAlive(String uniqueid) {
	purgeServerList();
	return liveServers.contains(uniqueid);
    }


    private void purgeServerList() {
	Date now = new Date();

	for (Enumeration e = liveServers.keys(); e.hasMoreElements();) {
	    String id = (String) e.nextElement();
	    Date updateTime = (Date) liveServers.get(id);

	    if ((now.getTime() - updateTime.getTime())/1000 > TIME_THRESHOLD)
		liveServers.remove(id);
	}	
    }




} // end ServerTracker class
