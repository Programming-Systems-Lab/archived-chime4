package psl.chime4.server.scs.ws.event;

/**
 * This interface contains several constants used for defining events.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface EventConstants
{
   public static final int WorldLogin = 3000;
   public static final String WorldLoginUri = "chime:event:world_login";
   
   public static final int WorldLoginSuccess = 3001;
   public static final String WorldLoginSuccessUri = 
      "chime:event:world_login_success";
   
   public static final int WorldLoginFailure = 3002;
   public static final String WorldLoginFailureUri = 
      "chime:event:world_login_failure";
   
   public static final int WorldEntry = 3100;
   public static final String WorldEntryUri = "chime:event:world_entry";
   
   public static final int WorldEntrySuccess = 3101;
   public static final String WorldEntrySuccessUri = 
      "chime:event:world_entry_success";
   
   public static final int WorldEntryFailure = 3102;
   public static final String WorldEntryFailureUri = 
      "chime:event:world_entry_failure";
}