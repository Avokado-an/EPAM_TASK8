package com.anton.day8.controller.command.impl;

import com.anton.day8.controller.command.Command;
import com.anton.day8.controller.responce.ResponseParameters;
import com.anton.day8.model.entity.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FailedCommand implements Command {
    @Override
    public Map<String, List<Book>> execute(Map<String, String> request) {
        Map<String, List<Book>> response = new HashMap<>();
        response.put(ResponseParameters.OPERATION_FAILED, new ArrayList<>());
        return response;
    }
}
