package com.anton.day6.model.service;

import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.ModelException;

import java.util.List;

public interface LibraryService {
    void addBook(String year, String name, String publisher, String authors) throws ModelException;
    void removeBook(String name) throws ModelException;
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
