package com.anton.day6.model.service.impl;

import com.anton.day6.model.comparator.AuthorComparator;
import com.anton.day6.model.comparator.BookNameComparator;
import com.anton.day6.model.comparator.PublishYearComparator;
import com.anton.day6.model.comparator.PublisherComparator;
import com.anton.day6.model.dao.impl.BookListDAOImplementation;
import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.ModelException;
import com.anton.day6.model.service.LibraryService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryServiceImplementation implements LibraryService {
    private static LibraryServiceImplementation instance;
    private static final int FIRST = 0;

    private LibraryServiceImplementation() {
    }

    public static LibraryServiceImplementation getInstance() {
        if (instance == null) {
            instance = new LibraryServiceImplementation();
        }
        return instance;
    }

    @Override
    public void addBook(Book book) throws ModelException {
        boolean flag = BookListDAOImplementation.getInstance().addBook(book);
        if(!flag) {
            throw new ModelException();
        }
    }

    @Override
    public void removeBook(String name) throws ModelException {
        boolean flag;
        if (name == null) {
            throw new ModelException();
        }
        Book book = findBooksByName(name).get(FIRST);
        if (book == null) {
            flag = false;
        } else {
            flag = BookListDAOImplementation.getInstance().removeBook(name);
        }
        if(!flag) {
            throw new ModelException();
        }
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
        if (tag == null) {
            throw new ModelException();
        }
        List<Book> res = new ArrayList<>();
        findAllBooks()
                .stream()
                .filter(b -> b.getAuthors().contains(tag))
                .forEach(res::add);
        return res;
    }

    @Override
    public List<Book> findBooksByPublisher(String tag) throws ModelException {
        if (tag == null) {
            throw new ModelException();
        }
        List<Book> res = new ArrayList<>();
        findAllBooks()
                .stream()
                .filter(b -> b.getPublisher().equalsIgnoreCase(tag))
                .forEach(res::add);
        return res;
    }

    @Override
    public List<Book> findBooksByPublishYear(String tag) throws ModelException {
        if (tag == null) {
            throw new ModelException();
        }
        List<Book> res = new ArrayList<>();
        findAllBooks()
                .stream()
                .filter(b -> b.getPublishYear() >= Integer.parseInt(tag))
                .forEach(res::add);
        return res;
    }

    @Override
    public List<Book> findBooksByName(String tag) throws ModelException {
        if (tag == null) {
            throw new ModelException();
        }
        List<Book> res = new ArrayList<>();
        findAllBooks()
                .stream()
                .filter(b -> b.getBookName().equalsIgnoreCase(tag))
                .forEach(res::add);
        return res;
    }

    @Override
    public List<Book> findAllBooks() throws ModelException {
        return BookListDAOImplementation.getInstance().findAll();
    }

    private List<Book> sortBooks(Comparator<Book> bookComparator) throws ModelException {
        return findAllBooks().stream().sorted(bookComparator).collect(Collectors.toList());
    }
}
