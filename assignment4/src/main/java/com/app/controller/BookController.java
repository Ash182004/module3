package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.BookEntity;
import com.app.service.BookService;

@RestController
@RequestMapping("api/books")
public class BookController {
	private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity book) {
        BookEntity saved = service.addBook(book);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        List<BookEntity> books = service.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable Integer id) {
        BookEntity book = service.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Integer id) {
        service.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("author/{author}")
    public ResponseEntity<List<BookEntity>> getBooksByAuthor(@PathVariable String author) {
        List<BookEntity> books = service.getBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }

    @GetMapping("price-less-than/{price}")
    public ResponseEntity<List<BookEntity>> getBooksByPriceLessThan(@PathVariable Double price) {
        List<BookEntity> books = service.getBooksByPriceLessThan(price);
        return ResponseEntity.ok(books);
    }

    @GetMapping("search")
    public ResponseEntity<List<BookEntity>> searchBooksByTitle(@RequestParam String keyword) {
        List<BookEntity> books = service.searchBooksByTitle(keyword);
        return ResponseEntity.ok(books);
    }

    @GetMapping("price-range")
    public ResponseEntity<List<BookEntity>> getBooksByPriceRange(
            @RequestParam double min,
            @RequestParam double max) {
        List<BookEntity> books = service.getBooksByPriceRange(min, max);
        return ResponseEntity.ok(books);
    }
}
