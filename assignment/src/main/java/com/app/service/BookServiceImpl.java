package com.app.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.app.entities.BookEntity;
import com.app.exceptions.BookNotFoundException;
import com.app.repo.BookRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	
	private BookRepo bookRepo;
	
	public BookServiceImpl(BookRepo bookRepo) {
		this.bookRepo = bookRepo;
	}

	@Override
	public BookEntity addBook(BookEntity book) {
		return bookRepo.save(book);
	}

	@Override
	public List<BookEntity> getAllBooks() {
		return bookRepo.findAll();
	}
	
	@Override
	public BookEntity getBookById(Integer id) {
		return bookRepo.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
	}

	@Override
	public void deleteBookById(Integer id) {
		bookRepo.deleteById(id);
	}

	@Override
	public List<BookEntity> getBooksByAuthor(String author) {
		return bookRepo.findByAuthor(author);
	}

	@Override
	public List<BookEntity> getBooksByPriceLessThan(Double price) {
		return bookRepo.findByPriceLessThan(price);
	}

	@Override
	public List<BookEntity> searchBooksByTitle(String keyword) {
		return bookRepo.searchByTitle(keyword);
	}

	@Override
	public List<BookEntity> getBooksByPriceRange(double min, double max) {
		return bookRepo.findByPriceRange(min, max);
	}

	@Override
	public boolean checkBookExistsByIsbn(String isbn) {
		return bookRepo.existsByIsbn(isbn);
	}

	@Override
	public void deleteBookByTitle(String title) {
		bookRepo.deleteByTitle(title);
	}
	
}
