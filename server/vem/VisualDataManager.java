/*
 * VisualDataManager.java
 *
 * Created on March 29, 2002, 1:17 PM
 */

package psl.chime4.vem;

import psl.chime4.data.*;

/**
 * Public API interface to the server side VEM (Visual Environment Modeler)
 *
 * @author  Vladislav Shchogolev
 */
public class VisualDataManager {
    
    private static VisualDataManager mVDM;
    
    /** Creates a new instance of VisualDataManager */
    private VisualDataManager() {
		VemMapDAO dao = VemMapDAOFactory.getInstance().getDAO();
    }
    
    public long getModel(long objectID, long typeID) {
        return 0;
    }
    
    public long getModel2D(long objectID, long typeID) {
        return 0;
    }

    // Will declare Environment class later
    /*
    public Environment getEnvironment(long locationID) {
    }
    */
    
    public void setUser(long userID) {
    }
    
    public void setTheme(String themeName) {
    }
    
    public void doChangeRequest(ChangeViewRequest cvr) {
    }
    
    public static VisualDataManager getInstance() {
        if (mVDM == null) {
            mVDM = new VisualDataManager();
        }
        
        return mVDM;
    }
    
}
