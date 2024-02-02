

<template>
  <div class="q-mt-sm" style="height: 210px">
    <q-scroll-area
      :thumb-style="thumbStyle"
      :bar-style="barStyle"
      style="height: 210px; max-width: 100%;"
    >
      <div class = "flex no-wrap">
      <div v-for="n in 10" :key="n" class=" col-3 q-pr-sm">
        <div class="scene-short-info-container q-mb-sm">
          <!-- 추후 :class의 조건을 클릭된 상태인지 여부에 따라 변경 -->
          <div class="out-back q-pa-sm" :class="{'scene-bordering': n === 2}">
            <div class="in-back">
              <div class="scene-number-container text-center q-pa-sm">
                <h6 class="npsfont white-background q-my-none" @click="alert()">장면 {{ n }}</h6>
              </div>
              <div class="scene-thumbnail-container q-px-sm">
<!--                <img src="~assets/RecordingPage/rtpage01.png" alt="dja"-->
<!--                     style="width: 100%;height: 100px; border-radius: 15px;object-fit: contain ">-->
                <img src="~assets/MyPageImages/karina.jpg" alt="dja"
                     style="width: 100%;height: 100px; border-radius: 15px;object-fit: contain ">
              </div>
            </div>
          </div>
        </div>

      </div>
      </div>
    </q-scroll-area>
  </div>
</template>
<script setup>
  const thumbStyle = {
    right: '1.5px',
    borderRadius: '5px',
    backgroundColor: '#C7A96E',
    width: '5px',
    opacity: 0.75,
  };

  const barStyle = {
    borderRadius: '9px',
    backgroundColor: '#ffffff',
    width: '8px',
    opacity: 0,
  };
</script>
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

  .scene-short-info-container {
    //border: black solid 1px;
  }

  .scene-number-container {

  }


  .scene-thumbnail-container {
    width: 100%;
  }


  .out-back {
    background: #C7A96E;
    border-radius: 15px;
  }

  .in-back {
    background: white;
    border-radius: 15px;
  }

  .white-background {
    background: white;
    border-radius: 15px;
    width: 100%;
    border: #6E4E1F dashed 2px
  }

  .scene-bordering {
    border-radius: 15px;
    border: #6E4E1F solid 3px
  }
</style>
