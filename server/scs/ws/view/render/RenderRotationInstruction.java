package psl.chime4.server.scs.ws.view.render;

/**
 * Tells the renderer to rotate the current context.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RenderRotationInstruction extends RenderInstruction
{
   /** angle to perform the rotation about **/
   private float angleOfRotation;
   
   public RenderRotationInstruction()
   {
      super.instructionCode = RenderConstants.RENDER_ROTATION;
      super.instructionURI = RenderConstants.RENDER_ROTATION_URI;
   }
   
   /**
    * Get the angle of the rotation to perform.
    *
    * @return angle of rotation to perform in radians
    **/
   public float getRotationAngle()
   {
      return angleOfRotation;
   }
   
   /**
    * Set the angle of the rotation to perform.
    *
    * @param angle angle of rotation to perform
    **/
   public void setRotationAngle(float angle)
   {
      this.angleOfRotation = angle;
   }
}