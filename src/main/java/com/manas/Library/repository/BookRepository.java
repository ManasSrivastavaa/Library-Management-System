package com.manas.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manas.Library.entity.Books;

public interface BookRepository extends JpaRepository<Books,Long> {

}
