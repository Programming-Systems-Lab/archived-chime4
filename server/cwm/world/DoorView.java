package psl.chime4.server.cwm.world;

/**
 * Represents a view of a Door object in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class DoorView extends LocatableWorldObjectView
{
   /** the thickness of the door **/
   private int thickness;
   
   /**
    * Get the thickness of the door.
    *
    * @return the thickness of the door
    **/
   public int getThickness()
   {
      return thickness;
   }
   
   /**
    * Set the thickness of the door.
    *
    * @param thickness the thickness of the object in the world
    **/
   public void setThickness(int thickness)
   {
      this.thickness = thickness;
   }
}

