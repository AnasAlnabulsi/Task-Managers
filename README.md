# Task Manager

<<<<<<< HEAD
## Kurzbeschreibung

Dieses Projekt ist eine webbasierte Task-Manager-Anwendung mit getrenntem Frontend und Backend.  
Das System ermöglicht die Verwaltung von Aufgaben, Mitarbeitern und Kunden.

Es gibt zwei Rollen:

- **Chef**: darf Aufgaben, Kunden und Mitarbeiter verwalten
- **Mitarbeiter**: darf nur seine zugewiesenen Aufgaben bearbeiten, deren Status ändern und optionale Notizen schreiben

Das Projekt wird lokal entwickelt und später auf **Render.com** deployed.

---

## Technologien

### Frontend
- Vue.js
- Vite
- JavaScript
- HTML5
- CSS3

### Backend
- Java
- Spring Boot
- Gradle
- Spring Web
- Spring Data JPA
- Hibernate ORM

### Datenbank
- PostgreSQL

### Deployment
- Render.com

---
=======
Ein einfaches Lernprojekt fuer eine persoenliche Task-Manager-Webanwendung.

## Ziel

Die Anwendung soll spaeter Aufgaben:

- hinzufuegen
- bearbeiten
- loeschen

Im Moment bauen wir das Projekt Schritt fuer Schritt auf.
>>>>>>> anas

## Projektstruktur

```text
task-manager/
├── README.md
├── frontend/
<<<<<<< HEAD
└── backend/
=======
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
.\gradlew test
```
>>>>>>> anas
