/*
 * LibrarianRequest.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.librarian;

import java.io.Serializable;
import java.net.URI;

/**
 * Encapsulates a librarian search request, which currently consists of a
 * query string and a list of URIs to probe but may eventually include other
 * fields that constrain the search domain.
 *
 * @author Mark Ayzenshtat 
 */
public class LibrarianRequest implements Serializable {
	private String mQuery;
	private URI[] mURIs;
	
	/**
	 * Retrieves the search query.
	 *
	 * @return the search query
	 */
	public String getQuery() {
		return mQuery;
	}
	
	/**
	 * Assigns the search query.
	 *
	 * @param iQuery the search query
	 */
	public void setQuery(String iQuery) {
		mQuery = iQuery;
	}

	/**
	 * Retrieves the URIs to pass to FRAX.
	 *
	 * @return the URIs
	 */
	public URI[] getURIs() {
		return mURIs;
	}
	
	/**
	 * Assigns the URIs to pass to FRAX.
	 *
	 * @param iURIs the URIs
	 */
	public void setURIs(URI[] iURIs) {
		mURIs = iURIs;
	}
}
