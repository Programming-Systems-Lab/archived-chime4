package psl.chime4.server.cwm.world.view;

/**
 * This class represents the display of an object in the world that has a 
 * location in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class LocatableWorldObjectView extends View
{
   /** x-coord of the object **/
   private int x;
   
   /** y-coord of the object **/
   private int y;
   
   /** z-coord of the object **/
   private int z;
   
   /** width of the object **/
   private int width;
   
   /** height of the object **/
   private int height;
   
   /** length of the object **/
   private int length;
   
   /**
    * Get the x-coordinate of the object in the world.
    *
    * @return x-coordinate of the object in the world 
    **/
   public int getX()
   {
      return x;
   }
   
   /**
    * Set the x-coordinate of the object in the world.
    *
    * @param x the x-coordinate of the object 
    **/
   public void setX(int x)
   {
      this.x = x;
   }
   
   /**
    * Get the y-coordinate of the object in the world.
    *
    * @return y-coordinate of the object
    **/
   public int getY()
   {
      return y;
   }
   
   /**
    * Set the y-coordinate of the object in the world.
    *
    * @param y the y-coordinate of the object
    **/
   public void setY(int y)
   {
      this.y = y;
   }
   
   /**
    * Get the z-coordinate of the object in the world.
    *
    * @return z the z-coordinate of the object
    **/
   public int getZ()
   {
      return z;
   }
   
   /**
    * Set the z-coordinate of the object in the world.
    *
    * @param z z-coordinate of the object
    **/
   public void setZ(int z)
   {
      this.z = z;
   }
   
   /**
    * Get the width of the object in the world.
    *
    * @return the width of the object in the world
    **/
   public int getWidth()
   {
      return width;
   }
   
   /**
    * Set the width of the object in the world.
    *
    * @param width the width of the object
    **/
   public void setWidth(int width)
   {
      this.width = width;
   }
   
   /**
    * Get the height of the object in the world.
    *
    * @return the height of the object
    **/
   public int getHeight()
   {
      return height;
   }
   
   /**
    * Set the height of the object in the world.
    *
    * @param height the height of the object 
    **/
   public void setHeight(int height)
   {
      this.height = height;
   }
   
   /**
    * Get the length of the room.
    *
    *@return length of the room
    **/
   public int getLength()
   {
      return length;
   }
   
   /**
    * Set the length of the room.
    *
    * @param length the length of the room
    **/
   public void setLength(int length)
   {
      this.length = length;
   }
}
