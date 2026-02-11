package com.app.dto;

import com.app.entities.BookEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
	private String title;
    private String author;
    private Double price;
    private String isbn;
    private String category;
	
	public BookEntity toBookEntity() {
		return new BookEntity(title, author, price, isbn, category);
	}
}
