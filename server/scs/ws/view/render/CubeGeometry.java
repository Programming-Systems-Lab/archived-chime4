package psl.chime4.server.scs.ws.view.render;

/**
 * This defines a simple geometry for a cube in space.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class CubeGeometry extends Geometry
{
   private float width;
   private float height;
   private float length;
   
   public CubeGeometry()
   {
      super.geomCode = RenderConstants.CUBE_GEOMETRY;
      super.geomURI = RenderConstants.CUBE_GEOMETRY_URI;
   }
   
   /**
    * Get the width of the cube.
    *
    * @return width of the cube
    **/
   public float getWidth()
   {
      return width;
   }
   
   /**
    * Set the width of the cube.
    *
    * @param width width of the cube
    **/
   public void setWidth(float width)
   {
      this.width = width;
   }
   
   /**
    * Get the height of the cube.
    *
    * @return height of the cube
    **/
   public float getHeight()
   {
      return height;
   }
   
   /**
    * Set the height of the cube.
    *
    * @param height height of the cube
    **/
   public void setHeight(float height)
   {
      this.height = height;
   }
   
   /**
    * Get the length of the cube.
    *
    * @return length of the cube
    **/
   public float getLength()
   {
      return length;
   }
   
   /**
    * Set the length of the cube.
    *
    * @param length length of the cube
    **/
   public void setLength(float length)
   {
      this.length = length;
   }
}
