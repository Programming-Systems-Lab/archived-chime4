package psl.chime4.server.di;


import psl.chime4.server.cebs.*;

import java.util.*;

public class DirectoryInterface
{
	public static final int STATUS_LEAF = 0;
	public static final int STATUS_SERVER = 1;
	public static final int STATUS_ZONE = 2;

	private static final String DI_CEBS_TOPIC = "DI";
	public static final DIType DI_CONTROL_MESSAGE = new DIType("DI_Control");

	public static final String DI_UPDATE_COMMAND = "UPDATE";
	public static final String DI_REQUEST_ZONES_COMMAND = "REQUEST_ZONES";
	public static final String DI_REQUEST_SUB_COMMAND = "REQUEST_SUB";
	public static final String DI_REQUEST_UNSUB_COMMAND = "REQUEST_UNSUB";
	public static final String DI_SEARCH_COMMAND = "SEARCH";
	public static final String DI_REQUEST_PROMOTE_COMMAND = "REQUEST_PROMOTE";
	public static final String DI_REQUEST_DEMOTE_COMMAND = "REQUEST_DEMOTE";

	private static DIZone primaryZone;
	private static LinkedList zones;
	private static Hashtable nodeTypeHash;
	private static Hashtable nodeIDHash;
	private static Hashtable objectSubHash;
	private static Hashtable hostSubHash;
	private static Hashtable objectSearchHash;

	private static DIEventReceiver master;
	private static DIType type;
	private static String info;
	private static DIType ident;
	private static int status;

	public static CebsEventSystem eventSystem;

	/**
	 * Initializes the Directory Interface as a leaf node, a node that will just
	 * receive messages and search results from an infrastructure node (zone server)
	 * and will not actually be an entry within the system itself.
	 *
	 * @param system the currently running CEBS event system to use for communications
	 * @param setMaster the main event receiver for the directory interface, for error conditions, etc
	 * @param setIdent the identification information for this host, used to identify it amongst all systems
	 */
    public static void initialize( CebsEventSystem system, DIEventReceiver setMaster, DIType setIdent )
    {
		status = STATUS_LEAF;
		master = setMaster;
		primaryZone = null;
		ident = setIdent;
		eventSystem = system;

		zones = new LinkedList();
		nodeTypeHash = new Hashtable();
		nodeIDHash = new Hashtable();
		objectSubHash = new Hashtable();
		objectSearchHash = new Hashtable();
    }

	/**
	 * This will initialize the Directory Interface as a server node, which will make
	 * it have a listing in the directory, as well as allow it to subscribe to events
	 * and messages, along with being able to send them (like a leaf node). When the
	 * Directory Interface is initialized this way, it then can become a Zone server.
	 * Otherwise, it will just be a leaf node (although leaf nodes can become servers by
	 * calling the update() method.
	 *
	 * @param system the currently running CEBS event system to use for communications
	 * @param setMaster the main event receiver for the directory interface, for error conditions, etc
	 * @param setIdent the identification information for this host, used to identify it amongst all systems
	 * @param setType the directory type for this host, which can then be used for searching
	 * @param setInfo the information to be listed for this server in the directory
	 */
	public static void initialize( CebsEventSystem system, DIEventReceiver setMaster, DIType setIdent, DIType setType, String setInfo )
	{
		initialize( system, setMaster, setIdent );

		status = STATUS_SERVER;
		type = setType;
		info = setInfo;
	}

	/**
	 * This will shutdown the Directory Interface, cancel all subscriptions, disconnect
	 * from remote hosts, and delete all internal structures containing network data.
	 */
    public static void shutdown()
    {
		master = null;
		eventSystem = null;

		zones = null;
		nodeTypeHash = null;
		nodeIDHash = null;
		objectSubHash = null;
		objectSearchHash = null;
    }

