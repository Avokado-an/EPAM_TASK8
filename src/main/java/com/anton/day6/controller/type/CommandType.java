package com.anton.day6.controller.type;

import com.anton.day6.controller.command.Command;
import com.anton.day6.controller.command.impl.*;

public enum CommandType {
    ADD(new AddCommand()),
    REMOVE(new RemoveCommand()),
    FIND_BY_AUTHORS(new SearchByAuthorCommand()),
    FIND_BY_NAME(new SearchByNameCommand()),
    FIND_BY_PUBLISH_YEAR(new SearchByYearCommand()),
    FIND_BY_PUBLISHER(new SearchByPublisherCommand()),
    SORT_BY_PUBLISH_YEAR(new SortByYearCommand()),
    SORT_BY_NAME(new SortByNameCommand()),
    SORT_BY_PUBLISHER(new SortByPublisherCommand()),
    SORT_BY_AUTHORS(new SortByAuthorCommand()),
    VIEW_ALL(new ViewCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
