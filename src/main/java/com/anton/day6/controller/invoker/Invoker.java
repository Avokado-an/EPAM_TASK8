package com.anton.day6.controller.invoker;

import com.anton.day6.controller.command.Command;
import com.anton.day6.controller.provider.ActionProvider;
import com.anton.day6.model.entity.Book;

import java.util.List;
import java.util.Map;

public class Invoker {
    private static Invoker instance;

    private Invoker() {

    }

    public static Invoker getInstance() {
        if (instance == null) {
            instance = new Invoker();
        }
        return instance;
    }

    public Map<String, List<Book>> doRequest(String action, Map<String, String> request) {
        Command command = ActionProvider.provideAction(action);
        return command.execute(request);
    }
}
