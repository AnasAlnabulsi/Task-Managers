<script setup>
// Die Komponente bekommt die Task-Liste von der Elternkomponente App.vue.
defineProps({
  tasks: {
    type: Array,
    required: true
  }
});

const emit = defineEmits(["update-status", "delete-task"]);

function statusClass(status) {
  if (status === "Erledigt") {
    return "done";
  }

  if (status === "In Arbeit") {
    return "progress";
  }

  return "todo";
}
</script>

<template>
  <ul class="task-list">
    <!-- v-for rendert für jeden Task einen eigenen Listeneintrag. -->
    <li v-for="task in tasks" :key="task.id" class="task-item">
      <div class="task-content">
        <h2>{{ task.title }}</h2>
        <p>{{ task.description }}</p>
      </div>

      <div class="task-actions">
        <select
          :class="['status-select', statusClass(task.status)]"
          :value="task.status"
          @change="emit('update-status', task, $event.target.value)"
        >
          <option value="TODO">TODO</option>
          <option value="In Arbeit">In Arbeit</option>
          <option value="Erledigt">Erledigt</option>
        </select>
        <button class="delete-button" type="button" @click="emit('delete-task', task.id)">
          Löschen
        </button>
      </div>
    </li>
  </ul>
</template>

<style scoped>
.task-list {
  list-style: none;
  padding: 0;
  margin: 0;
  display: grid;
  gap: 12px;
}

.task-item {
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  padding: 18px;
  background: #ffffff;
  display: flex;
  justify-content: space-between;
  gap: 16px;
  align-items: flex-start;
  box-shadow: 0 1px 2px rgba(15, 23, 42, 0.04);
}

.task-item:hover {
  border-color: #cbd5e1;
  box-shadow: 0 8px 22px rgba(15, 23, 42, 0.07);
}

.task-content {
  min-width: 0;
}

.task-actions {
  display: flex;
  gap: 8px;
  align-items: center;
}

h2 {
  margin: 0 0 6px;
  font-size: 18px;
  line-height: 1.3;
  color: #0f172a;
}

p {
  margin-top: 0;
  margin-bottom: 0;
  color: #64748b;
  line-height: 1.45;
}

.status-select,
.delete-button {
  min-height: 34px;
  border: 1px solid transparent;
  border-radius: 8px;
  padding: 6px 10px;
  font-size: 14px;
  font-weight: 800;
  cursor: pointer;
}

.done {
  background: #ecfdf5;
  border-color: #bbf7d0;
  color: #166534;
}

.todo {
  background: #fff7ed;
  border-color: #fed7aa;
  color: #9a3412;
}

.progress {
  background: #eff6ff;
  border-color: #bfdbfe;
  color: #1d4ed8;
}

.delete-button {
  background: #f8fafc;
  border-color: #e2e8f0;
  color: #475569;
}

.delete-button:hover {
  background: #fef2f2;
  border-color: #fecaca;
  color: #b91c1c;
}

@media (max-width: 600px) {
  .task-item,
  .task-actions {
    flex-direction: column;
    align-items: stretch;
  }

  .task-actions {
    width: 100%;
  }
}
</style>
