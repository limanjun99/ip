package waddles.task;

import waddles.WaddlesException;

import java.util.ArrayList;

/**
 * Represents a lists of tasks.
 * Note that this data structure is 1-indexed.
 */
public class Tasks {
    public Tasks() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the task at the given index (1-indexed).
     */
    public Task get(int index) throws WaddlesException.InvalidArgument {
        checkIndex(index);
        return getUnchecked(index);
    }

    /**
     * Returns the task at the given index (1-indexed).
     * Does not perform range checks (i.e. you get runtime error if index is out of bounds).
     */
    public Task getUnchecked(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Returns the number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new task to this list of tasks.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the given index (1-indexed).
     *
     * @return The removed task.
     */
    public Task remove(int index) throws WaddlesException.InvalidArgument {
        checkIndex(index);
        return tasks.remove(index - 1);
    }

    private final ArrayList<Task> tasks;

    /**
     * Checks if the given index is valid,
     * i.e. within the range [1, size()].
     *
     * @throws WaddlesException.InvalidArgument if out of range.
     */
    private void checkIndex(int index) throws WaddlesException.InvalidArgument {
        if (index <= 0 || index > tasks.size()) {
            throw new WaddlesException.InvalidArgument("task index",
                    String.format("%d is out of" + " range of [1, " + "%d]", index, size()));
        }
    }
}
