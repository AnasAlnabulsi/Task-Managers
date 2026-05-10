package htw.webtech.taskmanager.task;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class TaskController {

    @GetMapping("/api/tasks")
    public List<Task> getTasks() {
        return List.of(
                new Task(1L, "Spring Boot starten", "Pruefen, ob das Backend laeuft", false),
                new Task(2L, "Erste GET-Route testen", "Antwort im Browser kontrollieren", true)
        );
    }
}