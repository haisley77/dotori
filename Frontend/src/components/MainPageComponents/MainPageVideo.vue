<script setup>
  import {ref} from 'vue';
  import {useRouter} from 'vue-router';
  import {useOpenViduStore} from 'stores/openvidu';

  const ovstore = useOpenViduStore();
  const router = useRouter();

  const autoplay = ref(true);
  const videoSrc = 'src/assets/MainPage/mainpage1.mp4';
  const headText = '도토리와 함께 책 하자!';
  const bottomText = 'DO your sTORy, with DOTORI.';

  const start = () => {
    if (ovstore.isLoggedIn) {
      router.push('/list/books');
    } else {
      router.push('/login');
    }
  };

</script>

<template>
  <div class="video-container">
    <video autoplay loop muted>
      <source :src='videoSrc' type="video/mp4">
    </video>
  </div>
  <div class='absolute-center custom-caption'>
    <div class='npsfont text-h3 q-pa-sm text-center' style='color: white;'>{{ headText }}</div>
    <div class='npsfont text-h5 q-pa-sm text-center' style='color: white;'>{{ bottomText }}</div>
  </div>
  <div class="npsfont btn-3d yellow" @click="start">시작하기</div>

</template>

<style lang='scss' scoped>

  .glossy {
    bottom: 120px; /* 하단으로부터의 거리 */
    left: 50%; /* 가운데 정렬을 위해 왼쪽 위치를 50%로 지정 */
  }

  .video-container {
    width: 98.9vw; /* 뷰포트의 가로 크기 */
    height: 100vh; /* 뷰포트의 세로 크기 */
    overflow: hidden; /* 비디오가 부모 컨테이너를 벗어나지 않도록 함 */
  }

  video {
    width: 100%;
    height: 100%;
    object-fit: cover; /* 비디오를 컨테이너에 맞게 자동 조절 */
  }


</style>

<style scoped>
  /* 3D Button */
  .btn-3d {
    z-index: 9999;
    cursor: pointer;
    position: fixed;
    width: 230px;
    height: 65px;
    bottom: 120px; /* 하단으로부터의 거리 */
    left: 50%; /* 가운데 정렬을 위해 왼쪽 위치를 50%로 지정 */
    transform: translateX(-50%);
    display: inline-block;
    font-size: 20px;
    padding: 20px 60px;
    color: #191919;
    margin: 20px 10px 10px;
    border-radius: 300px;
    text-align: center;
    transition: top .01s linear;
    text-shadow: 0 1px 0 rgba(0,0,0,0.15);
  }
  .btn-3d.yellow:hover {
    background-color: #fae061;
  }

  .btn-3d.yellow {
    background-color: #ffec6b;
    box-shadow: 0 0 0 1px #F0D264 inset,
    0 0 0 1px rgb(197, 188, 170) inset,
    0 3px 0 0 rgba(255, 238, 141, 0.7),
    0 8px 8px 0 rgba(0,0,0,0.5);
  }
  .btn-3d.yellow:active {
    box-shadow: 0 0 0 1px #ffec6b inset;
  }

</style>
