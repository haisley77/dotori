<template>
  <header>
    <h4> header입니다. </h4>
  </header>

  <div class="home-container">
    <div >
      <Lside />
      <RouterLink :to="{name : 'Booklist'}">방만들기</RouterLink>
    </div>

    <div class="content-container">
      <Roomlist />
    </div>

    <RouterView/>
  </div>
</template>

<script>
import Lside from '../components/Lside.vue';
import Roomlist from '../components/Roomlist.vue';


export default {
  components: {
    Lside,
    Roomlist,
  },
};
</script>

<style scoped>
h4{
  margin : 0px;
}


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
