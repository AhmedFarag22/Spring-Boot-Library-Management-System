package com.example.demo.Library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;


    private  BookService bookService;

    private Book book;




    @Test
    public void getAllBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("secret");
        book.setAuthor("Ahmed");
        book.setPublicationYear(2002);
        book.setIsbn("122334");
        bookRepository.save(book);
        List<Book> books = bookRepository.findAll();
        assertNotEquals(books.size() , 0);


    }

    @Test
    void getBookById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("secret");
        book.setAuthor("Ahmed");
        book.setPublicationYear(2002);
        book.setIsbn("122334");
        bookRepository.save(book);
        Book b = bookRepository.findById(1L).get();
        assertEquals(book.getPublicationYear(), 2002);
    }

    @Test
    void addBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("secret");
        book.setAuthor("Ahmed");
        book.setPublicationYear(2002);
        book.setIsbn("122334");
        bookRepository.save(book);
        assertNotNull(bookRepository.findById(1L).get());
    }

    @Test
    void updateBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("secret");
        book.setAuthor("Ahmed");
        book.setPublicationYear(2002);
        book.setIsbn("122334");
        bookRepository.save(book);
        Book b = bookRepository.findById(1L).get();
        b.setPublicationYear(2003);
        bookRepository.save(b);
        assertNotEquals(2002, bookRepository.findById(1L).get().getPublicationYear());
    }

    @Test
    void deleteBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("secret");
        book.setAuthor("Ahmed");
        book.setPublicationYear(2002);
        book.setIsbn("122334");
        bookRepository.save(book);
        bookRepository.deleteById(1L);
        assertFalse(bookRepository.existsById(1L));
    }
}