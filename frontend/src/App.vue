<script setup>
import { onMounted, ref } from "vue";
import TaskList from "./components/TaskList.vue";

// Hier speichern wir die geladenen Tasks.
const tasks = ref([]);
// loading steuert die Anzeige, solange die Daten noch geladen werden.
const loading = ref(true);
// Hier speichern wir eine Fehlermeldung, falls die Anfrage scheitert.
const errorMessage = ref("");

// Lädt die Tasks vom Spring-Backend.
async function loadTasks() {
  try {
    const response = await fetch("http://localhost:8080/api/tasks");

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

// Sobald die Komponente angezeigt wird, laden wir die Daten.
onMounted(loadTasks);
</script>

<template>
  <main class="page">
    <section class="card">
      <p class="eyebrow">Persönlicher Task Manager</p>
      <h1>Meine Tasks</h1>

      <p v-if="loading">Tasks werden geladen...</p>
      <p v-else-if="errorMessage">{{ errorMessage }}</p>
      <TaskList v-else :tasks="tasks" />
    </section>
  </main>
</template>

<style>
:root {
  /* Grundlegende Standards für Schrift, Textfarbe und Hintergrund. */
  font-family: Arial, sans-serif;
  color: #1f2937;
  background: #f3f4f6;
}

body {
  margin: 0;
}

.page {
  /* Zentriert die Karte auf der Seite. */
  min-height: 100vh;
  display: grid;
  place-items: center;
  padding: 24px;
}

.card {
  /* Einfache Kartenoptik für den Hauptinhalt. */
  width: min(700px, 100%);
  background: white;
  border-radius: 16px;
  padding: 24px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
}

.eyebrow {
  margin: 0 0 8px;
  font-size: 14px;
  font-weight: bold;
  color: #2563eb;
  text-transform: uppercase;
}

h1 {
  margin-top: 0;
  margin-bottom: 20px;
}
</style>
