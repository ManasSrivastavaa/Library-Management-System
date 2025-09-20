package com.manas.Library.service;

import com.manas.Library.entity.Books;
import com.manas.Library.entity.Members;

public interface TransactionService {

	public void returnBook(Members member,Books book);
	public void takeBook(Members member,Books book);

}
