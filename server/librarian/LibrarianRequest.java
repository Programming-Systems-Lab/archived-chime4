/*
 * LibrarianRequest.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.librarian;

/**
 * Encapsulates a librarian search request, which currently consists of only a
 * query string but may eventually include other fields that constrain the
 * search domain.
 *
 * @author Mark Ayzenshtat 
 */
public class LibrarianRequest {
	private String mQuery;
	
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
}
