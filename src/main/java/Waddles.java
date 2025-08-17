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
        } else {
            handleAddTask(input);
        }
        return false;
    }

    /**
     * Adds a new task with the given description.
     */
    private void handleAddTask(String description) {
        tasks.add(new Task(description));
        printMessage(String.format("added: %s", description));
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
