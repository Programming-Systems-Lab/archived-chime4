package psl.chime4.server.cebs;

import java.util.LinkedList;

/**
 * BlockingEventQueue provides a simple mechanism for passing events between
 * threads. Producer threads can asynchronously place events on the queue,
 * while consumer threads can synchronously remove events from the queue. If
 * the queue is empty when a consumer thread attempts to remove an event from
 * the queue the consume thread will block until another event is placed on
 * the queue.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class BlockingEventQueue 
{
    /** list of events to stored on the queue **/
    private LinkedList list = new LinkedList();
    
    /** true if the queue is closed **/
    private boolean closed = false;
    
    /**
     * Place an event on the queue.
     *
     * @param event Event object to place on the queue
     * @throws IllegalArgumentException
     *         if <code>event</code> is <code>null</code>
     * @throws IllegalStateException
     *         if the queue has been closed
     **/
    public synchronized final void put(Event event)
    {
        // add it to the list
        list.addLast( event );
        
        // wake up any blocking threads
        notify();
    }
    
    /**
     * Remove an Event from the queue. If the queue is empty then the calling
     * thread blocks until another Event is added to the queue.
     *
     * @return Event object from the queue
     * @throws IllegalStateException
     *         if the queue has been closed
     **/
    public synchronized final Event get()
    {
        try
        {
            while ( list.size() <= 0 )
            {
                // while the list is empty, wait
                wait();
            
                // if woken up to find the queue is empty, throw the exception
                if ( closed )
                {
                    String msg = "queue has been closed";
                    throw new IllegalStateException(msg);
                }
            }
        
            return (Event) list.removeFirst();
        }
        catch ( Exception ex )
        {
            // should never happen
            String msg = "internal error in server.cebs.BlockingEventQueue";
            throw new Error(msg);
        }
    }
    
    /**
     * Close the queue so that it continue no longer be used. Any consumer
     * threads blocking in the get method will be woken up and an 
     * IllegalStateException will be thrown.
     *
     * Once this method is called any further attempts to use the queue will
     * result in an IllegalStateException.
     **/
    public final void close()
    {
        closed = true;
        
        // wake up all threads
        notifyAll();
        
        // empty the list
        list = null;
    }
    
    /**
     * Delete all Event objects on the queue.
     **/
    public synchronized final void clear()
    {
        list.clear();
    }
}