	/**
	 * This will connect the system to a new zone server. If the Directory Interface interface
	 * is a server or zone server, this will just add the zone server to the current list of
	 * zone servers, and then inform the other zone servers on the network that this one
	 * does exist. If the Directory Interface is a server/leaf node, and does not yet have
	 * a primary zone server, this will set the primary zone. If the primary zone is already
	 * set, this method will disconnect from the previous primary zone, and reconnect to the
	 * new one.
	 *
	 * @param address address of the server to connect to
	 * @param port port of the server to connect to
	 */
    public static void connect( String address, int port )
    {
		ListIterator list;
		DIZone zone;
		int mode;

		//if we already have a primaryZone, replace the old one with this one
		if( primaryZone != null )
		{
			zones.remove( primaryZone );

			try
			{
				eventSystem.unregisterEventHandler( primaryZone.getAddress(),
												primaryZone.getPort(), DI_CEBS_TOPIC );
			}

			catch( Exception e )
			{

			}

			primaryZone = null;
		}

		//see if we already have this zone
		list = zones.listIterator();

		while( list.hasNext() )
		{
			zone = (DIZone) list.next();

			if( zone.getAddress() == address && zone.getPort() == port )
			{
				//drop out here, we've already got this zone
				return;
			}
		}

		//set our zone mode
		if( status == STATUS_LEAF )
			mode = DIZone.MODE_LEAF;
		else
			mode = DIZone.MODE_INFRASTRUCTURE;

		//make a new zone
		zone = new DIZone( address, port, mode );
		zone.setHandler( new DICebsHandler( zone ) );

		//connect to it
		try
		{
			eventSystem.openConnection( address, port );
			eventSystem.registerEventHandler( address, port, DI_CEBS_TOPIC, zone.getHandler() );
		}


		catch( Exception e )
		{
			return;
		}

		//do our actual list add here, if the server could not be
		//connected to, it will return out of this in the catch block
		zones.add( zone );

		//set our primary zone, we consider ourselves not-promoted right now,
		//so we need a primary zone to send messages out over later on
		if( primaryZone == null )
			primaryZone = zone;

		//if it's a server, update us
		if( status == STATUS_SERVER )
			sendUpdate( zone );

		//request a list of all zones
		requestZones( zone );

    }

	/**
	 * This will change the directory information for this host in the network directory.
	 * If this host is currently in leaf mode, this will turn it into a server, and make it
	 * so it can possibly be upgraded to an infrastructure node (something that we don't
	 * want clients to do, only servers).
	 *
	 * @param setType the type of this host to store in the network directory
	 * @param setInfo the information about this host to store in the network directory
	 */
    public static void update( DIType setType, String setInfo )
    {
		type = setType;
		info = setInfo;

		//change our status if we were a leaf before
		if( status == STATUS_LEAF )
			status = STATUS_SERVER;

		//broadcast it to all zones
		broadcastUpdate();
    }

	/**
	 * This method will start a new search for hosts of a certain type. After a few minutes the
	 * host will automatically be cleaned up by the cleanup thread and thrown out. Results
	 * will stream in at a variable rate to the receiver's receiveResult method.
	 *
	 * @param receiver the object that is to receive the result event messages
	 * @param searchType the type of host to search for
	 */
    public static void search( DIEventReceiver receiver, DIType searchType )
    {
		DISubscriber subscriber;
		DISubscription sub;

		sub = getSearch( searchType );

		subscriber = new DISubscriber( receiver );

		sub.addSubscriber( subscriber );

		//send out the search request again, if we do have overlapping
		//requests for the same types of searches, we might get some
		//overlapping entries, however, this is expected, there's no
		//real way to know what the state of the returned list will be
		//so we can just expect the receiver to sort out any duplicates
		broadcastSearch( searchType );
    }

    private static DISubscription getSearch( DIType searchType )
    {
		DISubscription sub;

		sub = (DISubscription) objectSearchHash.get( searchType.toString() );

		//if we get null, we need to make a new one
		if( sub == null )
		{
			sub = new DISubscription( searchType );

			//put the new subscription into the hash
			objectSubHash.put( searchType.toString(), sub );
		}

		return sub;
	}

	/**
	 * This will subscribe the given receiver object to incoming messages of the
	 * given type. If this host is a zone, then no actual network messages will be
	 * sent, the object will just be added to the list of recipients. However, if it
	 * is a normal server or a leaf node, the Directory Interface will request the
	 * subscription from the primaryZone server.
	 *
	 * @param receiver the object to receive the incoming messages
	 * @param subType the type of the messages to subscribe to
	 */
    public static void subscribe( DIEventReceiver receiver, DIType subType )
    {
		DISubscriber subscriber;
		DISubscription sub;

		sub = getSubscription( subType );

		subscriber = new DISubscriber( receiver );

		sub.addSubscriber( subscriber );
    }

