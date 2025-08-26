import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public abstract class Task {
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public static final String inputDateTimeFormat = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern(inputDateTimeFormat);
    public static final String outputDateTimeFormat = "MMM dd yyyy HH:mm";
    public static final DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern(outputDateTimeFormat);

    /**
     * Marks this task as done / undone.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Splits the given serialization of some Task into its fields.
     * E.g. <code>"isDone | description"</code> becomes <code>[isDone, description]</code>.
     */
    public static String[] splitSerialized(String serialized) {
        return Arrays.stream(serialized.split("\\|")).map(String::strip).toArray(String[]::new);
    }

    /**
     * Returns a serialization of this task.
     * Serialization format: <code>isDone | description</code>.
     */
    public String toSerializedString() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        char doneMarker = isDone ? 'X' : ' ';
        return String.format("[%c] %s", doneMarker, description);
    }

    private final String description;
    private boolean isDone;
}
