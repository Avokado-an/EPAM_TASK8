package com.anton.day8.model.creator;

import com.anton.day8.model.entity.Book;
import com.anton.day8.model.exception.DaoException;
import com.anton.day8.model.validator.BookValidator;

import java.util.Arrays;
import java.util.List;

public class BookCreator {
    private static final String EXCEPTION_MESSAGE = "Non valid book info";

    public Book createBook(String publishYear, String bookName, String publisher, String author) throws DaoException {
        BookValidator validator = new BookValidator();
        Book book;
        if (validator.validatePublishYear(publishYear) &&
                validator.validateStringData(bookName) && validator.validateStringData(publisher) &&
                validator.validateStringData(author) && validator.validateAuthors(author)) {
            book = new Book(defineYear(publishYear), bookName, publisher, defineAuthors(author));
        } else {
            throw new DaoException(EXCEPTION_MESSAGE);
        }
        return book;
    }

    public Book createBook(int publishYear, String bookName, String publisher, String author) throws DaoException {
        BookValidator validator = new BookValidator();
        Book book;
        if (validator.validateStringData(bookName) && validator.validateStringData(publisher) &&
                validator.validateStringData(author) && validator.validateAuthors(author)) {
            book = new Book(publishYear, bookName, publisher, defineAuthors(author));
        } else {
            throw new DaoException(EXCEPTION_MESSAGE);
        }
        return book;
    }

    private List<String> defineAuthors(String author) {
        String comma = ", ";
        return Arrays.asList(author.split(comma));
    }

    private int defineYear(String year) {
        return Integer.parseInt(year);
    }
}
