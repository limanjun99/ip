package waddles;

import java.time.LocalDateTime;
import java.util.Scanner;

import waddles.task.Deadline;
import waddles.task.Event;
import waddles.task.Task;
import waddles.task.Tasks;
import waddles.task.Todo;

public class Waddles {
    private final Tasks tasks;
    private final SaveFile saveFile;
    private final WaddlesUi ui;

    public Waddles() {
        saveFile = new SaveFile();
        tasks = saveFile.load();
        ui = new WaddlesUi();
    }

    public static void main(String[] args) {
        Waddles waddles = new Waddles();
        waddles.run();
    }

    /**
     * Starts the Waddles chatbot.
     * Listens to user input from stdin and responds appropriately to stdout.
     */
    public void run() {
        ui.printGreeting();

        Scanner scanner = new Scanner(System.in);
        boolean isDone = false;
        while (!isDone) {
            String input = scanner.nextLine();
            try {
                isDone = handleInput(input);
                saveFile.save(tasks);
            } catch (WaddlesException e) {
                ui.printError(e);
            }
        }

        ui.printFarewell();
    }

    /**
     * Handles a single line of input from the user.
     *
     * @return Whether this input should make Waddles exit.
     */
    private boolean handleInput(String input) throws WaddlesException {
        Parser parser = new Parser(input);
        Command command = parser.getCommand();
        switch (command) {
        case BYE -> {
            return true;
        }
        case LIST -> ui.printTasks(tasks);
        case MARK -> handleMark(parser);
        case UNMARK -> handleUnmark(parser);
        case TODO -> handleAddTodo(parser);
        case DEADLINE -> handleAddDeadline(parser);
        case EVENT -> handleAddEvent(parser);
        case DELETE -> handleDelete(parser);
        case FIND -> handleFind(parser);
        case INVALID -> throw new WaddlesException.InvalidCommand(input);
        }
        return false;
    }

    /**
     * Adds a new Todo task.
     * The user input should have the format {@code todo <task description>}.
     */
    private void handleAddTodo(Parser parser) throws WaddlesException {
        String description = parser.readArgument("task description", "");
        Todo todo = new Todo(description, false);
        tasks.add(todo);
        ui.printAddedTask(tasks, todo);
    }

    /**
     * Adds a new Deadline task.
     * The user input should have the format {@code deadline <task description> /by <deadline>}.
     */
    private void handleAddDeadline(Parser parser) throws WaddlesException {
        String description = parser.readArgument("task description", " /by ");
        LocalDateTime by = parser.readDateTimeArgument("deadline (/by)", "");
        Deadline deadline = new Deadline(description, false, by);
        tasks.add(deadline);
        ui.printAddedTask(tasks, deadline);
    }

    /**
     * Adds a new Event task.
     * The user input should have the format {@code event <task description> /from <start> /to <end>}.
     */
    private void handleAddEvent(Parser parser) throws WaddlesException {
        String description = parser.readArgument("task description", " /from ");
        LocalDateTime from = parser.readDateTimeArgument("from (/from)", " /to ");
        LocalDateTime to = parser.readDateTimeArgument("to (/to)", "");
        Event event = new Event(description, false, from, to);
        tasks.add(event);
        ui.printAddedTask(tasks, event);
    }

    /**
     * Marks a task as done.
     * The user input should have the format {@code mark <task_index>}.
     */
    private void handleMark(Parser parser) throws WaddlesException {
        int taskIndex = parser.readIntegerArgument("task index", "");
        Task task = tasks.get(taskIndex);
        task.setDone(true);
        ui.printMarkedTask(task);
    }

    /**
     * Unmarks a done task (i.e. sets it to be undone).
     * The user input should have the format {@code unmark <task_index>}.
     */
    private void handleUnmark(Parser parser) throws WaddlesException {
        int taskIndex = parser.readIntegerArgument("task index", "");
        Task task = tasks.get(taskIndex);
        task.setDone(false);
        ui.printUnmarkedTask(task);
    }

    /**
     * Deletes a task.
     * The user input should have the format {@code delete <task_index>}.
     */
    private void handleDelete(Parser parser) throws WaddlesException {
        int taskIndex = parser.readIntegerArgument("task index", "");
        Task task = tasks.remove(taskIndex);
        ui.printDeletedTask(tasks, task);
    }

    /**
     * Finds tasks containing a keyword.
     * The user input should have the format {@code find <keyword>}.
     */
    private void handleFind(Parser parser) throws WaddlesException {
        String keyword = parser.readArgument("keyword", "");
        Tasks filteredTasks = tasks.find(keyword);
        ui.printTasks(filteredTasks);
    }
}
