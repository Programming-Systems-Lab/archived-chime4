package psl.chime4.server.scs.log;

import psl.chime4.server.scs.service.*;

/**
 * Defines an object that is capable of logging messages to some persistent
 * medium.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface LoggingService extends Service
{
   /**
    * Log a configuration message.
    *
    * @param cat category for the logging message
    * @param msg logging message
    **/
   public void config(String cat, String msg);
   
   /**
    * Log an informational message.
    *
    * @param cat category for the logging message
    * @param msg logging message
    **/
   public void info(String cat, String msg);
   
   /**
    * Log a warning message.
    *
    * @param cat category for the logging message
    * @param msg log message
    **/
   public void warning(String cat, String msg);
   
   /**
    * Log an exception generated during the runtime.
    *
    * @param cat   category for the logging message
    * @param msg   log message
    * @param cause exception thrown
    **/
   public void throwing(String cat, String msg, Throwable cause);
   
   /**
    * Log a severe warning.
    *
    * @param cat category for the logging service
    * @param msg log message
    **/
   public void severe(String cat, String msg);
}