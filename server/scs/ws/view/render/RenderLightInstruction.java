package psl.chime4.server.scs.ws.view.render;

/**
 * Tells the 3D Renderer to draw a light.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RenderLightInstruction extends RenderInstruction
{
   /** the intensity of the light **/
   private float intensity;
   
   /** vector defining the direction of the light **/
   private float xOri;
   private float yOri;
   private float zOri;
   
   /** the radius of the spotlight **/
   private int radius;
   
   /** the color of the light **/
   private int color;
   
   /** defines the type of light **/
   private int lightType;
   
   /** constants defining the type of light **/
   public static final int POINT = 0;
   public static final int RAY = 1;
   public static final int SPOTLIGHT = 2;
   
   public RenderLightInstruction()
   {
      super.instructionCode = RenderConstants.RENDER_LIGHT;
      super.instructionURI = RenderConstants.RENDER_LIGHT_URI;
   }
   
   /**
    * Get the intensity of the light to render.
    *
    * @return intensity of the light to render
    **/
   public float getIntensity()
   {
      return intensity;
   }
   
   /**
    * Set the intensity of the light to render.
    *
    * @param intensity intensity of the light to render
    **/
   public void setIntensity(float intensity)
   {
      this.intensity = intensity;
   }
   
   /**
    * Get the x-orientation of the light if it is a ray or spotlight.
    *
    * @return the x-orientation of the light if it's a ray or spotlight
    **/
   public float getXOrientation()
   {
      return xOri;
   }
   
   /**
    * Get the y-orientation of the light if it is ray or spotlight.
    *
    * @return the y-orientation of the light source 
    **/
   public float getYOrientation()
   {
      return yOri;
   }
   
   /**
    * Get the z-orientation of the light source.
    *
    * @return the z-orientation of the light source
    **/
   public float getZOrientation()
   {
      return zOri;
   }
   
   /**
    * Set the orientation of the light.
    *
    * @param xOri x-orientation of the light
    * @param yOri y-orientation of the light
    * @param zOri z-orientation of the light
    **/
   public void setOrientation(float xOri, float yOri, float zOri)
   {
      this.xOri = xOri;
      this.yOri = yOri;
      this.zOri = zOri;
   }
   
   /**
    * Get the radius of the light.
    *
    * @return radius of the light 
    **/
   public float getRadius()
   {
      return radius;
   }
   
   /**
    * Set the radius of the light. If the light is a spotlight this is the 
    * rate of increase in the spotlight area, if it's a ray it's the radius
    * of the ray.
    *
    * @param radian radius of the light in radians
    **/
   public void setRadius(int radius)
   {
      this.radius = radius;
   }
   
   /**
    * Get the color of the light.
    *
    * @return the color of the light
    **/
   public int getColor()
   {
      return color;
   }
   
   /**
    * Set the color of the light.
    *
    * @param color of the light
    **/
   public void setColor(int color)
   {
      this.color = color;
   }
   
   /**
    * Return the type of light this is. Must be one of the predefined 
    * constants.
    *
    * @return the type of light
    **/
   public int getLightType()
   {
      return lightType;
   }
   
   /**
    * Set the type of light to be rendered. Must be one of the predefined
    * constants.
    *
    * @param lightType type of light to be drawn
    * @throws IllegalArgumentException
    *         if <code>lightType</code> is not one of the pre-defined types
    **/
   public void setLightType(int lightType)
   {
      this.lightType = lightType;
   }
}
   