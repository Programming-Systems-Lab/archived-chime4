
package psl.chime4.server.zone.metrics;

import java.util.Hashtable;





/**
 * A NetworkMetrics class encapsulates a general container for recording
 * quantitative network statistics within the zone server.  These statistics
 * are useful for determining when network reorganization may be appropriate
 * due to excessively high or low usage, mis-configuration, etc.
 *
 * @author Gregory Estren (gce3@columbia.edu)
 * @version 1.0
 **/

public class NetworkMetrics {

    private Hashtable t;
    private ServerTracker serverList;

    private boolean zoneServiceEnabled = false;
   

    /**
     * Instantiate a new NetworkMetrics object with no metrics initially
     * recorded
     **/
    public NetworkMetrics() {
	t = new Hashtable();
	serverList = new ServerTracker();
    }


    public boolean zoneServiceEnabled() {
	return zoneServiceEnabled;
    }

    public void disableZoneService() {
	zoneServiceEnabled = false;
    }

    public void enableZoneService() {
	zoneServiceEnabled = true;
    }

    public ServerTracker getServerList() {
	return serverList;
    }


    /**
     * Add or update the specified metric.
     * 
     * @param name - metric variable name to set
     * @param value - integer value to set the metric to
     **/
    public void setMetric(String name, int value) {
	t.put(name, new Integer(value));
    }



    /**
     * Unset the specified metric.
     *
     * @param name - metric variable name
     **/
    public void clearMetric(String name) {
	t.remove(name);
    }



    /**
     * Retrieve the specified metric.
     *
     * @param name - metric variable name
     * @return numeric value for that metric, or -1 if no metric by the 
     *         specified name exists
     **/
    public int getMetric(String name) {
	Integer i = (Integer) t.get(name);
	if (i == null)
	    return -1;
	else
	    return i.intValue();
    }



} // end NetworkMetrics class





