package psl.chime4.server.cwm.world.view;

/**
 * Represents what a Door looks like in the world.
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
    * @return thickness of the door
    **/
   public int getThickness()
   {
      return thickness;
   }
   
   /**
    * Set the thickness of the door.
    *
    * @param thickness the thickness of the door
    **/
   public void setThickness(int thickness)
   {
      this.thickness = thickness;
   }  
}