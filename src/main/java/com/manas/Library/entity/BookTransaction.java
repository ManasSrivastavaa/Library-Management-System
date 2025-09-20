package com.manas.Library.entity;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.manas.Library.entity.embedded.BookTransactionId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookTransaction {

	@EmbeddedId
	private BookTransactionId id;

	@ManyToOne
	@MapsId("bookId")  
	@JoinColumn(name = "book_id")
	private Books book;

	@ManyToOne
	@MapsId("memberId") 
	@JoinColumn(name = "member_id")
	private Members member;

	private ZonedDateTime transactionDate;
	
	private String transactionType;
	
	  public BookTransaction(Books book, Members member,String transactionType) {
	        this.book = book;
	        this.member = member;
	        this.id = new BookTransactionId(book.getId(), member.getId());
	        this.transactionDate = ZonedDateTime.now(ZoneId.of("UTC"));
	        this.transactionType = transactionType;
	    }

}
