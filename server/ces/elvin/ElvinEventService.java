package psl.chime4.server.ces.elvin;

import psl.chime4.server.ces.Event;
import psl.chime4.server.ces.EventService;
import psl.chime4.server.ces.EventServiceException;

import java.util.Map;
import java.util.HashMap;

import org.elvin.je4.Connection;
import org.elvin.je4.Producer;
import org.elvin.je4.Consumer;
import org.elvin.je4.Notification;
import org.elvin.je4.ElvinURL;
import org.elvin.je4.Subscription;
import org.elvin.je4.NotificationListener;

/**
 * An interface to the Elvin event protocol using the EventService API.
 *
 * @author  Azubuko Obele
 * @version 0.1
 **/
public class ElvinEventService extends EventService 
   implements NotificationListener
{
   /** map of consumers to connections **/
   private Map consumerMap = new HashMap();
   
   /** map of producers to connections **/
   private Map producerMap = new HashMap();
   
   /** map of connection objects **/
   private Map connectionMap = new HashMap();
   
   /**
    * Open a physical connection to an Elvin server.
    *
    * @param host the host of the elvin server
    * @param port the port of the event server
    * @throws IllegalArgumentException
    *         if <code>host</code> is <code>null</code>
    * @throws IllegalStateException
    *         if a connection already exists to the servr
    * @throws EventServiceException
    *        if the connection could not be opened
    **/
   protected void openPhysicalConnection(String host, int port)
      throws EventServiceException
   {
      // check for null
      if (host == null)
      {
         String msg = "host cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure the connection doesn't already exist
      Object connKey = makeConnectionKey(host, port);
      if (connectionMap.containsKey(connKey))
      {
         String msg = "already connected to " + host + ":" + port;
         throw new IllegalStateException(msg);
      }
      
      // try to make the connection
      try
      {
         // turn it into an elvin url
         ElvinURL url = 
            new ElvinURL("elvin:4.0/tcp,none,xdr/" + host + ":" + port);
         
         Connection conn = new Connection(url);
         
         // put it in the map
         connectionMap.put(connKey, conn);
      }
      catch (Exception e)
      {
         String msg = "connection could not be made to " + host + ":" + port;
         throw new EventServiceException(msg, e);
      }
   }
   
   /**
    * Given a host and a port, create a unique key to identify it.
    *
    * @param host host of the event server
    * @param port port of the event server
    * @return key for <code>host</code> and <code>port</code>
    * @throws IllegalArgumentException
    *         if <code>host</code> is <code>null</code>
    **/
   protected Object makeConnectionKey(String host, int port)
   {
      if (host == null)
      {
         String msg = "host cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      return host + port;
   }
   
   /**
    * Close a physical connection. 
    *
    * @param host host of the server to connect to
    * @param port port of the server to connect to
    * @throws IllegalArgumentException
    *         if <code>host</code> is <code>null</code>
    * @throws IllegalStateException
    *         if no connection to the server exists
    **/
   protected void closePhysicalConnection(String host, int port)
   {
      // check for null
      if (host == null)
      {
         String msg = "host cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make the connection key, get the connection
      Object key = makeConnectionKey(host, port);
      Connection conn = (Connection) connectionMap.get(key);
      
      // if no connection exists, throw the exception
      if (conn == null)
      {
         String msg = "no connection exists to " + host + ":" + port;
         throw new IllegalStateException(msg);
      } else
      {
         // close the connection
         conn.close();
         
         // remove a producer and a consumer if used
         producerMap.remove(key);
         consumerMap.remove(key);
         
         // remove the connection 
         connectionMap.remove(key);
      }
   }
   
   /** 
    * Send an Event to the Elvin server.
    *
    * @param host  host to send the event to
    * @param port  port of the event server process
    * @param topic topic to publish the event to
    * @param event the event to send
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code> 
    * @throws IllegalStateException
    *         if no connection to the server exists
    * @throws EventServiceException
    *         if the event cannot be sent
    **/
   protected void sendEvent(String host, int port, String topic, Event event)
      throws EventServiceException
   {
      // check for null
      if ((host == null) || (topic == null) || (event == null))
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure the connection exists
      Object connKey = makeConnectionKey(host, port);
      Connection conn = (Connection) connectionMap.get(connKey);
      
      if (conn == null)
      {
         String msg = "no connection to the server " + host + ":" + port;
         throw new IllegalStateException(msg);
      } else
      {
         try
         {
            Producer prod;
            // see if a producer exists
            if (producerMap.containsKey(connKey))
            {
               // if it does, get the producer and send the event
               prod = (Producer) producerMap.get(connKey);
            } else
            {
               // no producer exists, make one and put it in the map
               prod = new Producer(conn);
               producerMap.put(connKey, prod);
            }
            
            // add the special topic key to the event
            event.put("ces.elvin.event.topic", topic);
            // send the event
            prod.notify(((ElvinEvent) event).getUnderlyingNotification());
         } catch (Exception e)
         {
            String msg = "event could not be sent to: " + host + ":" + port;
            throw new EventServiceException(msg, e);
         }
      }
   }
   
   /**
    * Add a subscription to the event server.
    *
    * @param host  host of the event server
    * @param port  port of the event server
    * @param topic topic that will define the subscription
    * @throws IllegalArgumentException
    *         if <code>host</code> or <code>topic</code> is <code>null</code>
    * @throws IllegalStateException
    *         if no connection exists to the server
    * @throws EventServiceException
    *         if the subscription could not be made
    **/
   protected void addSubscription(String host, int port, String topic)
      throws EventServiceException
   {
      // check for null
      if ((host == null) || (topic == null))
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure the connection exists
      Object connKey = makeConnectionKey(host, port);
      Connection conn = (Connection) connectionMap.get(connKey);
      if (conn == null)
      {
         String msg = "no connection to server " + host + ":" + port;
         throw new IllegalStateException(msg);
      }
      
      try
      {
         // make the subscription
         Subscription sub = makeSubscription(topic);
         
         // add the actual subscription
         Consumer consumer;
         if (consumerMap.containsKey(connKey))
         {
            // if there's already a consumer listening to this server
            consumer = (Consumer) consumerMap.get(connKey);
         } else
         {
            // make the new consumer, add it to the map
            consumer = new Consumer(conn);
            consumerMap.put(connKey, consumer);
         }
         
         consumer.addSubscription(sub);
      }
      catch (Exception e)
      {
         String msg = "could not send event to " + host + ":" + port;
         throw new EventServiceException(msg);
      }
   }
   
   /**
    * Remove a subscription from the the event server.
    *
    * @param host  host of the event server
    * @param port  the port of the event server
    * @param topic the topic to unsubscribe from
    * @throws IllegalArgumentException
    *         if <code>host</code> is <code>null</code>
    * @throws IllegalStateException
    *         if no connection to the server exists
    * @throws EventServiceException
    *         if the subscription could not be removed
    **/
   protected void removeSubscription(String host, int port, String topic)
      throws EventServiceException
   {
      // check for null
      if ((host == null) || (topic == null))
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure the connection exists
      Object connKey = makeConnectionKey(host, port);
      Connection conn = (Connection) consumerMap.get(connKey);
      if (conn == null)
      {
         String msg = "no connection to " + host + ":" + port;
         throw new IllegalStateException(msg);
      }
      
      // get the consumer
      Consumer consumer = (Consumer) consumerMap.get(connKey);
      
      // make sure it's not null
      if (consumer == null)
      {
         String msg = "no such subscription " + host + ":" + port +
         "," + topic;
         throw new IllegalStateException(msg);
      } else
      {
         try
         {
            // make the subscription
            Subscription sub = makeSubscription(topic);
            consumer.removeSubscription(sub);
         }
         catch (Exception e)
         {
            String msg = "could not remove subscription";
            throw new EventServiceException(msg, e);
         }
      }
   }
   
   /**
    * Given a topic, make a new subscription. Note that in the future, 
    * because subscriptions will constantly be created (whenever a user
    * enters or leaves a room) a cache for Subscription objects will be
    * implemented.
    *
    * @param topic topic to make the subscription for
    * @throws IllegalArgumentException
    *         if <code>topic</code> is <code>null</code>
    **/
   protected Subscription makeSubscription(String topic)
   {
      String subString = "ces.elvin.event.topic == '" + topic + "'";
      return new Subscription(subString, this);
   }
            
   /**
    * Construct an empty event.
    *
    * @return empty event
    **/
   public Event createEmptyEvent()
   {
      return new ElvinEvent();
   }
   
   /**
    * This method is called whenever an event is recieved.
    *
    * @param event event that was recieved
    **/
   public void notificationAction(Notification notif)
   {
      super.eventRecieved(new ElvinEvent(notif));
   }  
}