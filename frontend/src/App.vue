<script setup>
import { onMounted, ref } from "vue";
import TaskList from "./components/TaskList.vue";

const API_BASE_URL = "https://task-managers-14kw.onrender.com";

// Hier speichern wir die geladenen Tasks.
const tasks = ref([]);
// loading steuert die Anzeige, solange die Daten noch geladen werden.
const loading = ref(true);
// Hier speichern wir eine Fehlermeldung, falls die Anfrage scheitert.
const errorMessage = ref("");
const newTask = ref({
  title: "",
  description: "",
  status: "TODO"
});

// Lädt die Tasks vom Spring-Backend.
async function loadTasks() {
  try {
    const response = await fetch(`${API_BASE_URL}/api/tasks`);

    // Wenn der Server keinen erfolgreichen Status liefert, erzeugen wir einen Fehler.
    if (!response.ok) {
      throw new Error("Die Tasks konnten nicht geladen werden.");
    }

    // Die JSON-Antwort des Backends wird in unser tasks-Array geschrieben.
    tasks.value = await response.json();
  } catch (error) {
    errorMessage.value = error.message;
  } finally {
    // Egal ob erfolgreich oder nicht: Das Laden ist danach beendet.
    loading.value = false;
  }
}

async function addTask() {
  if (!newTask.value.title.trim()) {
    errorMessage.value = "Bitte gib einen Titel ein.";
    return;
  }

  try {
    errorMessage.value = "";

    // Sendet den neuen Task per POST an das Backend; dort wird er in PostgreSQL gespeichert.
    const response = await fetch(`${API_BASE_URL}/api/tasks`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(newTask.value)
    });

    if (!response.ok) {
      throw new Error("Der Task konnte nicht hinzugefügt werden.");
    }

    const savedTask = await response.json();
    tasks.value.push(savedTask);
    newTask.value = {
      title: "",
      description: "",
      status: "TODO"
    };
  } catch (error) {
    errorMessage.value = error.message;
  }
}

async function updateTaskStatus(task, status) {
  try {
    errorMessage.value = "";

    const response = await fetch(`${API_BASE_URL}/api/tasks/${task.id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ ...task, status })
    });

    if (!response.ok) {
      throw new Error("Der Status konnte nicht geändert werden.");
    }

    const updatedTask = await response.json();
    const taskIndex = tasks.value.findIndex((currentTask) => currentTask.id === task.id);

    if (taskIndex !== -1) {
      tasks.value[taskIndex] = updatedTask;
    }
  } catch (error) {
    errorMessage.value = error.message;
  }
}

async function deleteTask(taskId) {
  try {
    errorMessage.value = "";

    const response = await fetch(`${API_BASE_URL}/api/tasks/${taskId}`, {
      method: "DELETE"
    });

    if (!response.ok) {
      throw new Error("Der Task konnte nicht gelöscht werden.");
    }

    tasks.value = tasks.value.filter((task) => task.id !== taskId);
  } catch (error) {
    errorMessage.value = error.message;
  }
}

// Sobald die Komponente angezeigt wird, laden wir die Daten.
onMounted(loadTasks);
</script>

<template>
  <main class="page">
    <section class="workspace">
      <header class="page-header">
        <p class="eyebrow">Persönlicher Task Manager</p>
        <h1>Meine Tasks</h1>
      </header>

      <form class="task-form" @submit.prevent="addTask">
        <label>
          Titel
          <input v-model="newTask.title" type="text" placeholder="Neue Aufgabe" />
        </label>
        <label>
          Beschreibung
          <input v-model="newTask.description" type="text" placeholder="Kurze Beschreibung" />
        </label>
        <label>
          Status
          <select v-model="newTask.status">
            <option value="TODO">TODO</option>
            <option value="In Arbeit">In Arbeit</option>
            <option value="Erledigt">Erledigt</option>
          </select>
        </label>
        <button type="submit">Task hinzufügen</button>
      </form>

      <p v-if="loading">Tasks werden geladen...</p>
      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
      <TaskList
        v-if="!loading"
        :tasks="tasks"
        @update-status="updateTaskStatus"
        @delete-task="deleteTask"
      />
    </section>
  </main>
</template>

<style>
:root {
  font-family: Inter, "Segoe UI", Arial, sans-serif;
  color: #172033;
  background: #eef2f7;
}

body {
  margin: 0;
}

button,
input,
select {
  font: inherit;
}

.page {
  min-height: 100vh;
  padding: 48px 24px;
  box-sizing: border-box;
  background:
    linear-gradient(135deg, rgba(37, 99, 235, 0.10), transparent 34%),
    linear-gradient(315deg, rgba(20, 184, 166, 0.12), transparent 30%),
    #eef2f7;
}

.workspace {
  width: min(980px, 100%);
  margin: 0 auto;
  background: white;
  border: 1px solid #dbe3ef;
  border-radius: 8px;
  padding: 32px;
  box-shadow: 0 20px 60px rgba(30, 41, 59, 0.10);
}

.page-header {
  border-bottom: 1px solid #e5eaf2;
  margin-bottom: 24px;
  padding-bottom: 20px;
}

.eyebrow {
  margin: 0 0 8px;
  font-size: 12px;
  font-weight: 800;
  letter-spacing: 0;
  color: #2563eb;
  text-transform: uppercase;
}

h1 {
  margin: 0;
  font-size: 36px;
  line-height: 1.1;
  color: #0f172a;
}

.task-form {
  display: grid;
  grid-template-columns: minmax(180px, 1fr) minmax(220px, 1.3fr) 150px auto;
  gap: 12px;
  align-items: end;
  margin-bottom: 24px;
  padding: 18px;
  background: #f8fafc;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
}

.task-form label {
  display: grid;
  gap: 6px;
  color: #475569;
  font-size: 13px;
  font-weight: 700;
}

.task-form input,
.task-form select,
.task-form button {
  min-height: 42px;
  box-sizing: border-box;
}

.task-form input,
.task-form select {
  width: 100%;
  border: 1px solid #cbd5e1;
  border-radius: 8px;
  padding: 10px 12px;
  background: white;
  color: #172033;
}

.task-form input:focus,
.task-form select:focus {
  border-color: #2563eb;
  box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.14);
  outline: none;
}

.task-form button {
  border: none;
  border-radius: 8px;
  padding: 0 18px;
  background: #1d4ed8;
  color: white;
  font-weight: 800;
  cursor: pointer;
  white-space: nowrap;
}

.task-form button:hover {
  background: #1e40af;
}

.error {
  margin: 0 0 16px;
  padding: 10px 12px;
  border-radius: 8px;
  background: #fef2f2;
  color: #b91c1c;
  font-weight: 700;
}

@media (max-width: 800px) {
  .page {
    padding: 24px 16px;
  }

  .workspace {
    padding: 22px;
  }

  h1 {
    font-size: 30px;
  }

  .task-form {
    grid-template-columns: 1fr;
  }
}
</style>
