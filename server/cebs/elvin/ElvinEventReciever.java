package psl.chime4.server.cebs.elvin;

import psl.chime4.server.cebs.EventReciever;
import psl.chime4.server.cebs.Event;
import psl.chime4.server.cebs.CEBSException;
import psl.chime4.server.cebs.BlockingEventQueue;

import org.elvin.je4.Consumer;
import org.elvin.je4.NotificationListener;
import org.elvin.je4.Notification;
import org.elvin.je4.ElvinURL;
import org.elvin.je4.Subscription;

import java.util.Map;
import java.util.HashMap;

/**
 * An EventReciever for abstracting the Elvin mechanism of recieving events.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
class ElvinEventReciever extends EventReciever implements NotificationListener
{
    /** map of Consumer objects to the server address they're listening to **/
    private Map connectionMap = new HashMap();
    
    /**
     * Construct an ElvinEventReciever attached to a specific blocking queue.
     *
     * @param queue the BlockingEventQueue to place incoming events on
     **/
    ElvinEventReciever(BlockingEventQueue queue)
    {
        super(queue);
    }


    /**
     * Add a subscription to an Elvin Consumer-connection. All subscriptions
     * are done by filtering events based on the special 'event.topic' 
     * key whose value must be equal to a specific topic.
     *
     * @param topic  the topic to subscribe to
     * @param server the server to connect to
     * @throws CEBSException
     *         if the subscription cannot be added
     **/
    protected void addSubscription(String topic, String server) 
        throws CEBSException
    {
        try
        {
            // get the consumer to that server
            Consumer consumer = (Consumer) connectionMap.get(server);
        
            // make the elvin subscription string
            String subString = "event.topic == '" + topic + "'";
            Subscription sub = new Subscription(subString, this);
            
            // now add it to the consumer
            consumer.addSubscription(sub);
        }
        catch (Exception e)
        {
            String msg = "couldn't add subscription: " + topic + "," + server;
            throw new CEBSException(msg, e);
        }
    }
     
    /**
     * Remove a subscription from an Elvin Consumer-connection.
     *
     * @param topic  the topic to unsubscribe from
     * @param server the server managing that topic
     * @throws CEBSException
     *         if the subscription could not be removed
     **/
    protected void removeSubscription(String topic, String server) 
        throws CEBSException
    {
        try
        {
            // get the consumer to that server
            Consumer consumer = (Consumer) connectionMap.get(server);
            
            // make the elvin subscription string
            String subString = "event.topic == '" + topic + "'";
            Subscription sub = new Subscription(subString, this);
            
            // remove it from the consumer
            consumer.removeSubscription(sub);
        }
        catch (Exception e)
        {
            String msg = "couldn't remove subscription: " + topic + 
                "," + server;
            throw new CEBSException(msg, e);
        }
    }
    
    /**
     * Open a physical Elvin-Consumer connection to a server.
     *
     * @param server the server to connect to
     * @throws CEBSException
     *         if the connection could not be made
     **/
    protected void openRealConnection(String server) throws CEBSException
    {
         try
         {
             // parse the server address to an elvin URL
             String elvinURL = ElvinEventSystem.parseToElvinURL(server);
             
             // now, make a Consumer to that object
             Consumer consumer = new Consumer( new ElvinURL(elvinURL) );
             
             // now put the connection in the map
             connectionMap.put(server, consumer);
         }
         catch (Exception e)
         {
             String msg = "could not open connection to server: " + server;
             throw new CEBSException(msg, e);
         }
    }
    
    /**
     * Close a real connection to the Elvin event server.
     *
     * @param server the server to disconnect from
     **/
    protected void closeRealConnection(String server)
    {  
        // get the consumer and close it
        Consumer consumer = (Consumer) connectionMap.get(server);
        consumer.close();
        
        // remove it from the map
        connectionMap.remove(server);
    }
    
    /**
     * When a Notification is recieved, wrap it in an ElvinEvent and 
     * toss it up.
     **/
    public void notificationAction(Notification notif)
    {
        Event event = new ElvinEvent(notif);
        super.recievedEvent(event);
    }
}