package htw.webtech.taskmanager.task;

// Diese Klasse beschreibt die Datenstruktur eines Tasks.
public class Task {

    private Long id;
    private String title;
    private String description;
    private boolean completed;

    // Über den Konstruktor legen wir direkt alle Werte eines Tasks fest.
    public Task(Long id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }
}
