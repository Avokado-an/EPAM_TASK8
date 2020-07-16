package com.anton.day6.model.creator;

import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.ModelException;
import com.anton.day6.model.validator.BookValidator;

import java.util.Arrays;
import java.util.List;

public class BookCreator {
    public Book createBook(String publishYear, String bookName, String publisher, String author) throws ModelException {
        BookValidator validator = new BookValidator();
        Book book;
        if (publisher == null || publishYear == null || bookName == null || author == null) {
            throw new ModelException();
        }
        if (validator.validatePublishYear(publishYear) &&
                validator.validateStringData(bookName) && validator.validateStringData(publisher) &&
                validator.validateStringData(author) && validator.validateAuthors(author)) {
            book = new Book(defineYear(publishYear), bookName, publisher, defineAuthors(author));
        } else {
            throw new ModelException();
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
