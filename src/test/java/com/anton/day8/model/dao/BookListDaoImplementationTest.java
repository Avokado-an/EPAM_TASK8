package com.anton.day8.model.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookListDaoImplementationTest {
    /*@BeforeClass
    public void setup() throws DaoException {
        Book book1 = new Book(1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11"));
        Book book2 = new Book(1500, "Book2", "Publisher2", Arrays.asList("Author2", "Author21"));
        Book book3 = new Book(2000, "Book3", "Publisher3", Arrays.asList("Author3"));
        Book book4 = new Book(2005, "Book4", "Publisher4", Arrays.asList("Author4", "Author41"));
        Book book5 = new Book(2010, "Book5", "Publisher5", Arrays.asList("Author5", "Author51"));
        Book book6 = new Book(1950, "Book6", "Publisher6", Arrays.asList("Author6", "Author61", "Author62"));
        Book book7 = new Book(2000, "Book7", "Publisher7", Arrays.asList("Author7"));
        BookListDaoImplementation.getInstance().addBook(book1);
        BookListDaoImplementation.getInstance().addBook(book5);
        BookListDaoImplementation.getInstance().addBook(book2);
        BookListDaoImplementation.getInstance().addBook(book6);
        BookListDaoImplementation.getInstance().addBook(book4);
        BookListDaoImplementation.getInstance().addBook(book3);
        BookListDaoImplementation.getInstance().addBook(book7);
    }

    @AfterClass
    public void clearInfo() throws DaoException {
        for (int i = 1; i < 7; i++) {
            BookListDaoImplementation.getInstance().removeBook("Book" + i);
        }
    }

    @Test
    public void addBookValidTest() throws DaoException {
        String name = "new";
        String year = "2000";
        String publisher = "new";
        String author = "new";
        Book newBook = new Book(Integer.parseInt(year), name, publisher, Collections.singletonList(author));
        BookListDaoImplementation.getInstance().addBook(newBook);
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByName(name);
        assertEquals(expectedBooks.size(), 1);
    }

    @Test
    public void removeBookValidTest() throws DaoException {
        String name = "Book7";
        BookListDaoImplementation.getInstance().removeBook(name);
        List<Book> expectedBooks = LibraryServiceImplementation.getInstance().findBooksByName(name);
        assertTrue(expectedBooks.isEmpty());
    }*/

    /*@Test
    public void sortBooksByNameValidTest() throws ModelException {
        ArrayListHelper<Book> helper = new ArrayListHelper<>();
        List<Book> books = BookListDaoImplementation.getInstance().sortBooksByName();
        boolean isSorted = helper.isSorted(new ArrayList<>(books), new BookNameComparator());
        assertTrue(isSorted);
    }

    @Test
    public void sortBooksByAuthorValidTest() throws ModelException {
        ArrayListHelper<Book> helper = new ArrayListHelper<>();
        List<Book> books = BookListDaoImplementation.getInstance().sortBooksByAuthors();
        boolean isSorted = helper.isSorted(new ArrayList<>(books), new AuthorComparator());
        assertTrue(isSorted);
    }

    @Test
    public void sortBooksByPublisherValidTest() throws ModelException {
        ArrayListHelper<Book> helper = new ArrayListHelper<>();
        List<Book> books = BookListDaoImplementation.getInstance().sortBooksByPublisher();
        boolean isSorted = helper.isSorted(new ArrayList<>(books), new PublisherComparator());
        assertTrue(isSorted);
    }

    @Test
    public void sortBooksByPublishYearValidTest() throws ModelException {
        ArrayListHelper<Book> helper = new ArrayListHelper<>();
        List<Book> books = BookListDaoImplementation.getInstance().sortBooksByPublishYear();
        boolean isSorted = helper.isSorted(new ArrayList<>(books), new PublishYearComparator());
        assertTrue(isSorted);
    }*/

    /*@Test
    public void findBooksByAuthorValidTest() throws DaoException {
        String author = "Author1";
        List<Book> expectedBooks = BookListDaoImplementation.getInstance().findBooksByAuthor(author);
        List<Book> actualBooks = Collections.singletonList(
                new Book(1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11")));
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void findBooksByNameValidTest() throws DaoException {
        String name = "Book1";
        List<Book> expectedBooks = BookListDaoImplementation.getInstance().findBooksByName(name);
        List<Book> actualBooks = Collections.singletonList(
                new Book(1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11")));
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void findBooksByPublisherValidTest() throws DaoException {
        String publisher = "Publisher1";
        List<Book> expectedBooks = BookListDaoImplementation.getInstance().findBooksByPublisher(publisher);
        List<Book> actualBooks = Collections.singletonList(
                new Book(1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11")));
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void findBooksByPublishYearValidTest() throws DaoException {
        String year = "2010";
        List<Book> expectedBooks = BookListDaoImplementation.getInstance().findBooksByPublishYear(year);
        List<Book> actualBooks = Collections.singletonList(
                new Book(2010, "Book5", "Publisher5", Arrays.asList("Author5", "Author51")));
        assertEquals(expectedBooks, actualBooks);
    }*/
}
