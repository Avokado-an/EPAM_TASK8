package com.anton.day6.controller.data;

import com.anton.day6.model.entity.Book;
import com.anton.day6.model.entity.Library;
import com.anton.day6.model.exception.ModelException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CommandExecutionData {
    public static List<Book> provideBooks() throws ModelException {
        Book book1 = new Book(1900, "Book1", "Publisher1", Arrays.asList("Author1", "Author11"));
        Book book2 = new Book(1500, "Book2", "Publisher2", Arrays.asList("Author2", "Author21"));
        Book book3 = new Book(2000, "Book3", "Publisher3", Arrays.asList("Author3", "Author31"));
        Book book4 = new Book(2005, "Book4", "Publisher4", Arrays.asList("Author4", "Author41"));
        Book book5 = new Book(2005, "Book5", "Publisher5", Arrays.asList("Author5", "Author51"));
        Book book6 = new Book(1950, "Book6", "Publisher6", Arrays.asList("Author6", "Author61"));
        Library.getInstance().addBook(book1);
        Library.getInstance().addBook(book2);
        Library.getInstance().addBook(book3);
        Library.getInstance().addBook(book4);
        Library.getInstance().addBook(book5);
        Library.getInstance().addBook(book6);
        return Library.getInstance().getBooks();
    }

    //public static Map<String, String> provideRequest() {

    //}

    public static List<String> provideCommand() {
        List<String> commands = Arrays.asList("add", "remove", "find by name", "find by year", "find by publisher",
                "find by authors", "sort by name", "sort by year", "sort by publisher", "sort by authors");
        return commands;
    }
}
