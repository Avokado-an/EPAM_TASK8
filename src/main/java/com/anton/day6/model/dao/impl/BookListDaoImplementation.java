package com.anton.day6.model.dao.impl;

import com.anton.day6.model.comparator.AuthorComparator;
import com.anton.day6.model.comparator.BookNameComparator;
import com.anton.day6.model.comparator.PublishYearComparator;
import com.anton.day6.model.comparator.PublisherComparator;
import com.anton.day6.model.dao.BookListDao;
import com.anton.day6.model.entity.Book;
import com.anton.day6.model.entity.Library;
import com.anton.day6.model.exception.ModelException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookListDaoImplementation implements BookListDao {
    private static BookListDaoImplementation instance;

    //todo search in dao
    private BookListDaoImplementation() {
    }

    public static BookListDaoImplementation getInstance() {
        if (instance == null) {
            instance = new BookListDaoImplementation();
        }
        return instance;
    }

    @Override
    public void addBook(Book book) throws ModelException {
        Library.getInstance().addBook(book);
    }

    @Override
    public boolean removeBook(String bookName) throws ModelException {
        Book book = Library.getInstance().getBookStream()
                .filter(b -> b.getBookName().equals(bookName))
                .findFirst()
                .orElse(null);
        boolean flag;
        if (book != null) {
            flag = Library.getInstance().removeBook(book);
        } else {
            throw new ModelException();
        }
        return flag;
    }

    @Override
    public List<Book> sortBooksByAuthors() throws ModelException {
        return sortBooks(new AuthorComparator());
    }

    @Override
    public List<Book> sortBooksByPublisher() throws ModelException {
        return sortBooks(new PublisherComparator());
    }

    @Override
    public List<Book> sortBooksByPublishYear() throws ModelException {
        return sortBooks(new PublishYearComparator());
    }

    @Override
    public List<Book> sortBooksByName() throws ModelException {
        return sortBooks(new BookNameComparator());
    }

    @Override
    public List<Book> findBooksByAuthor(String tag) throws ModelException {
        List<Book> res = new ArrayList<>();
        findAllBooks()
                .stream()
                .filter(b -> b.getAuthors().contains(tag))
                .forEach(res::add);
        return res;
    }

    @Override
    public List<Book> findBooksByPublisher(String tag) throws ModelException {
        List<Book> res = new ArrayList<>();
        findAllBooks()
                .stream()
                .filter(b -> b.getPublisher().equalsIgnoreCase(tag))
                .forEach(res::add);
        return res;
    }

    @Override
    public List<Book> findBooksByPublishYear(String tag) throws ModelException {
        List<Book> res = new ArrayList<>();
        findAllBooks()
                .stream()
                .filter(b -> b.getPublishYear() >= Integer.parseInt(tag))
                .forEach(res::add);
        return res;
    }

    @Override
    public List<Book> findBooksByName(String tag) throws ModelException {
        List<Book> res = new ArrayList<>();
        findAllBooks()
                .stream()
                .filter(b -> b.getBookName().equalsIgnoreCase(tag))
                .forEach(res::add);
        return res;
    }

    @Override
    public List<Book> findAllBooks() throws ModelException {
        List<Book> books = new ArrayList<>();
        List<Book> daoBooks = Library.getInstance().getBooks();
        if (daoBooks == null) {
            throw new ModelException();
        }
        for (Book book : daoBooks) {
            if (book == null) {
                throw new ModelException();
            }
            books.add(book);
        }
        return books;
    }

    private List<Book> sortBooks(Comparator<Book> bookComparator) throws ModelException {
        return findAllBooks().stream().sorted(bookComparator).collect(Collectors.toList());
    }
}
