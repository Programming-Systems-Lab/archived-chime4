package psl.chime4.server.cebs.siena;

import psl.chime4.server.cebs.Event;

import siena.Notification;
import siena.AttributeValue;

/**
 * This class wraps Siena Notification objects to make them look like CEBS
 * Event objects.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
class SienaEvent extends Event
{
    /** underling Siena notification **/
    private Notification notif;
    
    /**
     * Construct a new SienaEvent to wrap a Siena notification object.
     *
     * @param notif Siena notification object to adapt
     * @throws IllegalArgumentException
     *         if <code>notif</code> is <code>null</code>
     **/
    SienaEvent(Notification notif)
    {
        // check for null
        if ( notif == null )
        {
            String msg = "notif cannot be null";
            throw new IllegalArgumentException(msg);
        }
        
        this.notif = notif;
    }
    
    /**
     * Construct an empty SienaEvent object.
     **/
    SienaEvent()
    {
        notif = new Notification();
    }
    
    public boolean containsKey(String key)
    {
        return (notif.getAttribute(key) == null);
    }
    
    public void clear()
    {
        notif.clear();
    }
    
    public int getInteger(String key)
    {
        AttributeValue val = notif.getAttribute(key);
        return val.intValue();
    }
    
    public long getLong(String key)
    {
        AttributeValue val = notif.getAttribute(key);
        return val.longValue();
    }
    
    public double getDouble(String key)
    {
        AttributeValue val = notif.getAttribute(key);
        return val.doubleValue();
    }
    
    public String getString(String key)
    {
        AttributeValue val = notif.getAttribute(key);
        return val.stringValue();
    }
    
    public byte[] getByteArray(String key)
    {
        AttributeValue val = notif.getAttribute(key);
        return val.byteArrayValue();
    }
    
    public void put(String key, int val)
    {
        notif.putAttribute(key, val);
    }
    
    public void put(String key, long val)
    {
        notif.putAttribute(key, val);
    }
    
    public void put(String key, double val)
    {
        notif.putAttribute(key, val);
    }
    
    public void put(String key, String val)
    {
        notif.putAttribute(key, val);
    }
    
    public void put(String key, byte[] val)
    {
        notif.putAttribute(key, val);
    }
    
    public void remove(String key)
    {
        notif.putAttribute(key, (AttributeValue) null);
    }
    
    /**
     * Get the underlying Siena notification.
     *
     * @return underlying Siena Notification object
     **/
    Notification getUnderlyingNotification()
    {
        return notif;
    }
}