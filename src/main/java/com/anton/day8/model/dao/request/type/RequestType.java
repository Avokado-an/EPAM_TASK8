package com.anton.day8.model.dao.request.type;

public enum RequestType {
    FIND_BY_NAME(" WHERE book_name = ?"),
    FIND_BY_AUTHOR(" WHERE authors = ?"),
    FIND_BY_PUBLISHER(" WHERE publisher = ?"),
    FIND_BY_YEAR(" WHERE publish_year = ?"),
    ORDER_BY_YEAR(" ORDER BY publish_year"),
    ORDER_BY_NAME(" ORDER BY book_name"),
    ORDER_BY_AUTHOR(" ORDER BY authors"),
    ORDER_BY_PUBLISHER(" ORDER BY publisher");

    private String tagResponse;

    RequestType(String tagResponse) {
        this.tagResponse = tagResponse;
    }

    public String getTagResponse() {
        return tagResponse;
    }
}
