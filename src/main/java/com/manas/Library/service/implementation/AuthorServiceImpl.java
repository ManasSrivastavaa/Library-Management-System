package com.manas.Library.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manas.Library.entity.Author;
import com.manas.Library.exception.AuthorNotFoundException;
import com.manas.Library.repository.AuthorRepository;
import com.manas.Library.service.AuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
	
	private final AuthorRepository authorRepository;

	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public Author getAuthorById(Long id) {
		return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author not found with Id:- " + id));
	}

	@Override
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public Author updateAuthor(Long id, Author updatedAuthor) {
		var author = this.getAuthorById(id);
		author.setName(updatedAuthor.getName());	
		return authorRepository.save(author);
	}

	@Override
	public void deleteAuthor(Long id) {
		var author = this.getAuthorById(id);
		authorRepository.delete(author);
		
	}

}
