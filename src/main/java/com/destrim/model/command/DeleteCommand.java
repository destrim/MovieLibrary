package com.destrim.model.command;

import lombok.Data;

import java.util.Arrays;

@Data
public class DeleteCommand {

    private long id;

    public static DeleteCommand fromInput(String command) {
        return new DeleteCommand(command);
    }

    private DeleteCommand(String command) {
        this.parse(command);
    }

    private void parse(String command) {
        String[] arguments = extractArgs(command);

        for (String argument : arguments) {
            parseArg(argument);
        }
    }

    private String[] extractArgs(String command) {
        String[] commandParts = command.split("-");
        return Arrays.copyOfRange(commandParts, 1, commandParts.length);
    }

    private void parseArg(String argument) {
        String param = extractParam(argument);

        if (isParamId(param)) {
            handleIdParam(argument);
        } else {
            throw new IllegalArgumentException("Wrong argument.");
        }
    }

    private String extractParam(String argument) {
        String[] argParts = argument.split(" ");
        return argParts[0];
    }

    private boolean isParamId(String param) {
        return param.equalsIgnoreCase("i");
    }

    private void handleIdParam(String argument) {
        String[] argParts = argument.split(" ");

        checkIfIdArgCorrect(argParts);
        id = Long.parseLong(argParts[1]);
    }

    private void checkIfIdArgCorrect(String[] argParts) {
        if (argParts.length <= 1 || !argParts[1].matches("\\d+")) {
            throw new IllegalArgumentException("Wrong command.");
        }
    }
}
