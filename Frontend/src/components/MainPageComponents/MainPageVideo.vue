<script setup>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  const router = useRouter();
  const autoplay = ref(true);
  const isLoggedIn = ref(false); // 예시로 로그인 여부를 false로 설정.
  const videoSrc = 'src/assets/MainPage/MAIN2.mp4';
  const headText = 'DO STORY I';
  const bottomText = '이야기와 하나되는 아이들';

  const start = () => {
    if (isLoggedIn.value) {
      router.push('/list/books');
    } else {
      router.push('/login');
    }
  };
</script>

<template>
  <video width="100%" height="%" autoplay loop muted>
    <source :src='videoSrc' type="video/mp4">
  </video>
  <div class='absolute-center custom-caption'>
  <div class='npsfont text-h4 q-pa-sm' style='color: white;'>{{ headText }}</div>
  <div class='npsfont text-h5 q-pa-sm' style='color: white;'>{{ bottomText }}</div>
  <q-btn class="glossy" rounded color="secondary" label="시작하기" style='color: black; width: 150px; height: 50px; background: rgba(255, 255, 255, 0)' @click="start"></q-btn>

</div>


</template>

<style lang='scss' scoped>
  // .text-border {
  //   // text-shadow: -1px 0px #C7A96E, 0px 1px #C7A96E, 1px 0px #C7A96E, 0px -1px #C7A96E;
  // }
</style>
