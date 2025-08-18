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
            try {
                isDone = handleInput(input);
            } catch (WaddlesException e) {
                printError(e);
            }
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
    private boolean handleInput(String input) throws WaddlesException {
        Parser parser = new Parser(input);
        String command = parser.getCommand();
        switch (command) {
            case "bye" -> {
                return true;
            }
            case "list" -> printList();
            case "mark" -> handleMark(parser);
            case "unmark" -> handleUnmark(parser);
            case "todo" -> handleAddTodo(parser);
            case "deadline" -> handleAddDeadline(parser);
            case "event" -> handleAddEvent(parser);
            case "delete" -> handleDelete(parser);
            default -> throw new WaddlesException.InvalidCommand(command);
        }
        return false;
    }

    /**
     * Adds a new Todo task.
     * The user input should have the format {@code todo <task description>}.
     */
    private void handleAddTodo(Parser parser) throws WaddlesException {
        String description = parser.readArgument("task description", "");
        Todo todo = new Todo(description);
        tasks.add(todo);
        printAddedTask(todo);
    }

    /**
     * Adds a new Deadline task.
     * The user input should have the format {@code deadline <task description> /by <deadline>}.
     */
    private void handleAddDeadline(Parser parser) throws WaddlesException {
        String description = parser.readArgument("task description", " /by ");
        String by = parser.readArgument("deadline (/by)", "");
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        printAddedTask(deadline);
    }

    /**
     * Adds a new Event task.
     * The user input should have the format {@code event <task description> /from <start> /to <end>}.
     */
    private void handleAddEvent(Parser parser) throws WaddlesException {
        String description = parser.readArgument("task description", " /from ");
        String from = parser.readArgument("from (/from)", " /to ");
        String to = parser.readArgument("to (/to)", "");
        Event event = new Event(description, from, to);
        tasks.add(event);
        printAddedTask(event);
    }

    /**
     * Marks a task as done.
     * The user input should have the format {@code mark <task_index>}.
     */
    private void handleMark(Parser parser) throws WaddlesException {
        int taskIndex = parser.readIntegerArgument("task index", "") - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new WaddlesException.InvalidArgument(
                    parser.getCommand(),
                    "task index",
                    String.format("%d is out of" + " range of [1, %d]", taskIndex + 1, tasks.size()));
        }
        tasks.get(taskIndex).markDone();
        printMessage(String.format("Nice! I've marked this task as done:\n%s", tasks.get(taskIndex)));
    }

    /**
     * Unmarks a done task (i.e. sets it to be undone).
     * The user input should have the format {@code unmark <task_index>}.
     */
    private void handleUnmark(Parser parser) throws WaddlesException {
        int taskIndex = parser.readIntegerArgument("task index", "") - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new WaddlesException.InvalidArgument(
                    parser.getCommand(),
                    "task index",
                    String.format("%d is out of" + " range of [1, %d]", taskIndex + 1, tasks.size()));
        }
        tasks.get(taskIndex).markNotDone();
        printMessage(String.format("Ok, I've marked this task as not done yet:\n%s", tasks.get(taskIndex)));
    }

    /**
     * Deletes a task.
     * The user input should have the format {@code delete <task_index>}.
     */
    private void handleDelete(Parser parser) throws WaddlesException {
        int taskIndex = parser.readIntegerArgument("task index", "") - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new WaddlesException.InvalidArgument(
                    parser.getCommand(),
                    "task index",
                    String.format("%d is out of" + " range of [1, %d]", taskIndex + 1, tasks.size()));
        }
        Task task = tasks.remove(taskIndex);
        printMessage(String.format("""
                        Noted. I've removed this task:
                          %s
                        Now you have %d tasks in the list."""
                , task, tasks.size()));
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

    /**
     * Formats the error nicely and prints it to stdout.
     * Prefer this method for printing to keep output consistent.
     */
    private void printError(WaddlesException error) {
        printMessage(String.format("ERROR:\n%s", error.getMessage()));
    }
}
