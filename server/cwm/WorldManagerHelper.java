package psl.chime4.cwm;

import psl.chime4.base.ChimeEvent;

/**
 * This is a Helper method that provides an interface to EventProcessors 
 * that need to to communicate and use the WorldManager.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class WorldManagerHelper
{
   /**
    * Get an empty ChimeEvent. This can be used when an EventProcessor must
    * generate a new Event.
    *
    * @return empty ChimeEvent
    **/
   public ChimeEvent getEmptyChimeEvent()
   {
      // return new ChimeEvent
      
       return null;
   }
   
   /**
    * Allow an EventProcessor to send an event. This method may perform
    * network I/O and it therefore may not return for a long time.
    *
    * @param event ChimeEvent to send
    * @param code  Code specifying the type of event from EventConstants
    * @throws IllegalArgumentException if <code>event</code> is 
    *         <code>null</code>
    **/
   public void sendEvent(ChimeEvent event, int code)
   {
      // STUB
   }
}