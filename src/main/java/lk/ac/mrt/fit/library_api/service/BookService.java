package lk.ac.mrt.fit.library_api.service;

import java.util.List;
import java.util.Optional;

import lk.ac.mrt.fit.library_api.model.Book;
import lk.ac.mrt.fit.library_api.repository.BookRepository;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // Constructor Injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get book by ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Create book
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Update book
    public Optional<Book> updateBook(Long id, Book details) {

        return bookRepository.findById(id).map(book -> {

            book.setTitle(details.getTitle());
            book.setAuthor(details.getAuthor());
            book.setIsbn(details.getIsbn());
            book.setPrice(details.getPrice());

            return bookRepository.save(book);
        });
    }

    // Delete book
    public boolean deleteBook(Long id) {

        if (!bookRepository.existsById(id)) {
            return false;
        }

        bookRepository.deleteById(id);

        return true;
    }
}

