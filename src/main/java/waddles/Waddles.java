package waddles;

import java.time.LocalDateTime;

import waddles.task.Deadline;
import waddles.task.Event;
import waddles.task.Task;
import waddles.task.Tasks;
import waddles.task.Todo;

/**
 * Waddles is a chatbot that manages user tasks.
 */
public class Waddles {
    private final Tasks tasks;
    private final SaveFile saveFile;
    private final WaddlesUi ui;

    /**
     * Constructs a new Waddles object.
     */
    public Waddles() {
        saveFile = new SaveFile();
        tasks = saveFile.load();
        ui = new WaddlesUi();
    }

    /**
     * Processes a single user command and returns a response to it.
     */
    public WaddlesResult getResponse(String input) {
        try {
            WaddlesResult result = handleInput(input);
            saveFile.save(tasks);
            return result;
        } catch (WaddlesException e) {
            String errorMessage = ui.makeErrorMessage(e);
            return WaddlesResult.makeError(errorMessage);
        }
    }

    /**
     * Handles a single line of input from the user.
     */
    @SuppressWarnings("checkstyle:Indentation")
    private WaddlesResult handleInput(String input) throws WaddlesException {
        Parser parser = new Parser(input);
        Command command = parser.getCommand();
        switch (command) {
        case BYE:
            return handleBye();
        case LIST:
            return handleList();
        case MARK:
            return handleMark(parser);
        case UNMARK:
            return handleUnmark(parser);
        case TODO:
            return handleAddTodo(parser);
        case DEADLINE:
            return handleAddDeadline(parser);
        case EVENT:
            return handleAddEvent(parser);
        case DELETE:
            return handleDelete(parser);
        case FIND:
            return handleFind(parser);
        default:
            throw new WaddlesException.InvalidCommand(input);
        }
    }

    /**
     * Terminates Waddles.
     */
    private WaddlesResult handleBye() {
        String message = ui.makeFarewellMessage();
        return WaddlesResult.makeEnd(message);
    }

    /**
     * Lists all current tasks.
     */
    private WaddlesResult handleList() {
        String listMessage = ui.makeTasksMessage(tasks);
        return WaddlesResult.makeSuccess(listMessage);
    }

    /**
     * Adds a new Todo task.
     * The user input should have the format {@code todo <task description>}.
     */
    private WaddlesResult handleAddTodo(Parser parser) throws WaddlesException {
        String description = parser.readArgument("task description", "");
        Todo todo = new Todo(description, false);
        tasks.add(todo);
        String verificationMessage = ui.makeAddedTaskMessage(tasks, todo);
        return WaddlesResult.makeSuccess(verificationMessage);
    }

    /**
     * Adds a new Deadline task.
     * The user input should have the format {@code deadline <task description> /by <deadline>}.
     */
    private WaddlesResult handleAddDeadline(Parser parser) throws WaddlesException {
        String description = parser.readArgument("task description", " /by ");
        LocalDateTime by = parser.readDateTimeArgument("deadline (/by)", "");
        Deadline deadline = new Deadline(description, false, by);
        tasks.add(deadline);
        String verificationMessage = ui.makeAddedTaskMessage(tasks, deadline);
        return WaddlesResult.makeSuccess(verificationMessage);
    }

    /**
     * Adds a new Event task.
     * The user input should have the format {@code event <task description> /from <start> /to <end>}.
     */
    private WaddlesResult handleAddEvent(Parser parser) throws WaddlesException {
        String description = parser.readArgument("task description", " /from ");
        LocalDateTime from = parser.readDateTimeArgument("from (/from)", " /to ");
        LocalDateTime to = parser.readDateTimeArgument("to (/to)", "");
        Event event = new Event(description, false, from, to);
        tasks.add(event);
        String verificationMessage = ui.makeAddedTaskMessage(tasks, event);
        return WaddlesResult.makeSuccess(verificationMessage);
    }

    /**
     * Marks a task as done.
     * The user input should have the format {@code mark <task_index>}.
     */
    private WaddlesResult handleMark(Parser parser) throws WaddlesException {
        int taskIndex = parser.readIntegerArgument("task index", "");
        Task task = tasks.get(taskIndex);
        task.setDone(true);
        String verificationMessage = ui.makeMarkedTaskMessage(task);
        return WaddlesResult.makeSuccess(verificationMessage);
    }

    /**
     * Unmarks a done task (i.e. sets it to be undone).
     * The user input should have the format {@code unmark <task_index>}.
     */
    private WaddlesResult handleUnmark(Parser parser) throws WaddlesException {
        int taskIndex = parser.readIntegerArgument("task index", "");
        Task task = tasks.get(taskIndex);
        task.setDone(false);
        String verificationMessage = ui.makeUnmarkedTaskMessage(task);
        return WaddlesResult.makeSuccess(verificationMessage);
    }

    /**
     * Deletes a task.
     * The user input should have the format {@code delete <task_index>}.
     */
    private WaddlesResult handleDelete(Parser parser) throws WaddlesException {
        int taskIndex = parser.readIntegerArgument("task index", "");
        Task task = tasks.remove(taskIndex);
        String verificationMessage = ui.makeDeletedTaskMessage(tasks, task);
        return WaddlesResult.makeSuccess(verificationMessage);
    }

    /**
     * Finds tasks containing a keyword.
     * The user input should have the format {@code find <keyword>}.
     */
    private WaddlesResult handleFind(Parser parser) throws WaddlesException {
        String keyword = parser.readArgument("keyword", "");
        Tasks filteredTasks = tasks.find(keyword);
        String foundMessage = ui.makeTasksMessage(filteredTasks);
        return WaddlesResult.makeSuccess(foundMessage);
    }
}
