package com.anton.day6.model.dao;

import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.ModelException;

import java.util.List;

public interface BookListDao {
    void addBook(Book book) throws ModelException;
    boolean removeBook(String bookName) throws ModelException;
    List<Book> findAll() throws ModelException;
}
