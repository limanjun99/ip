import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class handles saving/loading tasks to/from disk, so that user sessions are persisted across runs.
 */
public class SaveFile {
    public static final String SAVE_PATH = "./data/waddles.txt";

    /**
     * Saves the given tasks to a save file in SAVE_PATH.
     */
    public void save(Tasks tasks) throws WaddlesException.FileError {
        createSaveDirectory();
        try {
            FileWriter writer = new FileWriter(SAVE_PATH);
            for (int i = 1; i <= tasks.size(); i++) {
                writer.write(tasks.getUnchecked(i).toSerializedString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new WaddlesException.FileError(String.format("Failed to write to %s (%s)", SAVE_PATH,
                    e.getMessage()));
        }
    }

    /**
     * Loads the tasks from the previous save file at SAVE_PATH.
     * If there is no save file, we return an empty Tasks.
     */
    public Tasks load() {
        try {
            File file = new File(SAVE_PATH);
            Scanner scanner = new Scanner(file);
            Tasks tasks = new Tasks();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = deserializeLine(line);
                tasks.add(task);
            }
            return tasks;
        } catch (IOException e) {
            return new Tasks();
        }
    }

    /**
     * Creates the directory to place the save file in, if it does not already exist.
     */
    private void createSaveDirectory() throws WaddlesException.FileError {
        try {
            Path directoryPath = Paths.get(SAVE_PATH).getParent();
            Files.createDirectories(directoryPath);
        } catch (IOException e) {
            throw new WaddlesException.FileError(String.format("Failed to create %s (%s)", SAVE_PATH, e.getMessage()));
        }
    }

    /**
     * Deserializes a single line of the savefile into its corresponding task.
     * WARNING: Assumes that the line is a serialization of some task.
     */
    private Task deserializeLine(String line) {
        if (Deadline.isSerialization(line)) {
            return Deadline.fromSerializedString(line);
        } else if (Event.isSerialization(line)) {
            return Event.fromSerializedString(line);
        } else /* if (Todo.isSerialization(line)) */ {
            return Todo.fromSerializedString(line);
        }
    }
}
