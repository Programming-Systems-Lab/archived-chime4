package psl.chime4.server.cwm.world;

/**
 * Represents a human avatar in the world. This is an actual client in the 
 * CHIME system.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class HumanAvatar extends WorldObject
{
   /** the uid of the client in CHIME **/
   private int uid;
   
   /**
    * Get the user-ID of the client in CHIME.
    *
    * @return user-ID of the client
    **/
   public int getUID()
   {
      return userID;
   }
   
   /**
    * Set the user-id of the client in CHIME.
    *
    * @param uid the user-ID of the client.
    **/
   public void setUID(int uid)
   {
      this.uid = uid;
   }
}
