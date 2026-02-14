package com.app.service;

import java.util.List;

import com.app.entities.BookEntity;

public interface BookService {
	BookEntity addBook(BookEntity book);
	List<BookEntity> getAllBooks();
	BookEntity getBookById(Integer id);
	void deleteBookById(Integer id);
	List<BookEntity> getBooksByAuthor(String author);
	List<BookEntity> getBooksByPriceLessThan(Double price);
	List<BookEntity> searchBooksByTitle(String keyword);
	List<BookEntity> getBooksByPriceRange(double min, double max);
	boolean checkBookExistsByIsbn(String isbn);
	void deleteBookByTitle(String title);
}
