package com.manas.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manas.Library.entity.BookTransaction;
import com.manas.Library.entity.embedded.BookTransactionId;

public interface BookTransactionRepository extends JpaRepository<BookTransaction,BookTransactionId> {

}
