package com.anton.day6.model.comparator;

import com.anton.day6.model.entity.Book;

import java.util.Comparator;

public class AuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        Comparator<Book> authorComparator = new ArraySizeComparator().thenComparing(new AuthorsStringComparator());
        return authorComparator.compare(o1, o2);
    }

    private static class ArraySizeComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getAuthors().size() - o2.getAuthors().size();
        }
    }

    private static class AuthorsStringComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getAuthors().toString().compareTo(o2.getAuthors().toString());
        }
    }
}
