package com.destrim.model.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    @Test
    public void ifArgsCorrect_ShouldParseCommand() {
        /* Given */
        String command = "add -t \"Legend\" -y 2015";

        /* When */
        AddCommand addCommand = AddCommand.fromInput(command);

        /* Then */
        assertEquals("Legend", addCommand.getTitle());
        assertEquals("2015", addCommand.getYear());
    }

    @Test
    public void ifTitleNotExists_ShouldThrowException() {
        /* Given */
        String command = "add -t -y 2015";

        /* When */

        /* Then */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AddCommand addCommand = AddCommand.fromInput(command);
        });
    }

    @Test
    public void ifTitleHasNoQuotes_ShouldNotParseCommand() {
        /* Given */
        String command = "add -t Legend -y 2015";

        /* When */

        /* Then */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AddCommand addCommand = AddCommand.fromInput(command);
        });
    }

    @Test
    public void ifTitleHasTwoQuoteAtBeginning_ShouldNotParseCommand() {
        /* Given */
        String command = "add -t \"\"Legend -y 2015";

        /* When */

        /* Then */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AddCommand addCommand = AddCommand.fromInput(command);
        });
    }

    @Test
    public void ifTitleHasOneQuoteAtBeginning_ShouldNotParseCommand() {
        /* Given */
        String command = "add -t \"Legend -y 2015";

        /* When */

        /* Then */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AddCommand addCommand = AddCommand.fromInput(command);
        });
    }

    @Test
    public void ifTitleHasTwoQuoteAtEnd_ShouldNotParseCommand() {
        /* Given */
        String command = "add -t Legend\"\" -y 2015";

        /* When */

        /* Then */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AddCommand addCommand = AddCommand.fromInput(command);
        });
    }

    @Test
    public void ifTitleHasOneQuoteAtEnd_ShouldNotParseCommand() {
        /* Given */
        String command = "add -t Legend\" -y 2015";

        /* When */

        /* Then */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AddCommand addCommand = AddCommand.fromInput(command);
        });
    }

    @Test
    public void ifTitleHasDash_ShouldNotParseCommand() {
        /* Given */
        String command = "add -t \"Legend-zelda\" -y 2015";

        /* When */
        AddCommand addCommand = AddCommand.fromInput(command);

        /* Then */
        assertEquals("Legend-zelda", addCommand.getTitle());
        assertEquals("2015", addCommand.getYear());
    }

    @Test
    public void ifYearNotExists_ShouldNotParseCommand() {
        /* Given */
        String command = "add -t \"Legend\" -y";

        /* When */

        /* Then */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AddCommand addCommand = AddCommand.fromInput(command);
        });
    }

    @Test
    public void ifYearNotCorrect_ShouldNotParseCommand() {
        /* Given */
        String command = "add -t \"Legend\" -y abcd";

        /* When */

        /* Then */
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AddCommand addCommand = AddCommand.fromInput(command);
        });
    }
}