<script setup>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  const router = useRouter();
  // const autoplay = ref(true);
  const isLoggedIn = ref(false); // 예시로 로그인 여부를 false로 설정.
  // const videoSrc = 'src/assets/MainPage/MAIN2.mp4';
  // const headText = 'DO STORY I';
  // const bottomText = '이야기와 하나되는 아이들';

  const start = () => {
    if (isLoggedIn.value) {
      router.push('/list/books');
    } else {
      router.push('/login');
    }
  };

  const slide = ref('first');
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
  <!-- <div class="video-container">
    <video autoplay loop muted>
      <source :src='videoSrc' type="video/mp4">
    </video>
  </div>
  <div class='absolute-center custom-caption'>
    <div class='npsfont text-h4 q-pa-sm' style='color: rgb(255, 255, 255);'>{{ headText }}</div>
    <div class='npsfont text-h5 q-pa-sm' style='color: white;'>{{ bottomText }}</div>
  </div> -->

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
  <q-btn class="glossy" rounded color="secondary" label="시작하기" style='z-index: 9999; color: black; width: 150px; height: 50px; background: rgba(255, 255, 255, 0)' @click="start"></q-btn>

</template>

<style lang='scss' scoped>
  .glossy{
    position: fixed;
    bottom: 120px; /* 하단으로부터의 거리 */
    left: 50%; /* 가운데 정렬을 위해 왼쪽 위치를 50%로 지정 */
    transform: translateX(-50%);
  }

  .video-container {
    width: 100vw; /* 뷰포트의 가로 크기 */
    height: 100vh; /* 뷰포트의 세로 크기 */
    overflow: hidden; /* 비디오가 부모 컨테이너를 벗어나지 않도록 함 */
  }

  video {
    width: 100%;
    height: 100%;
    object-fit: cover; /* 비디오를 컨테이너에 맞게 자동 조절 */
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

