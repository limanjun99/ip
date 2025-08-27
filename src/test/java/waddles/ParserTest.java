package waddles;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void readArgument_hasArgument_success() throws WaddlesException {
        Parser parser = new Parser("command argument /key value");
        String argument = parser.readArgument("argument", " /key ");
        assertEquals("argument", argument);
        String value = parser.readArgument("value", "");
        assertEquals("value", value);
    }

    @Test
    public void readArgument_missingArgument_throwsException() {
        Parser parser = new Parser("command");
        try {
            parser.readArgument("argument", " /key ");
            fail();
        } catch (WaddlesException e) {
            assertEquals("Missing argument argument.", e.getMessage());
        }
    }

    @Test
    public void readIntegerArgument_validInteger_success() throws WaddlesException {
        Parser parser = new Parser("command 12345");
        int value = parser.readIntegerArgument("argument", "");
        assertEquals(12345, value);
    }

    @Test
    public void readIntegerArgument_invalidInteger_throwsException() {
        Parser parser = new Parser("command not_integer");
        try {
            parser.readIntegerArgument("integer", "");
            fail();
        } catch (WaddlesException e) {
            String expectedError = "Argument \"integer\" is invalid (expected integer, got not_integer)";
            assertEquals(expectedError, e.getMessage());
        }
    }

    @Test
    public void readDateTimeArgument_valid_success() throws WaddlesException {
        Parser parser = new Parser("command 2025-10-10 23:30");
        LocalDateTime dateTime = parser.readDateTimeArgument("date", "");
        assertEquals(LocalDateTime.of(2025, 10, 10, 23, 30), dateTime);
    }

    @Test
    public void readDateTimeArgument_invalidDateTime_throwsException() {
        Parser parser = new Parser("command 2025-30-30 23:30");
        try {
            parser.readDateTimeArgument("date", "");
            fail();
        } catch (WaddlesException e) {
            String expectedError =
                    "Argument \"date\" is invalid (expected a date+time in format yyyy-MM-dd HH:mm, " + "got " +
                            "2025-30-30 23:30)";
            assertEquals(expectedError, e.getMessage());
        }
    }
}
