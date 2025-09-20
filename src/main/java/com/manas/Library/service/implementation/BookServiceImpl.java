package com.manas.Library.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.manas.Library.dto.request.BookDTO;
import com.manas.Library.entity.Books;
import com.manas.Library.exception.BookNotAvailableException;
import com.manas.Library.exception.BookNotFoundException;
import com.manas.Library.exception.BookNotIssuedException;
import com.manas.Library.repository.BookRepository;
import com.manas.Library.service.AuthorService;
import com.manas.Library.service.BookService;
import com.manas.Library.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
	
	private final BookRepository bookRepository;
	
	private final MemberService memberService;
	
	private final AuthorService authorService;
	
	@Override
	public List<Books> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Books getBookById(Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id : " + id + " not found"));
	}

	@Override
	public Books saveBook(BookDTO bookDTO) {
		var author = authorService.getAuthorById(bookDTO.authorId());
		var book = new Books(bookDTO);
		book.setAuthor(author);
		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long id) {
		this.getBookById(id);
		bookRepository.deleteById(id);
	}

	@Override
	public Books updateBook(Long id, BookDTO updatedBook) {
		var fetchedBook = this.getBookById(id);
		var author = authorService.getAuthorById(updatedBook.authorId());
		fetchedBook.setAuthor(author);
		fetchedBook.setGenre(updatedBook.genre());
		fetchedBook.setTitle(updatedBook.title());
		fetchedBook.setCount(updatedBook.count());
		return bookRepository.save(fetchedBook);
	}

	@Override
	@Transactional
	public void borrowBook(Set<Long> bookIds, Long userId) {
		var books = new ArrayList<Books>();
		for(Long bookId : bookIds) {
			var book = getBookById(bookId);
			if(book.getCount() > 0) {
				books.add(book);
				decreaseBookCount(book);
			}else {
				throw new BookNotAvailableException("Book not available with id :- " + bookId);
			}
		}
		memberService.assignBook(userId, books);
	}

	@Override
	@Transactional
	public void returnBook(Set<Long> bookIds, Long userId) {
		var fetchedMember = memberService.getMemberById(userId);
		var books = new ArrayList<Books>();
		for(Long bookId : bookIds) {
			var fetchedBook = getBookById(bookId);
			var count = fetchedMember.getRentedBooks().stream().filter(book -> book.getId() == bookId).count();
			if(count == 0) {
				throw new BookNotIssuedException("Book with id "+ bookId +" not issued by user");
			}
			books.add(fetchedBook);
			increaseBookCount(fetchedBook);
		}
		memberService.returnBooks(userId, books);
	}

	@Override
	public List<Books> getBorrowedBooksByUser(Long userId) {
		return memberService.getMemberBooks(userId);
	}

	@Override
	public void increaseBookCount(Books book) {
		book.setCount(book.getCount() + 1);
		bookRepository.save(book);
	}

	@Override
	public void decreaseBookCount(Books book) {
		book.setCount(book.getCount() - 1);
		bookRepository.save(book);
	}

}
