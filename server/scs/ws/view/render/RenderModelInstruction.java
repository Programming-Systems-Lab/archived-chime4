package psl.chime4.server.scs.ws.view.render;

import psl.chime4.server.scs.ws.view.image.*;

/**
 * Tells the 3D Renderer to draw a model at the given context.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RenderModelInstruction extends RenderInstruction
{
   /** the image to render **/
   private ImageInfo image;
   
   public RenderModelInstruction()
   {
      super.instructionCode = RenderConstants.RENDER_MODEL;
      super.instructionURI = RenderConstants.RENDER_MODEL_URI;
   }
   
   /**
    * Get the model to render.
    *
    * @return info about the model to render
    **/
   public ImageInfo getImage()
   {
      return image;
   }
   
   /**
    * Set the information about the model to render.
    *
    * @param image info about the model to render
    **/
   public void setImage(ImageInfo image)
   {
      this.image = image;
   }
}