public class WaddlesException extends Exception {
    public WaddlesException(String command, String message) {
        super(String.format("Failed to run \"%s\" command: %s", command, message));
    }

    public static class InvalidCommand extends WaddlesException {
        public InvalidCommand(String command) {
            super(command, "Invalid command.");
        }
    }

    public static class MissingArgument extends WaddlesException {
        public MissingArgument(String command, String argument) {
            super(command, String.format("Missing argument %s.", argument));
        }
    }

    public static class InvalidArgument extends WaddlesException {
        public InvalidArgument(String command, String argument, String reason) {
            super(command, String.format("Argument \"%s\" is invalid (%s).", argument, reason));
        }
    }
}
