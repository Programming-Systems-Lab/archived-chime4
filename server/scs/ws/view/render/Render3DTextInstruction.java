package psl.chime4.server.scs.ws.view.render;

/**
 * Tells the renderer to draw 3D text at the current context.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class Render3DTextInstruction extends RenderInstruction
{
   /** string to render **/
   private String text;
   
   /** vector to draw the text in **/
   private float xOri;
   private float yOri;
   private float zOri;
   
   public Render3DTextInstruction()
   {
      super.instructionCode = RenderConstants.RENDER_3D_TEXT;
      super.instructionURI = RenderConstants.RENDER_3D_TEXT_URI;
   }
   
   /**
    * Get the text to render.
    *
    * @return text to render
    **/
   public String getText()
   {
      return text;
   }
   
   /**
    * Set the text of the render.
    *
    * @param text the text to render
    **/
   public void setText(String text)
   {
      this.text = text;
   }
   
   /**
    * Get the x-orientation to render the text in.
    *
    * @return x-orientation to render the text in
    **/
   public float getXOrientation()
   {
      return xOri;
   }
   
   /**
    * Get the y-orientation to render the text in.
    *
    * @return y-orientation to render the text in
    **/
   public float getYOrientation()
   {
      return yOri;
   }
   
   /**
    * Get the z-orientation to render the text in.
    *
    * @return z-orientation to render the text in
    **/
   public float getZOrientation()
   {
      return zOri;
   }
   
   /**
    * Set the orientation to render the text in.
    *
    * @param xOri orientation on the x-axis
    * @param yOri orientation on the y-axis
    * @param zOri orientation on the z-axis
    **/
   public void setOrientation(float xOri, float yOri, float zOri)
   {
      this.xOri = xOri;
      this.yOri = yOri;
      this.zOri = zOri;
   }
}