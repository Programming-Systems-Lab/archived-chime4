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
   /**
    * Set the CEBS URL that this event is being sent to. This URL must be of
    * the form <code>cebs://[host]:[port]?topic=[topic]</code> where 
    * [host]:[port] is the address of the event server and [topic] is the
    * topic the event will be published on.
    *
    * @param cebsURL the CEBS URL that the event will be published to
    * @throws IllegalArgumentException
    *         if <code>cebsURL</code> is <code>null</code>
    **/
   void setCebsURL(String cebsURL)
   {
      // check for null
      if (cebsURL == null)
      {
         String msg = "cannot set null cebs URL";
         throw new IllegalArgumentException(msg);
      }
      
      put("xxx.cebs.url", cebsURL);
   }
   
   /**
    * Get the Cebs URL that this event was sent to.
    *
    * @return event server that this event was published to
    **/
   String getCebsURL()
   {
      return getString("xxx.cebs.url");
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