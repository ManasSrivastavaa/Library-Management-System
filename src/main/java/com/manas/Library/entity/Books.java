package com.manas.Library.entity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manas.Library.dto.request.BookDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name ="tbl_books")
@Entity
@Data
@NoArgsConstructor
public class Books {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;
    
    @NotEmpty
    private String title;
    
    @PositiveOrZero
    private Long count;
    
    private boolean isAvailable;
    
    @NotEmpty
    private String genre;
    
    @NotNull
    private ZonedDateTime publicationTime;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "rentedBooks")
	private List<Members> rentedBy;
    
    public Books(BookDTO bookDTO) {
    	this.title = bookDTO.title();
    	this.genre = bookDTO.genre();
    	this.isAvailable = true;
    	this.count = bookDTO.count();
    	this.publicationTime = ZonedDateTime.now();
    	this.rentedBy = new ArrayList<Members>();
    }
}
