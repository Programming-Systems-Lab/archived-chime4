package psl.chime4.server.cebs;

/**
 * An EventHandler object encapsulates the logic required to handle a certain
 * class of events.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface EventHandler extends java.io.Serializable
{
    /**
     * Handle a given Event by executing the event logic needed to process 
     * it. 
     *
     * Note that the Event Dispatcher thread executes this method and it is
     * very important that this method execute in a reasonable amount of time
     * so that the Event Dispatcher can continue passing events to other
     * event handlers. If an EventHandler needs more than a reasonable amount
     * of time to handle an event it must start its own thread in order to
     * handle that event.
     *
     * @param event the event to be handled
     **/
    public void handleEvent(Event event);
}