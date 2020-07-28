package com.anton.day8.controller.invoker;

import com.anton.day8.controller.responce.ResponseParameters;
import com.anton.day8.helper.ArrayListHelper;
import com.anton.day8.helper.comparator.BookYearComparator;
import com.anton.day8.model.entity.Book;
import com.anton.day8.model.exception.DaoException;
import com.anton.day8.model.exception.ServiceException;
import com.anton.day8.model.service.impl.LibraryServiceImplementation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InvokerTest {
    @Test
    public void doRequestAddValidTest() throws ServiceException {
        Map<String, String> params = new HashMap<>();
        String publishYear = "2000";
        String name = "newBook";
        String publisher = "newPublisher";
        String authors = "newAuthor";
        params.put(ResponseParameters.PUBLISH_YEAR, publishYear);
        params.put(ResponseParameters.NAME, name);
        params.put(ResponseParameters.PUBLISHER, publisher);
        params.put(ResponseParameters.AUTHORS, authors);
        Invoker.getInstance().doRequest(ResponseParameters.ADD, params);
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByName(name);
        assertEquals(expectedBooks.size(), 1);
    }

    @Test
    public void doRequestRemoveValidTest() throws ServiceException {
        Map<String, String> params = new HashMap<>();
        String name = "newBook";
        params.put(ResponseParameters.NAME, name);
        Invoker.getInstance().doRequest(ResponseParameters.REMOVE, params);
        List<Book> actual = LibraryServiceImplementation.getInstance().findBooksByName(name);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void doRequestFindNameValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(ResponseParameters.NAME, "new");
        Map<String, List<Book>> actualMap = Invoker.getInstance().doRequest(
                ResponseParameters.FIND + ResponseParameters.NAME, params);
        Book actualBook = actualMap.get(ResponseParameters.OPERATION_SUCCEED).get(0);
        Book expected = new Book
                (1, "new", "new", Collections.singletonList("author"));

        assertEquals(actualBook, expected);
    }

    @Test
    public void doRequestFindPublisherValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(ResponseParameters.PUBLISHER, "new");
        Map<String, List<Book>> actualMap = Invoker.getInstance().
                doRequest(ResponseParameters.FIND + ResponseParameters.PUBLISHER, params);
        List<Book> actualBooks = actualMap.get(ResponseParameters.OPERATION_SUCCEED);
        List<Book> expectedBooks = new ArrayList<>(Collections.singletonList(new Book
                (1, "new", "new", Collections.singletonList("author"))));
        assertEquals(actualBooks, expectedBooks);
    }

    @Test
    public void doRequestFindYearValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(ResponseParameters.PUBLISH_YEAR, "1");
        Map<String, List<Book>> actualMap = Invoker.getInstance().doRequest(
                ResponseParameters.FIND + ResponseParameters.PUBLISH_YEAR, params);
        List<Book> actualBooks = actualMap.get(ResponseParameters.OPERATION_SUCCEED);
        List<Book> expectedBooks = new ArrayList<>(Collections.singletonList(new Book
                (1, "new", "new", Collections.singletonList("author"))));
        assertTrue(actualBooks.containsAll(expectedBooks));
    }

    @Test
    public void doRequestFindAuthorValidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(ResponseParameters.AUTHORS, "author");
        Map<String, List<Book>> actualMap = Invoker.getInstance().
                doRequest(ResponseParameters.FIND + ResponseParameters.AUTHORS, params);
        List<Book> actualBooks = actualMap.get(ResponseParameters.OPERATION_SUCCEED);
        List<Book> expectedBooks = new ArrayList<>(Collections.singletonList(new Book
                (1, "new", "new", Collections.singletonList("author"))));
        assertEquals(actualBooks, expectedBooks);
    }

    @Test
    public void doRequestSortYearValidTest() {
        Map<String, String> params = new HashMap<>();
        ArrayListHelper<Book> arrHandler = new ArrayListHelper<>();
        params.put(ResponseParameters.SORT + ResponseParameters.PUBLISH_YEAR, "");
        List<Book> sortedBooks = Invoker.getInstance().doRequest
                (ResponseParameters.SORT + ResponseParameters.PUBLISH_YEAR, params).
                get(ResponseParameters.OPERATION_SUCCEED);
        boolean isSorted = arrHandler.isSorted(new ArrayList<>(sortedBooks), new BookYearComparator());
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
