package psl.chime4.server.cebs;

/**
 * Base exception for all exceptions in Cebs.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class CebsException extends Exception
{
   public CebsException()
   {
      ; // do nothing
   }
   
   public CebsException(String msg)
   {
      super(msg);
   }
   
   public CebsException(String msg, Throwable cause)
   {
      super(msg, cause);
   }
   
   public CebsException(Throwable cause)
   {
      super(cause);
   }
}