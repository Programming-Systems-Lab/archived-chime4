package psl.chime4.server.cebs.siena;

import psl.chime4.server.cebs.EventReciever;
import psl.chime4.server.cebs.Event;
import psl.chime4.server.cebs.CEBSException;
import psl.chime4.server.cebs.BlockingEventQueue;

import siena.Notifiable;
import siena.Notification;
import siena.ThinClient;
import siena.Filter;
import siena.Op;

import java.util.Map;
import java.util.HashMap;

/**
 * An EventReciever for abstracting the mechanism of recieving events over
 * Siena.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
class SienaEventReciever extends EventReciever implements Notifiable
{
    /** map of connection objects to various servers **/
    private Map connectionMap = new HashMap();
    
    /**
     * Construct a SienaEventReciever attached to a specific blocking queue.
     *
     * @param queue the blocking queue to put incoming events on
     **/
    SienaEventReciever(BlockingEventQueue queue)
    {
        super(queue);
    }
    
    /**
     * Add a subscription to a Siena-ThinClient connection. All subscriptions
     * are done by filtering events based on the special 'event.topic' 
     * attribute. 
     *
     * @param topic  the topic to subscribe to
     * @param server the server managing <code>topic</code>
     * @throws CEBSException
     *         if the subscription could not be made
     **/
    protected void addSubscription(String topic, String server) 
        throws CEBSException
    {
        try
        {
            // get the thin client to that server
            ThinClient client = (ThinClient) connectionMap.get(server);
            
            // make the Siena subscription
            Filter sub = new Filter();
            sub.addConstraint("event.topic", topic);
            
            // subscribe to the server
            client.subscribe(sub, this);
        }
        catch (Exception e)
        {
            String msg = "couldn't add subscription: " + topic + "," + server;
            throw new CEBSException(msg, e);
        }
    }
    
    /**
     * Remove a subscription from a Siena-ThinClient connection.
     *
     * @param topic  the topic to unsubscribe from
     * @param server server managing the topic
     * @throws CEBSException
     *         if couldn't unsubscribe from <code>topic</code>
     **/
    protected void removeSubscription(String topic, String server) 
        throws CEBSException
    {
        try
        {
            // get the thin client to that server
            ThinClient client = (ThinClient) connectionMap.get(server);
            
            // make the Siena subscription
            Filter sub = new Filter();
            sub.addConstraint("event.topic", topic);
            
            // ubsubscribe from the server
            client.unsubscribe(sub, this);
        }
        catch (Exception e)
        {
            String msg = "couldn't remove subscription: " + topic + "," 
                + server;
            throw new CEBSException(msg, e);
        }
    }
    
    /** 
     * Open a real connection to a Siena event server.
     *
     * @param server the server to connect to
     * @throws CEBSException
     *         if the connection could not be made
     **/
    protected void openRealConnection(String server) 
        throws CEBSException
    {
        try
        {
            // parse the server to a siena url
            String sienaURL = SienaEventSystem.parseToSienaURL(server);
            
            // make a new ThinClient to that server
            ThinClient client = new ThinClient(sienaURL);
            
            // put it in the connection map
            connectionMap.put(server, client);
        }
        catch (Exception e)
        {
            String msg = "couldn't connect to server: " + server;
            throw new CEBSException(msg, e);
        }
    }
    
    /**
     * Close the real connection to a Siena event server.
     *
     * @param server the server to disconnect from
     **/
    protected void closeRealConnection(String server)
    {
        // get the client and close it
        ThinClient client = (ThinClient) connectionMap.get(server);
        client.shutdown();
        
        // now remove it from the map
        connectionMap.remove(server);
    }
    
    /**
     * Called when a Notification is recieved.
     *
     * @param notif the Notification recieved
     **/
    public void notify(Notification notif)
    {
        // wrap it in an event and give it up for processing
        Event event = new SienaEvent(notif);
        super.recievedEvent(event);
    }
    
    /**
     * This method is never called.
     **/
    public void notify(Notification[] notif)
    {
        ; // do nothing
    }
}