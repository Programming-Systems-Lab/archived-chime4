package psl.chime4.server.di;

import java.util.LinkedList;
import java.util.ListIterator;

public class DICommand
{
	LinkedList args;

	public DICommand()
	{
		args = new LinkedList();
	}

	public void addArg( byte data[] )
	{
		if( data == null )
			return;

		args.add( data );
	}

	public void addArg( String data )
	{
		if( data == null )
			return;

		args.add( data.getBytes() );
	}

	public byte[] getArg()
	{
		byte[] temp;

		temp = (byte[]) args.getFirst();
		args.removeFirst();

		return temp;
	}

	public String getArgString()
	{
		return new String( getArg() );
	}

	public static DICommand parseCommand( byte data[] )
	{
		DICommand cmd = new DICommand();
		int processed = 0;
		int totalLength;
		int curLength;
		byte argBytes[];
		int numArgs;
		int i = 0;

		if( data.length < 5 )
			return null;

		//decode the length
		totalLength = ( (new Byte(data[0])).intValue() << 24)
					& ( (new Byte(data[1])).intValue() << 16)
					& ( (new Byte(data[2])).intValue() << 8)
					& ( (new Byte(data[3])).intValue() );

		//get the num of args
		numArgs = (new Byte(data[4])).intValue();

		//we've done 5 so far
		processed = 5;

		//go until all bytes
		while( processed < totalLength )
		{
			//get the next arg's length
			curLength = ( (new Byte(data[processed])).intValue() << 24)
						& ( (new Byte(data[processed+1])).intValue() << 16)
						& ( (new Byte(data[processed+2])).intValue() << 8)
						& ( (new Byte(data[processed+3])).intValue() );

			//add 4 to processed, which is the length header
			processed += 4;

			//check for partial validity
			if( curLength < 0 || curLength > (totalLength-processed) )
				return null;

			//create the array
			argBytes = new byte[curLength];

			//copy
			for( i = 0; i < curLength; i++ )
				argBytes[i] = data[processed+i];

			//add it in
			cmd.addArg(argBytes);

			//update processed
			processed += curLength;
		}

		return cmd;
	}

	public byte[] toBytes()
	{
		ListIterator list;
		byte output[];
		byte curArg[];
		int totalLength = 0;
		int numArgs;
		int curLength;
		int processed = 0;
		int i;

		//get the list
		list = args.listIterator();

		numArgs = args.size();

		//add 5 for command length plus num args
		totalLength += 5;

		//get sizes
		while( list.hasNext() )
		{
			//add 4 for the length
			totalLength += 4;

			//and then more for the bytes
			curArg = (byte[]) list.next();

			totalLength += curArg.length;
		}

		//now fill it in
		output = new byte[totalLength];

		//set size
		output[0] = (new Integer((totalLength >> 24) & 0xFF)).byteValue();
		output[1] = (new Integer((totalLength >> 16) & 0xFF)).byteValue();
		output[2] = (new Integer((totalLength >> 8) & 0xFF)).byteValue();
		output[3] = (new Integer((totalLength) & 0xFF)).byteValue();

		//set num args
		output[4] = (new Integer(numArgs)).byteValue();

		//set our position
		processed = 5;

		//now copy the args over one by one
		list = args.listIterator();

		while( list.hasNext() )
		{
			//get the arg
			curArg = (byte[]) list.next();

			//set size
			output[processed] = (new Integer((curArg.length >> 24) & 0xFF)).byteValue();
			output[processed+1] = (new Integer((curArg.length >> 16) & 0xFF)).byteValue();
			output[processed+2] = (new Integer((curArg.length >> 8) & 0xFF)).byteValue();
			output[processed+3] = (new Integer((curArg.length) & 0xFF)).byteValue();

			//add to processed
			processed += 4;

			//copy the data
			for( i = 0; i < curArg.length; i++ )
				output[processed+i] = curArg[i];

			//update processed
			processed += curArg.length;
		}

		return output;
	}
}