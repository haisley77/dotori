<template>
  <div class="list-container">
    <ul class="list-items">
      <li v-for="item in items" :key="item.id">{{ item.name }} 왓더왓ㄷ어어아럳랸ㄷㄹ</li>
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
        { id: 1, name: 'Item 1' },
        { id: 2, name: 'Item 2' },
        { id: 3, name: 'Item 3' }
      ]
    };
  }
};
</script>

<style scoped>
.list-container {
  flex: 1;
  background-color: lightblue;
  padding: 10px;
  display: flex; /* 부모인 .list-container를 flex 컨테이너로 설정 */
}

.list-items {
  list-style-type: none;
  padding: 0;
  display: flex; /* 자식인 ul을 flex 컨테이너로 설정 */
  flex-wrap: wrap; /* 요소가 넘칠 경우 다음 줄에 배치하도록 설정 */
  width : 50px;
}

li {
  margin: 5px;
  padding: 10px;
  border: 1px solid #ddd;
}
</style>
