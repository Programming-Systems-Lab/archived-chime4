
package psl.chime4.server.zone.metrics;

import java.util.Hashtable;





/**
 * A NetworkMetrics class encapsulates a general container for recording
 * quantitative network statistics within the zone server.  These statistics
 * are useful for determining when network reorganization may be appropriate
 * due to excessively high or low usage, mis-configuration, etc.
 *
 * This class maintains no knowledge about what its data means.  It is up
 * to other classes to manipulate and interpret data correctly, as well as
 * to know what types of data are actually available.  Note that all
 * metric data is numeric.
 *
 * @author Gregory Estren (gce3@columbia.edu)
 * @version 1.0
 **/

public class NetworkMetrics {

    private Hashtable t;


    /**
     * Instantiate a new NetworkMetrics object with no metrics initially
     * recorded
     **/
    public NetworkMetrics() {
	t = new Hashtable();
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
     * @return numeric value for that metric, or 0 if no metric by the 
     *         specified name exists
     **/
    public int getMetric(String name) {
	Integer i = (Integer) t.get(name);
	if (i == null)
	    return 0;
	else
	    return i.intValue();
    }



} // end NetworkMetrics class












