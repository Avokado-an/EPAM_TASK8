package com.anton.day6.controller.command.impl;

import com.anton.day6.controller.command.Command;
import com.anton.day6.controller.responce.ResponseParameters;
import com.anton.day6.model.entity.Book;
import com.anton.day6.model.exception.ModelException;
import com.anton.day6.model.service.impl.LibraryServiceImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> request) {
        Map<String, List<Book>> response = new HashMap<>();
        try {
            String name = request.get(ResponseParameters.NAME);
            LibraryServiceImplementation.getInstance().removeBook(name);
            response.put(ResponseParameters.OPERATION_SUCCEED, LibraryServiceImplementation.getInstance().findAllBooks());
        } catch (ModelException ex) {
            response.put(ResponseParameters.OPERATION_FAILED, new ArrayList<>());
        }
        return response;
    }
}
