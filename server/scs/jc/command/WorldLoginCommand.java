package psl.chime4.server.scs.jc.command;

import psl.chime4.server.ces.*;
import psl.chime4.server.scs.jc.*;
import psl.chime4.server.scs.ws.event.*;
import psl.chime4.server.scs.*;

import java.util.*;

/**
 * Used by the client to login into the world. It's of the form:
 *
 * login: {server} {port} {world uniqueTopic} {username} {pass}
 *
 * This command will then send a WorldLoginEvent to the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class WorldLoginCommand extends Command
{
   public void execute(Client client, String input) throws Exception
   {
      // parse out the arguments
      StringTokenizer tokenizer = new StringTokenizer(input);
      String eServer = tokenizer.nextToken();
      int eServerPort = Integer.parseInt(tokenizer.nextToken());
      String worldTopic = tokenizer.nextToken();
      String username = tokenizer.nextToken();
      String pass = tokenizer.nextToken();
      
      // set up the client's state
      ClientState state = client.getState();
      state.EventServer = eServer;
      state.EventServerPort = eServerPort;
      state.Username = username;
      state.Password = pass;
      state.UniqueTopic = "topic-" + username;
      state.WorldTopic = worldTopic;
      
      // make the world login event
      WorldEvent loginEvent = new WorldLoginEvent(
         username, pass.getBytes(), state.UniqueTopic);
      Event event = client.EventService.createEmptyEvent();
      event = loginEvent.toCesEvent(event);
      
      // open a connection to the event server
      client.EventService.openConnection(eServer, eServerPort);
      
      // register the client's event handler
      client.EventService.registerEventHandler(
         eServer, eServerPort, state.UniqueTopic, client.EventHandler);
      
      // send the login event
      client.EventService.publish(eServer, eServerPort, worldTopic, 
                                  event);      
   }
}