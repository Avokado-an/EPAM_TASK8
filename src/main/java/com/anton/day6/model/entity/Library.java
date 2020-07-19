package com.anton.day6.model.entity;

import com.anton.day6.model.exception.ModelException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Library {
    private static Library instance;
    private static int MAX_CAPACITY = 1000;
    private List<Book> books;

    private Library() {
        books = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void setBooks(List<Book> books) {
        if (books.size() < MAX_CAPACITY) {
            this.books = books;
        }
    }

    public void addBook(Book book) throws ModelException {
        if (MAX_CAPACITY > books.size() && !books.contains(book)) {
            books.add(book);
        } else {
            throw new ModelException();
        }
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public Stream<Book> getBookStream() {
        return books.stream();
    }

    @Override
    public String toString() {
        return String.format("Storage of books:%n%s", booksToString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Library)) {
            return false;
        }
        Library library = (Library) o;
        if (getBooks().size() != library.getBooks().size()) {
            return false;
        }
        return getBooks().containsAll(library.getBooks());
    }

    @Override
    public int hashCode() {
        return 31 + (int) books.stream().filter(b -> b != null).mapToInt(b -> b.hashCode()).count();
    }

    private String booksToString() {
        String space = " ";
        StringBuilder allBooks = new StringBuilder();
        for (Book book : books) {
            if (book != null) {
                allBooks.append(book.toString()).append(space);
            }
            allBooks.append("\n");
        }
        return allBooks.toString();
    }
}