    private static DISubscription getSubscription( DIType subType )
    {
		DISubscription sub;

		sub = (DISubscription) objectSubHash.get( subType.toString() );

		//if we get null, we need to make a new one
		if( sub == null )
		{
			sub = new DISubscription( subType );

			//if we're a leaf, inform our parent to send us these types of messages
			if( status != STATUS_ZONE )
				requestSubscribe( primaryZone, subType );

			//put the new subscription into the hash
			objectSubHash.put( subType.toString(), sub );
		}

		return sub;
	}

	/**
	 * This method will unsubscribe the given receiver from the given subscription type.
	 * There is no guarantee that this effect is immediate, so messages of the given
	 * subType might still occur, but eventually once the system has acknowledged the
	 * change, all of those messages will stop. If this host is a zone, this will not
	 * actually send out any network messages. Servers and leaf nodes will send out
	 * unsubscription requests to their primaryZones.
	 *
	 * @param receiver the receiver to unsubscribe from the given subscription type
	 * @param subType the subscription type to unsubscribe from
	 */
    public static void unsubscribe( DIEventReceiver receiver, DIType subType )
    {
		DISubscription sub;

		sub = (DISubscription) objectSubHash.get( subType.toString() );

		//if we have no subscription, ignore it
		if( sub == null )
			return;

		//remove the subscriber
		sub.removeSubscriber( receiver );

		//if we have no more subscribers, kill this subscription
		if( sub.getNumSubscribers() == 0 )
		{
			objectSubHash.remove( subType.toString() );

			//inform the server if we're a leaf
			if( status != STATUS_ZONE )
				requestUnsubscribe( primaryZone, subType );
		}
    }

	/**
	 * This will send out a message of a given type with the given body to the entire network.
	 * Only objects that are specifically listening for this type of event will get it. For zones
	 * this means that the message will just be sent out to the local server and all remote zones.
	 * For leaf/server nodes, this message will be sent to the primaryZone.
	 *
	 * @param msgType the type of the message to send
	 * @param msgBody the body of the message to send out
	 */
    public static void publish( DIType msgType, DIMessageBody msgBody )
    {
		DIMessage publishMsg;

		publishMsg = new DIMessage( msgType, msgBody, ident, new DIType("") );

		broadcast( publishMsg );
    }

	/**
	 * This method will directly communicate a message to one host on the network. In reality, this
	 * is not done via direct communications, however, the message routing is as direct as possible.
	 * The only real difference is that when the event receiver object looks at the message receiver,
	 * they will see that they are infact the target of the message, so they can know that it was
	 * specifically for them.
	 *
	 * @param comHost the hostID of the host to send the message to
	 * @param msgType the type of message to send
	 * @param msgBody the body of the message to send to the comHost
	 */
    public static void communicate( DIType comHost, DIType msgType, DIMessageBody msgBody )
    {
		//if we're a zone, check hosts
		if( status == STATUS_ZONE )
		{

		}

		//if we have no host or we're not a zone,
		//just send it to the primary

    }

	/**
	 * This method will send out a promotion request method. If the primary zone server authorizes
	 * this promotion, then that primary zone server will take up the responsibility to inform all other
	 * zone servers of this change. Once they have been informed, all of those other zone servers will
	 * subscribe to this host's event distributing server, so that messages can be distributed. Similarly,
	 * this host will subscribe to every zone server's event distributing server. The zone list will then
	 * be updated to reflect the changes to the network's infrastructure.
	 */
    public static void promote()
    {
		//check our status
		if( status != STATUS_SERVER )
			return;

		//now send the message up to our primary
		requestPromote();
    }

	/**
	 * If the current host is in fact a zone server, this will cause the zone server to send out the
	 * demote message to all other servers on the network. This will cause all hosts to take this server
	 * out of their zones list, and will cause all zone servers to unsubscribe from this server's event
	 * distributing server. Once all of that has happend, this server will reconnect to a random zone server
	 * and start the connection process again, now as a normal server instead of as a zone server.
	 */
    public static void demote()
    {
		//check our status
		if( status != STATUS_ZONE )
			return;

		requestDemote();
    }

	/**
	 * This returns the identification number that was given to the interface when it was initialized.
	 *
	 * @return the identification information for this Directory Interface host
	 */
    public static DIType getIdent()
    {
		return ident;
	}

