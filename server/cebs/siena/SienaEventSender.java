package psl.chime4.server.cebs.siena;

import psl.chime4.server.cebs.EventSender;
import psl.chime4.server.cebs.Event;
import psl.chime4.server.cebs.CEBSException;

import siena.ThinClient;
import siena.Notification;

import java.util.Map;
import java.util.HashMap;

/**
 * An EventSender used for sending events over the Siena transport.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
class SienaEventSender extends EventSender
{
    /** connections to servers mapped to the server they're connected to **/
    private Map connectionMap = new HashMap();
    
    /**
     * Construct a SienaEventSender with the correct source ID.
     *
     * @param sourceUID user ID of the entity sending events
     **/
    SienaEventSender(int sourceUID)
    {
        super(sourceUID);
    }
    
    /**
     * Open a physical connection to a Siena event server.
     *
     * @param server the Siena event server to send the event to
     * @throws CEBSException
     *         if the connection to the server could not be made
     **/
    protected void openRealConnection(String server) throws CEBSException
    {
        try
        {
            // parse the URL to a siena URL
            String sienaURL = SienaEventSystem.parseToSienaURL(server);
            
            // make the thin client
            ThinClient client = new ThinClient(sienaURL);
            
            // put it in the connection map
            connectionMap.put(server, client);
        }
        catch (Exception e)
        {
            String msg = "couldn't connect to server: " + server;
            throw new CEBSException(msg, e);
        }
    }
    
    /**
     * Send an event to the Siena event server.
     *
     * @param server the sever to send the event to
     * @param event the event to send to the server
     * @throws CEBSException
     *         if the connection to the server could not be made
     **/
    protected void sendEvent(String server, Event event) throws CEBSException
    {
        try
        {
            // get the connection to the server
            ThinClient client = (ThinClient) connectionMap.get(server);
        
            client.publish( ((SienaEvent) event).getUnderlyingNotification() );
        }
        catch (Exception e)
        {
            String msg = "couldn't send event to server: " + server;
            throw new CEBSException(msg, e);
        }
    }
    
    /**
     * Close the real connection to the server.
     *
     * @param server Siena event server to disconnect from
     **/
    protected void closeRealConnection(String server)
    {
        // get the client and shut it down
        ThinClient client = (ThinClient) connectionMap.get(server);
        client.shutdown();
        
        // remove it from the map
        connectionMap.remove(server);
    }
}       