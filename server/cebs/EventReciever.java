package psl.chime4.server.cebs;

import java.util.Map;
import java.util.HashMap;

/**
 * An EventReciever provides a mechanism for recieving events. This class 
 * abstracts the underlying mechansim and protocol used for recieving events.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class EventReciever
{
    /** the map of EventHandlers **/
    private Map eventHandlerMap = new HashMap();
    
    /** the blocking queue to put all events on **/
    private BlockingEventQueue queue;
    
    /** 
     * Map for storing the number of times a connection has been opened to
     * a given server.
     **/
    private CounterMap connectionCountMap = new CounterMap();
    
    /**
     * Construct an EventReciever attached to the appropriate queue.
     *
     * @param queue the queue to put events on when the come in
     * @throws BlockingEventQueue
     *         if <code>queue</code> is <code>null</code>
     **/
    protected EventReciever(BlockingEventQueue queue)
    {
        // check for null
        if (queue == null)
        {
            String msg = "queue cannot be null";
            throw new IllegalArgumentException(msg);
        }
        
        this.queue = queue;
    }
    
    /** 
     * Register an EventHandler under a given topic. When events are
     * recieved from the given server under that topic they will be passed
     * to the event handler.
     *
     * @param topic   the topic to register the EventHandler under
     * @param server  the server to recieve events from
     * @param handler the EventHandler to actually handle the events
     * @throws IllegalArgumentException
     *         if any paramter is <code>null</code>
     * @throws IllegalStateException
     *         if you haven't connected to <code>server</code> yet
     * @throws CEBSException
     *         if it was impossible to register under a given topic
     **/
    public void registerEventHandler(String topic, String server,
                                     EventHandler handler) 
        throws CEBSException
    {
        // check for null
        if ( (topic == null) || (server == null) || (handler == null) )
        {
            String msg = "no parameter can be null";
            throw new IllegalArgumentException(msg);
        }
        
        // make sure we're already connected to that server
        if ( !isConnectedToServer(server) )
        {
            String msg = "not connected to server: " + server;
            throw new IllegalStateException(msg);
        }
        
        // now put it in the map
        eventHandlerMap.put(topic + "-" + server, handler);
        
        // now add the actual subscription to that server
        addSubscription(topic, server);
    }
    
    /**
     * Begin listening to a specific topic as published by a specific server.
     * 
     * @param topic  the topic to begin listening to
     * @param server the server managing that topic
     * @throws CEBSException 
     *         if it was impossible to register under that topic
     **/
    protected abstract void addSubscription(String topic, String server)
        throws CEBSException;
    
    /**
     * Determine if the reciever has already connected to a given server. If
     * the reciever is connected it's generally recieving events from that
     * server.
     *
     * @param server the server to test the connection to
     */
    protected boolean isConnectedToServer(String server)
    {
        return connectionCountMap.containsKey(server);
    }
    
    /**
     * Unregister an EventHandler from a given topic. It will no longer
     * recieve events published under a given topic from a given server.
     *
     * @param topic   the topic to register the EventHandler under
     * @param server  the server events are coming from
     * @param handler the handler that was recieving events
     * @throws CEBSException
     *         if it was impossible to unregister from the given topic
     * @throws IllegalArgumentException
     *         if <code>topic</code> or <code>server</code> is 
     *         <code>null</code>
     * @throws IllegalStateException
     *         if no connection was ever opened to <code>server</code>
     **/
    public void unregisterEventHandler(String topic, String server, 
                                       EventHandler handler) 
        throws CEBSException
    {
        // check for null
        if ( (topic == null) || (server == null) )
        {
            String msg = "topic nor server cannot be null";
            throw new IllegalArgumentException(msg);
        }
        
        // make sure we're already connected to that server
        if ( !isConnectedToServer(server) )
        {
            String msg = "not connected to server: " + server;
            throw new IllegalStateException(msg);
        }
        
        // remove it from the map
        eventHandlerMap.remove(topic + "-" + server);
        
        // now remove the actual subscription
        removeSubscription(topic, server);
    }
    
    /**
     * Stop listening to a specific topic from a specific server.
     * 
     * @param topic  the topic to stop listening to
     * @param server the server managing that topic
     * @throws CEBSException
     *         if it was impossible to delist from the topic
     **/
    protected abstract void removeSubscription(String topic, String server) 
        throws CEBSException;
    
    /**
     * Get the EventHandler registered under a given handlerID.
     *
     * @param topic the handlerID the EventHandler is registered under
     * @return EventHandler registered under <code>handlerID</code> or 
     *         <code>null</code> if no EventHandler is registered under 
     *         <code>handlerID</code>
     **/
    public EventHandler getEventHandler(String topic, String server)
    {
        return (EventHandler) eventHandlerMap.get(topic + "-" + server);
    }
    
    
    /**
     * Open a connection to an event server. Once a connection is made, the
     * entity can begin recieving events from that server. 
     *
     * @param server address of the server to connect to
     * @throws CEBSException
     *         if the connection could not be made
     * @throws IllegalArgumentException
     *         if <code>server</code> is <code>null</code>
     **/
    public void openConnection(String server) throws CEBSException
    {
        // check for null
        if ( server == null )
        {
            String msg = "server cannot be null";
            throw new IllegalArgumentException(msg);
        }
        
        // if we aren't already connected to the server
        if ( !isConnectedToServer(server) )
        {
            // open the physical connection
            openRealConnection(server);
        }
        
        // increment the # of connections to the server
        connectionCountMap.incrementCounter(server);
    }
    
    /**
     * Open a physical connection to the event server. Each call to 
     * {@link #openConnection(String)} does not open an actual connection,
     * instead, only the first request to connect to a specific server 
     * opens the physical connection. This is done to prevent several 
     * processes from opening the same connection again and again and to
     * prevent one process from closing a connection still in use by another
     * process.
     *
     * @param server the server to open the connection to
     * @throws CEBSException
     *         if the connection could not be made
     **/
    protected abstract void openRealConnection(String server) 
        throws CEBSException;
    
    /**
     * Close a connection to an event server. Once a connection is closed, 
     * you can no longer recieve events from that event server.
     *
     * @param server address of the server to close the connection to
     **/
    public void closeConnection(String server)
    {
        // decrement the # of connections to the server
        connectionCountMap.decrementCounter(server);
        
        // if there are no more connections, close the real connection
        if ( connectionCountMap.getCount(server) <= 0 )
        {
            closeRealConnection(server);
        }
    }
    
    /**
     * Close the real connection to a server.
     *
     * @param server the server to close the actual connection to
     **/
    protected abstract void closeRealConnection(String server);
    
    /**
     * Everytime an event is recieved, subclasses should must call this 
     * method in order to place the event on the incoming events queue.
     *
     * @param event the event that was recieved
     **/
    protected void recievedEvent(Event event)
    {
        if (event != null)
        {
            // set the time the event was recieved
            event.setRecvTime(System.currentTimeMillis());
            queue.put(event);
        }
    }
}