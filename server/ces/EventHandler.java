package psl.chime4.server.ces;

/**
 * An object capable of consuming Events. After registering with the
 * EventService, an EventHandler will be passed all events that meet a 
 * certain criteria.
 *
 * All EventHandler implementations should follow a few rules: 
 * 
 * EventHandlers should never change Events passed to them or mutate them in
 * any way. This is because the Event may be passed to more than one 
 * EventHandler. If the EventHandler must change the event then it should
 * make a copy of the event. 
 *
 * EventHandlers are also not allowed to perform lengthy operations
 * in the <code>handleEvent</code> method. This method is called by the
 * EventDispatcherThread and no other events can be dispatched as long as 
 * the dispatcher thread is in this method.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface EventHandler
{
   /**
    * Consume an event. If the event is passed, the handler can be sure that
    * it recieved it because it met the necessary criteria.
    *
    * @param event Event that met the handler's criteria
    **/
   public void handleEvent(Event event);
}