package psl.chime4.server.base;

import org.elvin.je4.Notification;

/**
 * The Event class used internally for the Chime World Server. For now, this
 * is merely a wrapper around the Elvin Notification Event class.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class BasicChimeEvent implements java.io.Serializable
{
   /** internal Elvin object **/
   private Notification _event;
   
   /**
    * Construct a new ChimeEvent object, passing it a Notification object
    * to initialize it. Only the Chime World Server should ever call this
    * constructor.
    **/
   public BasicChimeEvent(Notification notif)
   {
      this._event = notif;
   }
   
   /**
    * Retrieve the underlying Notification object.
    **/
   public Notification getUnderlyingNotification()
   {
      return _event;
   }
   
   /**
    * Get an array of bytes from the event.
    *
    * @param key key string for the event
    * @return array of bytes mapped to <code>key</code>
    * @throws IllegalArgumentException 
    *         if <code>key</code> is <code>null</code> or if there is 
    *         no value mapped to <code>key</code>
    **/
   public byte[] getBytes(String key)
   {
      // check for null
      if ( key == null ) 
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      return _event.getOpaque(key);
   }
   
   /**
    * Get a double from the event.
    *
    * @param key String that the value is mapped to
    * @return double mapped to <code>key</code>
    * @throws IllegalArgumentException 
    *         if <code>key</code> is <code>null</code> or if there is 
    *         no value mapped to <code>key</code>
    **/
   public double getDouble(String key)
   {
      // check for null
      if ( key == null ) 
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      return _event.getDouble(key);
   }
   
   /**
    * Get an integer from the event.
    *
    * @param key String that the value is mapped to
    * @return int mapped to <code>key</code>
    * @throws IllegalArgumentException 
    *         if <code>key</code> is <code>null</code> or if there is 
    *         no value mapped to <code>key</code>
    **/
   public int getInt(String key)
   {
      // check for null
      if ( key == null ) 
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      return _event.getInt(key);
   }
   
   /**
    * Get a long from the event.
    *
    * @param key String that the value is mapped to
    * @return long mapped to <code>key</code>
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code> or if there is no
    *         value mapped to <code>key</code>
    **/
   public long getLong(String key)
   {
      // if check for null
      if ( key == null )
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      return _event.getLong(key);
   }
   
   /**
    * Get a String from the event.
    *
    * @param key String that the value is mapped to
    * @return String value mapped to <code>key</code>
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code> or if there is no
    *         value mapped to <code>key</code>
    **/
   public String getString(String key)
   {
      // check for null
      if ( key == null )
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      return _event.getString(key);
   }
   
   /**
    * Add an array of bytes to the event.
    *
    * @param key Key to map the object to
    * @param val byte array to map <code>key</code> to
    * @throws IllegalArgumentException
    *         if <code>key</code> or <code>val</code> is <code>null</code>
    **/
   public void put(String key, byte[] val)
   {
      // check for null
      if ( (key == null) || (val == null) )
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      _event.put(key, val);
   }
   
   /**
    * Add a double to the event.
    *
    * @param key Key to map the value to
    * @param val double value to add 
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    **/
   public void put(String key, double val)
   {
      // check for null
      if ( key == null ) 
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      _event.put(key, val);
   }
   
   /**
    * Add an int to the event.
    *
    * @param key Key to map the value to
    * @param val int value to add
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    **/
   public void put(String key, int val)
   {
      // check for null
      if ( key == null )
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      _event.put(key, val);
   }
   
   /**
    * Add a long value to the event.
    *
    * @param key Key to map the value to
    * @param val long value to add
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    **/
   public void put(String key, long val)
   {
      // check for null
      if ( key == null )
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      _event.put(key, val);
   }
   
   /**
    * Add a Unicode string value to the event.
    *
    * @param key Key to map the value to
    * @param val long value to add
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    **/
   public void put(String key, String val)
   {
      // check for null key
      if ( key == null )
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // check for null value
      if ( val == null )
      {
         _event.put(key, "" );
      } else
      {
         _event.put(key, val);
      }
   }
}