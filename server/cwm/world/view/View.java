package psl.chime4.server.cwm.world.view;

import psl.chime4.server.cwm.AbstractPersistentObject;
import psl.chime4.server.cwm.world.WorldObject;

/**
 * A View represents how an object looks in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class View extends AbstractPersistentObject
{
   /** uri defining the view's model file **/
   private String modelURI;
   
   /** the world object the view is displaying **/
   private WorldObject worldObject;
   
   /**
    * Get the model URI associated with this view.
    *
    * @return model URI associated with this file or <code>null</code>
    **/
   public String getModelURI()
   {
      return modelURI;
   }
   
   /**
    * Set the model URI associated with this view.
    *
    * @param modelURI modelURI to associate with this file
    **/
   public void setModelURI(String modelURI)
   {
      this.modelURI = modelURI;
   }
   
   /**
    * Get the WorldObject associated with this view.
    *
    * @return WorldObject associated with this view or <code>null</code>
    **/
   public WorldObject getWorldObject()
   {
      return worldObject;
   }
   
   /**
    * Set the WorldObject associated with this view.
    *
    * @param worldObj WorldObject this view will display
    **/
   public void setWorldObject(WorldObject worldObj)
   {
      this.worldObject = worldObj;
   }
}