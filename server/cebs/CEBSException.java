package psl.chime4.server.cebs;

/**
 * A generic exception within the Cebs system..
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class CEBSException extends Exception
{
   public CEBSException()
   {
      ; // do nothing
   }
   
   public CEBSException(String msg)
   {
      super(msg);
   }
   
   public CEBSException(String msg, Throwable cause)
   {
      super(msg, cause);
   }
   
   public CEBSException(Throwable cause)
   {
      super(cause);
   }
}
