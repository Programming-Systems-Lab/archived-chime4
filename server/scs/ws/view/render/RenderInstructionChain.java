package psl.chime4.server.scs.ws.view.render;

import java.util.*;

/**
 * Represents a collection of RenderInstructions chained together.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RenderInstructionChain extends RenderInstruction
{
   /** list to store the render instructions in **/
   private ArrayList chainList = new ArrayList(5);
   
   public RenderInstructionChain()
   {
      super.instructionCode = RenderConstants.RENDER_INSTRUCTION_CHAIN;
      super.instructionURI = RenderConstants.RENDER_INSTRUCTION_CHAIN_URI;
   }
   
   /**
    * Add a RenderInstruction to the chain.
    *
    * @param ri render instruction to add to the chain
    **/
   public void addInstruction(RenderInstruction ri)
   {
      if (ri != null)
      {
         chainList.add(ri);
      }
   }
   
   /**
    * Return an iterator over all the render instructions in the chain.
    *
    * @return Iterator over all the render instructions in the chain
    **/
   public Iterator instructions()
   {
      chainList.trimToSize();
      return chainList.iterator();
   }
}
