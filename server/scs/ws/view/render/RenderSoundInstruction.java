package psl.chime4.server.scs.ws.view.render;

/**
 * Tells the 3D renderer to play a spatial sound.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RenderSoundInstruction extends RenderInstruction
{
   /** URI of the sound file to play **/
   private String sound;
   
   /** vector indicating the direction of the sound **/
   private float xOri;
   private float yOri;
   private float zOri;
   
   public RenderSoundInstruction()
   {
      super.instructionCode = RenderConstants.RENDER_SOUND;
      super.instructionURI = RenderConstants.RENDER_SOUND_URI;
   }
   
   /**
    * Get the URI of the sound to play.
    *
    * @return uri of the sound to play
    **/
   public String getSound()
   {
      return sound;
   }
   
   /**
    * Set the URI of the sound to play.
    *
    * @param sound uri of the sound to play
    **/
   public void setSound(String sound)
   {
      this.sound = sound;
   }
   
   /**
    * Get the x-orientation to play the sound in.
    *
    * @return x-orientation to play the sound in
    **/
   public float getXOrientation()
   {
      return xOri;
   }
   
   /**
    * Get the y-orientation to play the sound in.
    *
    * @return y-orientation to play the sound in
    **/
   public float getYOrientation()
   {
      return yOri;
   }
   
   /**
    * Get the z-orientation to play the sound in.
    *
    * @return z-orientation to play the sound in
    **/
   public float getZOrientation()
   {
      return zOri;
   }
   
   /**
    * Set the orientation of the sound. This is the general direction it will
    * play in the current 3D context.
    *
    * @param xOri orientation in the x-dir
    * @param yOri orientation in the y-dir
    * @param zOri orientation in the z-dir
    **/
   public void setOrientation(float xOri, float yOri, float zOri)
   {
      this.xOri = xOri;
      this.yOri = yOri;
      this.zOri = zOri;
   }
}
