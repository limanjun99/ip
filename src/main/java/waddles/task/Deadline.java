package waddles.task;

import java.time.LocalDateTime;

/**
 * Deadlines are like Todos, with the addition that they must be completed by a certain time.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a new Deadline object.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a Deadline from the serialized string.
     * WARNING: Assumes that the given string is a serialization of some Deadline.
     */
    public static Deadline fromSerializedString(String serialized) {
        String[] fields = Task.splitSerialized(serialized);
        assert fields.length == 4 : "Failed to deserialize deadline - invalid format";
        boolean isDone = fields[1].equals("1");
        String description = fields[2];
        LocalDateTime by = LocalDateTime.parse(fields[3], Task.OUTPUT_DATETIME_FORMATTER);
        return new Deadline(description, isDone, by);
    }

    /**
     * Returns whether the given string is a serialization of a Deadline.
     * WARNING: Assumes that the given string is a serialization of some Task.
     */
    public static boolean isSerialization(String serialized) {
        return serialized.startsWith("D |");
    }

    /**
     * Returns a formatted string of when this deadline is due by.
     */
    public String getByString() {
        return this.by.format(Task.OUTPUT_DATETIME_FORMATTER);
    }

    /**
     * Returns a serialization of this deadline.
     * Serialization format: <code>D | isDone | description | by</code>.
     */
    @Override
    public String toSerializedString() {
        return String.format("D | %s | %s", super.toSerializedString(), getByString());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getByString());
    }
}
