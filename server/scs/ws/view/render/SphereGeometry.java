package psl.chime4.server.scs.ws.view.render;

/**
 * Represents the geometry of a sphere.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class SphereGeometry extends Geometry
{
   private float radius;
   
   public SphereGeometry()
   {
      super.geomCode = RenderConstants.SPHERE_GEOMETRY;
      super.geomURI = RenderConstants.SPHERE_GEOMETRY_URI;
   }
   
   /**
    * Get the radius of the sphere.
    *
    * @return radius of the sphere.
    **/
   public float getRadius()
   {
      return radius;
   }
   
   /**
    * Set the radius of the sphere.
    *
    * @param radius radius of the spehre
    **/
   public void setRadius(float radius)
   {
      this.radius = radius;
   }
}