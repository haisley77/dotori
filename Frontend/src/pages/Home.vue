<template>
  <h4>list page</h4>
  <div class="home-container">
    <div >
      <Lside />
    </div>

    <div class="content-container">
      <List />
    </div>
  </div>
</template>

<script>
import Lside from '../components/Lside.vue';
import List from '../components/List.vue';

export default {
  components: {
    Lside,
    List,
  },
};
</script>

<style scoped>

html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}

.home-container {
  display: flex;
  flex-direction: row;
  /* align-items: center; */
}

.content-container {
  display: flex;
  flex-direction: row;
  width: 100%;
}
</style>
