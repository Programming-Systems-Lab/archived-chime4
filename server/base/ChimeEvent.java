package psl.chime4.server.base;

/**
 * This interface abstracts the actual type of Event used within the system.
 * 
 * This interface exists primarily to make it very easy to change the
 * type of event system used.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface ChimeEvent 
{
   /**
    * Get an array of bytes from the event.
    *
    * @param key key string for the event
    * @return array of bytes mapped to <code>key</code>
    * @throws IllegalArgumentException 
    *         if <code>key</code> is <code>null</code> or if there is 
    *         no value mapped to <code>key</code>
    **/
   public byte[] getBytes(String key);
   
   /**
    * Get a double from the event.
    *
    * @param key String that the value is mapped to
    * @return double mapped to <code>key</code>
    * @throws IllegalArgumentException 
    *         if <code>key</code> is <code>null</code> or if there is 
    *         no value mapped to <code>key</code>
    **/
   public double getDouble(String key);
   
   /**
    * Get an integer from the event.
    *
    * @param key String that the value is mapped to
    * @return int mapped to <code>key</code>
    * @throws IllegalArgumentException 
    *         if <code>key</code> is <code>null</code> or if there is 
    *         no value mapped to <code>key</code>
    **/
   public int getInt(String key);
   
   /**
    * Get a long from the event.
    *
    * @param key String that the value is mapped to
    * @return long mapped to <code>key</code>
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code> or if there is no
    *         value mapped to <code>key</code>
    **/
   public long getLong(String key);
   
   /**
    * Get a String from the event.
    *
    * @param key String that the value is mapped to
    * @return String value mapped to <code>key</code>
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code> or if there is no
    *         value mapped to <code>key</code>
    **/
   public String getString(String key);
   
   /**
    * Add an array of bytes to the event.
    *
    * @param key Key to map the object to
    * @param val byte array to map <code>key</code> to
    * @throws IllegalArgumentException
    *         if <code>key</code> or <code>val</code> is <code>null</code>
    **/
   public void put(String key, byte[] val);
   
   /**
    * Add a double to the event.
    *
    * @param key Key to map the value to
    * @param val double value to add 
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    **/
   public void put(String key, double val);
   
   /**
    * Add an int to the event.
    *
    * @param key Key to map the value to
    * @param val int value to add
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    **/
   public void put(String key, int val);
   
   /**
    * Add a long value to the event.
    *
    * @param key Key to map the value to
    * @param val long value to add
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    **/
   public void put(String key, long val);
   
   /**
    * Add a Unicode string value to the event.
    *
    * @param key Key to map the value to
    * @param val long value to add
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    **/
   public void put(String key, String val);
}