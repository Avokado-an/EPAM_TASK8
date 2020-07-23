package com.anton.day6.model.dao;

import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.DaoException;
import com.anton.day6.model.dao.request.type.TagType;

import java.util.List;

public interface BookListDao {
    void addBook(Book book) throws DaoException;

    void removeBook(String bookName) throws DaoException;

    List<Book> findBooks(TagType tagType, String tag) throws DaoException;

    List<Book> findAllBooks() throws DaoException;
}
