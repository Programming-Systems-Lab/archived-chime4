package psl.chime4.server.scs.ws.view.render;

/**
 * Renders an arbitrary geometry instruction on the screen.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RenderGeometryInstruction extends RenderInstruction
{
   /** the geometry to render **/
   private Geometry geometry;
   
   /**
    * Get the geometry that must be rendered.
    *
    * @return geometry to render
    **/
   public Geometry getGeometry()
   {
      return geometry;
   }
   
   /**
    * Set the geometry to render.
    *
    * @param geometry Geometry to render
    **/
   public void setGeometry(Geometry geometry)
   {
      this.geometry = geometry;
   }
}