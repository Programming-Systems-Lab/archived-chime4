/*
 * VisualizationService.java
 *
 * Created on May 17, 2002, 1:02 PM
 */

package psl.chime4.server.vem;

import psl.chime4.server.cwm.world.*;
import psl.chime4.server.data.*;
import psl.chime4.server.vem.util.*;

/**
 * Responsible for creating a view of the world as well as updating that view.
 *
 * @author Vladislav Shchogolev
 * @author Azubuko Obele
 * @version $Revision$
 *
 **/
public class VisualizationService {
    
    private VemMapDAO mDataStore;
    private int mUserID;
    
    /**
     *	Set the user from whom queries will be performed.
     */
    public void setUserID(int iUserID) {
	mUserID = iUserID;
    }
    
    public VisualizationService(VemMapDAO iDAO) {
	mDataStore = iDAO;
	mUserID = psl.chime4.server.auth.User.GLOBAL_ID;	
    }
    
    /**
     * Given a Room with a pre-defined size as well as other hints, the
     * VisualDataManager must assign a universally-unique model-ID which
     * corresponds to a three-dimensional data file.
     *
     * @param room     a description of a room in the world
     * @throws ModelException
     *         if no appropriate model of the room can be modeled
     **/
    public RoomView createView(Room room) throws ModelException {
	
	RoomView view = new RoomView();
	
	try {
	    view.setModelID(getModelIDFor(room.getMetadata(), VemType.CONTAINER));
	} catch (DataAccessException dae) {throw new ModelException(dae);}
	
	return view;
    }
    
    /**
     * Given a description of an object in the world, the VisualDataManager
     * must assign a universally-unique model-ID which corresponds to the
     * a three-dimensional data file.
     *
     * @param object the object in the world
     * @param room   the room that <code>object</code> is in
     * @param hints  hints of what the room should look like
     * @throws ModelException
     *         if the VDM cannot determine what an object should look like
     **/
    public LocatableWorldObjectView createView(WorldObject object, Room room)
    throws ModelException {
	
	//FIXME: why is LocatableWorldObjectView abstract?
	
	LocatableWorldObjectView view = new LocatableWorldObjectView();
	view.setWorldObject(object);
	MetadataWorldObject mdwo;
	
	if (object instanceof MetadataWorldObject) {
	    mdwo = (MetadataWorldObject) object;
	    
	    try {
		view.setModelID(getModelIDFor(mdwo.getMetadata(), VemType.COMPONENT));
	    } catch (DataAccessException dae) {throw new ModelException(dae);}
	    
	    return view;
	} else {
	    return null; // FIXME: not sure about buko's WorldObject.typeID
	}      
    }
    
    private int getModelIDFor(ResourceDescriptor iRD, VemType iVT)
    throws DataAccessException {
	String objectURL = iRD.getPath();
	String contentType = iRD.getContentType();
	int mappingID;
	VemMap vmap;
	
	mappingID = mDataStore.search(mUserID, objectURL, contentType, iVT);
	vmap = (VemMap) mDataStore.load(mappingID);
	return vmap.getVemData().getModelID();	
    }
    
    private ResourceFile getVisualizationFor(ResourceDescriptor iRD, VemType iVT)		    
    throws DataAccessException {
	ResourceFileManager rfm = ResourceFileManager.getInstance();
	int resourceID = getModelIDFor(iRD, iVT);
	
	return rfm.getResourceFile(resourceID);
    }
}