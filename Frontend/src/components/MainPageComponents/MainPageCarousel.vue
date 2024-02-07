<script setup>
  import { ref } from 'vue';

  const slide = ref('first');
  // 메인페이지 캐러셀 자동반복
  const autoplay = ref(true);
  const slides = ref([
    {
      type: 'image',
      img: 'src/assets/MyPageImages/karina.jpg',
      name: 'first',
      headText: 'DO STORY I',
      bottomText: '이야기와 하나되는 아이들',
    },
    {
      type: 'video',
      video: 'src/assets/MainPage/Main2.mp4',
      name: 'second',
      headText: 'DO STORY I',
      bottomText: '이야기와 하나되는 아이들',
    },
    {
      type: 'image',
      img: 'src/assets/MyPageImages/iupic.jpg',
      name: 'third',
      headText: 'DO STORY I',
      bottomText: '이야기와 하나되는 아이들',
    }
  ]);
</script>

<template>
  <q-carousel
    navigation
    infinite
    :autoplay='autoplay'
    animated
    v-model='slide'
    height='91vh'
    :interval="5000"
  >
    <q-carousel-slide
      v-for='(slide, index) in slides'
      :key='index'
      :name='slide.name'
    >
      <template v-if="slide.type === 'image'">
        <img :src="slide.img" width="100%" height="100%" />
      </template>
      <template v-else-if="slide.type === 'video'">
        <div class="video-wrapper">
          <video autoplay loop muted style="object-fit: cover;">
            <source :src="slide.video" type="video/mp4">
          </video>
        </div>
      </template>
      <div class='npsfont absolute-bottom custom-caption'>
        <div class='npsfont text-h2 npsfont q-pa-sm' style='color: white;'>{{ slide.headText }}</div>
        <div class='npsfont text-h3 npsfont q-pa-sm ' style='color: white;'>{{ slide.bottomText }}</div>
      </div>
    </q-carousel-slide>
  </q-carousel>
</template>

<style lang='scss' scoped>
  .text-border {
    text-shadow: -1px 0px #C7A96E, 0px 1px #C7A96E, 1px 0px #C7A96E, 0px -1px #C7A96E;
  }

  .video-wrapper {
    width: 100%;
    height: 100%;
    overflow: hidden;
  }

  .video-wrapper video {
    width: 100%;
    height: 100%;
  }
</style>
