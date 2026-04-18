package htw.webtech.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Startpunkt der Spring-Boot-Anwendung.
@SpringBootApplication
public class TaskmanagerApplication {

	public static void main(String[] args) {
		// Startet den eingebauten Webserver und die Spring-Konfiguration.
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

}
