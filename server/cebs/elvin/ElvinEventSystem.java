package psl.chime4.server.cebs.elvin;

import psl.chime4.server.cebs.EventSystem;

/**
 * An interface to the Elvin event protocol using it as a basic pub/sub 
 * system.
 *
 * @author Azubuko Obele
 * @version 0.1
 **/
public class ElvinEventSystem extends EventSystem
{
    /**
     * Construct a new EventSystem interface to elvin. 
     *
     * @param sourceUID user ID of the user using this system
     **/
    public ElvinEventSystem(int sourceUID)
    {
        super(sourceUID);
        super.eventReciever = new ElvinEventReciever(super.eventQueue);
        super.eventSender = new ElvinEventSender(super.sourceUID);
    }
    
    /**
     * Parse a cebs address in the form of, "cebs://[host]:[port]" into an
     * Elvin address in the form of "elvin:4.0/tcp,none,xdr/[host]:[port]".
     *
     * @param server cebs address to parse
     * @return elvin address equivalent to <code>server</code>
     * @throws IllegalArgumentException
     *        if <code>server</code> is not a valid cebs address or is
     *        <code>null</code>
     **/
    static String parseToElvinURL(String server)
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
        buf.insert(0, "elvin:4.0/tcp,none,xdr/");
        
        return buf.toString();
    }
}