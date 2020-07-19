package com.anton.day6.model.service;

import com.anton.day6.model.dao.impl.BookListDaoImplementation;
import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.ModelException;
import com.anton.day6.model.service.impl.LibraryServiceImplementation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.List;

public class LibraryServiceImplementationTest {
    @BeforeClass
    public void setup() throws ModelException {
        Book book1 = new Book(1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11"));
        Book book2 = new Book(1500, "Book2", "Publisher2", Arrays.asList("Author2", "Author21"));
        Book book3 = new Book(2000, "Book3", "Publisher3", Arrays.asList("Author3"));
        Book book4 = new Book(2005, "Book4", "Publisher4", Arrays.asList("Author4", "Author41"));
        Book book5 = new Book(2005, "Book5", "Publisher5", Arrays.asList("Author5", "Author51"));
        Book book6 = new Book(1950, "Book6", "Publisher6", Arrays.asList("Author6", "Author61", "Author62"));
        BookListDaoImplementation.getInstance().addBook(book1);
        BookListDaoImplementation.getInstance().addBook(book5);
        BookListDaoImplementation.getInstance().addBook(book2);
        BookListDaoImplementation.getInstance().addBook(book6);
        BookListDaoImplementation.getInstance().addBook(book4);
        BookListDaoImplementation.getInstance().addBook(book3);
    }

    @AfterClass
    public void clearInfo() throws ModelException {
        for (int i = 1; i < 7; i++) {
            BookListDaoImplementation.getInstance().removeBook("Book" + i);
        }
    }

    @Test
    public void addBookValidTest() throws ModelException {
        String name = "new";
        String year = "2000";
        String publisher = "new";
        String author = "new";
        LibraryServiceImplementation.getInstance().addBook(year, name, publisher, author);
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByName(name);
        assertEquals(expectedBooks.size(), 1);
    }
}
