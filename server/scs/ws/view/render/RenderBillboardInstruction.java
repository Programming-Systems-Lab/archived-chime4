package psl.chime4.server.scs.ws.view.render;

import psl.chime4.server.scs.ws.view.image.*;

/**
 * Tells the 3D Renderer to draw a 2D billboard image that is always facing 
 * the camera straight on.
 *
 * @author Azubuko Obele
 **/
public class RenderBillboardInstruction extends RenderInstruction
{
   private ImageInfo image;
   
   /**
    * Get the image for the billboard to render.
    *
    * @return info about the image to render
    **/
   public ImageInfo getImage()
   {
      return image;
   }
   
   /**
    * Set the information about the image to render as a billboard.
    *
    * @param info image info about the render
    **/
   public void setImage(ImageInfo image)
   {
      this.image = image;
   }
}