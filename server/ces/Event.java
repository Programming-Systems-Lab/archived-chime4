package psl.chime4.server.ces;

import java.io.Serializable;

/**
 * Defines a basic event. This represents either a change in the state of 
 * some object or a message.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class Event implements Serializable
{
   /** 
    * Get the topic this event was published under.
    *
    * @return topic this event was published under or <code>null</code>
    **/
   String getTopic()
   {
      return getString("ces.event.topic");
   }
   
   /**
    * Set the topic this event was published under.
    *
    * @param topic the topic the event was published under
    **/
   void setTopic(String topic)
   {
      put("ces.event.topic", topic);
   }
   
   /**
    * Get the host of the event server that sent this event.
    *
    * @return host of the event server that sent this event
    **/
   String getEventServerHost()
   {
      return getString("ces.event.server.host");
   }
   
   /**
    * Set the host of the event server that sent this event.
    *
    * @param host of the event server that sent this event
    **/
   void setEventServerHost(String host)
   {
      put("ces.event.server.host", host);
   }
   
   /**
    * Get the port of the event server that sent this event.
    *
    * @return port of the event server that sent this event
    **/
   int getEventServerPort()
   {
      return getInteger("ces.event.server.port");
   }
   
   /**
    * Set the port of the event server that sent this event.
    *
    * @param port port of the event server that sent this event
    **/
   void setEventServerPort(int port)
   {
      put("ces.event.server.port", port);
   }
   
   /**
    * Determine whether an event contains a value mapped to a given key.
    *
    * @param key the key to test for existence
    * @return <code>true</code> if the event contains a value mapped to
    *         <code>key</code> else <code>false</code>
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
    *         if there is no integer mapped to <code>key</code>
    **/
   public abstract int getInteger(String key);
   
   /**
    * Get a long value stored in the event.
    *
    * @param key the index the long is stored under in the event
    * @return long value mapped to <code>key</code>
    * @throws IllegalArgumentException
    *         if there is no long mapped to <code>key</code>
    **/
   public abstract long getLong(String key);
   
   /**
    * Get a double value stored in the event.
    *
    * @param key the index the double is stored under in the event
    * @return double value mapped to <code>key</code>
    * @throws IllegalArgumentException
    *         if there is no double mapped to <code>key</code>
    **/
   public abstract double getDouble(String key);
   
   /**
    * Get a string stored in the event.
    *
    * @param key the index the string is stored under in the event
    * @return string value mapped to <code>key</code>
    * @throws IllegalArgumentException
    *         if there is no String mapped to <code>key</code>
    **/
   public abstract String getString(String key);
   
   /**
    * Get an array of arbitrary bytes stored in the event.
    *
    * @param key the index the byte array is stored under in the event
    * @return byte[] value mapped to <code>key</code>
    * @throws IllegalArgumentException
    *         if there is no array of bytes mapped to <code>key</code>
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
