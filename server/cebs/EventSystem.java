package psl.chime4.server.cebs;

/**
 * An EventSystemInterface object provides access to the underlying mechanism
 * for sending and recieving events. It abstracts the underlying transport
 * protocol used for sending and recieving events in a publish/suscribe 
 * event system.
 * <p>
 * If the underlying event protocol allows it, a network entity can recieve
 * events from many different event servers.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class EventSystem
{
    /** the UID of the network entity using this event system **/
    protected int sourceUID;
    
    /** blocking queue for recieving incoming events **/
    protected BlockingEventQueue eventQueue = new BlockingEventQueue();
    
    /** the event dispatcher thread used to dispatch events **/
    private EventDispatcherThread dispatchThread;
    
    /** event reciever used to manage the recieving of events **/
    protected EventReciever eventReciever;
    
    /** event sender used to manage the sending of events **/
    protected EventSender eventSender;
    
    /**
     * Construct an EventSystem interface object with a given sourceUID.
     *
     * @param sourceUID user ID of the entity using this event system
     **/
    protected EventSystem(int sourceUID)
    {
        this.sourceUID = sourceUID;
        // setup the event dispatcher thread
        dispatchThread = new EventDispatcherThread(eventQueue, 
                                                   getEventReciever());
    }
    
    /**
     * Start the event system. Once this method is called you can begin
     * recieving events.
     *
     * @throws CEBSException 
     *         if the event system could not be started for some reason
     **/
    public void startup() throws CEBSException
    {
        // start the dispatcher thread
        dispatchThread.startup();
    }
    
    /**
     * Shutdown the event system. You will no longer recieve events or be
     * able to send events.
     **/
    public void shutdown()
    {
        // shutdown the dispatcher thread
        dispatchThread.shutdown();
    }
    
    /**
     * Get the EventSender that can be used to send events.
     *
     * @return EventSender used to send events.
     **/
    public EventSender getEventSender()
    {
        return eventSender;
    }
    
   /**
    * Get the EventReciever that can be used for recieving events.
    *
    * @return EventReciever used to recieve events.
    **/
    public EventReciever getEventReciever()
    {
        return eventReciever;
    }
}