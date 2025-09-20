package com.manas.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manas.Library.entity.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {

}
