package com.manas.Library.service;

import java.util.List;

import com.manas.Library.entity.Author;

public interface AuthorService {
	
	List<Author> getAllAuthors();

	Author getAuthorById(Long id);

	Author saveAuthor(Author Author);

	Author updateAuthor(Long id, Author updatedAuthor);

	void deleteAuthor(Long id);

}
