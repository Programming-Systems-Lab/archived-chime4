/*
 * LibrarianRequest.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.librarian;

/**
 * Encapsulates a librarian search request, which currently consists of a
 * query string and a list of URLs to probe but may eventually include other
 * fields that constrain the search domain.
 *
 * @author Mark Ayzenshtat 
 */
public class LibrarianRequest {
	private String mQuery;
	private String[] mURLs;
	
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
	 * Retrieves the URLs to pass to FRAX.
	 *
	 * @return the URLs
	 */
	public String[] getURLs() {
		return mURLs;
	}
	
	/**
	 * Assigns the URLs to pass to FRAX.
	 *
	 * @param iURLs the URLs
	 */
	public void setURLs(String[] iURLs) {
		mURLs = iURLs;
	}
}
