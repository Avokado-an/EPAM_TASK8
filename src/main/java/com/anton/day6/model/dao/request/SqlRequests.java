package com.anton.day6.model.dao.request;

public class SqlRequests {
    public static final String ADD_BOOK = "INSERT book(book_name, publish_year, publisher, authors) VALUES (?, ?, ?, ?)";
    public static final String REMOVE_BOOK_BY_NAME = "DELETE FROM book WHERE book_name = ?";
    public static final String FIND_ALL_BOOKS = "SELECT book_name, publish_year, publisher, authors FROM book";
}
