package psl.chime4.server.cwm;

/**
 * This class represents a User who has logged in to the CHIME world.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class User extends AbstractPersistentObject
{
   /** 
    * The topic that the user is subscribed to on the event server. This is
    * the topic that events should be published to if you only want this
    * individual user to recieve them.
    **/
   private String topic;
   
   /** globally unique user-ID of the user **/
   private int uid;
   
   /**
    * Get the individual-topic for the User. Events published to this topic
    * will only be recieved by the user.
    *
    * @return individual-topic for this user 
    **/
   public String getTopic()
   {
      return topic;
   }
   
   /**
    * Set the individual-topic for the User.
    *
    * @param topic individual-topic for this User
    **/
   public void setTopic(String topic)
   {
      this.topic = topic;
   }
   
   /**
    * Get the user-ID for this User. This uid is globally unique and 
    * persistent. This user-ID is defined by the authentication server.
    *
    * @return uid for this client
    **/
   public int getUID()
   {
      return uid;
   }
   
   /**
    * Set the user-ID for this User. 
    *
    * @param uid User-ID for this client.
    **/
   public void setUID(int uid)
   {
      this.uid = uid;
   }
}