public class Deadline extends Task {
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a Deadline from the serialized string.
     * WARNING: Assumes that the given string is a serialization of some Deadline.
     */
    public static Deadline fromSerializedString(String serialized) {
        String[] fields = Task.splitSerialized(serialized);
        boolean isDone = fields[1].equals("1");
        String description = fields[2];
        String by = fields[3];
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
     * Returns a serialization of this deadline.
     * Serialization format: <code>D | isDone | description | by</code>.
     */
    @Override
    public String toSerializedString() {
        return String.format("D | %s | %s", super.toSerializedString(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    private final String by;
}
