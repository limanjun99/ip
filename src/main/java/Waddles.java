import java.util.ArrayList;
import java.util.Scanner;

public class Waddles {
    public static void main(String[] args) {
        Waddles waddles = new Waddles();
        waddles.run();
    }

    public Waddles() {
        items = new ArrayList<>();
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
    private final ArrayList<String> items;

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
        } else {
            addItem(input);
            printMessage(String.format("added: %s", input));
        }
        return false;
    }

    private void addItem(String item) {
        items.add(item);
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
        for (int i = 0; i < items.size(); i++) {
            builder.append(i + 1).append(". ").append(items.get(i)).append("\n");
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
