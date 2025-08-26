public class Event extends Task {
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns an Event from the serialized string.
     * WARNING: Assumes that the given string is a serialization of some Event.
     */
    public static Event fromSerializedString(String serialized) {
        String[] fields = Task.splitSerialized(serialized);
        boolean isDone = fields[1].equals("1");
        String description = fields[2];
        String[] timing = fields[3].split("-");
        String start = timing[0];
        String end = timing[1];
        return new Event(description, isDone, start, end);
    }

    /**
     * Returns whether the given string is a serialization of an Event.
     * WARNING: Assumes that the given string is a serialization of some Task.
     */
    public static boolean isSerialization(String serialized) {
        return serialized.startsWith("E |");
    }

    /**
     * Returns a serialization of this event.
     * Serialization format: <code>E | isDone | description | start-end</code>.
     */
    @Override
    public String toSerializedString() {
        return String.format("E | %s | %s-%s", super.toSerializedString(), start, end);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
    }

    private final String start;
    private final String end;
}
