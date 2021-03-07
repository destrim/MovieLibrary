package com.destrim.model.command;

import java.util.Arrays;

abstract class Command {

    void parse(String command) {
        String[] arguments = extractArgs(command);

        for (String argument : arguments) {
            parseArg(argument);
        }
    }

    String extractParam(String argument) {
        String[] argParts = argument.split(" ");
        return argParts[0];
    }

    String[] extractArgs(String command) {
        String[] commandParts = command.split("-");
        return Arrays.copyOfRange(commandParts, 1, commandParts.length);
    }

    abstract void parseArg(String argument);
}
