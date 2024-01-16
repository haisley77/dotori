<template>
  <div>
    <ul>
      <li v-for="item in items" :key="item.id">{{ item.name }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        { id: 1, name: 'Item 1' },
        { id: 2, name: 'Item 2' },
        { id: 3, name: 'Item 3' },
      ]
    };
  }
};
</script>

<style scoped>
.list-container {
  flex: 1; /* 우측 컴포넌트가 좌측 컴포넌트보다 더 많은 공간을 차지하도록 함 */
  background-color: lightblue;
  padding: 10px;
}

ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 5px;
  padding: 10px;
  border: 1px solid #ddd;
}
</style>
