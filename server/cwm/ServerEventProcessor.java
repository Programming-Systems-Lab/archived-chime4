package psl.chime4.server.cwm;

import psl.chime4.server.base.ChimeException;
import psl.chime4.server.base.ChimeEvent;

import java.util.logging.Logger;

/**
 * A ServerEventProcessor is an object capable of handling events that the
 * server recieves. It abstracts the logic needed to handle a certain class
 * of events.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface ServerEventProcessor
{
   /**
    * Initialize the event processor instruct it to perform any startup
    * operations it may need.
    *
    * @param logger Logger that the processor should use for all its logging
    *               purposes
    * @param cwm    WorldManager that is using this processor
    * @throws ChimeException if something goes wrong
    **/
   public void startup(Logger logger, WorldManager cwm) 
      throws ChimeException;
   
   /**
    * Ask the processor to process a given event.
    *
    * @param event ChimeEvent containing relevant information
    * @throws ChimeException if something goes wrong
    **/
   public void processEvent(ChimeEvent event) throws ChimeException;
   
   /**
    * Ask the processor to shutdown and perform any necessary cleanup 
    * operations.
    *
    * @throws ChimeException if something goes wrong
    **/
   public void shutdown() throws ChimeException;
   
}
