/*
 * VisualDataManager.java
 *
 * Created on March 29, 2002, 1:17 PM
 */

package psl.chime4.server.vem;

/**
 * Public API interface to the server side VEM (Visual Environment Modeler)
 *
 * @author  Vladislav Shchogolev
 */
public class VisualDataManager {
    
    /** Singleton object  */
    private static VemMapDAO dataStore;
    private VemMapDAO mDataStore;
    
    public long mUser;
    public String mTheme;
    
    /** Creates a new instance of VisualDataManager */
    public VisualDataManager() {
        if (dataStore == null) {
            dataStore = VemMapDAOFactory.getInstance();
        }
        
        mDataStore = dataStore;
        
        
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
        this.mUser = userID;
    }
    
    public void setTheme(String themeName) {
        this.mTheme = themeName;
    }
    
    public void doChangeRequest(ChangeViewRequest cvr) {
    }
    
    /*
    public static VisualDataManager getInstance() {
        if (mVDM == null) {
            mVDM = new VisualDataManager();
        }
        
        return mVDM;
    }   
    */   
}
