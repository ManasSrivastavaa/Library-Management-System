package com.manas.Library.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookDTO(

		@NotBlank(message = "Book name is required")
		String name,

		@NotBlank(message = "Book title is required")
		String title,

		@NotBlank(message = "Genre is required")
		String genre,

		@NotNull(message = "Count is required")
		@Min(value = 1, message = "Count must be at least 1")
		Long count,	

		@NotNull(message = "AuthorId is required")
		Long authorId
		) {	
}
