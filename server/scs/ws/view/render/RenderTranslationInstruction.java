package psl.chime4.server.scs.ws.view.render;

/**
 * Tells the 3D renderer to change its rendering point.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RenderTranslationInstruction extends RenderInstruction
{
   /** change in x location **/
   private int xChange;
   
   /** change in y location **/
   private int yChange;
   
   /** change in the z location **/
   private int zChange;
   
   public RenderTranslationInstruction()
   {
      super.instructionCode = RenderConstants.RENDER_TRANSLATION;
      super.instructionURI = RenderConstants.RENDER_TRANSLATION_URI;
   }
   
   /**
    * Get the change in the x-direction
    *
    * @return change in the x-direction
    **/
   public int getXChange()
   {
      return xChange;
   }
   
   /**
    * Get the change in the y-direction
    *
    * @return change in the y-direction
    **/
   public int getYChange()
   {
      return yChange;
   }
   
   /**
    * Get the change in the z-direction
    *
    * @return change in the z-direction
    **/
   public int getZChange()
   {
      return zChange;
   }
   
   /**
    * Set the change to make the translation by.
    *
    * @param xChange change in the x-axis
    * @param yChange change in the y-axis
    * @param zChange change in the z-axis
    **/
   public void setChange(int xChange, int yChange, int zChange)
   {
      this.xChange = xChange;
      this.yChange = yChange;
      this.zChange = zChange;
   }
}
