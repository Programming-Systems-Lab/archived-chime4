package psl.chime4.server.util;

/**
 * An exception thrown when an operation times out.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class TimeOutException extends Exception
{
   public TimeOutException()
   {
      super();
   }
   
   public TimeOutException(String msg)
   {
      super(msg);
   }
   
   public TimeOutException(String msg, Throwable cause)
   {
      super(msg, cause);
   }
   
   public TimeOutException(Throwable cause)
   {
      super(cause);
   }
}