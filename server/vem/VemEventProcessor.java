/*
 * VemEventProcessor.java
 *
 * Created on April 4, 2002, 5:44 PM
 */

package psl.chime4.server.vem;

import psl.chime4.server.cwm.*;
import psl.chime4.server.base.*;

import java.util.logging.Logger;

/**
 *
 * @author  Vladislav Shchogolev
 */
public class VemEventProcessor {
    
    private VisualDataManager mVDM;
    
    /** Creates a new instance of VemEventProcessor */
    public VemEventProcessor(String topLevelModelDir) {
        mVDM = new VisualDataManager();
    }
    
    public void startup(Logger logger)
    throws ChimeException {
        // initialize database
    }
    
    public void processEvent(ChimeEvent event) throws ChimeException {
    }
    
    public void shutdown() throws ChimeException {
        
    }
}