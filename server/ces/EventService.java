package psl.chime4.server.ces;

import psl.chime4.server.util.CounterMap;
import psl.chime4.server.scs.service.*;
import psl.chime4.server.scs.log.*;

import java.util.Map;
import java.util.HashMap;

/**
 * The EventService provides a low-level interface to a generic network of
 * event servers using a simple publish/subscribe mechanism. 
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class EventService extends AbstractService
{
   /** counter map stores the connection count to different servers **/
   private CounterMap connectionCount = new CounterMap();
   
   /** map for the event handlers **/
   private EventHandlerMultiMap handlerMultiMap;
   
   /** queue for incoming events **/
   private EventQueue eventQueue = new EventQueue();
   
   /** true if the service has started **/
   protected boolean serviceStarted = false;
   
   /** event dispatcher for passing incoming events **/
   private EventDispatcher eventDispatcher;
   
   /** Logging service **/
   private LoggingService logService;
   
   /**
    * Construct the basic event service.
    **/
   protected EventService()
   {
      
   }
   
   /**
    * Override initialization of this service to start the event service.
    *
    * @param params no params need to be passed
    **/
   public void initialize(ServiceParamMap params) throws ServiceException
   {
      startup();
      System.out.println("ES: Event Service Started");
   }
   
   /**
    * Start the event service. No other methods on this object may be called
    * before this method.
    **/
   public void startup()
   {
      handlerMultiMap = new EventHandlerMultiMap();
      eventDispatcher = new EventDispatcher(eventQueue, handlerMultiMap);
      // start the event dispatcher thread
      eventDispatcher.startup();
      serviceStarted = true;
      
      // tell the subclass that the service is starting
      serviceStarted();
   }
   
   /**
    * Implementations may override this method to perform any initialization
    * required. This method is called as soon as the EventService has 
    * started.
    **/
   protected void serviceStarted() 
   { 
      ; // do nothing
   }
   
   /**
    * Shutdown the event service.
    **/
   public synchronized void shutdown()
   {
      // stop the event dispatcher thread and close the queue
      eventDispatcher.shutdown();
      eventQueue.close();
      
      // dispose of all resources
      eventDispatcher = null;
      handlerMultiMap = null;
      eventQueue = null;
      
      // tell the implementation
      serviceShutdown();
   }
   
   /**
    * Implementations may override this method to perform any cleanup 
    * necessary.
    **/
   protected void serviceShutdown()
   {
      ; // do nothing
   }
   
   /**
    * A little convience method. Throws an exception if the service has
    * not started.
    *
    * @throws IllegalStateException
    *         if the service has not started
    **/
   protected void checkServiceStarted()
   {
      if (!serviceStarted)
      {
         String msg = "event service has not started";
         throw new IllegalStateException(msg);
      }
   }
   
   /** 
    * A little convience method. Throws an exception if no connection exists
    * to a given server.
    *
    * @param connKey key for the connection in the connectionCount 
    * @throws IllegalArgumentException
    *         if <code>connKey</code> is <code>null</code>
    * @throws IllegalStateException
    *        if no connection exists to <code>host</code>:<code>port</code>
    **/
   protected void checkConnectionExists(Object connKey)
   {
      if (connKey == null)
      {
         String msg = "connKey cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      if (!connectionCount.isCountPositive(connKey))
      {
         String msg = "no connection exists for key: " + connKey;
         throw new IllegalStateException(msg);
      }
   }
   
   /**
    * Given a host and a port make a unique key.
    *
    * @param host the host of the event server
    * @param port the port of the event server process
    * @return Object key combining <code>host</code> and <code>port</code>
    * @throws IllegalArgumentException
    *         if <code>host</code> is <code>null</code>
    **/
   protected Object makeConnectionKey(String host, int port)
   {
      // check for null
      if (host == null)
      {
         String msg = "host cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      StringBuffer buf = new StringBuffer(host.length() + 3);
      buf.append(host).append(port);
      return buf.toString();
   }
   
   /**
    * Open a connection to an event server. Before sending or recieving 
    * events to and from a given server you must first connect to it.
    *
    * @param hort host of the event server
    * @param port port of the event server process
    * @throws IllegalArgumentException            
    *         if <code>host</code> is <code>null</code>
    * @throws IllegalStateException
    *         if the event service has not been started
    * @throws EventServiceException
    *         if the connection fails
    **/
   public synchronized void openConnection(String host, int port) 
      throws EventServiceException
   {
      // check for null
      if (host == null)
      {
         String msg = "host cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure the service started
      checkServiceStarted();
      
      // get the connection count to this server
      Object key = makeConnectionKey(host, port);
      int connCount = connectionCount.getCount(key);
      
      if (connCount == 0)
      {
         // no connections made to this server, open a real one
         openPhysicalConnection(host, port);
      }
      
      // increment the connection count
      connectionCount.incrementCount(key);
   }
   
   /**
    * Open a physical connection to a given server. 
    *
    * @param host host of the event server
    * @param port port of the event server
    * @throws EventServiceException
    *         if the connection cannot be made
    * @throws IllegalStateException
    *         if the connection to the server already exists
    **/
   protected abstract void openPhysicalConnection(String host, int port) 
      throws EventServiceException;
   
   /**
    * Close a connection to an event server. 
    *
    * @param host host of the event server
    * @param port port of the event service process
    * @throws IllegalArgumentException
    *         if <code>host</code> is <code>null</code>
    * @throws IllegalStateException
    *         if no connection to the server exists or the service has not
    *         been started
    **/
   public synchronized void closeConnection(String host, int port)
   {
      // check for null
      if (host == null)
      {
         String msg = "host cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure the system has started
      checkServiceStarted();
      
      // make sure the connection exists
      Object key = makeConnectionKey(host, port);
      if (connectionCount.getCount(key) <= 0)
      checkConnectionExists(key);
      
      // decrement the connection count
      connectionCount.decrementCount(key);
      
      // if there are no more connections to the server close it for real
      if (connectionCount.getCount(key) <= 0)
      {
         closePhysicalConnection(host, port);
      }
   }
   
   /**
    * Close a physical connection to a server.
    *
    * @param host host of the event server process
    * @param port port of the event server process
    **/
   protected abstract void closePhysicalConnection(String host, int port);
   
   /**
    * Publish an event to a specfic server on a specific topic. 
    *
    * @param host  the host of the event server process
    * @param port  the port of the event server process
    * @param topic the topic to publish the event server to
    * @param event the event to send 
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    * @throws IllegalStateException
    *         if the server has not been started or if no connection exists
    *         to the given server
    * @throws EventServiceException
    *         if no connection could be made
    **/
   public void publish(String host, int port, String topic, Event event) 
      throws EventServiceException
   {
      // check for null
      if ((host == null) || (topic == null) || (event == null))
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure the system's started
      checkServiceStarted();
      
      // make sure the connection exists
      Object key = makeConnectionKey(host, port);
      checkConnectionExists(key);
      
      // add routing information
      event.setTopic(topic);
      event.setEventServerHost(host);
      event.setEventServerPort(port);
      
      System.out.println("[ES] Sending Event: " + event);
      
      // send the event
      sendEvent(host, port, topic, event);
   }
   
   /**
    * Actually send an event to a given event server.
    *
    * @param host  host of the event server
    * @param port  port of the event server process
    * @param topic topic to send the event to
    * @param event event to send
    * @throws EventServiceException
    *         if the event couldn't be sent
    **/
   protected abstract void sendEvent(String host, int port, String topic, 
                                     Event event) 
      throws EventServiceException;
   
   /**
    * Register an event handler to recieve incoming events from a given
    * event server published to a given topic.
    *
    * @param host    the host of the event server to listen to
    * @param port    port of the event server to listen to
    * @param topic   the topic to listen to
    * @param handler the handler to recieve events from the topic
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    * @throws EventServiceException
    *         if the subscription could not be made
    **/
   public void registerEventHandler(String host, int port, String topic, 
                                    EventHandler handler) 
      throws EventServiceException
   {
      // check for null
      if ((host == null) || (topic == null) || (handler == null))
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure the service's started
      checkServiceStarted();
      
      // make sure a connection has been opened
      Object key = makeConnectionKey(host, port);
      checkConnectionExists(key);
      
      // if there are no handlers listening to this server topic then 
      // make the subscription
      if (!handlerMultiMap.isActive(host, port, topic))
      {
         addSubscription(host, port, topic);
      }
      
      // if the subscription was made, put it in the map
      handlerMultiMap.putEventHandler(host, port, topic, handler);
   }
   
   /**
    * Make a subscription to a given topic at a given server
    *
    * @param host  the event server to listen to
    * @param port  the port of the event server
    * @param topic the topic to subscribe to
    * @throws EventServicException
    *         if the subscription could not be made
    **/
   protected abstract void addSubscription(String host, int port, 
                                           String topic) 
      throws EventServiceException;
   
   /**
    * Unregister an event handler to stop listening to a given topic on a
    * given host.
    *
    * @param host    the host of the event server
    * @param port    the port of the event server process
    * @param topic   the topic the event was published under
    * @param handler the handler to unregister
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    * @throws IllegalStateException
    *         if no connection to the server exists or if the event system
    *         has not been started
    * @throws EventServiceException
    *         if a subscription to the event server cannot be undone
    **/
   public void unregisterEventHandler(String host, int port, String topic, 
                                      EventHandler handler)
      throws EventServiceException
   {
      // check for null
      if ((host == null) || (topic == null) || (handler == null))
      {
         String msg = "no parameter may be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure the service has started
      checkServiceStarted();
      
      // make sure a connection exists to the server
      Object key = makeConnectionKey(host, port);
      checkConnectionExists(key);
    
      // remove the handler form the multimap
      handlerMultiMap.removeEventHandler(handler, host, port, topic);
      
      // if there are no other handlers listening to this server topic then
      // remove the subscription
      if (!handlerMultiMap.isActive(host, port, topic))
      {
            removeSubscription(host, port, topic);
      }
   }
   
   /**
    * Remove a subscription from a given server.
    *
    * @param host  the host of the event server
    * @param port  port of the event server process
    * @param topic the topic of the event server process
    * @throws EventServiceException
    *         if the subscription could not be removed
    **/
   protected abstract void removeSubscription(String host, int port, 
                                              String topic)
      throws EventServiceException;
   
   /**
    * When an event is recieved, subclasses must call this method so that
    * the event can be forwarded to handlers.
    *
    * @param event the event that was recieved
    **/
   protected void eventRecieved(Event event)
   {
      if (event != null)
      {
         eventQueue.putEvent(event);
      }
   }
   
   /**
    * Create an empty event. No guarantee is made on the uniqueness of this
    * event object. For performance reasons, implementations may wish to
    * reuse Event objects.
    *
    * @return empty Event object
    **/
   public abstract Event createEmptyEvent();
}