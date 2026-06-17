package htw.webtech.taskmanager.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://task-managers-1-4934.onrender.com"
})
@RestController
public class TaskController {

    // Das Repository ersetzt die frühere ArrayList und speichert Tasks in PostgreSQL.
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/api/tasks")
    public List<Task> getTasks() {
        // Lädt alle gespeicherten Tasks aus der Datenbank.
        return taskRepository.findAll();
    }

    @PostMapping("/api/tasks")
    public Task addTask(@RequestBody Task task) {
        task.setStatus(normalizeStatus(task.getStatus()));
        // Speichert den vom Frontend gesendeten Task als neue Zeile in PostgreSQL.
        return taskRepository.save(task);
    }

    @PutMapping("/api/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task nicht gefunden"));

        task.setStatus(normalizeStatus(updatedTask.getStatus()));
        // Speichert die Änderung am bestehenden Task in PostgreSQL.
        return taskRepository.save(task);
    }

    @DeleteMapping("/api/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        // Löscht den Task mit dieser ID aus der Datenbank.
        taskRepository.deleteById(id);
    }

    private String normalizeStatus(String status) {
        if (status == null || status.isBlank()) {
            return "TODO";
        }

        return switch (status.toLowerCase()) {
            case "in arbeit" -> "In Arbeit";
            case "erledigt" -> "Erledigt";
            default -> "TODO";
        };
    }
}
