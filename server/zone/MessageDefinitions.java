
package psl.chime4.server.zone;


public interface MessageDefinitions {

    // Message types
    public static String TRANSFER_ZONE            = "tranfer_zone";
    public static String REJECT_ZONE_TRANSFER     = "reject_zone_transfer";
    public static String ACCEPT_ZONE_TRANSFER     = "accept_zone_transfer";
    public static String START_ZONE_SERVICE       = "start_zone_service";
    public static String STOP_ZONE_SERVICE        = "stop_zone_service";
    public static String REQUEST_PRIMARY_BACKUP   = "request_primary_backup";
    public static String REQUEST_SECONDARY_BACKUP = "request_secondary_backup";
    public static String OFFER_PRIMARY_BACKUP     = "offer_primary_backup";
    public static String OFFER_SECONDARY_BACKUP   = "offer_secondary_backup";
    public static String REJECT_ZONE_BACKUP       = "reject_zone_backup";
    public static String ACCEPT_ZONE_BACKUP       = "accept_zone_backup";



    // Message body key definitions
    public static String ZONE_LIST                = "zonelist";
    public static String SET_PREVIOUS_NEIGHBOR    = "setprevneighbor";
    public static String SET_NEXT_NEIGHBOR        = "setnextneighbor";

} // end MessageDefinitions Stringerface
