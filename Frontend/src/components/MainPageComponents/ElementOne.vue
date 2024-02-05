<template>
  <div>
    <p v-for="(item, index) in items" :key="index">
      {{ item.name }} - {{ item.value }}
    </p>
    <button @click="changeValue">Change Value</button>
  </div>
</template>

<script setup>
  import {ref, watch} from 'vue';

  const items = ref([
    {id: 1, name: 'Item 1', value: {count: 10}},
    {id: 2, name: 'Item 2', value: {count: 20}},
    {id: 3, name: 'Item 3', value: {count: 30}},
  ]);

  const changeValue = () => {
    // 임의의 아이템의 값을 변경
    const randomIndex = Math.floor(Math.random() * items.value.length);
    items.value[randomIndex].value.count += 10;
  };

  watch(items, (newItems, oldItems) => {
    // 객체 배열의 값이 변경될 때 실행되는 콜백
    console.log('Items changed:', newItems);
    console.log('Old items:', oldItems);
  }, {deep: true});
</script>

<style scoped>

</style>
