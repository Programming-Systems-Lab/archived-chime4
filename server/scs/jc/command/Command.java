package psl.chime4.server.scs.jc.command;

import psl.chime4.server.scs.jc.*;

/**
 * Basic interface for all commands that can be executed by the client.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class Command
{
   /**
    * Tell the command to execute its function.
    *
    * @param client client that executed the command
    * @param input  what the client typed
    * @throws Exception
    *         if the command cannot be executed
    **/
   public abstract void execute(Client client, String input) throws Exception;
}

