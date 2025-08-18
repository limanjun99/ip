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

    private Command(String name) {
        this.name = name;
    }

    private String name;
}
