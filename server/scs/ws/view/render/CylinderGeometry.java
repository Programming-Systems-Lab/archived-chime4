package psl.chime4.server.scs.ws.view.render;

/**
 * Represents the geometry of a cylinder.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class CylinderGeometry extends Geometry
{
   private float radius;
   private float height;
  
   public CylinderGeometry()
   {
      super.geomCode = RenderConstants.CYLINDER_GEOMETRY;
      super.geomURI = RenderConstants.CYLINDER_GEOMETRY_URI;
   }
   
   /**
    * Get the radius of the cylinder.
    *
    * @return radius of the cylinder
    **/
   public float getRadius()
   {
      return radius;
   }
   
   /**
    * Set the radius of the cylinder.
    *
    * @param radius radius of the cylinder
    **/
   public void setRadius(float radius)
   {
      this.radius = radius;
   }
   
   /**
    * Get the height of the cylinder.
    *
    * @return height of the cylinder
    **/
   public float getHeight()
   {
      return height;
   }
   
   /**
    * Set the height of the cylinder.
    *
    * @param height height of the cylinder
    **/
   public void setHeight(float height)
   {
      this.height = height;
   }
}
   

