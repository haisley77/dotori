<script setup>
  import { ref } from 'vue';

  const autoplay = ref(true);
  const videoSrc = 'src/assets/MainPage/MAIN2.mp4';
  const headText = 'DO STORY I';
  const bottomText = '이야기와 하나되는 아이들';
</script>

<template>
  <video width="100%" height="%" autoplay loop muted>
    <source :src='videoSrc' type="video/mp4">
  </video>
  <div class='absolute-center custom-caption'>
    <div class='npsfont text-h4 q-pa-sm' style='color: white;'>{{ headText }}</div>
    <div class='npsfont text-h5 q-pa-sm' style='color: white;'>{{ bottomText }}</div>
  </div>
</template>

<style lang='scss' scoped>
  .text-border {
    text-shadow: -1px 0px #C7A96E, 0px 1px #C7A96E, 1px 0px #C7A96E, 0px -1px #C7A96E;
  }

  .absolute-center {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
