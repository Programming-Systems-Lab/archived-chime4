package psl.chime4.server.ces;

import psl.chime4.server.util.CounterMap;

import java.util.Map;
import java.util.HashMap;

/**
 * This class maps one or more EventHandlers to a given (host, port, topic)
 * combination. Note that this is a special map because more than one 
 * object may be associated with a given key. This class makes none of the
 * guarantees made by {@link java.util.Map}.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
class EventHandlerMultiMap
{
   /** map for storing EventHandlers **/
   private Map handlerMap = new HashMap();
   
   /** the number of event handlers associated with a given key **/
   private CounterMap handlerCount = new CounterMap();
   
   /**
    * Associate a given EventHandler with the given (host, port, topic) 
    * combination. 
    *
    * @param host    the event server this handler is listening to
    * @param port    the port of the event server process
    * @param topic   the topic of events 
    * @param handler the event handler to associate with 
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    **/
   public synchronized void putEventHandler(String host, int port, 
                                            String topic, 
                                            EventHandler handler)
   {  
      // make the key
      Object key = makeKey(host, port, topic);
      
      // check the number of handlers associated with the key
      int handlersRegistered = handlerCount.getCount(key);
      if (handlersRegistered > 1)
      {
         // if there's more than one handler already associated with the 
         // key then we have a list registered to the key and just add
         // this one to the list
         EventHandlerList list = (EventHandlerList) handlerMap.get(key);
         list.addEventHandler(handler);
      } else if (handlersRegistered == 1)
      {
         // if there's one handler registered to the key then make a new
         // EventHandlerList and add both of them
         EventHandler oldHandler = (EventHandler) handlerMap.get(key);
         EventHandlerList handlerList = new EventHandlerList();
         handlerList.addEventHandler(oldHandler);
         handlerList.addEventHandler(handler);
         
         // put it back in the map under the key
         handlerMap.put(key, handlerList);
      } else
      {
         // there's zero handlers associated with it so just toss it in
         // the map
         handlerMap.put(key, handler);
      }
      
      // increment the count associated with the key
      handlerCount.incrementCount(key);
   }
   
   /**
    * Get the EventHandler associated with a given key. There is no 
    * equality contract made by this method. Instead of returning a 
    * specific EventHandler registered under a given (host, port, topic)
    * combination, it may return an EventHandlerList (assuming more than
    * one EventHandler has been registered).
    *
    * @param host  the host of the event server
    * @param port  port of the event server process
    * @param topic the topic the event handler listens to
    * @return EventHandler registered to listen from (host,port,topic) or an
    *         EventHandlerList if many handlers have registered or
    *         <code>null</code> if none have registered
    * @throws IllegalArgumentException
    *         if any parameter is null
    **/
   public synchronized EventHandler getEventHandler(String host, int port, 
                                                    String topic)
   {
      Object key = makeKey(host, port, topic);
      return (EventHandler) handlerMap.get(key);
   }
   
   /**
    * Remove the given handler from the map.
    *
    * @param handler the handler to remove
    * @param host    the host the handler was listening to
    * @param port    the port of the event server process
    * @param topic   the topic the handler was listening to
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    **/
   public synchronized void removeEventHandler(EventHandler handler,
                                               String host, int port, 
                                               String topic)
   {
      // make the key
      Object key = makeKey(host, port, topic);
      
      // figure out how many handlers associated with the key
      int handlersRegistered = handlerCount.getCount(key);
      
      if (handlersRegistered > 1)
      {
         // if more than one, it's an handler list
         EventHandlerList handlerList = 
            (EventHandlerList) handlerMap.get(key);
         
         handlerList.removeEventHandler(handler);
         
         // if the list is empty, remove the list too
         if (handlerList.isEmpty())
         {
            handlerMap.remove(key);
         }
      } else if (handlersRegistered == 1)
      {
         // if there's just one, remove it from the map
         handlerMap.remove(key);
      } else
      {
         // there are no handlers registered with the key, do nothing
         ;
      }
      
      // decrement the number of handlers
      handlerCount.decrementCount(key);
   }
   
   /**
    * Determine if there are any event handlers listening to a given
    * event server topic.
    *
    * @param host  the host of the event server
    * @param port  the port of the event server process
    * @param topic the topic listening to
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    **/
   public synchronized boolean isActive(String host, int port, String topic)
   {
      // check for null
      if ((host == null) || (topic == null))
      {
         String msg = "host or topic cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      Object key = makeKey(host, port, topic);
      return handlerMap.containsKey(key);
   }
   
   /**
    * Given a host, port, and topic construct a unique key.
    *
    * @param host  host of the event server
    * @param port  port of the event server process
    * @param topic the topic for an events
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    **/
   private Object makeKey(String host, int port, String topic)
   {
      // check for null
      if ((host == null) || (topic == null))
      {
         String msg = "no parameter may be null";
         throw new IllegalArgumentException(msg);
      }
      
      StringBuffer buf = new StringBuffer(host.length() + topic.length() + 3);
      buf.append(host);
      buf.append(topic);
      buf.append(port);
      
      return buf.toString();
   }
   
   public String toString()
   {
      return getClass() + "[handlerMap=" + handlerMap + "]";
   }
}
   
   
         