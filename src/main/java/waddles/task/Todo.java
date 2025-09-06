package waddles.task;

/**
 * Todos are the most generic task - they only have a description and completion status.
 */
public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a Todo from the serialized string.
     * WARNING: Assumes that the given string is a serialization of some Todo.
     */
    public static Todo fromSerializedString(String serialized) {
        String[] fields = Task.splitSerialized(serialized);
        assert fields.length == 3 : "Failed to deserialize todo - invalid format";
        boolean isDone = fields[1].equals("1");
        String description = fields[2];
        return new Todo(description, isDone);
    }

    /**
     * Returns whether the given string is a serialization of a Todo.
     * WARNING: Assumes that the given string is a serialization of some Task.
     */
    public static boolean isSerialization(String serialized) {
        return serialized.startsWith("T |");
    }

    /**
     * Returns a serialization of this todo.
     * Serialization format: <code>T | isDone | description</code>.
     */
    @Override
    public String toSerializedString() {
        return String.format("T | %s", super.toSerializedString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
