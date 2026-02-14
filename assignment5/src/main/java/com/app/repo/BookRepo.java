package com.app.repo;

import java.util.List;
import java.util.Optional;

import com.app.entities.BookEntity;

public interface BookRepo {
	List<BookEntity> findByAuthor(String author);
	List<BookEntity> findByPriceLessThan(Double price);
	List<BookEntity> findByCategory(String category);
	boolean existsByIsbn(String isbn);
	void deleteByTitle(String title);
	List<BookEntity> searchByTitle(String keyword);
	List<BookEntity> sortByPriceDesc();
	List<BookEntity> findByPriceRange(double min, double max);
	List<BookEntity> findAll();
	BookEntity save(BookEntity book);
	Optional<BookEntity> findById(Integer id);
	void deleteById(Integer id);
}