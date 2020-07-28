package com.anton.day8.model.service;

import com.anton.day8.helper.ArrayListHelper;
import com.anton.day8.helper.comparator.BookYearComparator;
import com.anton.day8.model.entity.Book;
import com.anton.day8.model.exception.ServiceException;
import com.anton.day8.model.service.impl.LibraryServiceImplementation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LibraryServiceImplementationTest {
    @Test
    public void addBookValidTest() throws ServiceException {
        String name = "newBook";
        String year = "2000";
        String publisher = "newBook";
        String author = "newBook";
        LibraryServiceImplementation.getInstance().addBook(year, name, publisher, author);
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByName(name);
        assertEquals(expectedBooks.size(), 1);
    }

    @Test
    public void removeBookValidTest() throws ServiceException {
        String name = "newBook";
        LibraryServiceImplementation.getInstance().removeBook(name);
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByName(name);
        assertTrue(expectedBooks.isEmpty());
    }

    @Test
    public void sortBooksByPublishYearValidTest() throws ServiceException {
        ArrayListHelper<Book> helper = new ArrayListHelper<>();
        List<Book> books = LibraryServiceImplementation.getInstance().sortBooksByPublishYear();
        boolean isSorted = helper.isSorted(new ArrayList<>(books), new BookYearComparator());
        assertTrue(isSorted);
    }

    @Test
    public void findBooksByAuthorValidTest() throws ServiceException {
        String author = "author";
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByAuthor(author);
        List<Book> actualBooks = Collections.singletonList(
                new Book(1, "new", "new", Collections.singletonList("author")));
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void findBooksByNameValidTest() throws ServiceException {
        String name = "new";
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByName(name);
        List<Book> actualBooks = Collections.singletonList(
                new Book(1, "new", "new", Collections.singletonList("author")));
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void findBooksByPublisherValidTest() throws ServiceException {
        String publisher = "new";
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByPublisher(publisher);
        List<Book> actualBooks = Collections.singletonList(
                new Book(1, "new", "new", Collections.singletonList("author")));
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void findBooksByPublishYearValidTest() throws ServiceException {
        String year = "1";
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByPublishYear(year);
        List<Book> actualBooks = Collections.singletonList(
                new Book(1, "new", "new", Collections.singletonList("author")));
        assertEquals(expectedBooks, actualBooks);
    }

    @DataProvider(name = "invalidYearTag")
    public Object[][] createInvalidYearTag() {
        return new Object[][]{
                {null}, {"-1234"}, {"werewr"}, {"20002002"}
        };
    }

    @Test(dataProvider = "invalidYearTag", expectedExceptions = ServiceException.class)
    public void findBooksByPublishYearExceptionTest(String tag) throws ServiceException {
        LibraryServiceImplementation.getInstance().findBooksByPublishYear(tag);
    }

    @DataProvider(name = "invalidTag")
    public Object[][] createInvalidTag() {
        return new Object[][]{
                {null}
        };
    }

    @Test(dataProvider = "invalidTag", expectedExceptions = ServiceException.class)
    public void findBooksByPublisherExceptionTest(String tag) throws ServiceException {
        LibraryServiceImplementation.getInstance().findBooksByPublisher(tag);
    }

    @Test(dataProvider = "invalidTag", expectedExceptions = ServiceException.class)
    public void findBooksByNameExceptionTest(String tag) throws ServiceException {
        LibraryServiceImplementation.getInstance().findBooksByName(tag);
    }

    @Test(dataProvider = "invalidTag", expectedExceptions = ServiceException.class)
    public void findBooksByAuthorsExceptionTest(String tag) throws ServiceException {
        LibraryServiceImplementation.getInstance().findBooksByAuthor(tag);
    }
}
