package psl.chime4.server.scs.service;

import java.io.*;

/**
 * Start the server process.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class StartServer
{
   public static void main(String[] args) throws Exception
   {
      String filename = args[0];
      FileInputStream in = new FileInputStream(filename);
      
      ServiceContext context = new ServiceContext();
      context.initialize(in);
   }
}

