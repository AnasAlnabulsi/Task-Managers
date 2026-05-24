# Task Manager

Ein einfaches Lernprojekt für eine persönliche Task-Manager-Webanwendung.

Das Projekt besteht aus einem Spring-Boot-Backend und einem Vue/Vite-Frontend.
Tasks werden aktuell im Backend im Speicher gehalten. Nach einem Neustart des
Backends sind neu angelegte oder geänderte Tasks wieder zurückgesetzt.

## Funktionen

- Tasks anzeigen
- Tasks hinzufügen
- Status eines Tasks ändern
- Tasks löschen
- Statuswerte:
  - `TODO`
  - `In Arbeit`
  - `Erledigt`

## Projektstruktur

```text
Task-Managers/
├── README.md
├── backend/
└── frontend/
```

## Technologien

- Backend: Java 17, Spring Boot, Gradle
- Frontend: Vue.js, Vite, HTML, CSS, JavaScript

## Voraussetzungen

- Java 17
- Node.js
- npm

## Backend starten

Windows PowerShell:

```powershell
cd backend
.\gradlew bootRun
```

macOS / Linux:

```bash
cd backend
chmod +x gradlew
./gradlew bootRun
```

Das Backend läuft lokal unter:

```text
http://localhost:8080
```

## Frontend starten

Windows PowerShell:

```powershell
cd frontend
npm.cmd install
npm.cmd run dev
```

macOS / Linux:

```bash
cd frontend
npm install
npm run dev
```

Das Frontend läuft lokal normalerweise unter:

```text
http://localhost:5173
```

## Lokal testen

1. Backend starten.
2. Frontend starten.
3. Im Browser öffnen:

```text
http://localhost:5173
```

Wenn alles funktioniert, werden die Tasks aus dem Backend im Frontend angezeigt.

## Backend bauen und testen

Windows PowerShell:

```powershell
cd backend
.\gradlew build
.\gradlew test
```

macOS / Linux:

```bash
cd backend
./gradlew build
./gradlew test
```

## Frontend bauen

Windows PowerShell:

```powershell
cd frontend
npm.cmd run build
```

macOS / Linux:

```bash
cd frontend
npm run build
```

Der Build wird im Ordner `frontend/dist/` erzeugt.

## API-Routen

### Alle Tasks laden

```http
GET /api/tasks
```

Beispiel:

```text
http://localhost:8080/api/tasks
```

### Task hinzufügen

```http
POST /api/tasks
```

Beispiel-Body:

```json
{
  "title": "Neue Aufgabe",
  "description": "Kurze Beschreibung",
  "status": "TODO"
}
```

### Task-Status ändern

```http
PUT /api/tasks/{id}
```

Beispiel-Body:

```json
{
  "status": "In Arbeit"
}
```

### Task löschen

```http
DELETE /api/tasks/{id}
```

## Render Deployment

- Backend ist auf Render deployed.
- Frontend ist auf Render deployed.
- Das Frontend ruft die Backend-GET-Route auf.

### Backend auf Render

Render-Service-Typ:

```text
Web Service
```

Root Directory:

```text
backend
```

Build Command:

```bash
chmod +x gradlew && ./gradlew clean build
```

Start Command:

```bash
java -Dserver.port=$PORT -jar build/libs/taskmanager-0.0.1-SNAPSHOT.jar
```

Nach dem Deployment sollte diese Route erreichbar sein:

```text
https://task-managers-14kw.onrender.com/api/tasks
```

### Frontend auf Render

Render-Service-Typ:

```text
Static Site
```

Root Directory:

```text
frontend
```

Build Command:

```bash
npm install && npm run build
```

Publish Directory:

```text
dist
```

Nach dem Deployment ist das Frontend erreichbar unter:

```text
https://task-managers-1-4934.onrender.com
```

### Wichtig für Render

Das Frontend verwendet für das Deployment diese Backend-URL:

```text
https://task-managers-14kw.onrender.com
```

Im Backend ist CORS aktuell für einfache Projekt- und Render-Tests offen:

```java
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://task-managers-1-4934.onrender.com"
})
```

## Aktueller Stand

- Backend stellt Tasks über REST-Routen bereit.
- Frontend ruft das Backend auf.
- Tasks können hinzugefügt, gelöscht und im Status geändert werden.
- Es gibt noch keine Datenbank.
- Neue Tasks bleiben nur bis zum Backend-Neustart gespeichert.
