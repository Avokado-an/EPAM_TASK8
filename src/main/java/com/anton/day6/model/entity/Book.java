package com.anton.day6.model.entity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Book {
    private UUID id;
    private int publishYear;
    private String bookName;
    private String publisher;
    private List<String> authors;

    static class IdGenerator {
        public UUID generateId() {
            return UUID.randomUUID();
        }
    }

    public Book() {
        id = new IdGenerator().generateId();
    }

    public Book(int publishYear, String bookName, String publisher, List<String> authors) {
        id = new IdGenerator().generateId();
        this.publishYear = publishYear;
        this.bookName = bookName;
        this.publisher = publisher;
        this.authors = authors;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        if (book.getAuthors().size() != getAuthors().size()) {
            return false;
        }
        if (!getAuthors().containsAll(book.getAuthors())) {
            return false;
        }
        return getPublishYear() == book.getPublishYear() &&
                getBookName().equals(book.getBookName()) &&
                getPublisher().equals(book.getPublisher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPublishYear(), getBookName(), getPublisher(), getAuthors());
    }

    @Override
    public String toString() {
        return String.format("%s(%d) published by %s, written by %s",
                bookName, publishYear, publisher, authorsToString());
    }

    private String authorsToString() {
        String space = " ";
        StringBuilder allAuthors = new StringBuilder();
        for (String author : authors) {
            if (author != null) {
                allAuthors.append(author).append(space);
            }
        }
        return allAuthors.toString();
    }
}
