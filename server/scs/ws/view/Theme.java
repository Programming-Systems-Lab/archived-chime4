package psl.chime4.server.scs.ws.view;

import psl.chime4.server.scs.ws.data.*;

import java.util.*;

/**
 * A Theme represents a mapping between views of objects and the objects 
 * themselves. The Theme is also responsible for mapping object meta-types
 * to views.
 *
 * @author Azubuko Obele 
 * @version 0.1
 **/
public class Theme
{
   /** map of all world object to their current views **/
   private IdentityHashMap viewMap = new IdentityHashMap();
   
   /**
    * Place an object with its matching view in the theme.
    *
    * @param wobj World Object to put into the theme
    * @param view View describing what the world object looks like
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    **/
   public void putViewMapping(WorldObject wobj, View view)
   {
      if ((wobj == null) || (view == null))
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      synchronized(viewMap)
      {
         viewMap.put(wobj, view);
      }
   }
   
   /**
    * Remove a view mapping for a specific object from the map.
    *
    * @param wobj WorldObject to remove the view for
    * @return View that was mapped to <code>wobj</code> or <code>null</code>
    * @throws IllegalArgumentException
    *         if <code>wobj</code> is <code>null</code>
    **/
   public View removeViewMapping(WorldObject wobj)
   {
      if (wobj == null)
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      synchronized(viewMap)
      {
         return (View) viewMap.remove(wobj);
      }
   }
   
   /**
    * Retreive the View for a given World Object.
    *
    * @param wobj WorldObject to retrieve the view for
    * @return View for <code>wobj</code> or <code>null</code>
    * @throws IllegalArgumentException 
    *         if <code>wobj</code> is <code>null</code>
    **/
   public View getViewMapping(WorldObject wobj)
   {
      if (wobj == null)
      {
         String msg = "view cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      synchronized(viewMap)
      {
         return (View) viewMap.get(wobj);
      }
   }
}