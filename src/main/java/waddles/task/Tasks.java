package waddles.task;

import java.util.ArrayList;

import waddles.WaddlesException;

/**
 * Represents a lists of tasks.
 * Note that this data structure is 1-indexed.
 */
public class Tasks {
    private final ArrayList<Task> tasks;

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
        assert index >= 1 && index <= tasks.size() : "Tasks index out of bounds";
        return tasks.get(index - 1);
    }

    /**
     * Returns all tasks containing the given keyword in their description.
     */
    public Tasks find(String keyword) {
        Tasks filteredTasks = new Tasks();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    /**
     * Returns the number of tasks.
     */
    public int getSize() {
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

    /**
     * Checks if the given index is valid,
     * i.e. within the range [1, size()].
     *
     * @throws WaddlesException.InvalidArgument if out of range.
     */
    private void checkIndex(int index) throws WaddlesException.InvalidArgument {
        if (index <= 0 || index > tasks.size()) {
            throw new WaddlesException.InvalidArgument("task index",
                    String.format("%d is out of" + " range of [1, " + "%d]", index, getSize()));
        }
    }
}
