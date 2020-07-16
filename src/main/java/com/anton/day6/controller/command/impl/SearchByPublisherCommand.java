package com.anton.day6.controller.command.impl;

import com.anton.day6.controller.command.Command;
import com.anton.day6.controller.exception.ControllerException;
import com.anton.day6.controller.parametersProvider.Parameters;
import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.ModelException;
import com.anton.day6.model.service.impl.LibraryServiceImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchByPublisherCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> request) {
        Map<String, List<Book>> response = new HashMap<>();
        try {
            String publisher = request.get(Parameters.PUBLISHER);
            List<Book> books = LibraryServiceImplementation.getInstance().findBooksByPublisher(publisher);
            StringBuilder message = new StringBuilder();
            message.append(Parameters.FIND).append(Parameters.PUBLISHER).append(": ").append(publisher);
            response.put(message.toString(), books);
        } catch (ModelException ex) {
            response.put(Parameters.OPERATION_FAILED, new ArrayList<>());
        }
        return response;
    }
}
