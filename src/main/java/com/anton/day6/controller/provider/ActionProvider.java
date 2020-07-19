package com.anton.day6.controller.provider;

import com.anton.day6.controller.command.Command;
import com.anton.day6.controller.command.impl.FailedCommand;
import com.anton.day6.controller.exception.ControllerException;
import com.anton.day6.controller.type.CommandType;

public class ActionProvider {
    private ActionProvider() {
    }

    public static Command provideAction(String action) {
        Command command;
        try {
            if (action == null) {
                throw new ControllerException("No action provided");
            }
            command = CommandType.valueOf(action.toUpperCase()).getCommand();
        } catch (IllegalArgumentException | ControllerException e) {
            command = new FailedCommand();
        }
        return command;
    }
}
