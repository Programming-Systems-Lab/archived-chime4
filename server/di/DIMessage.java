package psl.chime4.server.di;

import psl.chime4.server.ces.*;

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

	public Event getCesEvent()
	{
		Event cesEvent;

		cesEvent = DirectoryInterface.eventService.createEmptyEvent();

		cesEvent.put("sender", msgSender.toString() );
		cesEvent.put("receiver", msgReceiver.toString() );
		cesEvent.put("type", msgType.toString() );
		cesEvent.put("body", msgBody.toBytes() );

		return cesEvent;
	}
}
