/*
 * ResourceFile.java
 *
 * Created on May 26, 2002, 4:37 PM
 */

package psl.chime4.server.vem.util;

import java.util.zip.Adler32;
import java.util.zip.Checksum;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
/**
 *	Stores meta data about a resource file (either a model or image).
 *
 *	@author  Vladislav Shchogolev
 *	@version $Revision$
 */
public class ResourceFile
{

	/** Holds value of property relativePath. */
	private String relativePath;	
	
	/** Holds value of property checksum. */
	private long checksum;
	
	/** Creates a new instance of ResourceFile */
	public ResourceFile(String iRelativePath) throws IOException
	{
		setRelativePath(iRelativePath);
	}
	
	/** Getter for property relativePath.
	 * @return Value of property relativePath.
	 */
	public String getRelativePath()
	{
		return this.relativePath;
	}
	
	/** Setter for property relativePath.
	 * @param relativePath New value of property relativePath.
	 */
	public void setRelativePath(String relativePath) throws IOException
	{
		this.relativePath = relativePath;
		this.checksum = computeChecksum();		
	}
	
	/** Getter for property checksum.
	 * @return Value of property checksum.
	 */
	public long getChecksum()
	{
		return this.checksum;
	}
	
	private long computeChecksum() throws IOException
	{
		Checksum checksum = new Adler32();		
		FileInputStream fis = new FileInputStream(getFullPath());
		byte[] array = new byte[1024];
		int count = array.length;

		while (count == array.length)
		{
			count = fis.read(array);
			checksum.update(array, 0, count);
		}

		if (count != -1)
			checksum.update(array, 0, count);

		fis.close();
		
		return checksum.getValue();	
	}		
	
	/** Getter for property fullPath.
	 * @return Value of property fullPath.
	 */
	public String getFullPath()
	{
		String base = ResourceFileManager.getInstance().getBaseDir();
		return base + File.pathSeparator + getRelativePath();
	}
}
