/*
 * ResourceDescriptorFactory.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory for creating <code>ResourceDescriptor</code> objects.
 *
 * @author Mark Ayzenshtat
 * @see ResourceDescriptor
 */
public class ResourceDescriptorFactory {
	private static final ResourceDescriptorFactory kSingleton =
		new ResourceDescriptorFactory();
	
	private static final String kDefaultRDClassName = 
		"psl.chime4.server.data.GenericResourceDescriptor";
	
	private Map mProtocolMap;
	
	// prevent external instantiation
	private ResourceDescriptorFactory() {		
		mProtocolMap = new HashMap(10);
		
		// right now, these are hardcoded -- eventually, 
		// they should be loaded as preferences
		mProtocolMap.put("HTTP", "psl.chime4.server.data.HttpResourceDescriptor");
		mProtocolMap.put("LFS", "psl.chime4.server.data.LfsResourceDescriptor");
	}

	/**
	 * Retrieves the singleton instance of this factory.
	 *
	 * @return the singleton instance of this factory
	 */
	public static ResourceDescriptorFactory getInstance() {
		return kSingleton;
	}
	
	/**
	 * Instantiates a <code>ResourceDescriptor</code> object that corresponds
	 * to the supplied protocol.
	 *
	 * @return a <code>ResourceDescriptor</code> object
	 */
	public ResourceDescriptor newRD(String iProtocol) {
		// OldFrax handles protocols in all CAPS -- until NewFrax, we comply
		String protocolName = iProtocol.toUpperCase();
		
		String rdClassName = (String) mProtocolMap.get(protocolName);
		if (rdClassName == null) {
			rdClassName = kDefaultRDClassName;
		}

		try {
			Class rdClass = Class.forName(rdClassName);
			ResourceDescriptor rd = (ResourceDescriptor) rdClass.newInstance();
			rd.setProtocol(protocolName);
			
			return rd;
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(
				"Cannot resolve a ResourceDescriptor subclass.", ex
			);		
		} catch(InstantiationException ex) {
			throw new RuntimeException(
				"Cannot instantiate a ResourceDescriptor subclass.", ex
			);
		} catch(IllegalAccessException ex) {
			throw new RuntimeException(
				"Cannot instantiate a ResourceDescriptor subclass.", ex
			);
		}
	}
}