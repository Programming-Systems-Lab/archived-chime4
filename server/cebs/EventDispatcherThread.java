package psl.chime4.server.cebs;

/**
 * The EventDispatcherThread removes incoming Events from the queue and 
 * passes them on to registered EventHandlers.
 *
 * @author Azubuko 
 * @version 0.1
 **/
class EventDispatcherThread extends Thread
{
    /** true if the thread is running **/
    private boolean running = false;
    
    /** the blocking queue to remove events from **/
    private BlockingEventQueue incomingEvents;
    
    /** the event reciever the thread gets EventHandlers from **/
    private EventReciever eventReciever;
    
    /**
     * Construct a new EventDispatcherThread.
     *
     * @param blockingQueue the queue to remove incoming events from
     * @param eventReciever the reciever that contains event handlers
     * @throws IllegalArgumentException
     *         if <code>blockingQueue</code> or <code>eventReciever</code> 
     *         is <code>null</code>
     **/
    EventDispatcherThread(BlockingEventQueue blockingQueue, 
                          EventReciever eventReciever)
    {
        // check for null
        if ( (blockingQueue == null) || (eventReciever == null) )
        {
            String msg = "no parameter can be null";
            throw new IllegalArgumentException(msg);
        }
        
        this.incomingEvents = blockingQueue;
        this.eventReciever = eventReciever;
    }
    
    /**
     * Startup the EventDispatcherThread so it will now begin running.
     **/
    public void startup()
    {
        running = true;
        super.start();
    }
    
    /**
     * Shutdown the EventDispatcherThread so it will stop running.
     **/
    public void shutdown()
    {
        running = false;
    }
    
    /**
     * The main thread method. Continously try to remove events from the
     * blocking queue and pass them to the correct event handler.
     **/
    public void run()
    {
        while (running)
        {
            try
            {
                // remove the next event
                Event event = incomingEvents.get();
                
                // get the correct EventHandler listening to this topic
                // and from this server
                String topic = event.getTopic();
                String server = event.getSourceEventServer();
                EventHandler handler = 
                    eventReciever.getEventHandler(topic, server);
                
                // if there is one, ask the handler to handle it
                if (handler != null)
                {
                    handler.handleEvent( event );
                }
            }
            catch (Exception e)
            {
                // stop the thread from running
                shutdown();
            }
        }
    }
}