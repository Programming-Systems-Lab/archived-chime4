package psl.chime4.server.cwm.world.view;

import psl.chime4.server.cwm.world.persist.ObjectID;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collections;

/**
 * Represents the appearance of a room.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class RoomView extends View
{
   /** the width of the room **/
   private int width;
   
   /** the height of the room **/
   private int height;
   
   /** the length of the room **/
   private int length;
   
   /** the set of door views in the room **/
   private Set doorViews;
   
   /** set of views of objects in the room **/
   private Set contentsViews;
   
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
    * Get the length of the room.
    *
    * @return the length of the room
    **/
   public int getLength()
   {
      return length;
   }
   
   /**
    * Set the length of the room.
    *
    * @param length the length of the room
    **/
   public void setLength(int length)
   {
      this.length = length;
   }
   
   /**
    * Add a view of a door that leads from this room to another.
    *
    * @param doorView object id of the door view to add to the room
    * @throws IllegalArgumentException
    *         if <code>doorView</code> is not owned by a {@link DoorView}
    **/
   public void addDoorView(ObjectID doorView)
   {
      if (doorView != null)
      {
         if (doorView.getOwnerType() != DoorView.class)
         {
            String msg = "only door views can be added to rooms";
            throw new IllegalArgumentException(msg);
         }
         
         if (doorViews == null)
         {
            doorViews = new HashSet();
         }
         
         doorViews.add(doorView);
      }
   }
   
   /**
    * Remove a given view of a door from the room.
    *
    * @param doorView object id of the door view to remove from the room
    **/
   public void removeDoorView(ObjectID doorView)
   {
      if (doorView != null)
      {
         if (doorViews != null)
         {
            doorViews.remove(doorView); 
            
            // if there are no more door views, null it
            if (doorViews.isEmpty())
            {
               doorViews = null;
            }
         }
      }
   }
   
   /**
    * Get an iterator over all the door views in the room.
    *
    * @return iterator over all the views of doors in the room
    **/
   public Iterator doorViews()
   {
      if (doorViews == null)
      {
         return Collections.EMPTY_SET.iterator();
      } else
      {
         return doorViews.iterator();
      }
   }
   
   /**
    * Add a view for an object that's in the room.
    *
    * @param view object id of a view of an object that's in the room
    * @throws IllegalArgumentException
    *         if <code>view</code> doesn't belong to an object of type 
    *         {@link View}
    **/
   public void addContentView(ObjectID view)
   {
      if (view != null)
      {
         if (view.getOwnerType() != View.class)
         {
            String msg = "only views can be added to rooms";
            throw new IllegalArgumentException(msg);
         }
         
         if (contentsViews == null)
         {
            contentsViews = new HashSet();
         }
         
         contentsViews.add(view);
      }
   }
   
   /**
    * Remove the view of an object from inside the room.
    *
    * @param view view of an object that's inside the room
    **/
   public void removeContentView(View view)
   {
      if (view != null)
      {
         if (contentsViews != null)
         {
            contentsViews.remove(view);
            
            // if it's empty, null it
            if (contentsViews.isEmpty())
            {
               contentsViews = null;
            }
         }
      }
   }
   
   /**
    * Get an iterator over all the views of all the objects in the room.
    *
    * @return iterator over the views in the room.
    **/
   public Iterator contentViews()
   {
      if (contentsViews == null)
      {
         return Collections.EMPTY_SET.iterator();
      } else
      {
         return contentsViews.iterator();
      }
   }
}