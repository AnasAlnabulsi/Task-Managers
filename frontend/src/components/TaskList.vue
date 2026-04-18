<script setup>
// Die Komponente bekommt die Task-Liste von der Elternkomponente App.vue.
defineProps({
  tasks: {
    type: Array,
    required: true
  }
});
</script>

<template>
  <ul class="task-list">
    <!-- v-for rendert für jeden Task einen eigenen Listeneintrag. -->
    <li v-for="task in tasks" :key="task.id" class="task-item">
      <h2>{{ task.title }}</h2>
      <p>{{ task.description }}</p>
      <span :class="task.completed ? 'status done' : 'status open'">
        {{ task.completed ? "Erledigt" : "Offen" }}
      </span>
    </li>
  </ul>
</template>

<style scoped>
.task-list {
  /* Entfernt Standard-Listenstil und setzt Abstand zwischen den Karten. */
  list-style: none;
  padding: 0;
  margin: 0;
  display: grid;
  gap: 16px;
}

.task-item {
  /* Jede Aufgabe wird als einfache Karte dargestellt. */
  border: 1px solid #d1d5db;
  border-radius: 12px;
  padding: 16px;
}

h2 {
  margin-top: 0;
  margin-bottom: 8px;
  font-size: 20px;
}

p {
  margin-top: 0;
  margin-bottom: 12px;
}

.status {
  display: inline-block;
  padding: 6px 10px;
  border-radius: 999px;
  font-size: 14px;
  font-weight: bold;
}

.done {
  background: #dcfce7;
  color: #166534;
}

.open {
  background: #fee2e2;
  color: #991b1b;
}
</style>
