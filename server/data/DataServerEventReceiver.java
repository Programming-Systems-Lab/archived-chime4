/*
 * DataServerEventReceiver.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

import psl.chime4.server.di.*;

/**
 * An implementation of the directory service <code>DIEventReceiver</code>
 * interface, through which this data server communicates with other data
 * servers across the network.
 *
 * @author Mark Ayzenshtat
 */
class DataServerEventReceiver implements DIEventReceiver {
	private DataServer mDataServer;
	
	DataServerEventReceiver(DataServer iDataServer) {
		mDataServer = iDataServer;
	}
	
	public void receiveMessage(DIMessage iMessage) {		
	}
	
	public void receiveEvent(DIEvent iEvent) {
	}
	
	public void receiveResult(DIHost iResult) {
	}
}