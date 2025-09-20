package com.manas.Library.service;

import java.util.List;
import java.util.Set;

import com.manas.Library.dto.request.BookDTO;
import com.manas.Library.entity.Books;

public interface BookService {

	List<Books> getAllBooks();
	
	Books getBookById(Long id);
		
	Books saveBook(BookDTO book);
	
	void deleteBook(Long id);

	Books updateBook(Long id, BookDTO updatedBook);

	void borrowBook(Set<Long> bookIds, Long userId);

	void returnBook(Set<Long> bookIds, Long userId);

	List<Books> getBorrowedBooksByUser(Long userId);
	 
    void increaseBookCount(Books book);
    
    void decreaseBookCount(Books book);

}
