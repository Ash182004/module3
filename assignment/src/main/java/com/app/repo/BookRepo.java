package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.BookEntity;

public interface BookRepo extends JpaRepository<BookEntity, Integer> {

	List<BookEntity> findByAuthor(String author);
	List<BookEntity> findByPriceLessThan(Double price);
	List<BookEntity> findByCategory(String category);
	boolean existsByIsbn(String isbn);
	void deleteByTitle(String title);
	
	@Query("SELECT b FROM BookEntity b WHERE b.title LIKE %:keyword%")
	List<BookEntity> searchByTitle(@Param("keyword") String keyword);

	@Query("SELECT b FROM BookEntity b ORDER BY b.price DESC")
	List<BookEntity> sortByPriceDesc();
	
	@Query(	value = "SELECT * FROM books WHERE price BETWEEN ?1 AND ?2",
			nativeQuery = true )
	List<BookEntity> findByPriceRange(double min, double max);
}
