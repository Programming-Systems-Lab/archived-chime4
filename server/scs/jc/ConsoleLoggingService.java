package psl.chime4.server.scs.jc;

import psl.chime4.server.scs.service.*;
import psl.chime4.server.scs.log.LoggingService;

/**
 * A simple implementation of the LoggingService that logs everything to the
 * console.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ConsoleLoggingService extends AbstractService 
   implements LoggingService
{
   
   /**
    * Log a configuration message.
    *
    * @param cat category for the logging message
    * @param msg logging message
    */
   public void config(String cat, String msg)
   {
      System.out.println("\nCONFIG:[" + cat + "]:" + msg);
   }
   
   /**
    * Log an informational message.
    *
    * @param cat category for the logging message
    * @param msg logging message
    */
   public void info(String cat, String msg)
   {
      System.out.println("\nINFO:[" + cat + "]:" + msg);
   }
   
   /**
    * Log a severe warning.
    *
    * @param cat category for the logging service
    * @param msg log message
    */
   public void severe(String cat, String msg)
   {
      System.out.println("\nSEVERE:[" + cat + "]:" + msg);
   }
   
   /**
    * Log an exception generated during the runtime.
    *
    * @param cat   category for the logging message
    * @param msg   log message
    * @param cause cause of the log msg
    */
   public void throwing(String cat, String msg, Throwable cause)
   {
      System.out.println("\nTHROW:[" + cat + "]:" + msg + ":" + cause);
   }
   
   /**
    * Log a warning message.
    *
    * @param cat category for the logging message
    * @param msg log message
    */
   public void warning(String cat, String msg)
   {
      System.out.println("\nWARNING:[" + cat + "]:" + msg);
   }
   
}