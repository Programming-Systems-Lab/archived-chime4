package psl.chime4.server.cwm.world.persist;

/**
 * General exception for the persistence framework.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class PersistenceServiceException extends Exception
{
   public PersistenceServiceException()
   {
      ; // do nothing
   }
   
   public PersistenceServiceException(String msg)
   {
      super(msg);
   }
   
   public PersistenceServiceException(String msg, Throwable cause)
   {
      super(msg, cause);
   }
   
   public PersistenceServiceException(Throwable cause)
   {
      super(cause);
   }
}