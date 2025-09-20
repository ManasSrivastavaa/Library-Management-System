package com.manas.Library.service.implementation;

import org.springframework.stereotype.Service;

import com.manas.Library.entity.BookTransaction;
import com.manas.Library.entity.Books;
import com.manas.Library.entity.Members;
import com.manas.Library.repository.BookTransactionRepository;
import com.manas.Library.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
	
	private final BookTransactionRepository bookTransactionService;

	@Override
	public void returnBook(Members member, Books book) {
		var transaction = new BookTransaction(book, member, "return");
		bookTransactionService.save(transaction);
		
	}

	@Override
	public void takeBook(Members member, Books book) {
		var transaction = new BookTransaction(book, member, "take");
		bookTransactionService.save(transaction);
	}

}
