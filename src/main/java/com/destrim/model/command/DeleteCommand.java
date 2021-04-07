package com.destrim.model.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCommand extends Command {

    private long id;

    public static DeleteCommand fromInput(String command) {
        return new DeleteCommand(command);
    }

    private DeleteCommand(String command) {
        this.parse(command);
    }

    void parseArg(String argument) {
        String param = extractParam(argument);

        if (isParamId(param)) {
            handleIdParam(argument);
        } else {
            throw new IllegalArgumentException("Wrong argument.");
        }
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
