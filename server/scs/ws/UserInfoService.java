package psl.chime4.server.scs.ws;

import psl.chime4.server.scs.persist.*;
import psl.chime4.server.scs.service.*;

import java.util.*;

/**
 * Keeps track of all users logged into the current world service.
 *
 * @note Currently this class is not a true service. In the future this will
 *       probably change.
 * @note In the future this class will implement a ping service; it will 
 *       randomly ping users to determine if they're still connected and if
 *       not it'll disconnect them
 * 
 * @author Azubuko Obele
 * @version 0.1
 **/
public class UserInfoService 
{
   /** map used to store users **/
   private Map userMap = new HashMap();
   
   /** persistence service used to create UserInfo objects **/
   private PersistenceService pService;
   
   /** world service this object is working for **/
   private WorldService wService;
   
   /**
    * Set the WorldService that this UserInfoService is working for.
    *
    * @param wService WorldService that this user info service is working for
    * @throws IllegalArgumentException
    *         if <code>wService</code> is <code>null</code>
    **/
   public void setWorldService(WorldService wService)
   {
      if (wService == null)
      {
         String msg = "wService cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.wService = wService;
   }
         
   /**
    * Set the PersistenceService that this UserInfoService should use to
    * create, delete, and store UserInfo objects.
    *
    * @param pService PersistenceService to use 
    * @throws IllegalArgumentException
    *         if <code>pService</code> is <code>null</code>
    **/
   public void setPersistenceService(PersistenceService pService)
   {
      if (pService == null)
      {
         String msg = "pService cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.pService = pService;
   }
   
   /**
    * Create a new User. 
    *
    * @param name  globally unique name of the user
    * @param topic unique topic for the user
    * @return new UserInfo object initialized with a username and topic
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    * @throws ServiceException
    *         if the underlying persistence service fails
    **/
   public UserInfo newUser(String name, String topic)
   {
      if ((name == null) || (topic == null))
      {
         String msg = "no parameter can be null";
         throw new IllegalArgumentException(msg);
      }
      
      // create and initialize the user object
      try
      {
         UserInfo newUser = (UserInfo) pService.create(UserInfo.class);
         
         newUser.setUserName(name);
         newUser.setUniqueTopic(topic);
      
         // store it in the map
         userMap.put(name, newUser);
      
         return newUser;
      }
      catch (ServiceException se)
      {
         return null;
         // persistence service has failed, do something
      }
   }
   
   /**
    * Return true if a user is currently logged in and authenticated. 
    *
    * @param name name of the user
    * @return <code>true</code> if user with the given name is authenticated
    *         else <code>false</code>
    * @throws IllegalArgumentException
    *         if <code>name</code> is <code>null</code>
    **/
   public boolean isAuthenticated(String name)
   {
      if (name == null)
      {
         String msg = "name cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      if (!userMap.containsKey(name))
      {
         return false;
      } else
      {
         UserInfo user = (UserInfo) userMap.get(name);
         return user.isAuthenticated();
      }
   }
   
   /**
    * Get the user associated with a given globally unqiue username.
    *
    * @param username name of the user 
    * @return UserInfo that represents that user or <code>null</code>
    * @throws IllegalArgumentException
    *         if <code>username</code> is <code>null</code>
    **/
   public UserInfo getUser(String username)
   {
      if (username == null)
      {
         String msg = "username cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      return (UserInfo) userMap.get(username);
   }
}
