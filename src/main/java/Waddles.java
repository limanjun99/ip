import java.util.ArrayList;
import java.util.Scanner;

public class Waddles {
    public static void main(String[] args) {
        Waddles waddles = new Waddles();
        waddles.run();
    }

    public Waddles() {
        tasks = new ArrayList<>();
    }

    /**
     * Starts the Waddles chatbot.
     * Listens to user input from stdin and responds appropriately to stdout.
     */
    public void run() {
        printGreeting();

        Scanner scanner = new Scanner(System.in);
        boolean isDone = false;
        while (!isDone) {
            String input = scanner.nextLine();
            isDone = handleInput(input);
        }

        printFarewell();
    }

    private static final String NAME = "Waddles";
    private final ArrayList<Task> tasks;

    /**
     * Handles a single line of input from the user.
     *
     * @return Whether this input should make Waddles exit.
     */
    private boolean handleInput(String input) {
        if (input.equals("bye")) {
            return true;
        } else if (input.equals("list")) {
            printList();
        } else if (input.startsWith("mark ")) {
            handleMark(input);
        } else if (input.startsWith("unmark ")) {
            handleUnmark(input);
        } else if (input.startsWith("todo ")) {
            handleAddTodo(input);
        } else if (input.startsWith("deadline ")) {
            handleAddDeadline(input);
        } else if (input.startsWith("event ")) {
            handleAddEvent(input);
        } else {
            printMessage("Invalid command");
        }
        return false;
    }

    /**
     * Adds a new Todo task.
     *
     * @param input: Should have the format {@code todo <task description>}.
     */
    private void handleAddTodo(String input) {
        String description = input.split(" ", 2)[1];
        Todo todo = new Todo(description);
        tasks.add(todo);
        printAddedTask(todo);
    }

    /**
     * Adds a new Deadline task.
     *
     * @param input: Should have the format {@code deadline <task description> /by <deadline>}.
     */
    private void handleAddDeadline(String input) {
        String rawArguments = input.split(" ", 2)[1];
        String[] arguments = rawArguments.split(" /by ", 2);
        String description = arguments[0];
        String by = arguments[1];
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        printAddedTask(deadline);
    }

    /**
     * Adds a new Event task.
     *
     * @param input: Should have the format {@code event <task description> /from <start> /to <end>}.
     */
    private void handleAddEvent(String input) {
        String rawArguments = input.split(" ", 2)[1];
        String description = rawArguments.split(" /from ", 2)[0];
        String from = rawArguments.split(" /from ", 2)[1].split(" /to ", 2)[0];
        String to = rawArguments.split(" /to ", 2)[1];
        Event event = new Event(description, from, to);
        tasks.add(event);
        printAddedTask(event);
    }

    /**
     * Marks a task as done.
     *
     * @param input: Should have the format {@code mark <task_index>}.
     */
    private void handleMark(String input) {
        // TODO: Handle invalid arguments.
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.get(taskIndex).markDone();
        printMessage(String.format("Nice! I've marked this task as done:\n%s", tasks.get(taskIndex)));
    }

    /**
     * Unmarks a done task (i.e. sets it to be undone).
     *
     * @param input: Should have the format {@code unmark <task_index>}.
     */
    private void handleUnmark(String input) {
        // TODO: Handle invalid arguments.
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.get(taskIndex).markNotDone();
        printMessage(String.format("Ok, I've marked this task as not done yet:\n%s", tasks.get(taskIndex)));
    }

    private void printGreeting() {
        String greeting = String.format("Hello! I'm %s.\n" + "What can I do for you?", NAME);
        printMessage(greeting);
    }

    private void printFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        printMessage(farewell);
    }

    private void printList() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            builder.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        printMessage(builder.toString());
    }

    private void printAddedTask(Task task) {
        printMessage(String.format("""
                Got it. I've added this task:
                  %s
                Now you have %d tasks in the list.""", task, tasks.size()));
    }

    /**
     * Formats the message nicely and prints it to stdout.
     * Prefer this method for printing to keep output consistent.
     */
    private void printMessage(String message) {
        String leftPadding = " ".repeat(4);
        String horizontalLine = "-".repeat(80);
        System.out.println(leftPadding + horizontalLine);
        for (String line : message.split("\n")) {
            System.out.println(leftPadding + line);
        }
        System.out.println(leftPadding + horizontalLine);
    }
}
