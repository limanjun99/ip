package waddles;

import waddles.task.Task;
import waddles.task.Tasks;

/**
 * Handles printing information to the command line.
 */
public class WaddlesUi {
    private static final String NAME = "Waddles";

    /**
     * Prints a greeting to the user.
     */
    public void printGreeting() {
        String greeting = String.format("Hello! I'm %s.\n" + "What can I do for you?", NAME);
        printMessage(greeting);
    }

    /**
     * Prints a farewell message.
     */
    public void printFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        printMessage(farewell);
    }

    /**
     * Prints a list of tasks.
     */
    public void printTasks(Tasks tasks) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            builder.append(String.format("%d. %s\n", i, tasks.getUnchecked(i)));
        }
        printMessage(builder.toString());
    }

    /**
     * Prints a verification message for adding a task.
     *
     * @param tasks   Set of tasks after adding newTask.
     * @param newTask The new task to be added.
     */
    public void printAddedTask(Tasks tasks, Task newTask) {
        printMessage(String.format("""
                Got it. I've added this task:
                  %s
                Now you have %d tasks in the list.""", newTask, tasks.size()));
    }

    /**
     * Prints a verification message for deleting a task.
     *
     * @param tasks   Set of tasks after deleting oldTask.
     * @param oldTask The old task to be deleted.
     */
    public void printDeletedTask(Tasks tasks, Task oldTask) {
        printMessage(String.format("""
                Noted. I've removed this task:
                  %s
                Now you have %d tasks in the list.""", oldTask, tasks.size()));
    }

    /**
     * Prints a verification message for marking a task as done.
     */
    public void printMarkedTask(Task task) {
        printMessage(String.format("Nice! I've marked this task as done:\n%s", task));
    }

    /**
     * Prints a verification message for marking a task as not done.
     */
    public void printUnmarkedTask(Task task) {
        printMessage(String.format("Ok, I've marked this task as not done yet:\n%s", task));
    }

    /**
     * Formats the message nicely and prints it to stdout.
     */
    public void printMessage(String message) {
        String leftPadding = " ".repeat(4);
        String horizontalLine = "-".repeat(80);
        System.out.println(leftPadding + horizontalLine);
        for (String line : message.split("\n")) {
            System.out.println(leftPadding + line);
        }
        System.out.println(leftPadding + horizontalLine);
    }

    /**
     * Formats the error nicely and prints it to stdout.
     */
    public void printError(WaddlesException error) {
        printMessage(String.format("ERROR:\n%s", error.getMessage()));
    }
}

