package psl.chime4.server.scs.service;

/**
 * A generic exception in the Service framework.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ServiceException extends Exception
{
   public ServiceException()
   {
      super();
   }
   
   public ServiceException(String msg)
   {
      super(msg);
   }
   
   public ServiceException(String msg, Throwable cause)
   {
      super(msg, cause);
   }
   
   public ServiceException(Throwable cause)
   {
      super(cause);
   }
}