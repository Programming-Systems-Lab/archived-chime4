package psl.chime4.server.scs.ws.view;

/**
 * Represents the View of an object that has a location in the world. This 
 * is essentially every object except for Rooms (which are described by
 * views).
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class LocatedView extends View
{
   /** coordinates in the world **/
   private int xCoord;
   private int yCoord;
   private int zCoord;
   
   /**
    * Get the x-coordinate of the object.
    *
    * @return xCoord of the object
    **/
   public int getX()
   {
      return xCoord;
   }
   
   /**
    * Set the x-coordinate of the object.
    *
    * @param x x-coordinate of the object
    **/
   public void setX(int x)
   {
      this.xCoord = x;
   }
   
   /**
    * Get the y-coordinate of the object.
    *
    * @return y coord of the object
    **/
   public int getY()
   {
      return yCoord;
   }
   
   /**
    * Set the y coord of the object.
    *
    * @param y y-coordinate of the object
    **/
   public void setY(int y)
   {
      this.yCoord = y;
   }
   
   /**
    * Get the z coord of the object.
    *
    * @return z coord of the object
    **/
   public int getZ()
   {
      return zCoord;
   }
   
   /**
    * Set the z-coordinate of the object
    * 
    * @param z z-coordinate of the object
    **/
   public void setZ(int z)
   {
      this.zCoord = z;
   }
}