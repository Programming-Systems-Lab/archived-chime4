package psl.chime4.server.cebs;

import java.util.Map;
import java.util.HashMap;

/**
 * This class defines an interface to an underlying publish/subscribe event
 * system.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class CebsEventSystem
{
   /** counter map for storing the connection-count for each connection **/
   private CounterMap connectionCounter = new CounterMap();
   
   /** map for the event handlers **/
   private Map handlerMap = new HashMap();
   
   /** queue for incoming events **/
   private BlockingEventQueue eventQueue = new BlockingEventQueue();
   
   /**
    * Startup the event system. No methods should be called before this
    * method--if they are an <code>IllegalStateException</code> may be
    * thrown.
    **/
   public void startup()
   {
      // start the event dispatcher thread
   }
   
   /**
    * Shutdown the event system. 
    **/
   public void shutdown()
   {
      // shutdown the event dispatcher thread
   }
   
   /**
    * Open a connection to an event server. Before you can recieve or send
    * events to an event server, you must first open a connection to that
    * event server.
    *
    * @param host the host of the event server
    * @param port the port of the event server
    * @throws IllegalArgumentException
    *         if <code>host</code> is <code>null</code>
    * @throws CebsException
    *         if the connection could not be opened
    **/
   public void openConnection(String host, int port) throws CebsException
   {
      // check for null
      if (host == null)
      {
         String msg = "host cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // create the key for the connection
      String key = host + ":" + port;
      
      // get the connection count for this connection
      int connectionCount = connectionCounter.getCount(key);
      
      // if there are no connections to this server yet, open a real one
      if (connectionCount == 0)
      {
         openRealConnection(host, port);
      }
      
      // increment the connection count
      connectionCounter.incrementCount(key);
   }
   
   /**
    * Open a physical connection to an event server. Subclasses should 
    * override this method to specify their protocol and what not. 
    *
    * @param host host of the event server process to connect to
    * @param port port of the event server process to connect to
    * @throws CebsException
    *         if the connection cannot be made
    * @throws IllegalStateException
    *         if a connection to the server already exists
    **/
   protected abstract void openRealConnection(String host, int port) 
      throws CebsException;
   
   /**
    * Close a connection to a server. This method should be called after the
    * client is finished sending or recieving events from a server because
    * it may free considerable system resources.
    *
    * @param host the host to close the connection to
    * @param port the port of the event server process
    * @throws IllegalArgumentException
    *         if <code>host</code> is <code>null</code>
    * @throws IllegalStateException
    *         if no connection to the server exists
    **/
   public void closeConnection(String host, int port)
   {
      // check for null
      if (host == null)
      {
         String msg = "host cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure a connection exists
      String key = host + ":" + port;
      if (connectionCounter.getCount(key) <= 0)
      {
         String msg = "no connection to server exists";
         throw new IllegalStateException();
      }
      
      // decrement the connection count
      connectionCounter.decrementCount(key);
      
      // get the connection count and close if it's zero
      if (connectionCounter.getCount(key) == 0)
      {
         closeRealConnection(host, port);
      }
   }
   
   /**
    * Close a physical connection to a server.
    *
    * @param host the host of the event server process
    * @param port the port of the event server process
    **/
   protected abstract void closeRealConnection(String host, int port);
   
   /**
    * Publish an event to a specific server. Before calling this method the
    * client must call the {@link openConnection} method.
    *
    * @param host  the host to publish the event to
    * @param port  the port of the event server process
    * @param topic the topic to publish the event on
    * @param event the event to actually publish
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    * @throws IllegalStateException
    *         if no connection to the server exists
    * @throws CebsException
    *         if the event cannot be published
    **/
   public void publish(String host, int port, String topic, Event event)
      throws CebsException
   {
      // check for null
      if ((host == null) || (topic == null) || (event == null))
      {
         String msg = "no param can be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure the connection exists
      String key = host + ":" + port;
      if (connectionCounter.getCount(key) <= 0)
      {
         String msg = "must open connection before sending events";
         throw new IllegalStateException(msg);
      }
      
      sendEvent(host, port, topic, event);
   }
   
   /**
    * Actually send an event to a server on a specific topic. This method 
    * cannot be before the {@link openConnection} method.
    *
    * @param host  the host to publish the event to
    * @param port  port of the event server process
    * @param topic topic to publish the event under
    * @param event event to actually send
    * @throws CebsException
    *         if the event cannot be sent
    **/
   protected abstract void sendEvent(String host, int port, String topic, 
                                     Event event) throws CebsException;
   
   
   /**
    * Register an event handler to recieve events published under a given
    * topic. Before calling this method, clients must call the
    * {@link openConnection} method.
    *
    * @param host    host that is managing the topic
    * @param port    port of the event server process
    * @param topic   the topic to subscribe to
    * @param handler handler to handle events published on <code>topic</code>
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    * @throws IllegalStateException
    *         if no connection was made to server
    * @throws CebsException
    *         if the subscription could not be made
    **/
   public void registerEventHandler(String host, int port, String topic, 
                                    EventHandler handler) 
      throws CebsException
   {
      // check for null
      if ((host == null) || (topic == null) || (handler == null))
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure a connection has been opened
      String connkey = host + ":" + port;
      if (connectionCounter.getCount(connkey) <= 0)
      {
         String msg = "must open connection before sending events";
         throw new IllegalStateException(msg);
      }
      
      // make the key
      String key = host + ":" + port + ":" + topic;   
      handlerMap.put(key, handler);
      
      // tell the underlying connection to really make the subscription
      addSubscription(host, port, topic);
   }
      
   /**
    * Subscribe to a specific topic on a specific event server.
    *
    * @param host  the event server
    * @param port  port of the event server
    * @param topic topic to subscribe too
    * @throws CebsException
    *         if the subscription cannot be made
    **/
   protected abstract void addSubscription(String host, int port, 
                                           String topic) 
      throws CebsException;
   
   /**
    * Unregister an event handler that no longer wants to subscribe to a 
    * given topic. 
    *
    * @param host  the event server subscribed to
    * @param port  the port of the event server process
    * @param topic the topic to unsubscribe from
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    * @throws CebsException
    *         if the subscription to the event server cannot be undone
    **/
   public void unregisterEventHandler(String host, int port, String topic)
      throws CebsException
   {
      // check for null
      if ((host == null) || (topic == null))
      {
         String msg = "no param may be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure a connection has been opened
      String connkey = host + ":" + port;
      if (connectionCounter.getCount(connkey) <= 0)
      {
         String msg = "must open connection before sending events";
         throw new IllegalStateException(msg);
      }
      
      // remove it from the map
      String key = host + ":" + port + ":" + topic;
      handlerMap.remove(key);
      
      // remove the subscription
      removeSubscription(host, port, topic);
   }
   
   /**
    * Remove a subscription from an event server.
    *
    * @param host  the host of the event server
    * @param port  the port of the event server process
    * @param topic the topic to unsubscribe from
    * @throws CebsException
    *         if the subscription cannot be removed
    **/
   protected abstract void removeSubscription(String host, int port, 
                                              String topic) 
      throws CebsException;
   
   /**
    * Get an EventHandler subscribed to a topic on a given host.
    *
    * @param host  the host of the event server
    * @param port  port of the event server process
    * @param topic the topic the event was published under
    * @throws IllegalArgumentException
    *         if <code>host</code> or <code>topic</code> is <code>null</code>
    * @return EventHandler subscribed to <code>topic</code> or 
    *         <code>null</code>
    **/
   EventHandler getEventHandler(String host, int port, String topic)
   {
      // check for null
      if ((host == null) || (topic == null))
      {
         String msg = "no parameter may be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make the key
      String key = host + ":" + port + ":" + topic;
      return (EventHandler) handlerMap.get(key);
   }
   
   /**
    * When an event is actually recieved by the underlying event system 
    * subclasses should call this method in order to place the event within
    * the event system.
    *
    * @param event the event that was recieved
    **/
   protected void eventRecieved(Event event)
   {
      if (event != null)
      {
         eventQueue.put(event);
      }
   }
   
   /**
    * Create an empty event. No guarantee is made on the uniqueness of this
    * Event object. This is because for performance reasons the underlying
    * event system implementation may wish to do object pooling. Clients 
    * must not depend on the uniqueness of Event objects since they may 
    * unknowingly be reusing the same object.
    *
    * @return an empty Event object
    **/
   public abstract Event createEmptyEvent(); 
}
      
                                           
                                           
                                           
                                           
                                           
         
   
   
   