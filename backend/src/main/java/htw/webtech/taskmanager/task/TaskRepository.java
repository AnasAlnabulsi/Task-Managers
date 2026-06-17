package htw.webtech.taskmanager.task;

import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA erzeugt daraus automatisch die Datenbank-Methoden.
public interface TaskRepository extends JpaRepository<Task, Long> {
}
