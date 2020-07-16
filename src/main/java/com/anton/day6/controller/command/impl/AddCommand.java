package com.anton.day6.controller.command.impl;

import com.anton.day6.controller.command.Command;
import com.anton.day6.controller.parametersProvider.Parameters;
import com.anton.day6.controller.exception.ControllerException;
import com.anton.day6.model.creator.BookCreator;
import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.ModelException;
import com.anton.day6.model.service.impl.LibraryServiceImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> request) {
        Map<String, List<Book>> response = new HashMap<>();
        BookCreator creator = new BookCreator();
        try {
            Book newBook = creator.createBook(
                    Parameters.PUBLISH_YEAR, Parameters.NAME, Parameters.PUBLISHER, Parameters.AUTHORS);
            LibraryServiceImplementation.getInstance().addBook(newBook);
            response.put(Parameters.OPERATION_SUCCEED, LibraryServiceImplementation.getInstance().findAllBooks());
        } catch (ModelException ex) {
            response.put(Parameters.OPERATION_FAILED, new ArrayList<>());
        }
        return response;
    }
}
