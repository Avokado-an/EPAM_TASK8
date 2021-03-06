package com.anton.day8.controller.command.impl;

import com.anton.day8.controller.command.Command;
import com.anton.day8.controller.responce.ResponseParameters;
import com.anton.day8.model.entity.Book;
import com.anton.day8.model.exception.ServiceException;
import com.anton.day8.model.service.impl.LibraryServiceImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchByYearCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> request) {
        Map<String, List<Book>> response = new HashMap<>();
        try {
            String year = request.get(ResponseParameters.PUBLISH_YEAR);
            List<Book> books = LibraryServiceImplementation.getInstance().findBooksByPublishYear(year);
            response.put(ResponseParameters.OPERATION_SUCCEED, books);
        } catch (ServiceException ex) {
            response.put(ResponseParameters.OPERATION_FAILED, new ArrayList<>());
        }
        return response;
    }
}
