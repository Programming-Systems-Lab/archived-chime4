package psl.chime4.server.scs.ws;

import psl.chime4.server.scs.*;
import psl.chime4.server.scs.service.*;
import psl.chime4.server.scs.auth.*;
import psl.chime4.server.scs.log.*;
import psl.chime4.server.scs.persist.*;
import psl.chime4.server.scs.ws.view.*;
import psl.chime4.server.ces.*;

import java.util.*;

/**
 * Defines a world that exists on the server.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class WorldService extends AbstractService
{
   PersistenceService persistenceService;
   AuthenticationService authService;
   EventService eventService;
   LoggingService loggingService;
   
   String eventServerHost;
   int eventServerPort;
   String uniqueTopic;
   
   /** keep track of logged in users **/
   UserInfoService userInfoService;
   
   /** World being managed by this service **/
   World world;
   
   /** event handler for handling events **/
   EventHandler eventHandler;
   
   /** default theme that is applied to all first-time users **/
   Theme defaultTheme = new Theme();
   
   /**
    * Initialize the Service. At this point the Service has not started.
    *
    * @param params parameters passed from server.xml
    * @throws ServiceException
    *        if the service cannot initialize itself
    */
   public void initialize(ServiceParamMap params) throws ServiceException
   {
      if (params == null)
      {
         String msg = "World Service needs parameters!";
         throw new ServiceException(msg);
      }
      
      // get the context
      ServiceContext context = getServiceContext();
      if (context == null)
      {
         String msg = "no ServiceContext set!";
         throw new ServiceException(msg);
      }
     
      // get the the persistence service 
      persistenceService = 
         (PersistenceService)
            params.getParam("persistenceService").getValue();
      
      // get the name of the auth service and set it
      authService = 
         (AuthenticationService) params.getParam("authService").getValue();
      
      // get the name of the event service
      eventService = 
         (EventService) params.getParam("eventService").getValue();
      
      // get the logging service
      loggingService = 
         (LoggingService) params.getParam("loggingService").getValue();
      
      // get the event server host
      eventServerHost = params.getParam("eventServerHost").getValueAsString();
      
      // get the event server port
      eventServerPort = params.getParam("eventServerPort").getValueAsInt();
      
      // get the unique topic
      uniqueTopic = params.getParam("uniqueTopic").getValueAsString();
      
      // create the user info service
      userInfoService = new UserInfoService();
      userInfoService.setWorldService(this);
      userInfoService.setPersistenceService(persistenceService);
      
      // now register with the event service on our unique topic for our 
      // primary event server
      eventHandler = new WorldServiceEventHandler(this);
      try
      {
         eventService.openConnection(eventServerHost, eventServerPort);
         eventService.registerEventHandler(eventServerHost, eventServerPort, 
                                           uniqueTopic, eventHandler);
      }
      catch (EventServiceException es)
      {
         String msg = "couldn't register with event server!";
         throw new ServiceException(msg, es);
      }
      
      // create the world
      world = (World) persistenceService.create(World.class);
      world.setWorldService(this);
      world.setPersistenceService(persistenceService);
      world.initialize();
      
      // create the default theme
      defaultTheme = new Theme();
   }
   
   /**
    * Start the Service. At this point the service is running.
    *
    * @throws ServiceException
    *        if the service cannot start for some reason
    */
   public void start() throws ServiceException
   {
   }
   
   /**
    * Shut the Service down. The Service has roughly two minutes to shutdown
    * before it will be forcefully shutdown.
    */
   public void stop()
   {
   }
}