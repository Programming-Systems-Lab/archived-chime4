package psl.chime4.server.scs.jc.command;

import psl.chime4.server.scs.*;
import psl.chime4.server.scs.jc.*;
import psl.chime4.server.scs.ws.event.*;
import psl.chime4.server.ces.*;

/**
 * Tells the client to enter a given world. The syntax is:
 *
 * enter_world: <worldTopic>
 *
 * Sends a WorldEntryEvent to the world server.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class WorldEntryCommand extends Command
{
   
   /**
    * Tell the command to execute its function.
    *
    * @param client client that executed the command
    * @param input  what the client typed
    * @throws Exception
    *        if the command cannot be executed
    */
   public void execute(Client client, String input) throws Exception
   {
      // get the world topic to publish to
      ClientState state = client.getState();
      
      WorldEvent worldEntry = new WorldEntryEvent(state.Username);
      Event event = client.EventService.createEmptyEvent();
      event = worldEntry.toCesEvent(event);
      
      client.EventService.publish(state.EventServer, state.EventServerPort, 
                                  state.WorldTopic, event);
   }
   
}