package psl.chime4.server.base;

/**
 * Numerous constants used in event processing.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public interface EventConstants
{
   /** the many different events and their event codes **/
   
   // events concerning the VEM
   public static final int MODEL_CHANGE_REQUEST_EVENT = 5000;
   public static final int MODEL_UPLOAD_REQUEST_EVENT = 5001;
   public static final int MODEL_DATA_EVENT = 5002;
   public static final int MODEL_CHANGED_EVENT = 5003;
   public static final int MODEL_DOWNLOAD_REQUEST_EVENT = 5004;
   
   // events concerning the librarian
   public static final int LIBRARIAN_SEARCH_REQUEST_EVENT = 6000;
}