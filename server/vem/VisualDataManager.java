/*
 * VisualDataManager.java
 *
 * Created on March 29, 2002, 1:17 PM
 */

package psl.chime4.server.vem;

import psl.chime4.server.data.*;
import psl.chime4.server.cwm.world.*;
import psl.chime4.server.vem.util.*;

/**
 * Public API interface to the server side VEM (Visual Environment Modeler)
 *
 * @author  Vladislav Shchogolev
 */
public class VisualDataManager {
    
    private VemMapDAO mDataStore;
    public int mUserID;
    public String mTheme;
    
    /**
	 *	Creates a new instance of VisualDataManager 
	 */
    public VisualDataManager(DAOFactory iFactory) {
		VemMapDAO mDataStore = (VemMapDAO) iFactory.getDAO(VemMap.class);
		mUserID = psl.chime4.server.auth.User.GLOBAL_ID;
		mTheme = null;
	}
    
    public void setUserID(int userID) {
        this.mUserID = userID;
    }
    
    public void setTheme(String themeName) {
        this.mTheme = themeName;
    }
    
	private ResourceFile getResourceFileFor(MetadataWorldObject mdwo) 
	throws DataAccessException
	{
		ResourceDescriptor rd = mdwo.getMetadata();
		String objectURL = rd.getPath();
		String contentType = rd.getContentType();
		ResourceFileManager rfm = ResourceFileManager.getInstance();
		
		int mappingID, resourceID;
		VemMap vmap;
		
		mappingID = mDataStore.search(mUserID, objectURL, contentType, VemType.COMPONENT);
		vmap = (VemMap) mDataStore.load(mappingID);
		resourceID = vmap.getVemData().getModelID();
		
		return rfm.getResourceFile(resourceID);
	}
}
