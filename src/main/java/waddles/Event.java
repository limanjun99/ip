package waddles;

import java.time.LocalDateTime;

public class Event extends Task {
    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a formatted string of this event's start time.
     */
    public String getStartString() {
        return this.start.format(Task.outputDateTimeFormatter);
    }

    /**
     * Returns a formatted string of this event's end time.
     */
    public String getEndString() {
        return this.end.format(Task.outputDateTimeFormatter);
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
        LocalDateTime start = LocalDateTime.parse(timing[0], Task.outputDateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(timing[1], Task.outputDateTimeFormatter);
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
        return String.format("E | %s | %s-%s", super.toSerializedString(), getStartString(), getEndString());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), getStartString(), getEndString());
    }

    private final LocalDateTime start;
    private final LocalDateTime end;
}
