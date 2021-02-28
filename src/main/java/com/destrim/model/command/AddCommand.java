package com.destrim.model.command;

import com.destrim.util.VariousUtils;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

// TODO inheritance in all command package
@Data
public class AddCommand {

    private String title;
    private String year;

    public static AddCommand fromInput(String command) {
        return new AddCommand(command);
    }

    private AddCommand(String command) {
        this.parse(command);
    }

    private void parse(String command) {
        String[] arguments = extractArgs(command);

        for (String argument : arguments) {
            parseArg(argument);
        }
    }

    private String[] extractArgs(String command) {
        int count = StringUtils.countMatches(command, "\"");
        if (count % 2 == 1) {
            throw new IllegalArgumentException("Wrong quotes.");
        }

        String[] commandParts = VariousUtils.splitIgnoreInQuotes(command, '-');
        return Arrays.copyOfRange(commandParts, 1, commandParts.length);
    }

    private void parseArg(String argument) {
        String param = extractParam(argument);

        if (isParamTitle(param)) {
            handleTitleParam(argument);
        } else if (isParamYear(param)) {
            handleYearParam(argument);
        } else {
            throw new IllegalArgumentException("Wrong argument.");
        }
    }

    private String extractParam(String argument) {
        String[] argParts = argument.split(" ");
        return argParts[0];
    }

    private boolean isParamTitle(String param) {
        return param.equalsIgnoreCase("t");
    }

    private boolean isParamYear(String param) {
        return param.equalsIgnoreCase("y");
    }

    private void handleTitleParam(String argument) {
        String[] argParts = argument.split("\"");

        checkIfTitleArgCorrect(argParts);
        title = argParts[1];
    }

    private void handleYearParam(String argument) {
        String[] argParts = argument.split(" ");

        checkIfYearArgCorrect(argParts);
        year = argParts[1];
    }

    private void checkIfTitleArgCorrect(String[] argParts) {
        if (argParts.length != 3 || argParts[1].isBlank()) {
            throw new IllegalArgumentException("Wrong command.");
        }
    }

    private void checkIfYearArgCorrect(String[] argParts) {
        if (argParts.length <= 1 || !argParts[1].matches("\\d{4}")) {
            throw new IllegalArgumentException("Wrong command.");
        }
    }
}
