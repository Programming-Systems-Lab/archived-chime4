package psl.chime4.server.cwm.world.view;

import psl.chime4.server.cwm.world.persist.PersistentObject;
import psl.chime4.server.cwm.world.persist.Cacheable;
import psl.chime4.server.cwm.world.persist.ObjectID;

import psl.chime4.server.cwm.world.wms.WorldObject;

import java.io.Serializable;

/**
 * A View represents how an object looks in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class View extends PersistentObject 
   implements Cacheable, Serializable
{
   /** uri defining the view's model file **/
   private String modelURI;
   
   /** the id of the world object the view is displaying **/
   private ObjectID worldObject;
   
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
    * Get the id of the WorldObject associated with this view.
    *
    * @return object id of the world object owned by this view
    **/
   public ObjectID getWorldObject()
   {
      return worldObject;
   }
   
   /**
    * Set the WorldObject associated with this view.
    *
    * @param worldObj id of the WorldObject this view will display
    * @throws IllegalArgumentException
    *         if <code>worldObj</code> is not owned by a world object
    **/
   public void setWorldObject(ObjectID worldObj)
   {
      if ((worldObj != null) && 
          (worldObj.getOwnerType() != WorldObject.class))
      {
         String msg = "views can only be applied to world objects";
         throw new IllegalArgumentException(msg);
      }
      
      this.worldObject = worldObj;
   }
}