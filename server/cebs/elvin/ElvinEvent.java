package psl.chime4.server.cebs.elvin;

import psl.chime4.server.cebs.Event;

import org.elvin.je4.Notification;

/**
 * This class wraps Elvin notifications to make them look like Events.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ElvinEvent extends Event
{
    /** the notification object being wrapped **/
    private Notification notif;
    
    /**
     * Construct a new ElvinEvent to wrap a Notification object. This makes
     * Elvin Notification objects look like Events.
     *
     * @param notif Notification object to be adapted
     * @throws IllegalArgumentException
     *         if <code>notif</code> is <code>null</code>
     **/
    public ElvinEvent(Notification notif)
    {
        // check for null
        if (notif == null)
        {
            String msg = "notification cannot be null";
            throw new IllegalArgumentException(msg);
        }
        
        this.notif = notif;
    }
    
    /**
     * Construct a new empty Event object.
     **/
    public ElvinEvent()
    {
        notif = new Notification();
    }
    
    public boolean containsKey(String key)
    {
        return notif.containsKey(key);
    }
    
    public void clear()
    {
        // though the javadoc API defines such a method, there seems to be
        // no org.elvin.je4.Notification#clear() method. For now we just 
        // make a new object
        // notif.clear();
        notif = new Notification();
    }
    
    public int getInteger(String key)
    {
        return notif.getInt(key);
    }
    
    public long getLong(String key)
    {
        return notif.getLong(key);
    }
    
    public double getDouble(String key)
    {
        return notif.getDouble(key);
    }
    
    public String getString(String key)
    {
        return notif.getString(key);
    }
    
    public byte[] getByteArray(String key)
    {
        // though the javadoc API declares such a method, it doesn't seem to
        // exist in the api
        // return notif.getBytes(key);
        return (byte[]) notif.get(key);
    }
    
    public void put(String key, int val)
    {
        notif.put(key, val);
    }
    
    public void put(String key, long val)
    {
        notif.put(key, val);
    }
    
    public void put(String key, double val)
    {
        notif.put(key, val);
    }
    
    public void put(String key, String val)
    {
        notif.put(key, val);
    }
    
    public void put(String key, byte[] val)
    {
        notif.put(key, val);
    }
    
    public void remove(String key)
    {
        notif.remove(key);
    }
    
    /**
     * Get the underlying Notification object.
     *
     * @return underlying Notification object.
     **/
    public Notification getUnderlyingNotification()
    {
        return notif;
    }
}