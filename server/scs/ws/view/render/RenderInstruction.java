package psl.chime4.server.scs.ws.view.render;

import psl.chime4.server.scs.ws.view.*;
import psl.chime4.server.scs.persist.*;

/**
 * Represents an instruction to a 3D engine on how to draw something.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class RenderInstruction extends PersistentObject
{
   /** numeric code for this instruction **/
   protected int instructionCode;
   
   /** unique uri defining this instruction **/
   protected String instructionURI;
   
   /**
    * Return the numeric code for this drawing instruction.
    *
    * @return numeric code for this drawing instruction
    **/
   public int getInstructionCode()
   {
      return instructionCode;
   }
   
   /**
    * Get the URI that defines this instruction.
    *
    * @return uri defining this instruction
    **/
   public String getInstructionURI()
   {
      return instructionURI;
   }
}