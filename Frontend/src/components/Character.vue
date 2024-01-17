<template>
  <div class="q-pa-md row items-start q-gutter-md">
    <q-card class="my-card">
      <img src="https://cdn.quasar.dev/img/parallax1.jpg">
      <div class="text-center">{{ role }}</div>
    </q-card>
  </div>
</template>

<script setup>
  import { ref } from 'vue';
  const role = ref("거북이");
</script>

<style scoped>
  .my-card{
    width: 100%;
    max-width: 100px;
    color: black;
  }
</style>
