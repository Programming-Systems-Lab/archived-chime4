package psl.chime4.server.scs.ws.view.render;

/**
 * Tells the 3D Renderer to perform a scaling instruction.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RenderScalingInstruction extends RenderInstruction
{
   /** the scaling in the x-direction **/
   private float xFactor;
   
   /** the scaling of the y-direction **/
   private float yFactor;
   
   /** the scaling in the z-direction **/
   private float zFactor;
   
   public RenderScalingInstruction()
   {
      super.instructionCode = RenderConstants.RENDER_SCALING;
      super.instructionURI = RenderConstants.RENDER_SCALING_URI;
   }
   
   /**
    * Get the scaling factor in the x direction.
    *
    * @return scaling factor in the x direction
    **/
   public float getXFactor()
   {
      return xFactor;
   }
   
   /**
    * Get the scaling factor in the y direction.
    *
    * @return scaling factor in the y direction
    **/
   public float getYFactor()
   {
      return yFactor;
   }
   
   /**
    * Get the scaling factor in the z-direction.
    *
    * @return scaling factor in the z direction
    **/
   public float getZFactor()
   {
      return zFactor;
   }
   
   /**
    * Set the factors to perform the scaling operation by.
    *
    * @param xFac factor to scale by x-wise
    * @param yFac factor to scale by y-wise
    * @param zFac factor yo scale by z-wise
    **/
   public void setScalingFactors(float xFac, float yFac, float zFac)
   {
      this.xFactor = xFac;
      this.yFactor = yFac;
      this.zFactor = zFac;
   }
}