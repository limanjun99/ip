package waddles;

/**
 * Represents the result of processing a user command.
 */
public class WaddlesResult {
    private final String message;
    private final boolean isEnd;
    private final boolean isError;

    WaddlesResult(String message, boolean isEnd, boolean isError) {
        this.message = message;
        this.isEnd = isEnd;
        this.isError = isError;
    }

    /**
     * Constructs a response to a successfully executed user command.
     */
    public static WaddlesResult makeSuccess(String message) {
        return new WaddlesResult(message, false, false);
    }

    /**
     * Constructs a response to a user command that failed to execute.
     */
    public static WaddlesResult makeError(String message) {
        return new WaddlesResult(message, false, true);
    }

    /**
     * Constructs a response to executing the "bye" user command.
     */
    public static WaddlesResult makeEnd(String message) {
        return new WaddlesResult(message, true, false);
    }

    public boolean hasEnded() {
        return isEnd;
    }

    public String getMessage() {
        return message;
    }
}
