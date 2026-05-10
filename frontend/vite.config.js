import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// Vite bekommt hier das Vue-Plugin, damit .vue-Dateien verarbeitet werden.
export default defineConfig({
  plugins: [vue()]
});
