/*
 * VisualizationService.java
 *
 * Created on May 17, 2002, 1:02 PM
 */

package psl.chime4.server.vem;

import psl.chime4.server.cwm.world.*;

/**
 * Responsible for creating a view of the world as well as updating that view.
 *
 * @author Vladislav Shchogolev
 * @author Azubuko Obele
 * @version $Revision$
 *
 **/
class VisualizationService
{
         
   /**
    * Given a Room with a pre-defined size as well as other hints, the 
    * VisualDataManager must assign a universally-unique model-ID which
    * corresponds to a three-dimensional data file.
    *
    * @param room     a description of a room in the world
    * @throws ModelException
    *         if no appropriate model of the room can be modeled
    **/
   public RoomView createView(Room room) throws ModelException
   {
        return null; // do nothing
   }

   /**
    * Given a description of an object in the world, the VisualDataManager 
    * must assign a universally-unique model-ID which corresponds to the
    * a three-dimensional data file.
    *
    * @param object the object in the world
    * @param room   the room that <code>object</code> is in
    * @param hints  hints of what the room should look like
    * @throws ModelException 
    *         if the VDM cannot determine what an object should look like
    **/
   public LocatableWorldObjectView createView(WorldObject object,
					      Room room)
      throws ModelException
   {
      return null; // do nothing
   }
}