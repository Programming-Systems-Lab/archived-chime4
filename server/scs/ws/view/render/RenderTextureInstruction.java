package psl.chime4.server.scs.ws.view.render;

import psl.chime4.server.scs.ws.view.image.*;

/**
 * Renders a texture on some object.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RenderTextureInstruction extends RenderInstruction
{
   /** ImageInfo object that describes the texture to render **/
   private ImageInfo texture;
   
   public RenderTextureInstruction()
   {
      super.instructionCode = RenderConstants.RENDER_TEXTURE;
      super.instructionURI = RenderConstants.RENDER_TEXTURE_URI;
   }
   
   /**
    * Get information about the image for the texture to render.
    *
    * @return info about the image for the texture to render
    **/
   public ImageInfo getTexture()
   {
      return texture;
   }
   
   /**
    * Set information about the image for the texture to render.
    *
    * @param info info about the image of the texture to render
    **/
   public void setTexture(ImageInfo info)
   {
      this.texture = info;
   }
}