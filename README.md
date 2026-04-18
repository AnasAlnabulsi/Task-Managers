# Task Manager

Ein einfaches Lernprojekt fuer eine persoenliche Task-Manager-Webanwendung.

## Ziel

Die Anwendung soll spaeter Aufgaben:

- hinzufuegen
- bearbeiten
- loeschen

Im Moment bauen wir das Projekt Schritt fuer Schritt auf.

## Projektstruktur

```text
task-manager/
├── README.md
├── frontend/
└── backend/
```

## Technologien

- Backend: Java, Spring Boot, Gradle
- Frontend: Vue.js, HTML, CSS, JavaScript

## Backend bauen

Wechsle zuerst in den Backend-Ordner:

```powershell
cd backend
```

Dann baust du das Projekt mit:

```powershell
.\gradlew build
```

## Backend starten

Starte die Spring-Boot-Anwendung mit:

```powershell
cd backend
.\gradlew bootRun
```

Die Anwendung laeuft dann standardmaessig unter:

```text
http://localhost:8080
```

Die erste GET-Route ist:

```text
http://localhost:8080/api/tasks
```

## Backend testen

Die Tests startest du mit:

```powershell
cd backend
.\gradlew test
```