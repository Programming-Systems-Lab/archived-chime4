/*
 * LibrarianResult.java
 *
 * Copyright (c) 2002: The Trustees of Columbia University
 * in the City of New York.  All Rights Reserved.
 */

package psl.chime4.server.librarian;

import psl.chime4.server.data.metadata.ResourceDescriptor;

/**
 * Encapsulates a librarian search result.
 *
 * @author Mark Ayzenshtat 
 */
public class LibrarianResult {
	private LibrarianRequest mSourceRequest;
	private ResourceDescriptor[] mSearchResults;
	
	/**
	 * Retrieves the search request that is the source of this result.
	 *
	 * @return the source search request
	 */
	public LibrarianRequest getSourceRequest() {
		return mSourceRequest;
	}

	/**
	 * Assigns the search request that is the source of this result.
	 *
	 * @param iRequest the source search request
	 */	
	public void setSourceRequest(LibrarianRequest iRequest) {
		mSourceRequest = iRequest;
	}

	/**
	 * Retrieves the search results.
	 *
	 * @return the search results
	 */	
	public ResourceDescriptor[] getSearchResults() {
		return mSearchResults;
	}
	
	/**
	 * Assigns the search results.
	 *
	 * @param iResults the search results
	 */	
	public void setSearchResults(ResourceDescriptor[] iResults) {
		mSearchResults = iResults;
	}
}