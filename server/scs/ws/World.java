package psl.chime4.server.scs.ws;

import psl.chime4.server.scs.persist.*;
import psl.chime4.server.scs.service.*;
import psl.chime4.server.scs.ws.data.*;
import psl.chime4.server.scs.ws.view.*;
import psl.chime4.server.scs.ws.view.render.*;

import java.util.*;

/**
 * This object defines the state of the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public final class World extends PersistentObject
{
   /** world service that runs this world **/
   private WorldService wService;
   
   /** persistence service the world needs to create objects **/
   private PersistenceService pService;
   
   /** special lobby room **/
   private Room lobby;
   
   /** special door that leads into the world **/
   private Door worldDoor;
   
   /** map all objects in the world to their instance id **/
   public Map instanceMap = new HashMap();
   
   /**
    * Initializes the world. Creates the special lobby and world door.
    **/
   public void initialize() throws ServiceException
   {
      lobby = createLobbyRoom();
   }
   /**
    * Retrieve an instance from the map.
    *
    * @param oid object id for the object
    * @return WorldObject mapped to that object or <code>null</code>
    * @throws IllegalArgumentException
    *         if <code>oid</code> is <code>null</code>
    **/
   public WorldObject getWorldObject(ObjectID oid)
   {
      if (oid == null)
      {
         String msg = "oid cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      return (WorldObject) instanceMap.get(oid);
   }
   
   /**
    * Put a WorldObject in the map mapped to its object id.
    *
    * @param wobj to put into the map
    * @throws IllegalArgumentException
    *         if <code>oid</code> is <code>null</code>
    **/
   public void putWorldObject(WorldObject wobj)
   {
      if (wobj == null)
      {
         String msg = "wobj cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      instanceMap.put(wobj.getObjectID(), wobj);
   }
   
   /**
    * Set the WorldService that manages this object.
    *
    * @param wService WorldService that manages this object
    * @throws IllegalArgumentException
    *         if <code>wService</code> is <code>null</code>
    **/
   public void setWorldService(WorldService wService)
   {
      if (wService == null)
      {
         String msg = "wService cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.wService = wService;
   }
   
   /**
    * Set the PersistenceService the World should use to create objects. This
    * method must be called by the WorldService when the world is created.
    *
    * @param pService PersistenceService used to create objects
    * @throws IllegalArgumentException
    *         if <code>pService</code> is <code>null</code>
    **/
   public void setPersistenceService(PersistenceService pService)
   {
      if (pService == null)
      {
         String msg = "pService cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.pService = pService;
   }
   
   /**
    * Get the special lobby room.
    *
    * @return Room representing the special lobby room
    **/
   public Room getLobbyRoom()
   {
      return lobby;
   }
   
   /**
    * Get the special world door that leads into the lobby from remote 
    * servers.
    *
    * @return Door that leads into the lobby
    **/
   public Door getLobbyEntrance()
   {
      return worldDoor;
   }
   
   /**
    * Create the lobby room. Set both the structure of the lobby room and the
    * view of it.
    **/
   private Room createLobbyRoom() throws ServiceException
   {
      // create a new room
      Room lobby = (Room) pService.create(Room.class);
      putWorldObject(lobby);
      
      // give it a cube geometry
      CubeGeometry geom = (CubeGeometry) pService.create(CubeGeometry.class);
      geom.setHeight(30);
      geom.setLength(30);
      geom.setWidth(30);
      
      View lobbyView = (View) pService.create(View.class);
      lobbyView.setGeometry(geom);
      
      // tell it to draw a basic black room
      RenderInstruction ri = (RenderInstruction) 
         pService.create(RenderGeometryInstruction.class);
      lobbyView.setRenderInstruction(ri);
      
      // map the view of the lobby in the theme
      wService.defaultTheme.putViewMapping(lobby, lobbyView);
      
      // now place a door in the room
      Door worldDoor = (Door) pService.create(Door.class);
      this.worldDoor = worldDoor;
      // say that the door leads to the lobby
      worldDoor.setDestination(lobby);
      
      // give it a cube geometry
      CubeGeometry doorGeom = (CubeGeometry)
         pService.create(CubeGeometry.class);
      doorGeom.setHeight(10);
      doorGeom.setLength(.5f);
      doorGeom.setWidth(1.5f);
      
      LocatedView doorView = (LocatedView) pService.create(LocatedView.class);
      doorView.setGeometry(doorGeom);
      doorView.setX(15);
      doorView.setY(0);
      doorView.setZ(0);
      
      // map the view of the door to the door in the theme
      wService.defaultTheme.putViewMapping(worldDoor, doorView);
      
      RenderInstruction doorRI = (RenderInstruction)
         pService.create(RenderGeometryInstruction.class);
      doorView.setRenderInstruction(doorRI);
      
      // add it to the lobby room
      lobby.addDoor(worldDoor);
      
      return lobby;
   }
}