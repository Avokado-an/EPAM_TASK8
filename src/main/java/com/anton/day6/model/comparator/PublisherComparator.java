package com.anton.day6.model.comparator;

import com.anton.day6.model.entity.Book;

import java.util.Comparator;

public class PublisherComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getPublisher().compareTo(o2.getPublisher());
    }
}
