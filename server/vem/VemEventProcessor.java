/*
 * VemEventProcessor.java
 *
 * Created on April 4, 2002, 5:44 PM
 */

package psl.chime4.vem;

/**
 *
 * @author  Vladislav Shchogolev
 */
public class VemEventProcessor implements ServerEventProcessor {
	
	VisualDataManager mVDM;
	
	/** Creates a new instance of VemEventProcessor */
	public VemEventProcessor() {
		mVDM = new VisualDataManager();
	}
	
}
