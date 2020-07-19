package com.anton.day6.model.dao;

import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.ModelException;

import java.util.List;

public interface BookListDao {
    void addBook(Book book) throws ModelException;

    boolean removeBook(String bookName) throws ModelException;

    List<Book> sortBooksByAuthors() throws ModelException;

    List<Book> sortBooksByPublisher() throws ModelException;

    List<Book> sortBooksByPublishYear() throws ModelException;

    List<Book> sortBooksByName() throws ModelException;

    List<Book> findBooksByAuthor(String tag) throws ModelException;

    List<Book> findBooksByPublisher(String tag) throws ModelException;

    List<Book> findBooksByPublishYear(String tag) throws ModelException;

    List<Book> findBooksByName(String tag) throws ModelException;

    List<Book> findAllBooks() throws ModelException;
}
