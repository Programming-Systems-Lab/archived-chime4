package psl.chime4.server.cwm.world;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a view of a Room in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RoomView extends WorldObjectView 
{
   /** the width of the room **/
   private int width;
   
   /** the height of the room **/
   private int height;
   
   /** List of views of doors in the room **/
   private List doorViews = new ArrayList();
   
   /** list of views of objects in the room **/
   private List contentViews = new ArrayList();
   
   /**
    * Get the width of the room.
    *
    * @return the width of the room
    **/
   public int getWidth()
   {
      return width;
   }
   
   /**
    * Set the width of the room.
    *
    * @param width the width of the room
    **/
   public void setWidth(int width)
   {
      this.width = width;
   }
   
   /**
    * Get the height of the room.
    *
    * @return the height of the room
    **/
   public int getHeight()
   {
      return height;
   }
   
   /**
    * Set the height of the room.
    *
    * @param height the height of the room
    **/
   public void setHeight(int height)
   {
      this.height = height;
   }
   
   /**
    * Add a view of a Door object that leads <em>from</em this room to
    * another room.
    *
    * @param doorView view of a door leading from this room
    **/
   public void addDoorView(DoorView doorView)
   {
      if (doorView != null)
      {
         doorViews.add(doorView);
      }
   }
   
   /**
    * Remove a view of a Door that leads from this room to another room.
    *
    * @param doorView view of a door leading from this room to another
    **/
   public void removeDoorView(DoorView doorView)
   {
      if (doorView != null)
      {
         doorViews.remove(doorView);
      }
   }
   
   /**
    * Get an Iterator over all the door views in this room.
    *
    * @return Iterator over the views of doors leading from this room.
    **/
   public Iterator doorViews()
   {
      return doorViews.iterator();
   }
   
   /**
    * Add a view of a content object to the room.
    *
    * @param view view of an object to be placed in the room
    **/
   public void addObjectView(WorldObjectView view)
   {
      if (view != null)
      {
         contentViews.add(view);
      }
   }
   
   /**
    * Remove a view of a content object from the room.
    *
    * @param view view of an object to be removed from the room
    **/
   public void removeObjectView(WorldObjectView view)
   {
      if (view != null)
      {
         contentViews.remove(view);
      }
   }
   
   /**
    * Get an Iterator over all the content object views in the world.
    *
    * @return Iterator over the content object views in the world
    **/
   public Iterator contentViews()
   {
      return contentViews.iterator();
   }
}