package psl.chime4.server.cwm;

/**
 * A door represents a connection from one room to another.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class Door extends WorldObject
{
   /** the room this door is in **/
   private Room source;
   
   /** the room this door leads to **/
   private Room destination;
   
   /**
    * Get the room this Door is in.
    *
    * @return Room this door is in
    **/
   public Room getSource()
   {
      return source;
   }
   
   /**
    * Set the room this door is in.
    *
    * @param source the room this door is in
    **/
   public void setSource(Room source)
   {
      this.source = source;
   }
   
   /**
    * Get the Room this door leads to.
    *
    * @return the room this door leads to
    **/
   public Room getDestination()
   {
      return destination;
   }
   
   /**
    * Set the Room this door leads to.
    *
    * @param dest the room this door leads to
    **/
   public void setDestination(Room dest)
   {
      this.destination = dest;
   }
}