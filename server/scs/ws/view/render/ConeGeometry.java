package psl.chime4.server.scs.ws.view.render;

/**
 * Represents a cone in space.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ConeGeometry extends Geometry
{
   private float height;
   private float radius;
   
   public ConeGeometry()
   {
      super.geomCode = RenderConstants.CONE_GEOMETRY;
      super.geomURI = RenderConstants.CONE_GEOMETRY_URI;
   }
   
   /**
    * Get the height of the cone.
    *
    * @return height of the cone
    **/
   public float getHeight()
   {
      return height;
   }
   
   /**
    * Set the height of the cone.
    *
    * @param height height of the cone
    **/
   public void setHeight(float height)
   {
      this.height = height;
   }
   
   /**
    * Get the radius of the cone.
    *
    * @return radius of the cone
    **/
   public float getRadius()
   {
      return radius;
   }
   
   /**
    * Set the radius of the cone.
    *
    * @param radius of the cone
    **/
   public void setRadius(float radius)
   {
      this.radius = radius;
   }
}