package com.manas.Library.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class BookTransactionId {
	
	private Long bookId;
	private Long memberId;
	
	public BookTransactionId(Long bookId, Long memberId) {
		this.bookId = bookId;
		this.memberId = memberId;
	}

}
