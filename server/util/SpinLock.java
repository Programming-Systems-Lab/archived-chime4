package psl.chime4.server.util;

/**
 * A simple SpinLock that waits for some condition to be true.
 *
 * This code is borrowed heavily from Allen Holub's 
 * <em>Taming Java Threads</em>.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public final class SpinLock
{
   /**
    * A Strategy object that lets the SpinLock know when it's condition
    * has been satisfied.
    *
    * @author Azubuko Obele
    * @version 0.1
    **/
   public interface Condition
   {
      /**
       * Determine if the condition has been met.
       *
       * @return <code>true</code> if the condition has been satisfied
       *         else <code>false</code>
       **/
      public boolean isSatisfied();
   }
   
   /**
    * Acquire the spin lock. This causes the calling thread to block until
    * some condition is satisfied or until the timeout expires.
    *
    * @param condition the condition to wait for
    * @param timeout   the maximum amount of time to wait
    * @throws IllegalArgumentException
    *         if <code>condition</code> is <code>false</code>
    * @throws TimeOutException
    *         if the timeout expires
    * @throws InterruptedException
    *         if another thread interrupts the wait
    **/
   public synchronized void acquire(Condition condition, long timeout)
      throws TimeOutException, InterruptedException
   {
      // check for null
      if (condition == null)
      {
         String msg = "condition cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // make sure timeout is reasonable
      if (timeout < 0)
      {
         timeout = Long.MAX_VALUE;
      }
      
      long expiration = System.currentTimeMillis() + timeout;
      
      // keep looping while the condition isn't satisfied
      while (!condition.isSatisfied())
      {
         timeout = expiration - System.currentTimeMillis();
         
         // check for the timeout condition
         if (timeout <= 0)
         {
            throw new TimeOutException("lock timed out");
         }
         
         // wait for the given amount of time
         wait(timeout);
      }
   }
   
   /**
    * Release the lock, notifying any threads waiting on the lock.
    **/
   public synchronized void release()
   {
      notify();
   }
}

