package psl.chime4.server.di;

import java.util.ListIterator;
import java.util.LinkedList;

public class DISubscription
{
	private LinkedList subscribers;
	private DIType type;

	public DISubscription( DIType subType )
	{
		subscribers = new LinkedList();
		type = subType;
	}

	public void addSubscriber( DISubscriber addSub )
	{
		subscribers.add( addSub );
	}

	public void removeSubscriber( Object sub )
	{
		ListIterator list;
		DISubscriber cur;

		list = subscribers.listIterator(0);

		while( list.hasNext() )
		{
			cur = (DISubscriber) list.next();

			if( cur.getIdentValue() == sub )
			{
				subscribers.remove( cur );
				break;
			}
		}
	}

	public LinkedList getSubscribers()
	{
		return subscribers;
	}

	public int getNumSubscribers()
	{
		return subscribers.size();
	}
}