package waddles;

/**
 * Enum of all possible commands handled by Waddles.
 * Note that {@code Command.INVALID} represents an unknown command,
 * with the given command string stored in the {@code name} field.
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    INVALID("invalid"),
    ;

    /**
     * Parses a string and returns the command it corresponds to.
     * If the string is not a valid command, then {@code Command.INVALID} is returned.
     */
    public static Command fromString(String s) {
        for (Command command : Command.values()) {
            if (!command.equals(INVALID) && command.toString().equals(s)) {
                return command;
            }
        }
        Command invalid = INVALID;
        invalid.name = s;
        return invalid;
    }

    @Override
    public String toString() {
        return this.name;
    }

    Command(String name) {
        this.name = name;
    }

    private String name;
}
