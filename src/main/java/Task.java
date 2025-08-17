public class Task {
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public String toString() {
        char doneMarker = isDone ? 'X' : ' ';
        return String.format("[%c] %s", doneMarker, description);
    }

    private final String description;
    private boolean isDone;
}
