package waddles.task;

import waddles.WaddlesException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksTest {
    @Test
    public void get_valid_success() throws WaddlesException {
        Tasks tasks = new Tasks();
        tasks.add(new Todo("return book", false));
        tasks.get(1);
    }

    @Test
    public void get_outOfBounds_throwsException() {
        Tasks tasks = new Tasks();
        try {
            tasks.get(1);
            fail();
        } catch (WaddlesException e) {
            assertEquals("Argument \"task index\" is invalid (1 is out of range of [1, 0])", e.getMessage());
        }
    }

    @Test
    public void remove_valid_success() throws WaddlesException {
        Tasks tasks = new Tasks();
        tasks.add(new Todo("return book", false));
        tasks.remove(1);
    }

    @Test
    public void remove_outOfBounds_throwsException() {
        Tasks tasks = new Tasks();
        tasks.add(new Todo("return book", false));
        try {
            tasks.remove(2);
            fail();
        } catch (WaddlesException e) {
            assertEquals("Argument \"task index\" is invalid (2 is out of range of [1, 1])", e.getMessage());
        }
    }
}
