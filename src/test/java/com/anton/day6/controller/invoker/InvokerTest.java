package com.anton.day6.controller.invoker;

import com.anton.day6.controller.parametersProvider.Parameters;
import com.anton.day6.model.dao.impl.BookListDAOImplementation;
import com.anton.day6.model.entity.Book;
import com.anton.day6.model.entity.Library;
import com.anton.day6.model.exception.ModelException;
import com.anton.day6.model.service.LibraryService;
import com.anton.day6.model.service.impl.LibraryServiceImplementation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class InvokerTest {
    @BeforeClass
    public void setup() throws ModelException {
        Book book1 = new Book(1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11"));
        Book book2 = new Book(1500, "Book2", "Publisher2", Arrays.asList("Author2", "Author21"));
        Book book3 = new Book(2000, "Book3", "Publisher3", Arrays.asList("Author3"));
        Book book4 = new Book(2005, "Book4", "Publisher4", Arrays.asList("Author4", "Author41"));
        Book book5 = new Book(2005, "Book5", "Publisher5", Arrays.asList("Author5", "Author51"));
        Book book6 = new Book(1950, "Book6", "Publisher6", Arrays.asList("Author6", "Author61", "Author62"));
        BookListDAOImplementation.getInstance().addBook(book1);
        BookListDAOImplementation.getInstance().addBook(book5);
        BookListDAOImplementation.getInstance().addBook(book2);
        BookListDAOImplementation.getInstance().addBook(book6);
        BookListDAOImplementation.getInstance().addBook(book4);
        BookListDAOImplementation.getInstance().addBook(book3);
    }

    @AfterClass
    public void clearInfo() throws ModelException {
        for (int i = 1; i < 7; i++) {
            BookListDAOImplementation.getInstance().removeBook("Book" + i);
        }
    }

    @Test
    public void doRequestAddValidTest() throws ModelException {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.NAME, "newBook");
        params.put(Parameters.PUBLISHER, "newPublisher");
        params.put(Parameters.PUBLISH_YEAR, "1900");
        params.put(Parameters.AUTHORS, "newAuthor1, newAuthor2");
        Invoker.getInstance().doRequest(Parameters.ADD, params);
        Book expected = new Book
                (1900, "newBook", "newPublisher", Arrays.asList("newAuthor1", "newAuthor2"));
        Book actual = LibraryServiceImplementation.getInstance().findBooksByName("newBook").get(0);
        assertEquals(actual, expected);
    }

    /*@Test
    public void doRequestRemoveValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.NAME, "newBook");
        Invoker.getInstance().doRequest(Parameters.REMOVE, params);
        Book actual = LibraryServiceImplementation.getInstance().findBooksByName("newBook").get(0);
        assertEquals(actual, expected);
    }*/

    @Test
    public void doRequestFindNameValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.NAME, "Book1");
        Map<String, List<Book>> actualMap = Invoker.getInstance().doRequest(Parameters.FIND + Parameters.NAME, params);
        Book actualBook = actualMap.get(Parameters.FIND + Parameters.NAME + ": " + "Book1").get(0);
        Book expected = new Book
                (1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11"));

        assertEquals(actualBook, expected);
    }

    @Test
    public void doRequestFindPublisherValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.PUBLISHER, "Publisher1");
        Map<String, List<Book>> actualMap = Invoker.getInstance().doRequest(Parameters.FIND + Parameters.PUBLISHER, params);
        List<Book> actualBooks = actualMap.get(Parameters.FIND + Parameters.PUBLISHER + ": " + "Publisher1");
        List<Book> expectedBooks = new ArrayList<>(Collections.singletonList(new Book
                (1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11"))));

        assertEquals(actualBooks, expectedBooks);
    }

    @Test
    public void doRequestFindYearValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.PUBLISH_YEAR, "2002");
        Map<String, List<Book>> actualMap = Invoker.getInstance().doRequest(Parameters.FIND + Parameters.PUBLISH_YEAR, params);
        List<Book> actualBooks = actualMap.get(Parameters.FIND + Parameters.PUBLISH_YEAR + ": " + "2002");
        List<Book> expectedBooks = new ArrayList<>(Arrays.asList(new Book
                        (2005, "Book4", "Publisher4", Arrays.asList("Author4", "Author41")),
                new Book(2005, "Book5", "Publisher5", Arrays.asList("Author5", "Author51"))));
        assertEquals(actualBooks, expectedBooks);
    }

    @Test
    public void doRequestFindAuthorValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.AUTHORS, "Author1");
        Map<String, List<Book>> actualMap = Invoker.getInstance().doRequest(Parameters.FIND + Parameters.AUTHORS, params);
        List<Book> actualBooks = actualMap.get(Parameters.FIND + Parameters.AUTHORS + ": " + "Author1");
        List<Book> expectedBooks = new ArrayList<>(Collections.singletonList(new Book
                (1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11"))));
        assertEquals(actualBooks, expectedBooks);
    }

    @Test
    public void doRequestSortNameValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.SORT + Parameters.NAME, "");
        System.out.println(Invoker.getInstance().doRequest(Parameters.SORT + Parameters.NAME, params));
    }

    @Test
    public void doRequestSortPublisherValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.SORT + Parameters.PUBLISHER, "");
        System.out.println(Invoker.getInstance().doRequest(Parameters.SORT + Parameters.PUBLISHER, params));
    }

    @Test
    public void doRequestSortYearValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.SORT + Parameters.PUBLISH_YEAR, "");
        System.out.println(Invoker.getInstance().doRequest(Parameters.SORT + Parameters.PUBLISH_YEAR, params));
    }

    @Test
    public void doRequestSortAuthorValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(Parameters.SORT + Parameters.AUTHORS, "");
        System.out.println(Invoker.getInstance().doRequest(Parameters.SORT + Parameters.AUTHORS, params));
    }

    @Test
    public void doRequestViewValidTest() {

    }
}
