package psl.chime4.server.vem.util;

import java.util.zip.Adler32;
import java.util.zip.Checksum;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *	Base class for file analyzers. This class determines the checksum of the 
 *	file by default as part of its constructor.
 *
 * @author  Vladislav Shchogolev
 */
public class FileAnalyzer
{
	protected String mFilename;
	protected long mChecksum;	// will use Adler-32 or CRC-32

	public FileAnalyzer(String iFilename) throws IOException 
	{
		mFilename = iFilename;
		computeChecksum();
	}

	public void computeChecksum() throws IOException
	{
		Checksum checksum = new Adler32();		
		FileInputStream fis = new FileInputStream(mFilename);
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
		
		mChecksum = checksum.getValue();	
	}
}
