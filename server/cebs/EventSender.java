package psl.chime4.server.cebs;

/**
 * An EventSender is used to send Event objects to different event servers.
 * This interface abstracts the underlying protocol/mechanism actually used
 * for sending events.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class EventSender
{
    /** counter used to give all events a unique id **/
    private int eventIDCounter = 0;
    
    /** the user ID of the network entity sending events **/
    private int sourceUID;
    
    /**
     * map for keeping track of how many times a connection to an actual 
     * server has been opened
     **/
    private CounterMap connectionCountMap = new CounterMap();
    
    /**
     * Construct a new EventSender with a given source ID.
     *
     * @param sourceUID the user ID of the entity sending events
     **/
    protected EventSender(int sourceUID)
    {
        this.sourceUID = sourceUID;
    }
    
    /**
     * Open a connection to an event server. Once you have opened a
     * connection to an event server you can begin sending events to it. If
     * you are already connected to an event server and you try to connect 
     * again this method does nothing.
     *
     * @param server the event server to open the connection to
     * @throws IllegalArgumentException
     *         if <code>server</code> is <code>null</code>
     * @throws CEBSException
     *         if a connection could not be made to <code>server</code>
     **/
    public void openConnection(String server) throws CEBSException
    {
        // check for null
        if (server == null)
        {
            String msg = "server cannot be null";
            throw new IllegalArgumentException(msg);
        }
        
        // if we're not connected to the server already
        if (! isConnectedToServer(server) )
        {
            // open the physical connection
            openRealConnection(server);
        }
        
        // increment the count for connections to that server
        connectionCountMap.incrementCounter(server);
    }
    
    /**
     * Open a real connection to an event server. A call to 
     * {@link #openConnection(String)} does not  create a real connection. 
     * The first time a connection is opened to a given server a real 
     * connection is created. From then on, opening a connection to the 
     * same server results in creating a 'virtual connection' -- usually a 
     * counter is simply incremented. In order for this system to work,
     * subclasses should reuse their Connection objects.
     *
     * @param server the server to connect to
     **/
    protected abstract void openRealConnection(String server) 
        throws CEBSException;
    
    /**
     * Determine if a connection exists to a given server. This works only
     * if the EventSender depends on stateful/persistent connections.
     *
     * @param server address of the server to connect to
     **/
    protected boolean isConnectedToServer(String server)
    {
        return connectionCountMap.containsKey(server);
    }
    
    /**
     * Send an Event to a given event server. In order for the event to be
     * sent it must contain a valid topic set using 
     * {@link Event#setTopic(String)}.
     *
     * @param server the CEBS address of the event server
     * @param event  the event to send
     * @throws CEBSException
     *         if <code>event</code> could not be sent to <code>server</code>
     * @throws IllegalArgumentException
     *         if <code>server</code> or <code>event</code> is 
     *          <code>null</code> or <code>event</code> does not contain
     *         a valid topic
     * @throws IllegalStateException
     *         if no connection was ever opened to <code>server</code>
     **/
    public void publishEvent(String server, Event event) throws CEBSException
    {
        // check for null and a valid topic
        if ( (event == null) || (event.getTopic() == null) 
             || (server == null) )
        {
            String msg = "server, event or event.topic cannot be null";
            throw new IllegalArgumentException(msg);
        }
        
        // make sure we are connected to that server
        if ( !isConnectedToServer(server) )
        {
            String msg = "cannot send event before opening connection";
            throw new IllegalStateException(msg);
        }
        
        // set the event id
        event.setEventID(eventIDCounter++);
        
        // set the send time
        event.setSendTime(System.currentTimeMillis());
        
        // set the source id of who set this event
        event.setSourceUID( sourceUID );
        
        // set where this event is being sent
        event.setSourceEventServer(server);
        
        // now send the actual event
        sendEvent(server, event);
    }
    
    /**
     * Send an actual event to a given server.
     *
     * @param server the server to send the event to
     * @param event  the event to send
     * @throws CEBSException if <code>event</code> could not be sent to
     *                       <code>server</code>
     **/
    protected abstract void sendEvent(String server, Event event) 
        throws CEBSException;
    
    /**
     * Close a connection to an event server. Once you have closed the 
     * connection you can no longer send events to that event server.
     *
     * @param server the event server to close the connection to
     * @throws IllegalArgumentException
     *         if <code>server</code> is <code>null</code>
     **/
    public void closeConnection(String server)
    {
        // decrement the connection count to the server
        connectionCountMap.decrementCounter(server);
        
        // if the connection count is zero, then close the real connection
        if ( connectionCountMap.getCount(server) <= 0 )
        {
            closeRealConnection(server);
        }
    }
    
    /** 
     * Close a real connection to the server. A call to 
     * {@link #closeConnection(String)} does not actually close a connection.
     * Only when <code>closeConnection</code> is called the same amount of
     * times as {@link #openConnection(String)} will the call to 
     * <code>closeConnection</code> result in the actual connection being
     * closed.
     *
     * @param server the event server to close the connection to
     **/
    protected abstract void closeRealConnection(String server);
}