	private static void requestPromote()
	{
		DIMessageBody msgBody;
		DIMessage requestMsg;
		DICommand command;

		command = new DICommand();
		command.addArg( DI_REQUEST_PROMOTE_COMMAND );

		msgBody = new DIMessageBody( command.toString() );
		requestMsg = new DIMessage( DI_CONTROL_MESSAGE, msgBody, ident, primaryZone.getIdent() );

		sendMessage( primaryZone, requestMsg );
	}

	private static void requestDemote()
	{
		DIMessageBody msgBody;
		DIMessage requestMsg;
		DICommand command;

		command = new DICommand();
		command.addArg( DI_REQUEST_DEMOTE_COMMAND );

		msgBody = new DIMessageBody( command.toString() );
		requestMsg = new DIMessage( DI_CONTROL_MESSAGE, msgBody, ident, primaryZone.getIdent() );

		sendMessage( primaryZone, requestMsg );
	}

	private static void broadcastSearch( DIType searchType )
	{
		DIMessageBody msgBody;
		DIMessage searchMsg;
		DICommand command;

		command = new DICommand();
		command.addArg( DI_SEARCH_COMMAND );
		command.addArg( searchType.toString() );

		msgBody = new DIMessageBody( command.toString() );
		searchMsg = new DIMessage( DI_CONTROL_MESSAGE, msgBody, ident, new DIType("") );

		broadcast( searchMsg );
	}

	private static void requestSubscribe( DIZone theZone, DIType subType )
	{
		DIMessageBody msgBody;
		DIMessage requestMsg;
		DICommand command;

		command = new DICommand();
		command.addArg( DI_REQUEST_SUB_COMMAND );
		command.addArg( subType.toString() );

		msgBody = new DIMessageBody( command.toString() );
		requestMsg = new DIMessage( DI_CONTROL_MESSAGE, msgBody, ident, theZone.getIdent() );

		sendMessage( theZone, requestMsg );
	}

	private static void requestUnsubscribe( DIZone theZone, DIType subType )
	{
		DIMessageBody msgBody;
		DIMessage requestMsg;
		DICommand command;

		command = new DICommand();
		command.addArg( DI_REQUEST_UNSUB_COMMAND );
		command.addArg( subType.toString() );

		msgBody = new DIMessageBody( command.toString() );
		requestMsg = new DIMessage( DI_CONTROL_MESSAGE, msgBody, ident, theZone.getIdent() );

		sendMessage( theZone, requestMsg );
	}

	private static void requestZones( DIZone theZone )
    {
		DIMessageBody msgBody;
		DIMessage requestMsg;
		DICommand command;

		command = new DICommand();
		command.addArg( DI_REQUEST_ZONES_COMMAND );

		msgBody = new DIMessageBody( command.toString() );
		requestMsg = new DIMessage( DI_CONTROL_MESSAGE, msgBody, ident, theZone.getIdent() );

		sendMessage( theZone, requestMsg );
	}

	private static DIMessage makeUpdate()
	{
		DIMessageBody msgBody;
		DIMessage updateMsg;
		DICommand command;

		command = new DICommand();
		command.addArg( DI_UPDATE_COMMAND );
		command.addArg( type.toString() );
		command.addArg( info );

		msgBody = new DIMessageBody( command.toString() );
		updateMsg = new DIMessage( DI_CONTROL_MESSAGE, msgBody, ident, new DIType("") );

		return updateMsg;
	}

    private static void sendUpdate( DIZone theZone )
    {
		DIMessage updateMsg = makeUpdate();

		updateMsg.setReceiver( theZone.getIdent() );

		sendMessage( theZone, updateMsg );
	}

	private static void broadcastUpdate()
	{
		broadcast( makeUpdate() );
	}

	private static void broadcast( DIMessage msg )
	{
		ListIterator list;
		DIZone cur;
		list = zones.listIterator();

		//go through all zones
		while( list.hasNext() )
		{
			cur = (DIZone) list.next();

			//if it's actually connected, send it an update
			if( cur.getMode() == DIZone.MODE_INFRASTRUCTURE ||
				cur.getMode() == DIZone.MODE_LEAF )
			{
				msg.setReceiver( cur.getIdent() );

				sendMessage( cur, msg );
			}
		}
	}

	private static void sendMessage( DIZone receiver, DIMessage msg )
	{
		try
		{
			eventSystem.publish( receiver.getAddress(), receiver.getPort(), DI_CEBS_TOPIC, msg.getCebsEvent() );
		}

		catch( Exception e )
		{

		}
	}
}