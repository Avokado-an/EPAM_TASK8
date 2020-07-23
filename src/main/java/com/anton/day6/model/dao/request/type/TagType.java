package com.anton.day6.model.dao.request.type;

public enum TagType {
    FIND_BY_NAME(" WHERE book_name = ?"),
    FIND_BY_AUTHOR(" WHERE authors = ?"),//todo change to find one of authors
    FIND_BY_PUBLISHER(" WHERE publisher = ?"),
    FIND_BY_YEAR(" WHERE publish_year = ?"),
    SORT_BY_YEAR(" ORDER BY publish_year"),
    SORT_BY_NAME(" ORDER BY book_name"),
    SORT_BY_AUTHOR(" ORDER BY authors"),
    SORT_BY_PUBLISHER(" ORDER BY publisher");

    private String tagResponse;

    TagType(String tagResponse) {
        this.tagResponse = tagResponse;
    }

    public String getTagResponse() {
        return tagResponse;
    }
}
