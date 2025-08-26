package waddles.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void fromSerializedString_valid_success() {
        Todo actualTodo = Todo.fromSerializedString("T | 0 | return book");
        Todo expectedTodo = new Todo("return book", false);
        assertEquals(actualTodo.toString(), expectedTodo.toString());
    }

    @Test
    public void toSerializedString_valid_success() {
        Todo todo = new Todo("return book", true);
        String serialized = todo.toSerializedString();
        assertEquals("T | 1 | return book", serialized);
    }
}
