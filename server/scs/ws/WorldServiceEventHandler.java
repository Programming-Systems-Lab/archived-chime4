package psl.chime4.server.scs.ws;

import psl.chime4.server.ces.*;
import psl.chime4.server.scs.*;
import psl.chime4.server.scs.log.*;
import psl.chime4.server.scs.service.*;
import psl.chime4.server.scs.ws.*;
import psl.chime4.server.scs.ws.event.*;
import psl.chime4.server.scs.ws.data.*;
import psl.chime4.server.scs.ws.view.*;
import psl.chime4.server.scs.ws.view.render.*;
import psl.chime4.server.scs.persist.*;
import psl.chime4.server.scs.auth.*;


/**
 * Embodies all of the event-handling logic for a given WorldService.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class WorldServiceEventHandler implements EventHandler
{
   /** WorldService that created us **/
   private WorldService wService;
   
   /**
    * Create a new event handler to handle events for the given world.
    *
    * @param ws WorldService that created us
    * @throws IllegalArgumentException
    *         if <code>ws</code> is <code>null</code>
    **/
   public WorldServiceEventHandler(WorldService ws)
   {
      if (ws == null)
      {
         String msg = "world service cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.wService = ws;
   }
   
   /**
    * Consume an event. If the event is passed, the handler can be sure that
    * it recieved it because it met the necessary criteria.
    *
    * @param event Event that met the handler's criteria
    */
   public void handleEvent(Event event)
   {
      wService.loggingService.info("WS", "Got Event: " + event);
      
      // map the event into a WorldEvent
      WorldEvent wevent = WorldEventMapper.cesToWorld(event);
      
      switch (wevent.EventCode)
      {
         case EventConstants.WorldLogin:
            handleEvent((WorldLoginEvent) wevent);
            break;
         
         case EventConstants.WorldEntry:
            handleEvent((WorldEntryEvent) wevent);
            break;
            
         default:
            handleEvent(wevent);
      }
   }
   
   /**
    * Generic handler event. This means that no specific handler was created.
    * This method should never be called.
    *
    * @param wevent WorldEvent that's unknown
    **/
   protected void handleEvent(WorldEvent wevent)
   {
      LoggingService lService = wService.loggingService;
      
      lService.severe("WS", "Unknown Event: " + wevent);
   }
   
   /**
    * Handle the world login event.
    * 
    * @todo Make sure that two clients will not both log in with the same 
    *       name.
    *
    * @param wle World Login Event sent by the client
    **/
   protected void handleEvent(WorldLoginEvent wle)
   {
      if (wle == null)
      {
         String msg = "wle cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // get the user's name, ticket and topic
      String username = wle.Username;
      byte[] ticket = wle.Ticket;
      String topic = wle.UniqueTopic;
      
      // get the name of the world and event server stuff
      String worldName = wService.getServiceName(); 
      String eServerHost = wService.eventServerHost;  
      int eServerPort = wService.eventServerPort;     
      
      // get the services needed to handle this event
      AuthenticationService aService = wService.authService;
      Theme defaultTheme = wService.defaultTheme;
      EventService eService = wService.eventService;
      LoggingService lService = wService.loggingService;
      
      // try to authenticate the user
      String authResult = "bad login";
      try
      {
         authResult = aService.authenticate(username, ticket);
      }
      catch (ServiceException se)
      {
         // authentication service has failed take appropriate action
      }
      
      WorldEvent reply;
      
      if (authResult == null)
      { 
         // authentication was successful, send back a login success
         reply = new WorldLoginSuccessEvent(worldName);
         
         // create a user info object for the user and initialize it
         UserInfo user = wService.userInfoService.newUser(username, topic);
         user.setAuthenticated(true);
         user.setCurrentTheme(defaultTheme);
         user.setEventBridge(new EventBridge(eServerHost, 
                                             eServerPort, eService)
                            );
         
         lService.info("WS", "User Logged In: " + user);
      } else
      {
         // authentication failed
         reply = new WorldLoginFailureEvent(worldName, authResult);
         
         lService.info("WS", "User Failed to Log In: " + username);
      }
      
      // try to send the reply
      try
      {
         Event event = eService.createEmptyEvent();
         event = reply.toCesEvent(event);
         eService.publish(eServerHost, eServerPort, topic, event);
      }
      catch (EventServiceException ese)
      {
         // event service has failed, take appropriate action
      }
   }
   
   /**
    * Handle the World Entry event when the client attempts to enter the 
    * world for the first time.
    *
    * @param wee event representing the request
    * @throws IllegalArgumentException
    *         if <code>event</code> is <code>null</code>
    **/
   protected void handleEvent(WorldEntryEvent wee)
   {      
      if (wee == null)
      {
         String msg = "wee cannot be null";
         throw new IllegalArgumentException(msg);
      }
    
      UserInfoService uiService = wService.userInfoService;
      EventService eService = wService.eventService;
      PersistenceService pService = wService.persistenceService;
      World world = wService.World;
      Theme theme = wService.defaultTheme;
      
      String worldName = wService.getServiceName();
      int lobbyRoomID = wService.world.getLobbyRoom().getInstanceID();
      int worldDoorID = wService.world.getLobbyEntrance().getInstanceID();
      
      // get the user who sent this event
      UserInfo user = (UserInfo) uiService.getUser(wee.Username);
      
      // if the user has never logged in or is not authenticated, ignore 
      // the event
      if ((user == null) || !(user.isAuthenticated()))
      {
         return;
      }
      
      // return a world entry success by default, in the future security 
      // would be implemented. this world entry tells the client to enter the
      // lobby through the special world door
      WorldEvent reply = new WorldEntrySuccessEvent(
         worldName, lobbyRoomID, worldDoorID);
      
      // construct a new HumanAvatar to represent this user in the world
      try
      {
         UserAvatar avatar = (UserAvatar) pService.create(UserAvatar.class);
         avatar.setUserInfo(user);
         
         
         // make it look like a red cube
         LocatedView view = (LocatedView) pService.create(LocatedView.class);
         CubeGeometry cubeGeom = 
            (CubeGeometry) pService.create(CubeGeometry.class);
         view.setGeometry(cubeGeom);
         
         RenderGeometryInstruction ri = 
            (RenderGeometryInstruction) 
               pService.create(RenderGeometryInstruction.class);
         
         view.setRenderInstruction(ri);
         
         // put it into the theme
      }
      catch (ServiceException se)
      {
         // the persistence service failed so take appropriate action
      }
         
      
      // now send the event to the user
      try
      {
         Event event = eService.createEmptyEvent();
         event = reply.toCesEvent(event);
         user.sendEvent(event);
      }
      catch (EventServiceException ese)
      {
         // event service has failed, take appropriate action
      }
   }
}
   