package psl.chime4.server.vem.util;

import java.util.Vector;
import java.io.*;


public class FileAnalyzer3DS extends FileAnalyzer
{
	// global flags
	public final static boolean DEBUG = true;

	// 3DS Chunk Flags
	public final static int CHUNK_MAIN		= 0x4d4d; 
	public final static int CHUNK_OBJMESH	= 0x3d3d;
	public final static int CHUNK_OBJBLOCK	= 0x4000;
	public final static int CHUNK_TRIMESH	= 0x4100;
	public final static int CHUNK_VERTLIST	= 0x4110;

	// member variables
	private Vector mVertexList;
	private Box3D mBoundingBox;

	public FileAnalyzer3DS(String iFilename) throws IOException
	{
		super(iFilename);
		processVertexPoints();
	}

	public Vector getVertexList() {
		return mVertexList;
	}

	public Box3D getBoundingBox() {
		return mBoundingBox;
	}

	private void processVertexPoints() throws IOException
	{
		// little endian version of DataInputStream
		LEDataInputStream in = new LEDataInputStream(new FileInputStream(mFilename));

		if (DEBUG) System.out.println("Flag\tLength\tComment");

		Vector vertexList = new Vector();
		String name;
		int flag, len, count;
		Vertex v, max, min;
		char c;

		max = new Vertex();
		min = new Vertex();

		try {
			
			while(true)
			{
				flag = in.readUnsignedShort();
				len = in.readInt();
				if (DEBUG) System.out.print(toHex(flag) + "\t" + len);
				
				if (flag == CHUNK_MAIN || flag == CHUNK_OBJMESH || flag == CHUNK_OBJBLOCK || 
					flag == CHUNK_TRIMESH) 
				{
					if (flag == CHUNK_OBJBLOCK) { // read ASCIIZ object name
						name = "";
						while ((c = (char)in.readByte()) != 0) {
							name += c;
						}
						if (DEBUG) System.out.print("\t[name = " + name + "] ");
					}
				}
				else {
					if (flag == CHUNK_VERTLIST) {
						if (DEBUG) System.out.print(" \t[found vertex list]");

						// extract points
						count = in.readUnsignedShort();
						
						for (int i = 0; i < count; i++)
						{
							v = new Vertex();
							v.x = in.readFloat();
							v.y = in.readFloat();
							v.z = in.readFloat();
							vertexList.addElement(v);

							if (vertexList.size() == 1) {
								max.setTo(v);
								min.setTo(v);
							} else {
								if (v.x > max.x) max.x = v.x;
								if (v.y > max.y) max.y = v.y;
								if (v.z > max.z) max.z = v.z;
								if (v.x < min.x) min.x = v.x;
								if (v.y < min.y) min.y = v.y;
								if (v.z < min.z) min.z = v.z;
							}
						}
					} else {
						if (DEBUG) System.out.print("\tskipping this chunk, " + (len-6) + " bytes");
						in.skipBytes(len-6);
					}
				}
				if (DEBUG) System.out.println();
			}

		} catch (EOFException eof) {}
		
		in.close();

		mVertexList = vertexList;
		mBoundingBox = new Box3D(min, max);
	}

	public static void main(String[] args) throws Exception
	{
		FileAnalyzer3DS fa = new FileAnalyzer3DS(args[0]);
		Vertex v;

		System.out.println("\n\nSize of list is " + fa.getVertexList().size());
		
		/*
		for (int j=0; j<vertexList.size(); j++)
		{
			v = (Vertex) vertexList.elementAt(j);
			System.out.println("(" + v.x + ",\t" + v.y + ",\t" + v.z + ")");
		}
		*/

		v = fa.getBoundingBox().mPointA;
		System.out.println("A(" + v.x + ",\t" + v.y + ",\t" + v.z + ")");
		v = fa.getBoundingBox().mPointB;
		System.out.println("B(" + v.x + ",\t" + v.y + ",\t" + v.z + ")");
	}
	
	public static String toHex(int value)
	{
		char hexChar[] = {'0','1','2','3','4','5','6','7','8','9','a','b',
						  'c','d','e','f'};
		int remainder;
		String out = "";
		
		if (value == 0) {
			return "0000";
		}
		
		while (value > 0) {
			out = hexChar[value % 16] + out;
			value /= 16;
		}
		
		while (out.length() % 4 != 0)
		{
			out = "0" + out;
		}

		return out;
	}
	
	class Vertex {
		public float x,y,z;

		public void setTo(Vertex a) {
			x = a.x;
			y = a.y;
			z = a.z;
		}
	}

	class Box3D {
		public Vertex mPointA, mPointB;

		public Box3D(Vertex a, Vertex b)
		{
			mPointA = a;
			mPointB = b;
		}
	}	
}


