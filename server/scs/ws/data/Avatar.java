package psl.chime4.server.scs.ws.data;

/**
 * Represents an 'active' object in the world that can move around and 
 * intiate actions.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class Avatar extends LocatedWorldObject
{
   /** current location of the avatar **/
   private Room currentLoc;
   
   /**
    * Get the current location of the avatar.
    *
    * @return current location of the avatar
    **/
   public Room getCurrentLocation()
   {
      return currentLoc;
   }
   
   /**
    * Set the current location of the avatar.
    *
    * @param loc new current location for the avatar
    **/
   public void setCurrentLocation(Room loc)
   {
      this.currentLoc = loc;
   }
}

