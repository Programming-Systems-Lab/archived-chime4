package psl.chime4.server.base;

/**
 * The base class for all exceptions within the Chime server.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ChimeException extends Exception
{
   public ChimeException()
   {
      ; // do nothing
   }
   
   public ChimeException(String detail)
   {
      super(detail);
   }
   
   public ChimeException(String detail, Throwable cause)
   {
      super(detail, cause);
   }
   
   public ChimeException(Throwable cause)
   {
      super(cause);
   }
}