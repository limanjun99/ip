package waddles.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void fromSerializedString_valid_success() {
        Event actualEvent = Event.fromSerializedString("E | 0 | meeting | Dec 12 2025 23:59-Dec 13 2025 23:59");
        Event expectedEvent = new Event("meeting", false, LocalDateTime.of(2025, 12, 12, 23, 59),
                LocalDateTime.of(2025, 12, 13, 23, 59));
        assertEquals(expectedEvent.toString(), actualEvent.toString());
    }

    @Test
    public void toSerializedString_valid_success() {
        Event event = new Event("meeting", false, LocalDateTime.of(2025, 12, 12, 23, 59), LocalDateTime.of(2025, 12,
                13, 23, 59));
        String serialized = event.toSerializedString();
        assertEquals("E | 0 | meeting | Dec 12 2025 23:59-Dec 13 2025 23:59", serialized);
    }
}
