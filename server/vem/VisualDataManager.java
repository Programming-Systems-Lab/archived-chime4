/*
 * VisualDataManager.java
 *
 * Created on March 29, 2002, 1:17 PM
 */

package psl.chime4.server.vem;

import java.io.File;

import psl.chime4.server.data.*;
import psl.chime4.server.cwm.world.*;
import psl.chime4.server.vem.util.*;

/**
 * Public API interface to the server side VEM (Visual Environment Modeler)
 *
 * @author  Vladislav Shchogolev
 * @version $Revision$
 */
public class VisualDataManager {
    
    private int mUserID;
    private String mTheme;
    
    private ResourceFileManager mFileManager;
    private VemMapDAO mDataStore;
    
    /**
     *	Creates a new instance of VisualDataManager
     */
    public VisualDataManager(DAOFactory iFactory) {
	VemMapDAO mDataStore = (VemMapDAO) iFactory.getDAO(VemMap.class);
	mUserID = psl.chime4.server.auth.User.GLOBAL_ID;
	mTheme = null;
    }
    
    /**
     * Startup. At this point the VDM should parse its configuration
     * data. There may be a long delay between when this method is called and
     * the start method is called so the VDM should only do basic initialization
     * and not actually begin executing in this method.
     *
     * @param properties the global properties for the world manager
     * @param logger     if the VDM must do logging it should use this logger
     **/
    public void initialize(java.util.Map properties,
	java.util.logging.Logger logger) 
    {
	mFileManager = ResourceFileManager.getInstance(properties);
    }
    
    /**
     * Begin running. At this point the VDM file should begin running.
     **/
    public void start() {
	// start any threads here
    }
    
    /**
     * Stop running. At this point the VDM file should be ready to shutdown.
     **/
    public void stop() {
	// stop any threads
    }
    
    /**
     * Shutdown. At this point the VDM should store all necessary information
     * it must store in the global configuration properties object. All data
     * that the VDM stores must be matched to keys which match the wildcard
     * "vem.*" to ensure that there are no key collisions.
     *
     * @param properties the global properties object
     **/
    public void shutdown(java.util.Map properties) {
    }
    
    /**
     * Retrieve an image. If the client recieves a modelID that it has no
     * corresponding data file for, it must retrieve the data file from the
     * server. In order to do this, it sends back the modelID and the VDM must
     * retrieve that image and return a reference to that model file on the
     * local disk.
     *
     * @param modelID universally unique ID for an image file
     * @return File corresponding to the given model-ID
     **/
    public File getModelFile(int modelID) {
	return mFileManager.getResourceFile(modelID).getFile();
    }
    
    public void setUserID(int userID) {
	this.mUserID = userID;
    }
    
    public void setTheme(String themeName) {
	this.mTheme = themeName;
    }
    
    /**
     *	Performs a change on an instance specific basis.
     */
    private void doInstanceViewChange(ChangeViewRequest req) 
    throws DataAccessException {
	
	String objectURL = null;
	int mappingID, priority;
	VemMap vmap;
	VemType component = VemType.COMPONENT;
	
	// FIXME: get Metadata for req.getObjectID()
	// objectURL = WorldManager.getObjectForID(req.getObjectID()).getMetadata().getPath()
	
	mappingID = mDataStore.search(
	    (int)req.getUser(), null, objectURL, component);
	vmap = (VemMap) mDataStore.load(mappingID);
	
	// find the model ID's for the given checksum
	int modelID = mFileManager.getKeyForChecksum(req.getChecksum());
	
	if (vmap.isExplicitMap()) {
	    // change this VemMap to the new model ID
	    vmap.getVemData().setModelID(modelID);
	} else {
	    // create new VemMap
	    VemData data = new VemData();
	    data.setImageID(-1);
	    data.setModelID(modelID);
	    data.setType(component);
	    data.setSubType('A');   // FIXME 
	    
	    priority = VemMap.EXPLICIT + 
		(req.isGlobalChange() ? VemMap.GLOBAL_MAP : VemMap.USER_MAP);
	    
	    vmap = new VemMap((int)req.getUser(), objectURL, priority, data);
	}
	
	// store it back
	mDataStore.store(vmap);
    }
}
