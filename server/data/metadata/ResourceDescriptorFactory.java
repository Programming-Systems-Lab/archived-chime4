/*
 * ResourceDescriptorFactory.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.data.metadata;

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
		"psl.chime4.server.data.metadata.TypelessResourceDescriptor";
	
	private Map mRDMap;
	
	// prevent external instantiation
	private ResourceDescriptorFactory() {		
		mRDMap = new HashMap(10);
		
		// right now, these are hardcoded -- eventually, 
		// they should be loaded as preferences
    mRDMap.put("text/html", 
      "psl.chime4.server.data.metadata.HtmlResourceDescriptor");
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
	 * to the supplied MIME type.
	 *
	 * @return a <code>ResourceDescriptor</code> object
	 */
	public ResourceDescriptor newRD(String iType) {
		String rdClassName = (String) mRDMap.get(iType);
		if (rdClassName == null) {
			rdClassName = kDefaultRDClassName;
		}

		try {
			Class rdClass = Class.forName(rdClassName);
			return (ResourceDescriptor) rdClass.newInstance();
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