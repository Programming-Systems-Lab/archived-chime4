package psl.chime4.server.cwm;

/**
 * A LocatableWorldObjectView is a view of an object that has a location and
 * dimensions within the three-dimensional world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class LocatableWorldObjectView extends WorldObjectView
{
   /** the x-location in the world **/
   private int x = -1;
   
   /** the y-location in the world **/
   private int y = -1;
   
   /** the z-location in the world **/
   private int z = -1;
   
   /** the width of the object in the world **/
   private int width = -1;
   
   /** the height of the object in the world **/
   private int height = -1;
   
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
}
