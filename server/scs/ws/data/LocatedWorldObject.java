package psl.chime4.server.scs.ws.data;

/**
 * Represents a WorldObject that has a current location; that is it exists
 * inside a Room.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class LocatedWorldObject extends WorldObject
{
   /** current location of the object **/
   private Room currentLoc;
   
   /**
    * Get the current location of this world object.
    *
    * @return current location of this world object
    **/
   public Room getCurrentLocation()
   {
      return currentLoc;
   }
   
   /**
    * Set the current location of this world object.
    *
    * @param loc current location of this world object
    **/
   public void setCurrentLocation(Room loc)
   {
      this.currentLoc = loc;
   }
}
