package psl.chime4.server.cebs.elvin;

import psl.chime4.server.cebs.Event;
import psl.chime4.server.cebs.CebsEventSystem;
import psl.chime4.server.cebs.CebsException;

import java.util.Map;
import java.util.HashMap;

import org.elvin.je4.Connection;
import org.elvin.je4.Producer;
import org.elvin.je4.Consumer;
import org.elvin.je4.Notification;
import org.elvin.je4.ElvinURL;
import org.elvin.je4.Subscription;

/**
 * An interface to the Elvin event protocol using it as a basic pub/sub 
 * system.
 *
 * @note Currently, this class does not perform Subscription caching. Every
 * time a client subscribes and unsubscribes from a topic a new Subscription
 * object is created. This is bad, because each time a client enters and 
 * leaves a room it must subscribe/unsubscribe from a topic. This means 
 * Subscription objects should be cached.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ElvinEventSystem extends CebsEventSystem
{
   /** a map of Consumer objects currently active **/
   private Map consumerMap = new HashMap();
   
   /** map of Producer objects currently active **/
   private Map producerMap = new HashMap();
   
   /** map for Connection objects to the server **/
   private Map connectionMap = new HashMap();
   
   /**
    * Open a physical connection to an Elvin server.
    *
    * @param host the host of the elvin event server
    * @param port the port of the event server process
    * @throws IllegalArgumentException
    *         if <code>host</code> is <code>null</code> 
    * @throws CebsException
    *         if the connection could not be made
    * @throws IllegalStateException
    *         if already connected to the event server
    **/
   protected void openRealConnection(String host, int port) 
      throws CebsException
   {
      // check for null
      if (host == null)
      {
         String msg = "host cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // first make sure we'return not already connected
      String key = host + ":" + port;
      if (connectionMap.containsKey(key))
      {
         String msg = "already connected to server";
         throw new IllegalStateException(msg);
      }
      
      try
      {
         // turn the host/port into a elvin url
         ElvinURL url = 
            new ElvinURL("elvin:4.0/tcp,none/xdr/" + host + ":" + port);
      
         Connection conn = new Connection(url);
         
         // put it in the map
         connectionMap.put(key, conn);
      }
      catch (Exception e)
      {
         String msg = "could not connect to server: " + host + ":" + port;
         throw new CebsException(msg, e);
      }
   }
       
   /**
    * Close a physical connection to the Elvin event server. If no connection
    * exists to the server this method is a No-op.
    *
    * @param host host of the event server
    * @param port port of the event server process
    * @throws IllegalArgumentException
    *         if <code>host</code> is <code>null</code>
    * @throws IllegalStateException
    *         if no connection to the server exists
    **/
   protected void closeRealConnection(String host, int port)
   {
      // check for null
      if (host == null)
      {
         String msg = "host cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // get the Connection object from the map and close it
      String key = host + ":" + port;
      Connection conn = (Connection) connectionMap.get(key);
      
      // if no connection exists, throw an exception
      if (conn == null)
      {
         String msg = "no connection to server: " + host + ":" + port;
         throw new IllegalStateException(msg);
      }
      
      conn.close();
    
      // remove a producer or consumer if used
      producerMap.remove(key);
      consumerMap.remove(key);
      
      // remove the connection itself
      connectionMap.remove(key);
   }
   
   /**
    * Send an Event to the elvin event system.
    *
    * @param host  host to send the event to
    * @param port  port of the event server process
    * @param topic the topic to publish the event under
    * @param event the event to send 
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    * @throws IllegalStateException 
    *         if no connection to the server exists
    * @throws CebsException
    *         if the exception could not be sent
    * @throws ClassCastException
    *         if the event is not an Elvin event
    **/
   protected void sendEvent(String host, int port, String topic, 
                            Event event) 
      throws CebsException
   {
      // check for null
      if ((host == null) || (topic == null) || (event == null))
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure we're connected to the server
      String connKey = host + ":" + port;
      Connection conn = (Connection) connectionMap.get(connKey);
      if (conn == null)
      {
         String msg = "no connection to server: " + host + ":" + port;
         throw new IllegalStateException(msg);
      }
      
      try
      {
         // see if a producer already exists for this one
         if (producerMap.containsKey(connKey))
         {
            // retrieve the producer and send the event
            Producer prod = (Producer) producerMap.get(connKey);
            prod.notify( ((ElvinEvent) event).getUnderlyingNotification() );
         } else
         {
            // it's the first time sending to the server, need a new producer
            Producer prod = new Producer(conn);
            prod.notify(((ElvinEvent) event).getUnderlyingNotification());
            producerMap.put(connKey, prod);
         }
      } catch (Exception e)
      {
         String msg = "could not send event to server: " + host + ":" + port;
         throw new CebsException(msg, e);
      }
   }
   
   /**
    * Modify a subscription to an Elvin event server. 
    *
    * @param host  host of the event server
    * @param port  port of the event server process
    * @param topic the topic to subscribe to
    * @throws IllegalArgumentException
    *         if <code>host</code> or <code>topic</code> is <code>null</code>
    * @throws IllegalStateException
    *         if no connection exists to the server
    * @throws CebsException
    *         if the subscription could not be made
    **/
   protected void addSubscription(String host, int port, String topic) 
      throws CebsException
   {
      // check for null
      if ((host == null) || (topic == null))
      {
         String msg = "no paramters can be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure connection exists to the server
      String connKey = host + ":" + port;
      Connection cxn = (Connection) connectionMap.get(connKey);
      if (cxn == null)
      {
         String msg = "no connection to server: " + host + ":" + port;
         throw new IllegalStateException(msg);
      }
      
      try
      {
         // make a new subscription object
         Subscription sub = 
            new Subscription("xxx.event.topic == '" + topic + "'")   ;
      
         // add the actual subscription
         if (consumerMap.containsKey(connKey))
         {
            Consumer consumer = (Consumer) consumerMap.get(connKey);
            consumer.addSubscription(sub);
         } else
         {
            // first time a consumer is being made
            Consumer consumer = new Consumer(cxn);
            consumer.addSubscription(sub);
       
            // now it in the map
            consumerMap.put(connKey, consumer);
         }
      } catch (Exception e)
      {
         String msg = "could not send event to: " + host + "," + port;
         throw new CebsException(msg, e);
      }
   }
   
   /**
    * Remove a subscription from the elvin event server.
    *
    * @param host  host of the event server
    * @param port  port of the event server process
    * @param topic topic to unsubscribe from
    * @throws IllegalArgumentException
    *         if <code>host</code> or <code>topic</code> is <code>null</code>
    * @throws IllegalStateException
    *         if no connection the server exists
    * @throws CebsException
    *         if the subscription could not be removed
    **/
   protected void removeSubscription(String host, int port, String topic)
      throws CebsException
   {
      // check for null
      if ((host == null) || (topic == null))
      {
         String msg = "no paramter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure a connection exists to the server
      String connKey = host + ":" + port;
      Connection cxn = (Connection) connectionMap.get(connKey);
      if (cxn == null)
      {
         String msg = "no connection exists to server";
         throw new IllegalStateException(msg);
      }
      
      try
      {
         // now get the consumer to the connection
         Consumer consumer = (Consumer) consumerMap.get(connKey);
      
         // create the subscription and remove it
         Subscription sub = 
            new Subscription("xxx.event.topic == '" + topic + "'");
         consumer.removeSubscription(sub);
      } catch (Exception e)
      {
         String msg = "cannot remove subscription";
         throw new CebsException(msg, e);
      }
   }
   
   /**
    * Create an empty Elvin event.
    *
    * @return an empty Elvin event.
    **/
   public Event createEmptyEvent()
   {
      return new ElvinEvent();
   }
}
