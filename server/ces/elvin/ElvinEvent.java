package psl.chime4.server.ces.elvin;

import psl.chime4.server.ces.Event;

import org.elvin.je4.Notification;

/**
 * Makes an Elvin notification look like a CES Event.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
class ElvinEvent extends Event
{
   /** notification object being wrapped **/
   private Notification notif;
   
   /**
    * Construct a new ElvinEvent to wrap a given Notification object. 
    *
    * @param notif Notification to adapt
    * @throws IllegalArgumentException 
    *         if <code>notif</code> is <code>null</code>
    **/
   ElvinEvent(Notification notif)
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
    ElvinEvent()
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
    Notification getUnderlyingNotification()
    {
        return notif;
    }
}