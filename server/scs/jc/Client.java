package psl.chime4.server.scs.jc;

import psl.chime4.server.scs.jc.command.*;
import psl.chime4.server.ces.*;
import psl.chime4.server.ces.elvin.*;
import psl.chime4.server.scs.*;
import psl.chime4.server.scs.service.*;
import psl.chime4.server.scs.log.*;

import java.util.*;
import java.util.HashMap;
import java.io.*;

/**
 * A simple test client.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class Client
{   
   public EventService EventService = new ElvinEventService();
   public EventHandler EventHandler;
   public LoggingService LoggingService = new ConsoleLoggingService();
   public Map commandMap = new HashMap();
   
   /** state of the client **/
   public ClientState state = new ClientState();
  
   public static void main(String[] args) throws Exception
   {
      Client client = new Client();
      client.inputLoop();
   }
   
   /**
    * Default constructor to initialize the command map and start up the 
    * different services.
    **/
   public Client()
   {
      initializeCommandMap();
      EventService.startup();  
      EventHandler = new ClientEventHandler(this);
   }
   
   /**
    * Map all the supported commands to their textual equals.
    **/
   private void initializeCommandMap()
   {
      commandMap.put("quit", new QuitCommand());
      commandMap.put("login", new WorldLoginCommand());
      commandMap.put("enter_world", new WorldEntryCommand());
   }
   
   /**
    * Main input loop to wait for input and execute commands.
    **/
   private void inputLoop() throws Exception
   {
      BufferedReader inputReader = 
         new BufferedReader(new InputStreamReader(System.in));
      
      while (true)
      {
         System.out.print(">> ");
         String input = inputReader.readLine();
         
         // get the command, everything before 'command: <args>'
         int colonPos = input.indexOf(':');
         if (colonPos > 0)
         {
            String command = input.substring(0, colonPos);
            String args = input.substring(colonPos + 1);
            
            Command commandObj = (Command) commandMap.get(command);
            if (commandObj == null)
            {
               output("No Such Command!");
            } else
            {
               try
               {
                  commandObj.execute(this, args);
               }
               catch (Exception e)
               {
                  e.printStackTrace();
                  output("Bad command syntax!");
               }
            }
         } else
         {
            output("No Such Command!");
         }
      }
   }
   
   /**
    * Print out something that will display on the client's main screen.
    *
    * @param msg String to display to the client
    **/
   public void output(String msg)
   {
      System.out.println("<< " + msg);
   }
   
   /**
    * Get the state of the client.
    *
    * @return state of the client
    **/
   public ClientState getState()
   {
      return state;
   }
}
   

