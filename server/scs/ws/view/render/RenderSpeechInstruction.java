package psl.chime4.server.scs.ws.view.render;

/*
 * Tells the 3D renderer--if it can--to render a speech sound from the current
 * context.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RenderSpeechInstruction extends RenderInstruction
{
   /** text to say **/
   private String text;
   
   /** orientation to render the speech in **/
   private float xOri;
   private float yOri;
   private float zOri;
   
   public RenderSpeechInstruction()
   {
      super.instructionCode = RenderConstants.RENDER_SPEECH;
      super.instructionURI = RenderConstants.RENDER_SPEECH_URI;
   }
   
   /**
    * Get the text to say.
    *
    * @return text to say
    **/
   public String getText()
   {
      return text;
   }
   
   /**
    * Set the text to say
    *
    * @param text text to say
    **/
   public void setText(String text)
   {
      this.text = text;
   }
   
   /**
    * Get the x-orientation to render the speech in.
    *
    * @return x-orientation to render the speech in
    **/
   public float getXOrientation()
   {
      return xOri;
   }
   
   /**
    * Set the y-orientation to render the speech in.
    *
    * @return y-orientation to render the speech in
    **/
   public float getYOrientation()
   {
      return yOri;
   }
   
   /**
    * Get the z-orientation to render the speech in.
    *
    * @return z-orientation to render the speech in
    **/
   public float getZOrientation()
   {
      return zOri;
   }
   
   /**
    * Set the orientation to render the speech in.
    *
    * @param xOri x-orientation to render the speech in
    * @param yOri y-orientation to render the speech in     
    * @param zOri z-orientation to render the speech in
    **/
   public void setOrientation(float xOri, float yOri, float zOri)
   {
      this.xOri = xOri;
      this.yOri = yOri;
      this.zOri = zOri;
   }
}
