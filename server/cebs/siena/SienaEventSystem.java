package psl.chime4.server.cebs.siena;

import psl.chime4.server.cebs.Event;
import psl.chime4.server.cebs.EventSystem;

import siena.ThinClient;

import java.util.Map;
import java.util.HashMap;

/**
 * An EventSystem interface for using the Siena transport system. 
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class SienaEventSystem extends EventSystem
{
    /**
     * Construct a new EventSystem interface to Siena.
     *
     * @param sourceUID the entity recieving and sending events
     **/
    public SienaEventSystem(int sourceUID)
    {
        super(sourceUID);
        super.eventReciever = new SienaEventReciever(super.eventQueue);
        super.eventSender = new SienaEventSender(super.sourceUID);
    }
    
    /**
     * Parse a cebs address of the form "cebs://[host]:[port]" to a Siena
     * URL of the form "senp://[host]:[port]".
     *
     * @param server cebs address to parse
     * @return equivalent Siena address
     * @throws IllegalArgumentException
     *         if the conversion could not take place
     **/
    static String parseToSienaURL(String server)
    {
        if ( server == null )
        {
            String msg = "server cannot be null";
            throw new IllegalArgumentException(msg);
        }
        
        StringBuffer buf = new StringBuffer(server);
        
        // remove the first 7 letters
        buf.delete(0, 6);
        
        // now prepend the elvin stuff
        buf.insert(0, "senp://");
        
        return buf.toString();
    }
    
    /**
     * Create an empty event.
     *
     * @return an empty Event object.
     **/
    public Event createEmptyEvent()
    {
        return new SienaEvent();
    }
}