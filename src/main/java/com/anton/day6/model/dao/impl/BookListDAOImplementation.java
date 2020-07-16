package com.anton.day6.model.dao.impl;

import com.anton.day6.model.dao.BookListDAO;
import com.anton.day6.model.entity.Book;
import com.anton.day6.model.entity.Library;
import com.anton.day6.model.exception.ModelException;

import java.util.ArrayList;
import java.util.List;

public class BookListDAOImplementation implements BookListDAO {
    private static BookListDAOImplementation instance;
//todo search in dao
    private BookListDAOImplementation() {
    }

    public static BookListDAOImplementation getInstance () {
        if(instance == null) {
            instance = new BookListDAOImplementation();
        }
        return instance;
    }

    @Override
    public boolean addBook(Book book) throws ModelException {
        return Library.getInstance().addBook(book);
    }

    @Override
    public boolean removeBook(String bookName) throws ModelException {
        Book book = Library.getInstance().getBookStream()
                .filter(b -> b.getBookName().equals(bookName))
                .findFirst()
                .orElse(null);
        boolean flag = false;
        if (book != null) {
            flag = Library.getInstance().removeBook(book);
        } else {
            throw new ModelException();
        }
        return flag;
    }

    @Override
    public List<Book> findAll() throws ModelException {
        List<Book> books = new ArrayList<>();
        List<Book> daoBooks = Library.getInstance().getBooks();
        if(daoBooks == null) {
            throw new ModelException();
        }
        for(Book book: daoBooks) {
            if(book == null) {
                throw new ModelException();
            }
            books.add(book);
        }
        return books;
    }

    //todo singleton + extra functionality(search for books with pages from x to n) etc.

    //todo think about extra stuff to add? just for fun

    //todo create id in special class method that gives access to info
    //todo enum for tags(both search and sort use one); add and remove in library other in dao; check command pattern

    /*@Override
    public List<Book> findByTag(Library storage, SortSearchType tagType, String tag) {
        List<Book> res = new ArrayList<>();
        switch (tagType) {
            case BY_ID:
                storage.getBookStream().filter(b -> b.getId().equals(UUID.fromString(tag))).forEach(res::add);
                break;
            case BY_NAME:
                storage.getBookStream().filter(b -> b.getBookName().equals(tag)).forEach(res::add);
                break;
            case BY_AUTHOR:
                storage.getBookStream().filter(b -> b.getAuthors().contains(tag)).forEach(res::add);
                break;
            case BY_PUBLISHER:
                storage.getBookStream().filter(b -> b.getPublisher().equals(tag)).forEach(res::add);
                break;
            case BY_PUBLISH_YEAR:
                storage.getBookStream().filter(b -> b.getPublishYear() == Integer.parseInt(tag)).forEach(res::add);
                break;
            case NONE:
                break;
            default:
        }
        return res;
    }*/ //todo do I need it?
}
