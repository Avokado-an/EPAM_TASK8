package com.anton.day6.controller.invoker;

import com.anton.day6.helper.ArrayListHelper;
import com.anton.day6.controller.responce.ResponseParameters;
import com.anton.day6.model.comparator.AuthorComparator;
import com.anton.day6.model.comparator.BookNameComparator;
import com.anton.day6.model.comparator.PublishYearComparator;
import com.anton.day6.model.comparator.PublisherComparator;
import com.anton.day6.model.dao.impl.BookListDaoImplementation;
import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.ModelException;
import com.anton.day6.model.service.impl.LibraryServiceImplementation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InvokerTest {
    @BeforeClass
    public void setup() throws ModelException {
        Book book1 = new Book(1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11"));
        Book book2 = new Book(1500, "Book2", "Publisher2", Arrays.asList("Author2", "Author21"));
        Book book3 = new Book(2000, "Book3", "Publisher3", Arrays.asList("Author3"));
        Book book4 = new Book(2005, "Book4", "Publisher4", Arrays.asList("Author4", "Author41"));
        Book book5 = new Book(2005, "Book5", "Publisher5", Arrays.asList("Author5", "Author51"));
        Book book6 = new Book(1950, "Book6", "Publisher6", Arrays.asList("Author6", "Author61", "Author62"));
        Book book7 = new Book(1950, "Book7", "Publisher7", Arrays.asList("Author7", "Author71", "Author72"));
        BookListDaoImplementation.getInstance().addBook(book1);
        BookListDaoImplementation.getInstance().addBook(book5);
        BookListDaoImplementation.getInstance().addBook(book2);
        BookListDaoImplementation.getInstance().addBook(book6);
        BookListDaoImplementation.getInstance().addBook(book4);
        BookListDaoImplementation.getInstance().addBook(book3);
        BookListDaoImplementation.getInstance().addBook(book7);
    }

    @AfterClass
    public void clearInfo() throws ModelException {
        for (int i = 1; i < 7; i++) {
            BookListDaoImplementation.getInstance().removeBook("Book" + i);
        }
    }

    @Test
    public void doRequestAddValidTest() throws ModelException {
        Map<String, String> params = new HashMap<>();
        String publishYear = "2000";
        String name = "new";
        String publisher = "new";
        String authors = "newAuthor1";
        params.put(ResponseParameters.PUBLISH_YEAR, publishYear);
        params.put(ResponseParameters.NAME, name);
        params.put(ResponseParameters.PUBLISHER, publisher);
        params.put(ResponseParameters.AUTHORS, authors);
        Invoker.getInstance().doRequest(ResponseParameters.ADD, params);
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByName(name);
        assertEquals(expectedBooks.size(), 1);
    }

    @Test
    public void doRequestRemoveValidTest() throws ModelException {
        Map<String, String> params = new HashMap<>();
        params.put(ResponseParameters.NAME, "Book7");
        Invoker.getInstance().doRequest(ResponseParameters.REMOVE, params);
        List<Book> actual = LibraryServiceImplementation.getInstance().findBooksByName("Book7");
        assertTrue(actual.isEmpty());
    }

    @Test
    public void doRequestFindNameValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(ResponseParameters.NAME, "Book1");
        Map<String, List<Book>> actualMap = Invoker.getInstance().doRequest(
                ResponseParameters.FIND + ResponseParameters.NAME, params);
        Book actualBook = actualMap.get(ResponseParameters.FIND + ResponseParameters.NAME + ": " + "Book1").get(0);
        Book expected = new Book
                (1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11"));

        assertEquals(actualBook, expected);
    }

    @Test
    public void doRequestFindPublisherValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(ResponseParameters.PUBLISHER, "Publisher1");
        Map<String, List<Book>> actualMap = Invoker.getInstance().doRequest(ResponseParameters.FIND + ResponseParameters.PUBLISHER, params);
        List<Book> actualBooks = actualMap.get(ResponseParameters.FIND + ResponseParameters.PUBLISHER + ": " + "Publisher1");
        List<Book> expectedBooks = new ArrayList<>(Collections.singletonList(new Book
                (1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11"))));

        assertEquals(actualBooks, expectedBooks);
    }

    @Test
    public void doRequestFindYearValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(ResponseParameters.PUBLISH_YEAR, "2002");
        Map<String, List<Book>> actualMap = Invoker.getInstance().doRequest(ResponseParameters.FIND + ResponseParameters.PUBLISH_YEAR, params);
        List<Book> actualBooks = actualMap.get(ResponseParameters.FIND + ResponseParameters.PUBLISH_YEAR + ": " + "2002");
        List<Book> expectedBooks = new ArrayList<>(Arrays.asList(
                new Book(2005, "Book4", "Publisher4", Arrays.asList("Author4", "Author41")),
                new Book(2005, "Book5", "Publisher5", Arrays.asList("Author5", "Author51"))));
        assertTrue(actualBooks.containsAll(expectedBooks));
    }

    @Test
    public void doRequestFindAuthorValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(ResponseParameters.AUTHORS, "Author1");
        Map<String, List<Book>> actualMap = Invoker.getInstance().
                doRequest(ResponseParameters.FIND + ResponseParameters.AUTHORS, params);
        List<Book> actualBooks = actualMap.get(ResponseParameters.FIND + ResponseParameters.AUTHORS + ": " + "Author1");
        List<Book> expectedBooks = new ArrayList<>(Collections.singletonList(new Book
                (1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11"))));
        assertEquals(actualBooks, expectedBooks);
    }

    @Test
    public void doRequestSortNameValidTest() {
        Map<String, String> params = new HashMap<>();
        ArrayListHelper<Book> arrHandler = new ArrayListHelper<>();
        params.put(ResponseParameters.SORT + ResponseParameters.NAME, "");
        List<Book> sortedBooks = Invoker.getInstance().
                doRequest(ResponseParameters.SORT + ResponseParameters.NAME, params).
                get(ResponseParameters.SORT + ResponseParameters.NAME);
        boolean isSorted = arrHandler.isSorted(new ArrayList<>(sortedBooks), new BookNameComparator());
        assertTrue(isSorted);
    }

    @Test
    public void doRequestSortPublisherValidTest() {
        ArrayListHelper<Book> arrHandler = new ArrayListHelper<>();
        Map<String, String> params = new HashMap<>();
        params.put(ResponseParameters.SORT + ResponseParameters.PUBLISHER, "");
        List<Book> sortedBooks = Invoker.getInstance().doRequest
                (ResponseParameters.SORT + ResponseParameters.PUBLISHER, params).
                get(ResponseParameters.SORT + ResponseParameters.PUBLISHER);
        boolean isSorted = arrHandler.isSorted(new ArrayList<>(sortedBooks), new PublisherComparator());
        assertTrue(isSorted);
    }

    @Test
    public void doRequestSortYearValidTest() {
        Map<String, String> params = new HashMap<>();
        ArrayListHelper<Book> arrHandler = new ArrayListHelper<>();
        params.put(ResponseParameters.SORT + ResponseParameters.PUBLISH_YEAR, "");
        List<Book> sortedBooks = Invoker.getInstance().doRequest
                (ResponseParameters.SORT + ResponseParameters.PUBLISH_YEAR, params).
                get(ResponseParameters.SORT + ResponseParameters.PUBLISH_YEAR);
        boolean isSorted = arrHandler.isSorted(new ArrayList<>(sortedBooks), new PublishYearComparator());
        assertTrue(isSorted);
    }

    @Test
    public void doRequestSortAuthorValidTest() {
        Map<String, String> params = new HashMap<>();
        ArrayListHelper<Book> arrHandler = new ArrayListHelper<>();
        params.put(ResponseParameters.SORT + ResponseParameters.AUTHORS, "");
        List<Book> sortedBooks = Invoker.getInstance().doRequest
                (ResponseParameters.SORT + ResponseParameters.AUTHORS, params).
                get(ResponseParameters.SORT + ResponseParameters.AUTHORS);
        boolean isSorted = arrHandler.isSorted(new ArrayList<>(sortedBooks), new AuthorComparator());
        assertTrue(isSorted);
    }

    @DataProvider(name = "invalidCommand")
    public Object[][] createInvalidCommand() {
        return new Object[][]{
                {null}, {"invalidCommand"}
        };
    }

    @Test(dataProvider = "invalidCommand")
    public void doRequestFailedCommand(String command) {
        Map<String, String> emptyMap = new HashMap<>();
        Map<String, List<Book>> actualMap = Invoker.getInstance().doRequest(command, emptyMap);
        Map<String, List<Book>> expectedMap = new HashMap<>();
        expectedMap.put(ResponseParameters.OPERATION_FAILED, new ArrayList<>());
        assertEquals(actualMap, expectedMap);
    }
}
