package com.anton.day8.model.service.impl;

import com.anton.day8.model.creator.BookCreator;
import com.anton.day8.model.dao.impl.BookListDaoImplementation;
import com.anton.day8.model.entity.Book;
import com.anton.day8.model.exception.DaoException;
import com.anton.day8.model.exception.ServiceException;
import com.anton.day8.model.service.LibraryService;
import com.anton.day8.model.dao.request.type.RequestType;
import com.anton.day8.model.validator.BookValidator;

import java.util.List;

public class LibraryServiceImplementation implements LibraryService {
    private static LibraryServiceImplementation instance;
    private static final String EMPTY_REQUEST = "";

    private LibraryServiceImplementation() {
    }

    public static LibraryServiceImplementation getInstance() {
        if (instance == null) {
            instance = new LibraryServiceImplementation();
        }
        return instance;
    }

    @Override
    public void addBook(String year, String name, String publisher, String authors) throws ServiceException {
        if (name == null) {
            throw new ServiceException();
        }
        try {
            List<Book> dbBook = findBooksByName(name);
            if(!dbBook.isEmpty()) {
                throw new ServiceException();
            }
            BookCreator creator = new BookCreator();
            Book newBook = creator.createBook(year, name, publisher, authors);
            BookListDaoImplementation.getInstance().addBook(newBook);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public void removeBook(String name) throws ServiceException {
        if (name == null) {
            throw new ServiceException();
        }
        try {
            BookListDaoImplementation.getInstance().removeBook(name);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Book> sortBooksByAuthors() throws ServiceException {
        try {
            return BookListDaoImplementation.getInstance().findBooks(RequestType.ORDER_BY_AUTHOR, EMPTY_REQUEST);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Book> sortBooksByPublisher() throws ServiceException {
        try {
            return BookListDaoImplementation.getInstance().findBooks(RequestType.ORDER_BY_PUBLISHER, EMPTY_REQUEST);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Book> sortBooksByPublishYear() throws ServiceException {
        try {
            return BookListDaoImplementation.getInstance().findBooks(RequestType.ORDER_BY_YEAR, EMPTY_REQUEST);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Book> sortBooksByName() throws ServiceException {
        try {
            return BookListDaoImplementation.getInstance().findBooks(RequestType.ORDER_BY_NAME, EMPTY_REQUEST);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Book> findBooksByAuthor(String tag) throws ServiceException {
        if (tag == null) {
            throw new ServiceException();
        }
        try {
            return BookListDaoImplementation.getInstance().findBooks(RequestType.FIND_BY_AUTHOR, tag);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Book> findBooksByPublisher(String tag) throws ServiceException {
        if (tag == null) {
            throw new ServiceException();
        }
        try {
            return BookListDaoImplementation.getInstance().findBooks(RequestType.FIND_BY_PUBLISHER, tag);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Book> findBooksByPublishYear(String tag) throws ServiceException {
        if (tag == null || !(new BookValidator().validatePublishYear(tag))) {
            throw new ServiceException();
        }
        try {
            return BookListDaoImplementation.getInstance().findBooks(RequestType.FIND_BY_YEAR, tag);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Book> findBooksByName(String tag) throws ServiceException {
        if (tag == null) {
            throw new ServiceException();
        }
        try {
            return BookListDaoImplementation.getInstance().findBooks(RequestType.FIND_BY_NAME, tag);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Book> findAllBooks() throws ServiceException {
        try {
            return BookListDaoImplementation.getInstance().findAllBooks();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }
}
