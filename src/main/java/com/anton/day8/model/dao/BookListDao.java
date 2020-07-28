package com.anton.day8.model.dao;

import com.anton.day8.model.entity.Book;
import com.anton.day8.model.exception.DaoException;
import com.anton.day8.model.dao.request.type.RequestType;

import java.util.List;

public interface BookListDao {
    void addBook(Book book) throws DaoException;

    void removeBook(String bookName) throws DaoException;

    List<Book> findBooks(RequestType requestType, String tag) throws DaoException;

    List<Book> findAllBooks() throws DaoException;
}
