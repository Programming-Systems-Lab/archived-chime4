package psl.chime4.server.cebs.elvin;

import psl.chime4.server.cebs.EventSender;
import psl.chime4.server.cebs.Event;
import psl.chime4.server.cebs.CEBSException;

import org.elvin.je4.Producer;
import org.elvin.je4.Notification;
import org.elvin.je4.ElvinURL;

import java.util.Map;
import java.util.HashMap;

/**
 * An EventSender used to send events over the Elvin protocol.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
class ElvinEventSender extends EventSender
{
    /** map of Producer's mapped to the address they're sending to **/
    private Map connectionMap = new HashMap();
    
    /**
     * Construct an ElvinEventSender with the correct source user ID.
     *
     * @param sourceUID the user ID of the entity sending events
     **/
    ElvinEventSender(int sourceUID)
    {
        super(sourceUID);
    }
    
    /**
     * Open a physical connection to an Elvin event server. This constructs
     * an actual Producer object and caches it.
     *
     * @param server Elvin server to connect to
     **/
    protected void openRealConnection(String server) throws CEBSException
    {
         try
         {
             // parse the server address to an elvin URL
             String elvinURL = ElvinEventSystem.parseToElvinURL(server);
             
             // make a new Producer
             Producer producer = new Producer(new ElvinURL(elvinURL));
             
             // add it to the connectionMap
             connectionMap.put(server, producer);
         }
         catch (Exception e)
         {
             String msg = "could not open connection to: " + server;
             throw new CEBSException(msg, e);
         }
    }

    
    /**
     * Send an Event to an Elvin server.
     *
     * @param server event server to send the event to
     * @param event  the event to send
     * @throws CEBSException
     *         if the event cannot be sent
     **/
    protected void sendEvent(String server, Event event) throws CEBSException
    {
        try
        {
            // get the producer
            Producer producer = (Producer) connectionMap.get(server);
            
            // pass it the underlying Notification obj
            producer.notify( 
                ((ElvinEvent) event).getUnderlyingNotification() );
        }
        catch (Exception e)
        {
            String msg = "could not send event to " + server;
            throw new CEBSException(msg, e);
        }
    }
    
    /**
     * Close a real connection to an Elvin server. This method closes the
     * Producer and uncaches it. 
     *
     * @param server the server to disconnect from
     **/
    protected void closeRealConnection(String server)
    {
        // get the producer from the map
        Producer producer = (Producer) connectionMap.get(server);
        
        // close the producer
        producer.close();
        
        // remove it from the map
        connectionMap.remove(server);
    }
}