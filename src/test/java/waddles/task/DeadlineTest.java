package waddles.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void fromSerializedString_valid_success() {
        Deadline actualDeadline = Deadline.fromSerializedString("D | 0 | do work | Dec 12 2025 23:59");
        Deadline expectedDeadline = new Deadline("do work", false, LocalDateTime.of(2025, 12, 12, 23, 59));
        assertEquals(expectedDeadline.toString(), actualDeadline.toString());
    }

    @Test
    public void toSerializedString_valid_success() {
        Deadline deadline = new Deadline("do work", false, LocalDateTime.of(2025, 12, 12, 23, 59));
        String serialized = deadline.toSerializedString();
        assertEquals("D | 0 | do work | Dec 12 2025 23:59", serialized);
    }
}
