package waddles;

public class WaddlesException extends Exception {
    public WaddlesException(String message) {
        super(message);
    }

    public static class InvalidCommand extends WaddlesException {
        public InvalidCommand(String command) {
            super(String.format("Invalid command: \"%s\".", command));
        }
    }

    public static class MissingArgument extends WaddlesException {
        public MissingArgument(String argument) {
            super(String.format("Missing argument %s.", argument));
        }
    }

    public static class InvalidArgument extends WaddlesException {
        public InvalidArgument(String argument, String reason) {
            super(String.format("Argument \"%s\" is invalid (%s)", argument, reason));
        }
    }

    public static class FileError extends WaddlesException {
        public FileError(String message) {
            super(message);
        }
    }
}
