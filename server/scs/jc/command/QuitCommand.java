package psl.chime4.server.scs.jc.command;

import psl.chime4.server.scs.jc.*;

/**
 * Command to quit the client.
 *
 * @author Azubuko Obele 
 * @version 0.1
 **/
public class QuitCommand extends Command
{
   public void execute(Client client, String input) throws Exception
   {
      client.output("Shutting down client...");
      System.exit(0);
   }
}