package psl.chime4.server.cebs;

import java.io.Serializable;

/**
 * An event represents a significant change in the state of some object
 * within the Chime system or a request by one object to another object.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class Event implements Serializable
{
    /** constant defining the id of this event **/
    private static final String EVENT_ID = "event.id";
    
    /** when this event was sent **/
    private static final String EVENT_SEND_TIMESTAMP = "event.send.timestamp";
    
    /** when this event was recieved **/
    private static final String EVENT_RECV_TIMESTAMP = "event.recv.timestamp";
    
    /** user id of the person who sent this event **/
    private static final String EVENT_SOURCE_UID = "event.source.uid";
    
    /** the topic to publish this event under **/
    private static final String EVENT_TOPIC = "event.topic";
    
    /** the event server this event was sent to **/
    private static final String EVENT_SOURCE_EVENT_SERVER = 
        "event.source.event-server";
    
    /**
     * Get the unique-to-the-source integer id of this event.
     *
     * @return unique-to-the-source integer id of this event
     **/
    public int getEventID()
    {
        return getInteger( EVENT_ID );
    }
    
    /**
     * Set the unique-to-the-source integer id of this event.
     *
     * @param eventID a unique integer id for this event
     **/
    public void setEventID(int eventID)
    {
        put( EVENT_ID, eventID );
    }
    
    /**
     * Get the time when this event was sent.
     *
     * @return time when this event was sent
     **/
    public long getSendTime()
    {
        return getLong( EVENT_SEND_TIMESTAMP );
    }
    
    /**
     * Set the timestamp when the source sent this event.
     *
     * @param sendTime the time when the source sent this event
     **/
    public void setSendTime(long sendTime)
    {
        put( EVENT_SEND_TIMESTAMP, sendTime );
    }
    
    /**
     * Get the time when this event was recieved.
     *
     * @return the time this event was recieved
     **/
    public long getRecvTime()
    {
        return getLong( EVENT_RECV_TIMESTAMP );
    }
    
    /**
     * Set the timestamp when the destination recieved this event.
     *
     * @param recvTime the time when the reciever recieved thhis evnet
     **/
    public void setRecvTime(long recvTime)
    {
        put( EVENT_RECV_TIMESTAMP, recvTime );
    }
    
    /**
     * Get the user id of the source that generated this event.
     *
     * @return user id of the source that generated this evnet
     **/
    public int getSourceUID() 
    {
        return getInteger( EVENT_SOURCE_UID );
    }
    
    /**
     * Set the globally unique user id for the source of this event.
     *
     * @param sourceUID the user id of the source of this event
     **/
    public void setSourceUID(int sourceUID)
    {
        put( EVENT_SOURCE_UID, sourceUID );
    }
    
    /**
     * Get the topic this event was published under.
     *
     * @return the topic this event was published under
     **/
    public String getTopic()
    {
        return getString( EVENT_TOPIC );
    }
        
    /**
     * Set the topic this event will be published under.
     *
     * @param topic the topic this event will be published under
     **/
    public void setTopic(String topic)
    {
        put( EVENT_TOPIC, topic );
    }
    
    /**
     * Get the event server this event was originally sent to. This is
     * the event server that the source originally sent the event to, to
     * be routed.
     *
     * @return the source event server
     **/
    public String getSourceEventServer()
    {
        return getString(EVENT_SOURCE_EVENT_SERVER);
    }
    
    /**
     * Set the event server that this event should be sent to.
     *
     * @param eventServer the event server that this event will be sent to
     **/
    public void setSourceEventServer(String eventServer)
    {
        put(EVENT_SOURCE_EVENT_SERVER, eventServer);
    }
    
    /**
     * Determine whether an event contains a value mapped to a given key.
     *
     * @param key the key to test for existence
     * @return <code>true</code> if the event contains a value mapped to
     *         <code>key</code>, else <code>false</code>
     **/
    public abstract boolean containsKey(String key);
    
    /**
     * Erase all key/value pairs within the event.
     **/
    public abstract void clear();
    
    /**
     * Get an integer value stored in the event.
     *
     * @param key the index the integer is stored under in the event
     * @return integer value mapped to <code>key</code>
     * @throws IllegalArgumentException
     *         if event does not contain <code>key</code>
     **/
    public abstract int getInteger(String key);
    
    /**
     * Get a long value stored in the event.
     *
     * @param key the index the long is stored under in the event
     * @return long value mapped to <code>key</code>
     * @throws IllegalArgumentException
     *         if event does not contain <code>key</code>
     **/
    public abstract long getLong(String key);
    
    /**
     * Get a double value stored in the event.
     *
     * @param key the index the double is stored under in the event
     * @return double value mapped to <code>key</code>
     * @throws IllegalArgumentException
     *         if event does not contain <code>key</code>
     **/
    public abstract double getDouble(String key);
    
    /**
     * Get an ASCII string stored in the event.
     *
     * @param key the index the string is stored under in the event
     * @return string value mapped to <code>key</code>
     * @throws IllegalArgumentException
     *         if event does not contain <code>key</code>
     **/
    public abstract String getString(String key);
    
    /**
     * Get an array of arbitrary bytes stored in the event.
     *
     * @param key the index the byte array is stored under in the event
     * @return byte[] value mapped to <code>key</code>
     * @throws IllegalArgumentException
     *         if event does not contain <code>key</code>
     **/
    public abstract byte[] getByteArray(String key);
    
    /**
     * Put an integer value into the event.
     *
     * @param key the key to store the integer value under
     * @param val the value of the integer value
     **/
    public abstract void put(String key, int val);
    
    /**
     * Put a long value into the event.
     *
     * @param key the key to store the long value under
     * @param val the value of the integer value
     **/
    public abstract void put(String key, long val);
    
    /**
     * Put a double value into the event.
     *
     * @param key the key to store the double value under
     * @param val the double to store into the event
     **/
    public abstract void put(String key, double val);
    
    /**
     * Put an ASCII string value into the event.
     *
     * @param key the key to store the String value under
     * @param val the value of the String value
     * @throws IllegalArgumentException
     *         if <code>val</code> is <code>null</code>
     **/
    public abstract void put(String key, String val);
    
    /**
     * Put an array of bytes into the event.
     *
     * @param key the key to store the byte array under 
     * @param val the bytes to store into the event
     **/
    public abstract void put(String key, byte[] val);
    
    /**
     * Remove a value from the event.
     *
     * @param key the value is mapped to in the event
     **/
    public abstract  void remove(String key);
}