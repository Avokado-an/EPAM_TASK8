package com.anton.day8.model.dao;

import com.anton.day8.helper.ArrayListHelper;
import com.anton.day8.helper.comparator.BookYearComparator;
import com.anton.day8.model.dao.impl.BookListDaoImplementation;
import com.anton.day8.model.dao.request.type.RequestType;
import com.anton.day8.model.entity.Book;
import com.anton.day8.model.exception.DaoException;
import com.anton.day8.model.exception.ServiceException;
import com.anton.day8.model.service.impl.LibraryServiceImplementation;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookListDaoImplementationTest {
    @Test
    public void addBookValidTest() throws DaoException, ServiceException {
        String name = "newBook";
        String year = "2000";
        String publisher = "newBook";
        String author = "newBook";
        Book newBook = new Book(Integer.parseInt(year), name, publisher, Collections.singletonList(author));
        BookListDaoImplementation.getInstance().addBook(newBook);
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByName(name);
        assertEquals(expectedBooks.size(), 1);
    }

    @Test
    public void removeBookValidTest() throws DaoException {
        String name = "newBook";
        BookListDaoImplementation.getInstance().removeBook(name);
        List<Book> expectedBooks = BookListDaoImplementation.getInstance().findBooks(RequestType.FIND_BY_NAME, name);
        assertTrue(expectedBooks.isEmpty());
    }

    @Test
    public void sortBooksByPublishYearValidTest() throws DaoException {
        ArrayListHelper<Book> helper = new ArrayListHelper<>();
        List<Book> books = BookListDaoImplementation.getInstance().findBooks(RequestType.ORDER_BY_YEAR, "");
        boolean isSorted = helper.isSorted(new ArrayList<>(books), new BookYearComparator());
        assertTrue(isSorted);
    }

    @Test
    public void findBooksByAuthorValidTest() throws DaoException {
        String author = "author";
        List<Book> expectedBooks = BookListDaoImplementation.getInstance().findBooks(RequestType.FIND_BY_AUTHOR, author);
        List<Book> actualBooks = Collections.singletonList(
                new Book(1, "new", "new", Collections.singletonList("author")));
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void findBooksByNameValidTest() throws DaoException {
        String name = "new";
        List<Book> expectedBooks = BookListDaoImplementation.getInstance().findBooks(RequestType.FIND_BY_NAME, name);
        List<Book> actualBooks = Collections.singletonList(
                new Book(1, "new", "new", Collections.singletonList("author")));
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void findBooksByPublisherValidTest() throws DaoException {
        String publisher = "new";
        List<Book> expectedBooks = BookListDaoImplementation.getInstance().findBooks(RequestType.FIND_BY_PUBLISHER, publisher);
        System.out.println(expectedBooks);
        List<Book> actualBooks = Collections.singletonList(
                new Book(1, "new", "new", Collections.singletonList("author")));
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void findBooksByPublishYearValidTest() throws DaoException {
        String year = "1000";
        List<Book> expectedBooks = BookListDaoImplementation.getInstance().findBooks(RequestType.FIND_BY_YEAR, year);
        System.out.println(expectedBooks);
        List<Book> actualBooks = Collections.singletonList(
                new Book(1000, "old", "old", Collections.singletonList("old")));
        assertEquals(expectedBooks, actualBooks);
    }
}
