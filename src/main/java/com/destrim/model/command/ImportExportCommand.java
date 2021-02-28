package com.destrim.model.command;

import lombok.Data;

import java.util.Arrays;

@Data
public class ImportExportCommand {

    private String fileName;

    public static ImportExportCommand fromInput(String command) {
        return new ImportExportCommand(command);
    }

    private ImportExportCommand(String command) {
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

        if (isParamFileName(param)) {
            handleFileNameParam(argument);
        } else {
            throw new IllegalArgumentException("Wrong argument.");
        }
    }

    private String extractParam(String argument) {
        String[] argParts = argument.split(" ");
        return argParts[0];
    }

    private boolean isParamFileName(String param) {
        return param.equalsIgnoreCase("f");
    }

    private void handleFileNameParam(String argument) {
        String[] argParts = argument.split(" ");

        checkIfFileNameArgCorrect(argParts);
        fileName = argParts[1];
    }

    private void checkIfFileNameArgCorrect(String[] argParts) {
        if (argParts.length <= 1 || argParts[1].isBlank()) {
            throw new IllegalArgumentException("Wrong commnad.");
        }
    }
}
