/*
 * ChangeViewRequest.java
 *
 * Created on March 29, 2002, 1:31 PM
 */

package psl.chime4.vem;

import java.beans.*;

/**
 * JavaBean representing a change request to the view model.
 *
 * @author  Vladislav Shchogolev
 */
public class ChangeViewRequest extends Object implements java.io.Serializable {
    
    /** Holds value of property user. */
    private long user;
    
    /** Holds value of property objectID. */
    private long objectID;
    
    /** Holds value of property typeID. */
    private long typeID;
    
    /** Holds value of property globalChange. */
    private boolean globalChange;
    
    /** Holds value of property modelFilename. */
    private String modelFilename;
    
    /** Holds value of property modelFilename2D. */
    private String modelFilename2D;
    
    /** Holds value of property checksum. */
    private String checksum;
    
    /** Holds value of property clientCallbackID. */
    private String clientCallbackID;
    
    /** Creates new ChangeViewRequest */
    public ChangeViewRequest() {
    }
    
    /** Getter for property user.
     * @return Value of property user.
     */
    public long getUser() {
        return this.user;
    }
    
    /** Setter for property user.
     * @param user New value of property user.
     */
    public void setUser(long user) {
        this.user = user;
    }
    
    /** Getter for property objectID.
     * @return Value of property objectID.
     */
    public long getObjectID() {
        return this.objectID;
    }
    
    /** Setter for property objectID.
     * @param objectID New value of property objectID.
     */
    public void setObjectID(long objectID) {
        this.objectID = objectID;
    }
    
    /** Getter for property typeID.
     * @return Value of property typeID.
     */
    public long getTypeID() {
        return this.typeID;
    }
    
    /** Setter for property typeID.
     * @param typeID New value of property typeID.
     */
    public void setTypeID(long typeID) {
        this.typeID = typeID;
    }
    
    /** Getter for property globalChange.
     * @return Value of property globalChange.
     */
    public boolean isGlobalChange() {
        return this.globalChange;
    }
    
    /** Setter for property isGlobalChange.
     * @param isGlobalChange New value of property isGlobalChange.
     */
    public void setGlobalChange(boolean globalChange) {
        this.globalChange = globalChange;
    }
    
    /** Getter for property modelFilename.
     * @return Value of property modelFilename.
     */
    public String getModelFilename() {
        return this.modelFilename;
    }
    
    /** Setter for property modelFilename.
     * @param modelFilename New value of property modelFilename.
     */
    public void setModelFilename(String modelFilename) {
        this.modelFilename = modelFilename;
    }
    
    /** Getter for property modelFilename2D.
     * @return Value of property modelFilename2D.
     */
    public String getModelFilename2D() {
        return this.modelFilename2D;
    }
    
    /** Setter for property modelFilename2D.
     * @param modelFilename2D New value of property modelFilename2D.
     */
    public void setModelFilename2D(String modelFilename2D) {
        this.modelFilename2D = modelFilename2D;
    }
    
    /** Getter for property checksum.
     * @return Value of property checksum.
     */
    public String getChecksum() {
        return this.checksum;
    }
    
    /** Setter for property checksum.
     * @param checksum New value of property checksum.
     */
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
    
    /** Getter for property clientCallbackID.
     * @return Value of property clientCallbackID.
     */
    public String getClientCallbackID() {
        return this.clientCallbackID;
    }
    
    /** Setter for property clientCallbackID.
     * @param clientCallbackID New value of property clientCallbackID.
     */
    public void setClientCallbackID(String clientCallbackID) {
        this.clientCallbackID = clientCallbackID;
    }
    
}
