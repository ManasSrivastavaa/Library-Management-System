package com.manas.Library.exception;

public class BookNotIssuedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BookNotIssuedException(String message){
		super(message);
	}

}
