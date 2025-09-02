package waddles;

import waddles.task.Task;
import waddles.task.Tasks;

/**
 * Responsible for the output format for various commands.
 */
public class WaddlesUi {
    private static final String NAME = "Waddles";

    /**
     * Returns a greeting.
     */
    public String makeGreetingMessage() {
        return String.format("Hello! I'm %s.\n" + "What can I do for you?", NAME);
    }

    /**
     * Returns a farewell message.
     */
    public String makeFarewellMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message for a list of tasks.
     */
    public String makeTasksMessage(Tasks tasks) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= tasks.getSize(); i++) {
            builder.append(String.format("%d. %s\n", i, tasks.getUnchecked(i)));
        }
        return builder.toString();
    }

    /**
     * Returns a verification message for adding a task.
     *
     * @param tasks   Set of tasks after adding newTask.
     * @param newTask The new task to be added.
     */
    public String makeAddedTaskMessage(Tasks tasks, Task newTask) {
        return String.format("""
                Got it. I've added this task:
                  %s
                Now you have %d tasks in the list.""", newTask, tasks.getSize());
    }

    /**
     * Returns a verification message for deleting a task.
     *
     * @param tasks   Set of tasks after deleting oldTask.
     * @param oldTask The old task to be deleted.
     */
    public String makeDeletedTaskMessage(Tasks tasks, Task oldTask) {
        return String.format("""
                Noted. I've removed this task:
                  %s
                Now you have %d tasks in the list.""", oldTask, tasks.getSize());
    }

    /**
     * Returns a verification message for marking a task as done.
     */
    public String makeMarkedTaskMessage(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s", task);
    }

    /**
     * Returns a verification message for marking a task as not done.
     */
    public String makeUnmarkedTaskMessage(Task task) {
        return String.format("Ok, I've marked this task as not done yet:\n%s", task);
    }

    /**
     * Returns an error message.
     */
    public String makeErrorMessage(WaddlesException error) {
        return String.format("ERROR:\n%s", error.getMessage());
    }
}

