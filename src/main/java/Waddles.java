public class Waddles {
    public static void main(String[] args) {
        printGreeting();
        printFarewell();
    }

    private static final String NAME = "Waddles";

    private static void printGreeting() {
        String greeting = String.format("Hello! I'm %s.\n" + "What can I do for you?", NAME);
        printMessage(greeting);
    }

    private static void printFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        printMessage(farewell);
    }

    /**
     * Wraps the message in horizontal lines before printing it to stdout.
     * Prefer this method for printing to keep output consistent.
     */
    private static void printMessage(String message) {
        final String HORIZONTAL_LINE = "-".repeat(80);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }
}
