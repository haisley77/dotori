

<template>
  <div class="q-py-sm" style="height: 100%">
    <q-scroll-area
      :thumb-style="thumbStyle"
      :bar-style="barStyle"
      style="height: 100%; max-width: 100%;"
    >
      <div v-for="n in 10" :key="n" class="q-px-sm q-mr-sm">
        <div class="scene-short-info-container q-mb-sm">
          <!-- 추후 :class의 조건을 클릭된 상태인지 여부에 따라 변경 -->
          <div class="out-back q-pa-sm" :class="{'scene-bordering': n === 2}">
            <div class="in-back">
              <div class="scene-number-container text-center q-pa-sm">
                <h6 class="npsfont white-background q-my-none" @click="alert()">장면 {{ n }}</h6>
              </div>
              <div class="scene-thumbnail-container q-px-sm">
                <img src="https://www.w3schools.com/howto/img_forest.jpg" alt="dja"
                     style="width: 100%; border-radius: 15px;">
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

  .entire-container {
    height: 85vh;
    //border: green solid 1px;
    //background: #d2fff7;
  }

  .left-container {
    height: 100%;
    //border: red solid 1px;
  }

  .scene-short-info-container {
    //border: black solid 1px;
  }

  .scene-number-container {

  }

  .right-container {
    height: 100%;
    //border: blue solid 1px;
  }

  .scene-info-container {
    height: 70%;
    //border: black solid 1px;
  }

  .scene-background-container {
    height: 100%;
  }

  .script-controller-container {
    height: 30%;
  }

  .scene-script-container {
    height: 100%;
    //border: dimgrey solid 1px;
    //display: flex;
    //justify-content: center;
    //align-items: center;
  }

  .scene-thumbnail-container {
    width: 100%;
  }


  .controller-container {
    height: 100%;
    //border: black solid 1px;
  }

  .left-button-container {
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .center-button-container {
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .right-button-container {
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .play-button-container {
    margin-right: 10px;
  }

  .stop-button-container {
    margin-left: 10px;
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
    border: #6E4E1F dashed 4px
  }

  .script-background {
    background: white;
    border-radius: 15px;
    height: 100%;
    border: #6E4E1F dashed 4px;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  .button-container {
    background: white;
    border-radius: 15px;
    height: 100%;
    border: #6E4E1F dashed 4px;
  }

  .scene-bordering {
    border-radius: 15px;
    border: red solid 4px
  }
</style>
