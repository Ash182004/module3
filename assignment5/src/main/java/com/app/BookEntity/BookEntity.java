package com.app.BookEntity;

import com.app.dto.BookDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books_jdbc")
public class BookEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
    private String author;
    private Double price;
    private String isbn;
    private String category;
	
	public BookDto toBookDto() {
		return new BookDto(title, author, price, isbn, category);
	}

	public BookEntity(String title, String author, double price, String isbn, String category) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.category = category;
	}

}
