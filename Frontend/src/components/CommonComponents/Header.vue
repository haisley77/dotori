<script setup>
  import {useRouter} from 'vue-router';

  const router = useRouter();
  const moveMyPage = () => {
    router.push('/my-page/info');

  };
  const moveMainPage = ()=>{
    router.push('/');
  }

</script>

<template>
  <div class='row header-bg q-pt-sm q-pb-sm' style='background: #ffffff'>
    <!--    로고-->
    <div class='col-2 offset-0 flex justify-center items-center ' style='cursor: pointer'>
<!--      <img src='../../assets/DotoriImages/logowithborder-removebg.png' alt='logo' width='70%' @click='moveMainPage'>-->
    </div>
    <!--페이지 위치-->
    <div class='col-4 offset-2 flex justify-center items-center'>
<!--      <h5 class='q-my-none npsfont'>연극 대기실</h5>-->
      <div class='col-2 offset-0 flex justify-center items-center ' style='cursor: pointer'>
        <img src='../../assets/DotoriImages/dotorisq.png' alt='logo' width='40%' @click='moveMainPage'>
      </div>
    </div>
    <!-- 마이페이지와 로그인-->
    <!-- 마이페이지-->
    <div class='col-2  flex items-center justify-end npsfont'>
      <!--      <h6 class='q-ma-none'>-->
      <!--        마이페이지-->
      <!--      </h6>-->
      <q-btn flat class='text-h6 q-ma-none' color='brand' @click='moveMyPage'><span style='color: black'>마이페이지</span>
        &nbsp&nbsp
        <svg xmlns='http://www.w3.org/2000/svg' height='28' width='35' viewBox='0 0 640 512'>
          <!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
          <path fill='#C7A96E'
                d='M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H322.8c-3.1-8.8-3.7-18.4-1.4-27.8l15-60.1c2.8-11.3 8.6-21.5 16.8-29.7l40.3-40.3c-32.1-31-75.7-50.1-123.9-50.1H178.3zm435.5-68.3c-15.6-15.6-40.9-15.6-56.6 0l-29.4 29.4 71 71 29.4-29.4c15.6-15.6 15.6-40.9 0-56.6l-14.4-14.4zM375.9 417c-4.1 4.1-7 9.2-8.4 14.9l-15 60.1c-1.4 5.5 .2 11.2 4.2 15.2s9.7 5.6 15.2 4.2l60.1-15c5.6-1.4 10.8-4.3 14.9-8.4L576.1 358.7l-71-71L375.9 417z' />
        </svg>
      </q-btn>
    </div>
    <!-- 로그인-->
    <div class='col-2 flex items-center justify-center npsfont'>
      <q-btn flat class='text-h6 q-ma-none' color='brand' @click='moveMyPage'><span style='color: black'>로그인</span>
        &nbsp&nbsp
        <svg xmlns='http://www.w3.org/2000/svg' height='28' width='28' viewBox='0 0 512 512'>
          <!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
          <path fill='#C7A96E'
                d='M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z' />
        </svg>
      </q-btn>
    </div>


  </div>
</template>

<style scoped>
  @font-face {
    font-family: 'NPSfontBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2310@1.0/NPSfontBold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
  }

  .npsfont {
    font-family: 'NPSfontBold';
  }

</style>
