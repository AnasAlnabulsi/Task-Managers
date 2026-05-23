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

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class TaskController {

    private final List<Task> tasks = new ArrayList<>(List.of(
            new Task(1L, "Spring Boot starten", "Pruefen, ob das Backend laeuft", "TODO"),
            new Task(2L, "Erste GET-Route testen", "Antwort im Browser kontrollieren", "Erledigt")
    ));
    private long nextId = 3L;

    @GetMapping("/api/tasks")
    public List<Task> getTasks() {
        return tasks;
    }

    @PostMapping("/api/tasks")
    public Task addTask(@RequestBody Task task) {
        task.setId(nextId);
        nextId++;
        task.setStatus(normalizeStatus(task.getStatus()));
        tasks.add(task);
        return task;
    }

    @PutMapping("/api/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setStatus(normalizeStatus(updatedTask.getStatus()));
                return task;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task nicht gefunden");
    }

    @DeleteMapping("/api/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
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
