package htw.webtech.taskmanager.task;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Ein REST-Controller beantwortet HTTP-Anfragen mit Daten, hier als JSON.
@RestController
// Erlaubt dem Vue-Frontend auf Port 5173 den Zugriff auf dieses Backend.
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    // Diese Route liefert eine einfache Liste von Test-Tasks.
    @GetMapping("/api/tasks")
    public List<Task> getTasks() {
        return List.of(
                new Task(1L, "Spring Boot starten", "Prüfen, ob das Backend läuft", false),
                new Task(2L, "Erste GET-Route testen", "Antwort im Browser kontrollieren", true)
        );
    }
}
