package com.manas.Library.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manas.Library.dto.request.BookDTO;
import com.manas.Library.entity.Books;
import com.manas.Library.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	@GetMapping
	public ResponseEntity<List<Books>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Books> getBookById(@PathVariable Long id) {
		return ResponseEntity.ok(bookService.getBookById(id));
	}

	@PostMapping
	public ResponseEntity<Books> saveBook(@Valid @RequestBody BookDTO book) {
		return ResponseEntity.ok(bookService.saveBook(book));
	}

	@PostMapping("/{id}/borrow")
	public ResponseEntity<String> borrowBook(@PathVariable Long id, @RequestBody Set<Long> bookIds) {
		bookService.borrowBook(bookIds,id);
		return ResponseEntity.created(URI.create("/api/member/" + id + "/books")).build();
	}

	@PostMapping("/{id}/return")
	public ResponseEntity<String> returnBook(@PathVariable Long id, @RequestBody Set<Long> bookIds) {
		bookService.returnBook(bookIds,id);
		return ResponseEntity.created(URI.create("/api/member/" + id + "/books")).build();	
	}
}

