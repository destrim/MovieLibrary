package com.destrim.model.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileCommand extends Command {

    private String fileName;

    public static FileCommand fromInput(String command) {
        return new FileCommand(command);
    }

    private FileCommand(String command) {
        this.parse(command);
    }

    void parseArg(String argument) {
        String param = extractParam(argument);

        if (isParamFileName(param)) {
            handleFileNameParam(argument);
        } else {
            throw new IllegalArgumentException("Wrong argument.");
        }
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
