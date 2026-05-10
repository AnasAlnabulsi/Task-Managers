# Task Manager

Ein einfaches Lernprojekt für eine persönliche Task-Manager-Webanwendung.

## Ziel

Die Anwendung soll später Aufgaben:

- hinzufügen
- bearbeiten
- löschen

Im Moment zeigt das Frontend bereits zwei Tasks aus dem Spring-Boot-Backend an.

## Projektstruktur

```text
task-manager/
├── README.md
├── frontend/
└── backend/
```

## Technologien

- Backend: Java, Spring Boot, Gradle
- Frontend: Vue.js, Vite, HTML, CSS, JavaScript

## Voraussetzungen

- Java 17
- Node.js

---

# Backend bauen

## Windows (PowerShell)

```powershell
cd backend
.\gradlew build
```

## macOS / Linux (zsh / bash)

```bash
cd backend
chmod +x gradlew
./gradlew build
```

---

# Backend starten

## Windows (PowerShell)

```powershell
.\gradlew bootRun
```

## macOS / Linux (zsh / bash)

```bash
./gradlew bootRun
```

Das Backend läuft dann unter:

```text
http://localhost:8080
```

Die aktuelle Test-Route ist:

```text
http://localhost:8080/api/tasks
```

---

# Backend testen

## Windows (PowerShell)

```powershell
.\gradlew test
```

## macOS / Linux (zsh / bash)

```bash
./gradlew test
```

---

# Frontend installieren

## Windows (PowerShell)

Im PowerShell-Terminal funktioniert auf diesem Projekt `npm.cmd` sicher:

```powershell
cd frontend
npm.cmd install
```

## macOS / Linux (zsh / bash)

```bash
cd frontend
npm install
```

---

# Frontend starten

## Windows (PowerShell)

```powershell
npm.cmd run dev
```

## macOS / Linux (zsh / bash)

```bash
npm run dev
```

Das Frontend läuft dann normalerweise unter:

```text
http://localhost:5173
```

---

# App lokal testen

## Windows

1. Starte zuerst das Backend mit `.\gradlew bootRun`.
2. Starte danach das Frontend mit `npm.cmd run dev`.
3. Öffne `http://localhost:5173` im Browser.
4. Wenn alles funktioniert, siehst du die beiden Tasks aus dem Backend im Frontend.

## macOS / Linux

1. Starte zuerst das Backend mit `./gradlew bootRun`.
2. Starte danach das Frontend mit `npm run dev`.
3. Öffne `http://localhost:5173` im Browser.
4. Wenn alles funktioniert, siehst du die beiden Tasks aus dem Backend im Frontend.

---

# Was aktuell funktioniert

- Spring-Boot-Backend mit GET-Route
- Vue-Frontend als Single Page Application
- Frontend ruft `/api/tasks` vom Backend auf
- Tasks werden mit `v-for` angezeigt