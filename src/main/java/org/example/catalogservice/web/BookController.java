package org.example.catalogservice.web;

import jakarta.validation.Valid;
import org.example.catalogservice.domain.Book;
import org.example.catalogservice.domain.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookService.viewBookList();
    }

    @GetMapping("{isbn}")
    public Book getBookById(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid  @RequestBody Book book) {
        return  bookService.addBookToCatalog(book);
    }

    @PutMapping
    public Book put(@PathVariable String isbn, @Valid @RequestBody Book book){
        return bookService.editBookDetails(isbn, book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn){
        bookService.removeBookFromCatalog(isbn);
    }



}
