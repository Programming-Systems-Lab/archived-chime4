/*
 * ImageFileAnalyzer.java
 *
 * Created on May 26, 2002, 3:41 AM
 */

package psl.chime4.server.vem.util;

import java.awt.Toolkit;
import java.awt.Image;
import java.io.*;
/**
 *
 * @author  Vladislav Shchogolev
 */
public class ImageFileAnalyzer extends FileAnalyzer
{
	// member variables
	private int mWidth, mHeight;
	
	/** Creates a new instance of ImageFileAnalyzer */
	public ImageFileAnalyzer(String iFilename) throws IOException
	{
		super(iFilename);
		
		// FIXME: determine dimensions here
		mWidth = 0;
		mHeight = 0;
	}
	
}
