package psl.chime4.server.ces;

/**
 * The Event Dispatcher is the background thread of the Event Service 
 * responsible for continously passing events onto EventHandlers.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
class EventDispatcher extends Thread
{
   /** true if the thread is running **/
   private boolean running = false;
   
   /** event queue to retrieve incoming events from **/
   private EventQueue incomingEvents;
   
   /** event handler multimap to get events from **/
   EventHandlerMultiMap handlerMultiMap;
   
   /**
    * Construct a new EventDispatcher.
    *
    * @param eventQueue      queue containing incoming events
    * @param handlerMultiMap multimap containing event handlers
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    **/
   public EventDispatcher(EventQueue eventQueue, 
                          EventHandlerMultiMap handlerMultiMap)
   {
      // check for null
      if ((eventQueue == null) || (handlerMultiMap == null))
      {
         String msg = "no parameter may be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.incomingEvents = eventQueue;
      this.handlerMultiMap = handlerMultiMap;
   }
   
   /**
    * Start the EventDispatcher.
    **/
   public void startup()
   {
      running = true;
      super.start();
   }
   
   /**
    * Shutdown the EventDispatcher.
    **/
   public void shutdown()
   {
      running = false;
   }
   
   /**
    * Continually remove events from the event queue and pass them on to
    * the approrpriate handler.
    **/
   public void run()
   {
      while (running)
      {
         try
         {
            // remove the next incoming events
            Event event = incomingEvents.getEvent();
            
            //System.out.println("ED: got event: " + event);
            
            // get the routing information
            String topic = event.getTopic();
            String host = event.getEventServerHost();
            int port = event.getEventServerPort();
            
            //System.out.println("ED: topic=" + topic + ",host=" + host + 
             //  ",port=" + port);
            
            // make sure the routing information was set
            if ((topic == null) || (host == null) || (port <= 0))
            {
               String msg = "event contained no routing information " + event;
               throw new IllegalStateException(msg);
            }

            // get the correct handler
            EventHandler handler = 
               handlerMultiMap.getEventHandler(host, port, topic);
            
            // if there is a handler, pass the event on
            if (handler != null)
            {
               handler.handleEvent(event);
            }
         }
         catch (Exception e)
         {
            // stop the thread
            shutdown();
         }
      }
   }
}
            