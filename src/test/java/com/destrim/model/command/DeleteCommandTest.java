package com.destrim.model.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCommandTest {

    @Test
    public void ifArgCorrect_ShouldParseCommand() {
        /* Given */
        String command = "delete -i 24";

        /* When */
        DeleteCommand deleteCommand = DeleteCommand.fromInput(command);

        /* Then */
        assertEquals(24, deleteCommand.getId());
    }

    @Test
    public void ifIdNotExists_ShouldThrowException() {
        /* Given */
        String command = "delete -i";

        /* When */

        /* Then */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            DeleteCommand deleteCommand = DeleteCommand.fromInput(command);
        });
    }

    @Test
    public void ifIdNotCorrect_ShouldThrowException() {
        /* Given */
        String command = "delete -i abcd";

        /* When */

        /* Then */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            DeleteCommand deleteCommand = DeleteCommand.fromInput(command);
        });
    }
}