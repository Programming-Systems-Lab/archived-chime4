package psl.chime4.server.util;

import java.util.Map;
import java.util.HashMap;

/**
 * A simple convience class for associated an integer counter variable with
 * non-null Object keys.
 *
 * This class is not threadsafe.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public final class CounterMap
{
   /** map used for storing integers **/
   private Map counterMap = new HashMap();
   
   /**
    * Increment the count associated with a given key. If there was no count
    * ever associated with the given key, a new count is created and set to
    * <code>1</code>.
    *
    * @param key key to increase the count of
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    **/
   public void incrementCount(Object key)
   {
      // check for null
      if (key == null)
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      int[] value;
      if (counterMap.containsKey(key))
      {
         // if it's already in the map, increment it
         value = (int[]) counterMap.get(key);
         value[0] = value[0] + 1;
      } else
      {
         // put 1 into the map
         value = new int[1];
         value[0] = 1;
      }
         
      counterMap.put(key, value);
   }
   
   /**
    * Decrement the count associated with a given key. If there is no value
    * associated with the given key this method does nothing.
    *
    * @param key the key to the counter
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    * @throws NoSuchElementException
    *         if there is no counter associated with <code>key</code>
    **/
   public void decrementCount(Object key)
   {
      // check for null
      if (key == null)
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      if (counterMap.containsKey(key))
      {
         // get the count and decrement it
         int[] value = (int[]) counterMap.get(key);
         value[0] = value[0] - 1;
         
         // do the removal check
         if (value[0] <= 0)
         {
            counterMap.remove(key);
         } else
         {
            counterMap.put(key, value);
         }
      }
   }
   
   /**
    * Get the count associated with a specific key.
    *
    * @param key the amount the key is associated with
    * @return the count associated with <code>key</code>. 
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    **/
   public int getCount(Object key)
   {
      // check for null
      if (key == null)
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // if it's in the map, return the associated value
      if (counterMap.containsKey(key))
      {
         int[] value = (int[]) counterMap.get(key);
         return value[0];
      } else
      {
         return 0;
      }
   }
   
   /**
    * Determine if there is a positive-value count associated with the 
    * given key. If the value associated with the key is zero or less 
    * <code>false</code> is returned.
    *
    * @param key the name of the key to check the value for
    * @return <code>true</code> if there is a positive value associated with
    *         <code>key</code> else <code>false</code>
    * @throws IllegalArgumentException
    *         if <code>key</code> is <code>null</code>
    **/
   public boolean isCountPositive(Object key)
   {
      // check for null
      if (key == null)
      {
         String msg = "key cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      return counterMap.containsKey(key);
   }
}
      