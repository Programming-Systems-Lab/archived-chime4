package psl.chime4.server.cwm;

/**
 * A WorldObjectView represents a view of an object in the world, literally
 * how it looks to observers.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public abstract class WorldObjectView extends AbstractPersistentObject
{
   /** model ID of the object **/
   private int modelID = -1;
   
   /** WorldObject that this is the view for **/
   private WorldObject worldObj;
   
   /**
    * Get the model ID of the 3D model file used to display the object in the
    * world.
    *
    * @return model-ID of this object or <code>-1</code> if there is none
    **/
   public int getModelID()
   {
      return modelID;
   }
   
   /**
    * Set the model ID of the 3D model file used to display the object in
    * the world.
    *
    * @param modelID the model-ID of the object
    **/
   public void setModelID(int modelID)
   {
      this.modelID = modelID;
   }
   
   /**
    * Get the WorldObject that this view is displaying.
    *
    * @return WorldObject whose view this is
    **/
   public WorldObject getWorldObject()
   {
      return worldObj;
   }
   
   /**
    * Set the WorldObject whose view this will be displaying.
    *
    * @param worldObj the underlying world object to be viewed
    **/
   public void setWorldObject(WorldObject worldObj)
   {
      this.worldObj = worldObj;
   }
}