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
   /** blocking queue for recieving incoming events **/
   private BlockingEventQueue eventQueue = new BlockingEventQueue();
   
   /** the event dispatcher thread used to dispatch events **/
   private EventDispatcherThread dispatchThread;
   
   /** amount of time a connection has been made to a server **/
   private CounterMap connectionCount = new CounterMap();
   
   /**
    * Construct an EventSystem interface object. This method is protected to
    * prevent client instantiation.
    **/
   protected EventSystem()
   {
      ; // do nothing
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
      dispatchThread = new EventDispatcherThread(this);
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
    * Create an empty Event.
    *
    * @return an empty Event
    **/
   public abstract Event createEmptyEvent();
}