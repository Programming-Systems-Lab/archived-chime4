package psl.chime4.server.scs.ws.data;

/**
 * Represents a connection from one room to another.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class Door extends LocatedWorldObject
{
   /** room this door is leading from **/
   private Room source;
   
   /** room this door is goes to **/
   private Room destination;
   
   /**
    * Get the room that this door is leading from.
    *
    * @return room this door leads from
    **/
   public Room getSource()
   {
      return source;
   }
   
   /**
    * Set the room that this door leads from.
    *
    * @param source door this from
    **/
   public void setSource(Room source)
   {
      this.source = source;
   }
   
   /**
    * Get the room that this door leads to.
    *
    * @return room this door goes to
    **/
   public Room getDestination()
   {
      return destination;
   }
   
   /**
    * Set the destination that this door leads to.
    *
    * @param dest room this room leads to
    **/
   public void setDestination(Room dest)
   {
      this.destination = dest;
   }
}
   