package com.destrim.model.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowCommand extends Command {

    private String sortBy;

    public static ShowCommand fromInput(String command) {
        return new ShowCommand(command);
    }

    private ShowCommand(String command) {
        this.parse(command);
    }

    void parse(String command) {
        if (command.equals("show")) {
            handleWithoutParam();
        } else {
            String[] arguments = extractArgs(command);

            for (String argument : arguments) {
                parseArg(argument);
            }
        }
    }

    void parseArg(String argument) {
        String param = extractParam(argument);

        if (isParamSortBy(param)) {
            handleSortByParam(argument);
        } else {
            throw new IllegalArgumentException("Wrong argument.");
        }
    }

    private boolean isParamSortBy(String param) {
        return param.equalsIgnoreCase("b");
    }

    private void handleWithoutParam() {
        sortBy = "";
    }

    private void handleSortByParam(String argument) {
        String[] argParts = argument.split(" ");

        checkIfSortByArgCorrect(argParts);

        switch (argParts[1]) {
            case "t" -> sortBy = "title";
            case "y" -> sortBy = "year";
            case "r" -> sortBy = "imdbRating";
        }

    }

    private void checkIfSortByArgCorrect(String[] argParts) {
        if (argParts.length <= 1 || !argParts[1].matches("[tyr]")) {
            throw new IllegalArgumentException("Wrong command.");
        }
    }
}
