import java.util.Scanner;

public class Waddles {
    public static void main(String[] args) {
        printGreeting();
        runInputLoop();
        printFarewell();
    }

    private static final String NAME = "Waddles";

    private static void runInputLoop() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            handleInput(input);
        }
    }

    private static void handleInput(String input) {
        printMessage(input);
    }

    private static void printGreeting() {
        String greeting = String.format("Hello! I'm %s.\n" + "What can I do for you?", NAME);
        printMessage(greeting);
    }

    private static void printFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        printMessage(farewell);
    }

    /**
     * Formats the message nicely and prints it to stdout.
     * Prefer this method for printing to keep output consistent.
     */
    private static void printMessage(String message) {
        final String leftPadding = " ".repeat(4);
        final String horizontalLine = "-".repeat(80);
        System.out.println(leftPadding + horizontalLine);
        for (String line : message.split("\n")) {
            System.out.println(leftPadding + line);
        }
        System.out.println(leftPadding + horizontalLine);
    }
}
