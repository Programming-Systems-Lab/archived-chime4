package psl.chime4.server.di;

import java.util.LinkedList;

public class DICommand
{
	LinkedList args;

	public DICommand()
	{
		args = new LinkedList();
	}

	public void addArg( String data )
	{
		args.add( data );
	}

	public String getArg()
	{
		String temp;

		temp = (String) args.getFirst();
		args.removeFirst();

		return temp;
	}

	public static DICommand parseCommand( String data )
	{
		return null;
	}

	public String toString()
	{
		return null;
	}
}