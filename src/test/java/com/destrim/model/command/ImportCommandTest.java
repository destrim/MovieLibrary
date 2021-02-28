package com.destrim.model.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImportCommandTest {

    @Test
    public void ifArgCorrect_ShouldParseCommand() {
        /* Given */
        String command = "import -f movieDatabase";

        /* When */
        ImportCommand importCommand = ImportCommand.fromInput(command);

        /* Then */
        assertEquals("movieDatabase", importCommand.getFileName());
    }

    @Test
    public void ifIdNotExists_ShouldThrowException() {
        /* Given */
        String command = "import -i";

        /* When */

        /* Then */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            DeleteCommand deleteCommand = DeleteCommand.fromInput(command);
        });
    }
}