
package psl.chime4.server.auth;


public interface MessageDefinitions {

    // Message types
     static String AUTHENTICATION_REQUEST = "authenticate";
     static String AUTHENTICATION_GRANT   = "authgrant";
     static String AUTHENTICATION_REJECT  = "authreject";


    // Message body key definitions
     static String USERID                 = "userid";
     static String AUTHCODE               = "authcode";
     static String AUTHTICKET             = "authticket";

} // end MessageDefinitions interface
