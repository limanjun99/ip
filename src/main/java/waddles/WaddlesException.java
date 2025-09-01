package waddles;

/**
 * Base class for all possible exceptions that Waddles can throw.
 */
public class WaddlesException extends Exception {
    public WaddlesException(String message) {
        super(message);
    }

    /**
     * Thrown when the user types an unrecognized command.
     */
    public static class InvalidCommand extends WaddlesException {
        public InvalidCommand(String command) {
            super(String.format("Invalid command: \"%s\".", command));
        }
    }

    /**
     * Thrown when the user executes a command, but is missing an argument for that command.
     */
    public static class MissingArgument extends WaddlesException {
        public MissingArgument(String argument) {
            super(String.format("Missing argument %s.", argument));
        }
    }

    /**
     * Thrown when the user executes a command and supplied an argument, but the argument is invalid.
     */
    public static class InvalidArgument extends WaddlesException {
        public InvalidArgument(String argument, String reason) {
            super(String.format("Argument \"%s\" is invalid (%s)", argument, reason));
        }
    }

    /**
     * Thrown when Waddles needs to read/write/open a file, but encountered an error.
     */
    public static class FileError extends WaddlesException {
        public FileError(String message) {
            super(message);
        }
    }
}
