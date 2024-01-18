<script setup>
  const thumbStyle = {
    right: '1px',
    borderRadius: '5px',
    backgroundColor: '#a84d2f',
    width: '5px',
    opacity: 0.75,
  };

  const barStyle = {
    borderRadius: '9px',
    backgroundColor: '#a84d2f',
    width: '8px',
    opacity: 0.3,
  };
</script>


<template>
  <div class="entire-container row">
    <div class="left-container col-2">
      <q-scroll-area
        :thumb-style="thumbStyle"
        :bar-style="barStyle"
        style="height: 100%; max-width: 100%;"
      >
        <div v-for="n in 10" :key="n" class="q-px-sm">
          <div class="scene-short-info-container q-my-xs ">
            <div class="out-back q-pa-sm">
              <div class="in-back">
                <div class="scene-number-container text-center q-pa-sm">
                  <h6 class="npsfont white-background q-my-none">장면 {{ n }}</h6>
                </div>
                <div class="scene-thumbnail-container q-px-sm">
                  <img src="https://www.w3schools.com/howto/img_forest.jpg" alt="dja" style="width: 100%; border-radius: 15px;">
                </div>
              </div>
            </div>
          </div>
        </div>
      </q-scroll-area>
    </div>
    <div class="right-container q-pa-sm col-10">
      <div class="scene-info-and-controller-container">
        <div class="scene-info-container">
          <!-- 추후 컴포넌트로 분리예정 -->
          <div class="temp-container q-pa-xs out-back">
            <div class="scene-background-container">
              <q-img
                src="~assets/recording/tmp_scene_thumbnail.jpg"
                :ratio="16/9"
                style="height: 100%; border-radius: 15px"
              />
            </div>
            <div class="scene-script-container npsfont text-center">
              <div class="role-script-container">
                <p class="text-weight-bold q-ma-none">호랑이 : 어흥어흥 ~ 어흥어흥어흐얼어</p>
              </div>
              <div class="role-script-container">
                <p class="text-weight-bold q-ma-none">돼지: 꿀굴굴굴꿀...</p>
              </div>
              <div class="role-script-container">
                <p class="text-weight-bold q-ma-none">호랑이 : 어흥어흥 ~ 어흥어흥어흐얼어</p>
              </div>
              <div class="role-script-container">
                <p class="text-weight-bold q-ma-none">돼지: 꿀굴굴굴꿀...</p>
              </div>
            </div>
          </div>
        </div>
        <div class="controller-container row">
          <div class="left-button-container col-1">
            <q-btn round color="black" icon="mdi-arrow-left-bold" size="lg" />
          </div>
          <div class="center-button-container col-10">
            <div class="play-button-container">
              <q-btn round color="primary" icon="mdi-play" size="lg" />
            </div>
            <div class="stop-button-container">
              <q-btn outline round color="white" text-color="red" icon="mdi-stop" size="lg" />
            </div>
          </div>
          <div class="right-button-container col-1">
            <q-btn round color="black" icon="mdi-arrow-right-bold" size="lg" />
          </div>
        </div>
      </div>
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

  .entire-container {
    height: 100vh;
    //border: green solid 1px;
    background: #d2fff7;
  }

  .left-container {
    height: 100%;
    //border: red solid 1px;
  }

  .scene-short-info-container {
    //border: black solid 1px;
  }

  .scene-number-container{

  }

  .right-container {
    height: 100%;
    //border: blue solid 1px;
  }

  .scene-info-and-controller-container {
    height: 100%;
    //border: aqua solid 1px;
  }

  .scene-info-container {
    height: 90%;
    //border: black solid 1px;
  }

  .scene-background-container {
    height: 80%;
  }

  .scene-script-container {
    height: 20%;
    //border: dimgrey solid 1px;
    //display: flex;
    //justify-content: center;
    //align-items: center;
  }

  .scene-thumbnail-container {
    width: 100%;
  }


  .controller-container {
    height: 10%;
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

  .temp-container {
    height: 100%;
    //border: blue solid 1px;
  }

  .out-back {
    background: #35daa0;
    border-radius: 15px;
  }

  .in-back {
    background: #ebf686;
    border-radius: 15px;
  }

  .white-background{
    background: white;
    border-radius: 15px;
    width: 100%;
    border: #cc765a dashed 4px
  }
</style>
