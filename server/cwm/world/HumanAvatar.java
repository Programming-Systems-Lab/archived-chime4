package psl.chime4.server.cwm.world;

import psl.chime4.server.cwm.User;

/**
 * Represents a human avatar in the world. This is an actual client in the 
 * CHIME system.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class HumanAvatar extends WorldObject
{
   /** the User object representing of the client in CHIME **/
   private User user;
   
   /**
    * Get the User object for the client in CHIME.
    *
    * @return User that represents this client
    **/
   public User getUser()
   {
      return user;
   }
   
   /**
    * Set the User of this client in CHIME.
    *
    * @param user the User object for the client.
    **/
   public void set(User user)
   {
      this.user = user;
   }
}
