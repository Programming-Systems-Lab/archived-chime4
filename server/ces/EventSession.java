package psl.chime4.server.ces;

import psl.chime4.server.util.TimeOutException;

/**
 * An EventSession allows a client to simulate synchronous "RPC-like" 
 * programming over an asynchronous event system. Because event systems are
 * totally asynchronous performing RPC-like processes like authentication 
 * over them can be difficult and often requires the creation of complex 
 * state machines. Using an EventSession, clients can send events and then
 * block until a reply is recieved. 
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class EventSession implements EventHandler
{
   /** true if the session is open **/
   private boolean sessionOpen = false;
   
   /** the host of the event server **/
   private String host;
   
   /** port of the event server **/
   private int port;
   
   /** the topic to listen to **/
   private String topic;
   
   /** low-level event service **/
   private EventService eventService;
   
   /** event queue for storing replies **/
   private EventQueue eventQueue = new EventQueue();
   
   /**
    * Open an EventSession that's connected to a given low-level 
    * EventService.
    *
    * @param eventService low-level EventService used to send and retrieve
    *                     events
    * @throw IllegalArgumentException
    *        if <code>eventService</code> is <code>null</code>
    **/
   public EventSession(EventService eventService)
   {
      // check for null
      if (eventService == null)
      {
         String msg = "eventService cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.eventService = eventService;
   }
   
   /**
    * Open the EventSession to begin sending and recieving events.
    *
    * @param host  host of the event server
    * @param port  port of the event server process
    * @param topic the topic to send and recieve to
    * @throws IllegalStateException
    *         if the service is already open
    * @throws EventServiceException
    *         if the opening failed
    **/
   public void openSession(String host, int port, String topic)
      throws EventServiceException
   {
      // try to open it on the event service and register as an event handler
      eventService.openConnection(host, port);
      eventService.registerEventHandler(host, port, topic, this);
      
      sessionOpen = true;
   }
   
   /**
    * Close a given session to stop sending and recieving events.
    *
    * @throws EventServiceException
    *         if the session could not be closed
    **/
   public void closeSession() throws EventServiceException
   {
      // unregister the event handler
      eventService.unregisterEventHandler(host, port, topic, this);
      // close the connection
      eventService.closeConnection(host, port);
      
      sessionOpen = false;
   }
   
   /**
    * Publish an event.
    *
    * @param event the event to send over the session
    * @throws IllegalStateException
    *         if the session is closed
    * @throws EventServiceException
    *         if the event could not be sent
    **/
   public void publish(Event event) throws EventServiceException
   {
      // check for open status
      if (!sessionOpen)
      {
         String msg = "session is closed";
         throw new IllegalStateException(msg);
      }
      
      eventService.sendEvent(host, port, topic, event);
   }
   
   /**
    * Called when an event comes in through the session by the event 
    * dispatcher thread.
    *
    * @param event the event that came in
    **/
   public void handleEvent(Event event)
   {
      // put it on the queue
      eventQueue.putEvent(event);
   }
   
   /**
    * Get a reply from the end point of the Event Session. If no reply is
    * available, then the calling thread will block until one is available.
    *
    * @return Event consisting of the reply
    **/
   public Event getReply()
   {
      return eventQueue.getEvent();
   }
   
   /**
    * Get a reply from the end point of the Event Session. If no reply is 
    * available, then the calling thread will block until one is available
    * or a given timeout is exceeded.
    *
    * @param timeout the maximum amount of time to wait
    * @return Event consisting of the reply
    * @throw TimeOutException
    *        if <code>timeout</code> is exceeded
    **/
   public Event getReply(long timeout) throws TimeOutException
   {
      return eventQueue.getEvent(timeout);
   }
}