package psl.chime4.server.di;

import psl.chime4.server.cebs.*;

public class DIMessage
{
	private DIType msgType;
	private DIMessageBody msgBody;
	private DIType msgSender;
	private DIType msgReceiver;

    public DIMessage( DIType setType, DIMessageBody setBody, DIType sender, DIType receiver )
    {
		msgType = setType;
		msgBody = setBody;
		msgSender = sender;
		msgReceiver = receiver;
    }

    public DIType getType()
    {
		return msgType;
    }

    public DIMessageBody getBody()
    {
		return msgBody;
    }

    public DIType getSender()
    {
		return msgSender;
    }

    public DIType getReceiver()
    {
		return msgReceiver;
	}

	public void setType( DIType setType )
	{
		msgType = setType;
	}

	public void setBody( DIMessageBody setBody )
	{
		msgBody = setBody;
	}

	public void setSender( DIType sender )
	{
		msgSender = sender;
	}

	public void setReceiver( DIType receiver )
	{
		msgReceiver = receiver;
	}

	public Event getCebsEvent()
	{
		Event cebsEvent;

		cebsEvent = DirectoryInterface.eventSystem.createEmptyEvent();

		cebsEvent.put("sender", msgSender.toString() );
		cebsEvent.put("receiver", msgReceiver.toString() );
		cebsEvent.put("type", msgType.toString() );
		cebsEvent.put("body", msgBody.getData() );

		return cebsEvent;
	}
}
