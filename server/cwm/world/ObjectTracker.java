package psl.chime4.server.cwm;

import java.util.Map;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;

import psl.chime4.server.data.ResourceDescriptor;

/**
 * The ObjectTracker is responsible for keeping track of information 
 * concerning a large variety of objects.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ObjectTracker 
{
   /** map for keeping track of which metadata has been completed **/
   private Map metadataCompletionMap = new IdentityHashMap(10);
   
   /**
    * Remember that a piece of metadata in the world has been completed
    * and stored in memory.
    *
    * @param resDes ResourceDescriptor object that's been completed
    **/
   public void completed(ResourceDescriptor resDes)
   {
      if (resDes != null)
      {
         metadataCompletionMap.put(resDes, Boolean.TRUE);
      }
   }
   
   /**
    * Determine whether a piece of metadata in the world has been completed
    * and stored in memory.
    *
    * @param resDes ResourceDescriptor to determine for completion
    * @return <code>true</code> if <code>resDes</code> has been completed
    *         else <code>false</code>
    **/
   public boolean isCompleted(ResourceDescriptor resDes)
   {
      return metadataCompletionMap.containsKey(resDes);
   }
}