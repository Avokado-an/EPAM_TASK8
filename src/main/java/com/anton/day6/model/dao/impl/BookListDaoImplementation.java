package com.anton.day6.model.dao.impl;

import com.anton.day6.model.creator.BookCreator;
import com.anton.day6.model.creator.ConnectionCreator;
import com.anton.day6.model.dao.BookListDao;
import com.anton.day6.model.dao.requests.SqlRequests;
import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.DaoException;
import com.anton.day6.model.dao.requests.type.TagType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookListDaoImplementation implements BookListDao {
    private static BookListDaoImplementation instance;

    //todo use prepared statements for potentially problematic requests(google preparedStatements)
    //todo check sql injection attack
    //todo check loggs
    //todo check connection pool and dataSource
    private BookListDaoImplementation() {
    }

    public static BookListDaoImplementation getInstance() {
        if (instance == null) {
            instance = new BookListDaoImplementation();
        }
        return instance;
    }

    @Override
    public void addBook(Book book) throws DaoException {
        try (Connection connection = ConnectionCreator.provideConnection();
             PreparedStatement statement = connection.prepareStatement(SqlRequests.ADD)) {
            statement.setString(1, book.getBookName());
            statement.setInt(2, book.getPublishYear());
            statement.setString(3, book.getPublisher());
            statement.setString(4, book.authorsToString());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public void removeBook(String bookName) throws DaoException {
        try (Connection connection = ConnectionCreator.provideConnection();
             PreparedStatement statement = connection.prepareStatement(SqlRequests.REMOVE)) {
            statement.setString(1, bookName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Book> findBooks(TagType tagType, String tag) throws DaoException {
        List<Book> books;
        try (Connection connection = ConnectionCreator.provideConnection()) {
            books = provideResultSetForTag(connection, tag, tagType);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return books;
    }

    @Override
    public List<Book> findAllBooks() throws DaoException {
        List<Book> books;
        try (Connection connection = ConnectionCreator.provideConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlRequests.FIND_ALL_BOOKS);
            books = readBookInfo(resultSet);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return books;
    }

    private List<Book> readBookInfo(ResultSet resultSet) throws SQLException, DaoException {
        BookCreator creator = new BookCreator();
        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            String bookName = resultSet.getString(1);
            int publishYear = resultSet.getInt(2);
            String publisher = resultSet.getString(3);
            String authors = resultSet.getString(4);
            books.add(creator.createBook(publishYear, bookName, publisher, authors));
        }
        return books;
    }

    private List<Book> provideResultSetForTag(
            Connection connection, String tag, TagType tagType) throws SQLException, DaoException {
        ResultSet resultSet = null;
        List<Book> books;
        if (tag.isEmpty()) {
            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(SqlRequests.FIND_ALL_BOOKS + tagType.getTagResponse());
                books = readBookInfo(resultSet);
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
            }
        } else {
            try (PreparedStatement statement = connection.prepareStatement(
                    SqlRequests.FIND_ALL_BOOKS + tagType.getTagResponse())) {
                if (tagType == TagType.FIND_BY_AUTHOR || tagType == TagType.FIND_BY_PUBLISHER ||
                        tagType == TagType.FIND_BY_NAME) {
                    statement.setString(1, tag);
                } else {
                    statement.setInt(1, Integer.parseInt(tag));
                }
                resultSet = statement.executeQuery();
                books = readBookInfo(resultSet);
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
            }
        }
        return books;
    }
}
