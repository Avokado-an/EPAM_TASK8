package com.anton.day8.helper.comparator;

import com.anton.day8.model.entity.Book;

import java.util.Comparator;

public class BookYearComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getPublishYear() - o2.getPublishYear();
    }
}
