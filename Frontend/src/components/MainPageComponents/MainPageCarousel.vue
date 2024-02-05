<script setup>
  import {ref, onMounted, watchEffect} from 'vue';

  const slide = ref('first');
  //메인페이지 캐러셀 자동반복
  const autoplay = ref(true);
  const slides = ref([{
    img: 'src/assets/MyPageImages/karina.jpg',
    name: 'first',
    headText: 'FirstStop',
    bottomText: 'this is first stop',

  }, {
    img: 'src/assets/MyPageImages/winter.png',
    name: 'second',
    headText: 'secondStop',
    bottomText: 'this is second stop',

  }]);
</script>

<template>
  <q-carousel

    infinite
    :autoplay="autoplay"
    animated
    v-model="slide"
    height="600px"
  >
    <q-carousel-slide
      v-for="(slide, index) in slides"
      :key="index"
      :name="slide.name"
      :img-src="slide.img"
    >
      <div class="absolute-bottom custom-caption">
        <div class="text-h2 npsfont">{{ slide.headText }}</div>
        <div class="text-subtitle1 npsfont">{{ slide.bottomText }}</div>
      </div>
    </q-carousel-slide>
  </q-carousel>
</template>

<style lang="scss" scoped>

</style>
