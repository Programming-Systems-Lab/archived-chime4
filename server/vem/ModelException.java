/*
 * ModelException.java
 *
 * Created on May 28, 2002, 4:03 PM
 */

package psl.chime4.server.vem;

/**
 * Indicates that an exception occured while determining a model for an object
 * or retrieving data about the model.
 *
 * @author  Vladislav Shchogolev
 */
public class ModelException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>ModelException</code> without detail message.
     */
    public ModelException() {
    }
    
    public ModelException(Throwable cause) {
	super(cause);
    }
    
    /**
     * Constructs an instance of <code>ModelException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ModelException(String msg) {
        super(msg);
    }
}
