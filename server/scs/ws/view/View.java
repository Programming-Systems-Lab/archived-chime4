package psl.chime4.server.scs.ws.view;


/**
 * Represents what an object looks like in the world. This class can also
 * be used to describe what rooms in the world look like.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class View extends PersistentObject
{
   /** instruction to the 3D engine how to draw this view **/
   private RenderInstruction renderInstruction;
   
   /** world object that is being drawn **/
   private WorldObject wobj;
   
   /** geometry that defines the shape of this view **/
   private Geometry geometry;
   
   /**
    * Get the render instructions for how to draw this object.
    *
    * @return render instruction for how to draw this object
    **/
   public RenderInstruction getRenderInstruction()
   {
      return renderInstruction;
   }
   
   /**
    * Set the render instruction for how to draw this object.
    *
    * @param instr instructions how to draw this object
    **/
   public void setRenderInstruction(RenderInstruction instr)
   {
      this.renderInstruction = instr;
   }
   
   /**
    * Get the world object being drawn.
    *
    * @return world object being drawn
    **/
   public WorldObject getWorldObject()
   {
      return wobj;
   }
   
   /**
    * Set the world object this room is drawing.
    *
    * @param wobj world object that this room is being drawn
    **/
   public void setWorldObject(WorldObject wobj)
   {
      this.wobj = wobj;
   }
   
   /**
    * Get the geometry defining the general shape of this view.
    *
    * @return gemoetry defining the shape of this view
    **/
   public Geometry getGeometry()
   {
      return geometry;
   }
   
   /**
    * Set the geometry that defines the general shape of this view.
    *
    * @param geom geometry defining the shape of the view
    **/
   public void setGeometry(Geometry geom)
   {
      this.geometry = geom;
   }
}