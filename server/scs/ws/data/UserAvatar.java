package psl.chime4.server.scs.ws.data;

import psl.chime4.server.scs.ws.UserInfo;

/**
 * Represents a human/user avatar in the world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class UserAvatar extends Avatar
{
   /** user info obj for this avatar **/
   private UserInfo user;
   
   /**
    * Get info about the user for this avatar.
    *
    * @return info about the user for this avatar
    **/
   public UserInfo getUserInfo()
   {
      return user;
   }
   
   /**
    * Set info about the user for this avatar.
    *
    * @param user info about the user for this avatar
    **/
   public void setUserInfo(UserInfo user)
   {
      this.user = user;
   }
}
   
   