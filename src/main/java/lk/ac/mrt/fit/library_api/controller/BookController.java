package lk.ac.mrt.fit.library_api.controller;

import java.util.List;

import jakarta.validation.Valid;

import lk.ac.mrt.fit.library_api.model.Book;
import lk.ac.mrt.fit.library_api.service.BookService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    // Constructor Injection
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET: Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // GET: Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(
            @PathVariable Long id) {

        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Create a new book
    @PostMapping
    public ResponseEntity<Book> createBook(
            @Valid @RequestBody Book book) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.createBook(book));
    }

    // PUT: Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody Book book) {

        return bookService.updateBook(id, book)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable Long id) {

        return bookService.deleteBook(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

