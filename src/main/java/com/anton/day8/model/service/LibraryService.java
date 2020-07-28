package com.anton.day8.model.service;

import com.anton.day8.model.entity.Book;
import com.anton.day8.model.exception.DaoException;
import com.anton.day8.model.exception.ServiceException;

import java.util.List;

public interface LibraryService {
    void addBook(String year, String name, String publisher, String authors) throws DaoException, ServiceException;

    void removeBook(String name) throws DaoException, ServiceException;

    List<Book> sortBooksByAuthors() throws DaoException, ServiceException;

    List<Book> sortBooksByPublisher() throws DaoException, ServiceException;

    List<Book> sortBooksByPublishYear() throws DaoException, ServiceException;

    List<Book> sortBooksByName() throws DaoException, ServiceException;

    List<Book> findBooksByAuthor(String tag) throws DaoException, ServiceException;

    List<Book> findBooksByPublisher(String tag) throws DaoException, ServiceException;

    List<Book> findBooksByPublishYear(String tag) throws DaoException, ServiceException;

    List<Book> findBooksByName(String tag) throws DaoException, ServiceException;

    List<Book> findAllBooks() throws DaoException, ServiceException;
}
