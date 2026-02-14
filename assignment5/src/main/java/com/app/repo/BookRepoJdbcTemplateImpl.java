package com.app.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.entities.BookEntity;
import com.app.repo.mappers.BookMapper;

@Repository
@Primary
public class BookRepoJdbcTemplateImpl implements BookRepo {
	
	private JdbcTemplate jdbcTemplate;
	
	public BookRepoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<BookEntity> findByAuthor(String author) {
		String sql = "SELECT * FROM books_jdbc WHERE author = ?";
		return jdbcTemplate.query(sql, new BookMapper(), author);
	}

	@Override
	public List<BookEntity> findByPriceLessThan(Double price) {
		String sql = "SELECT * FROM books_jdbc WHERE price < ?";
		return jdbcTemplate.query(sql, new BookMapper(), price);
	}

	@Override
	public List<BookEntity> findByCategory(String category) {
		String sql = "SELECT * FROM books_jdbc WHERE category = ?";
		return jdbcTemplate.query(sql, new BookMapper(), category);
	}

	@Override
	public boolean existsByIsbn(String isbn) {
		String sql = "SELECT COUNT(*) FROM books_jdbc WHERE isbn = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
		return count != null && count > 0;
	}

	@Override
	public void deleteByTitle(String title) {
		String sql = "DELETE FROM books_jdbc WHERE title = ?";
		jdbcTemplate.update(sql, title);
	}

	@Override
	public List<BookEntity> searchByTitle(String keyword) {
		String sql = "SELECT * FROM books_jdbc WHERE title LIKE ?";
		return jdbcTemplate.query(sql, new BookMapper(), "%" + keyword + "%");
	}

	@Override
	public List<BookEntity> sortByPriceDesc() {
		String sql = "SELECT * FROM books_jdbc ORDER BY price DESC";
		return jdbcTemplate.query(sql, new BookMapper());
	}

	@Override
	public List<BookEntity> findByPriceRange(double min, double max) {
		String sql = "SELECT * FROM books_jdbc WHERE price BETWEEN ? AND ?";
		return jdbcTemplate.query(sql, new BookMapper(), min, max);
	}

	@Override
	public List<BookEntity> findAll() {
		System.out.println("JDBC");
		String sqlQuery = "SELECT * FROM books_jdbc";
		return jdbcTemplate.query(sqlQuery, new BookMapper());
	}

	@Override
	public BookEntity save(BookEntity book) {
		if (book.getId() == null) {
			String sql = "INSERT INTO books_jdbc (title, author, price, isbn, category) VALUES (?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getIsbn(), book.getCategory());
		} else {
			String sql = "UPDATE books_jdbc SET title = ?, author = ?, price = ?, isbn = ?, category = ? WHERE id = ?";
			jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getIsbn(), book.getCategory(), book.getId());
		}
		return book;
	}

	@Override
	public Optional<BookEntity> findById(Integer id) {
		String sql = "SELECT * FROM books_jdbc WHERE id = ?";
        return jdbcTemplate.query(sql, new BookMapper(), id)
                           .stream()
                           .findFirst();
	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM books_jdbc WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
}
