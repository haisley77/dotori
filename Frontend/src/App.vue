<template>
  <Header/>
  <router-view />
</template>
<script setup>
  import Header from 'components/CommonComponents/Header.vue';
</script>
<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'App',
});
</script>
<style>
  @font-face {
    font-family: 'NPSfontBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2310@1.0/NPSfontBold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
  }

  .npsfont {
    font-family: 'NPSfontBold';
  }

  .text-brand {
    color: #C7A96E !important;
  }
  .bg-brand {
    background: #C7A96E !important;
  }
</style>
