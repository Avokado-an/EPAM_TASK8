package com.anton.day6.model.dao.requests;

public class SqlRequests {
    public static final String ADD = "INSERT book(book_name, publish_year, publisher, authors) VALUES (?, ?, ?, ?)";
    public static final String REMOVE = "DELETE FROM book WHERE book_name = ?";
    public static final String FIND_ALL_BOOKS = "SELECT book_name, publish_year, publisher, authors FROM book";
}
