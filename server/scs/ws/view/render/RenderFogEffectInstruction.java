package psl.chime4.server.scs.ws.view.render;

/**
 * Tells the 3D Renderer to draw fog within a sphere.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RenderFogEffectInstruction extends RenderInstruction
{
   /** the density of the fog to render **/
   private float density;
   
   /** sphere of radius to render the fog in **/
   private int radius;
   
   /** color of the fog **/
   private int color;
   
   public RenderFogEffectInstruction()
   {
      super.instructionCode = RenderConstants.RENDER_FOG_EFFECT;
      super.instructionURI = RenderConstants.RENDER_FOG_EFFECT_URI;
   }
   
   /**
    * Get the density of the fog to render.
    *
    * @return density of the fog to render
    **/
   public float getDensity()
   {
      return density;
   }
   
   /**
    * Set the density of the fog to render.
    *
    * @param density density of the fog to render
    **/
   public void setDensity(float density)
   {
      this.density = density;
   }
   
   /**
    * Get the radius of the field to render the fog in.
    *
    * @return radius of the fog to render the fog in
    **/
   public int getRadius()
   {
      return radius;
   }
   
   /**
    * Set the radius of the field to render the fog in.
    *
    * @param radius radius of the field to render the fog in
    **/
   public void setRadius(int radius)
   {
      this.radius = radius;
   }
   
   /**
    * Get the color of the fog to render.
    *
    * @return the color of the fog to render
    **/
   public int getColor()
   {
      return color;
   }
   
   /**
    * Set the color of the fog to render.
    *
    * @param color color of the fog to render
    **/
   public void setColor(int color)
   {
      this.color = color;
   }
}