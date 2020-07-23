package com.anton.day6.model.creator;

import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.DaoException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.testng.Assert.assertEquals;

public class BookCreatorTest {
    BookCreator creator;

    @BeforeClass
    public void setup() {
        creator = new BookCreator();
    }

    @Test
    public void createBookValidTest() throws DaoException {
        String year = "1400";
        String name = "Book1";
        String publisher = "publisher1";
        String authors = "Author1";
        Book expectedBook = creator.createBook(year, name, publisher, authors);
        Book actualBook = new Book(Integer.parseInt(year), name, publisher, Collections.singletonList(authors));
        assertEquals(expectedBook, actualBook);
    }

    @DataProvider(name = "invalidBook")
    public Object[][] createInvalidBook() {
        return new Object[][]{
                {"-1234", "a", "a", "a"}, {"2050", "a", "a", "a"}, {null, "a", "a", "a"},
                {"", "aa&a", "a", "a"},
                {"1234", "aa&a", "a", "a"}, {"1234", null, "a", "a"}, {"1234", "", "a", "a"},
                {"1234", "a", "aa&a", "a"}, {"1234", "a", null, "a"}, {"1234", "aa&a", "", "a"},
                {"1234", "a", "a", "a, a, a, a, a, a, a, a, a, a, a"}, {"1234", "aa&a", "a", ""},
                {"1234", "aa&a", "a", null}
        };
    }

    @Test(dataProvider = "invalidBook", expectedExceptions = DaoException.class)
    public void createBookValidTest(String year, String name, String publisher, String authors) throws DaoException {
        creator.createBook(year, name, publisher, authors);
    }
}
