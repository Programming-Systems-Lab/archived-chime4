package psl.chime4.server.ces;

import psl.chime4.server.util.TimeOutException;

import java.util.LinkedList;

/**
 * A simple blocking queue for Event objects.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class EventQueue 
{
   /** list of events on the queue **/
   private LinkedList list = new LinkedList();
   
   /** true if the queue has been closed **/
   private boolean closed = false;
   
   /**
    * Place an event on the queue.
    *
    * @param event Event to put on the queue
    * @throws IllegalArgumentException
    *         if <code>event</code> is <code>null</code>
    * @throws IllegalStateException
    *         if the queue has been closed
    **/
   public synchronized final void putEvent(Event event)
   {
      // check for closed
      if (closed)
      {
         String msg = "queue has been closed";
         throw new IllegalStateException(msg);
      }
      
      // check for null
      if (event == null)
      {
         String msg = "event cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      list.addLast(event);
      
      // wake up any blocking threads
      notify();
   }
   
   /**
    * Remove an Event from the queue. If the queue is empty the current 
    * thread will block until another thread places an Event on the queue.
    *
    * @return first Event object on the queue
    * @throws IllegalStateException
    *         if the queue has been closed
    **/
   public synchronized final Event getEvent()
   {
      // check for the closed condition
      if (closed)
      {
         String msg = "queue has been closed";
         throw new IllegalStateException(msg);
      }
      
      try
      {
         while (list.isEmpty())
         {
            // while the list is empty, block
            wait();
            
            // if woken up to a closed queue, throw the exception
            if (closed)
            {
               String msg = "queue has been closed";
               throw new IllegalStateException(msg);
            }
         }
         
         return (Event) list.removeFirst();
      }
      catch (Exception ex)
      {
         // should never happen
         String msg = "internal error in psl.chime4.server.ces.EventQueue";
         throw new Error(msg);
      }
   }
   
   /**
    * Remove an Event from the queue. If the queue is empty the current 
    * thread will block until another thread places an Event on the queue.
    * This method will only block for a given number of seconds.
    *
    * @param timeout number of seconds to block for
    * @return first Event object on the queue
    * @throws IllegalStateException
    *         if the queue has been closed
    * @throws TimeOutException
    *         if the timeout has expired
    **/
   public synchronized final Event getEvent(long timeout) 
      throws TimeOutException
   {
      // check for the closed condition
      if (closed)
      {
         String msg = "queue has been closed";
         throw new IllegalStateException(msg);
      }
      
      // sanitize timeout
      if (timeout < 0)
      {
         timeout = Long.MAX_VALUE;
      }
      
      try
      {
         // figure out when we should top waiting
         long expiration = System.currentTimeMillis() + timeout;
         while (list.isEmpty())
         {
            // figure out how long we have to wait
            timeout = expiration - System.currentTimeMillis();
            
            // if we've waited to long, throw the exception
            if (timeout <= 0)
            {
               throw new TimeOutException();
            }
            
            // otherwise wait for the timeout time
            wait(timeout);
            
            // if woken up to a closed queue, throw the exception
            if (closed)
            {
               String msg = "queue has been closed";
               throw new IllegalStateException(msg);
            }
         }
      }
      catch (InterruptedException ex)
      {
         // any other exception should never happen
         String msg = "internal error in psl.chime4.server.ces.EventQueue";
         throw new Error(msg);
      }
            
      // once we acquire the spin lock the condition is satisfied 
      // so we can go ahead
      return (Event) list.removeFirst();
   }
   
   /**
    * Close the queue so that it can no longer be used. After this method
    * has been called, all operations on the queue will throw an 
    * IllegalStateException. If there are any threads blocking on the queue
    * they will all be woken up and be thrown an IllegalStateException.
    **/
   public final void close()
   {
      // check for closed. if the client is calling close() more than once
      // then it's most likely an error
      if (closed)
      {
         String msg = "queue has been closed";
         throw new IllegalStateException(msg);
      }
      
      closed = true;
      list.clear();
      list = null;
      
      // wakeup all threads
      notifyAll();
   }
   
   /**
    * Delete all events currently on the queue.
    **/
   public synchronized final void clear()
   {
      list.clear();
   }
}