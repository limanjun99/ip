/**
 * Helps to parse the user input into a command.
 * The expected format looks like this:
 * {@code <command> <arg> /<key1> <value1> /<key2> <value2>}
 *
 * <br>
 * E.g.:
 * {@code event project meeting /from 1pm /to 3pm}
 */
public class Parser {
    public Parser(String input) {
        this.input = input;
        this.command = input.isEmpty() ? "" : input.split(" ", 2)[0];
        this.consumed = this.command.length() + 1;  // +1 to consume the space after the command.
    }

    public String getCommand() {
        return command;
    }

    /**
     * Reads an argument until the given delimiter.
     *
     * @param argument  Describes the current argument being read.
     * @param delimiter Delimiter to read until (empty if reading until the end).
     */
    public String readArgument(String argument, String delimiter) {
        int argumentEnd = delimiter.isEmpty() ? input.length() : input.indexOf(delimiter, consumed);
        String argumentValue = input.substring(consumed, argumentEnd);
        consumed = argumentEnd + delimiter.length();
        return argumentValue;
    }

    /**
     * Reads an integer argument until the given delimiter.
     *
     * @param argument  Describes the current argument being read.
     * @param delimiter Delimiter to read until (empty if reading until the end).
     */
    public int readIntegerArgument(String argument, String delimiter) {
        String rawArgument = readArgument(argument, delimiter);
        return Integer.parseInt(rawArgument);
    }

    private final String input;
    private final String command;
    private int consumed;
}
