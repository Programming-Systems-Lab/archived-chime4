/*
 * ResourceFileManager.java
 *
 * Created on May 26, 2002, 3:39 PM
 */

package psl.chime4.server.vem.util;

import java.util.Hashtable;
import java.util.prefs.Preferences;

/**
 *	Class for managing resource files such as 3DS files and images.
 *
 *	@author  Vladislav Shchogolev
 *	@version $Revision$
 */
public class ResourceFileManager
{
	// singleton object
	private static final ResourceFileManager kSingleton = 
		new ResourceFileManager();
	
	// for determining base directory
	private static final Preferences kPrefs =
		Preferences.userNodeForPackage(ResourceFileManager.class);	
	private static final String kKeyBaseDir = "ResourceBaseDir";
	private static final String kBaseDirSystemProp = "user.dir";

	// subfolders of base
	private static final String kResourceDir = "resources";
	private static final String kThemeDir = "themes";

	// instance variables
	private String mBaseDir;
	private Hashtable mResourceFiles;
	
	/** 
	 *	Creates a new instance of ResourceFileManager.
	 *	The base directory for file storage is assumed to be
	 *	<code>System.getProperty(kBaseDirSystemProp)</code>.
	 *	The value is stored as a <code>Preference</code> and can be modified
	 *	in the backing store after the initial run.
	 *
	 *	Access modifier is package to prevent external instantiation.
	 */
	ResourceFileManager()
	{
		String defaultBaseDir = System.getProperty(kBaseDirSystemProp);
		mBaseDir = kPrefs.get(kKeyBaseDir, defaultBaseDir);
	}
	
	public String getBaseDir()
	{
		return mBaseDir;
	}
	
	/**
	 *	Returns the singleton instance of this class.
	 *
	 *	@param	iBaseDir	sets the base directory to the given string
	 *	@param	iUpdatePrefs	if true, updates the preferences backing
	 *					store to hold the new base directory value.
	 */
	public static ResourceFileManager getInstance(
		String iBaseDir, 
		boolean iUpdatePrefs)
	{
		if (iBaseDir == null) {
			return kSingleton;
		} else {
			kSingleton.mBaseDir = iBaseDir;
			if (iUpdatePrefs) kPrefs.put(kKeyBaseDir, iBaseDir);
			return kSingleton;
		}
	}
	
	/**
	 *	Returns the singleton instance of this class.
	 */
	public static ResourceFileManager getInstance()
	{
		return kSingleton;
	}
}
