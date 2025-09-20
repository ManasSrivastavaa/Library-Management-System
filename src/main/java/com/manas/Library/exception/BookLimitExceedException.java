package com.manas.Library.exception;

public class BookLimitExceedException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public BookLimitExceedException(String message) {
		super(message);
	}

}
