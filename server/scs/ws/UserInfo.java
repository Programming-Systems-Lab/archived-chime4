package psl.chime4.server.scs.ws;

import psl.chime4.server.scs.persist.PersistentObject;
import psl.chime4.server.scs.ws.view.Theme;

import psl.chime4.server.ces.*;

/**
 * Represents information about a User logged onto the Chime server.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class UserInfo extends PersistentObject
{
   /** authentication status for the user **/
   public boolean authenticated;
   
   
   /** globally unique for this user **/
   public String username;
   
   /** the topic on the event server that only the user is listening to **/
   public String uniqueTopic;
   
   /** the current theme of the user **/
   private Theme currentTheme;
   
   /** EventBridge used to send events to the user **/
   private EventBridge bridge;
   
   /**
    * Get whether this user has been authenticated.
    * 
    * @return <code>true</code> if this user is authenticated else 
    *         <code>false</code>
    **/
   public boolean isAuthenticated()
   {
      return authenticated;
   }
   
   /**
    * Set whether this user has been authenticated.
    *
    * @param authenticated the auth status of this user
    **/
   public void setAuthenticated(boolean authenticated)
   {
      this.authenticated = authenticated;
   }
   
   /**
    * Get the globally unique username of this user.
    *
    * @return globally unique username of this user
    **/
   public String getUserName()
   {
      return username;
   }
   
   /**
    * Set the globally unique username of this user.
    *
    * @param username username for this user
    **/
   public void setUserName(String username)
   {
      this.username = username;
   }
   
   /**
    * Get the unique topic for this user. The user should be the only person
    * subscribed to this topic.
    *
    * @return unique topic for this user
    **/
   public String getUniqueTopic()
   {
      return uniqueTopic;
   }
   
   /**
    * Set the unique topic for this user.
    *
    * @param uniqueTopic unique topic for this user
    **/
   public void setUniqueTopic(String uniqueTopic)
   {
      this.uniqueTopic = uniqueTopic;
   }
   
   /**
    * Get the current theme for this user.
    *
    * @return current theme for this user
    **/
   public Theme getCurrentTheme()
   {
      return currentTheme;
   }
   
   /**
    * Set the current theme for this user.
    *
    * @param theme the current theme for this user
    **/
   public void setCurrentTheme(Theme theme)
   {
      this.currentTheme = theme;
   }
   
   /**
    * Set the event bridge that will be used to send and recieve events 
    * directly to this user.
    *
    * @param bridge EventBridge used to send events to this user
    * @throws IllegalArgumentException
    *         if <code>bridge</code> is <code>null</code>
    **/
   public void setEventBridge(EventBridge bridge)
   {
      if (bridge == null)
      {
         String msg = "bridge cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      this.bridge = bridge;
   }
   
   /**
    * Send an event to the unique topic of this user. Ideally, only the user
    * will see this event.
    *
    * @param event    Event to send to the user
    * @throws IllegalArgumentException
    *         if any parameter is <code>null</code>
    * @throws EventServiceException
    *         if the event could not be sent
    **/
   public void sendEvent(Event event) throws EventServiceException
   {
      if (event == null)
      {
         String msg = "event cannot be null";
         throw new IllegalArgumentException(msg);
      }
      
      // publish the event to the user's unique topic
      bridge.publish(event, uniqueTopic);
   }
   
   /**
    * Get string representation suitable for debugging.
    *
    * @return debugging string
    **/
   public String toString()
   {
      return "UserInfo[authenticated=" + authenticated + ",username=" + 
         username + ",uniqueTopic=" + uniqueTopic + ",theme=" + currentTheme
         + "]";
   }
}