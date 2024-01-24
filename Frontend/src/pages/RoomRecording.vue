<script setup>
  import Header from 'layouts/Header.vue';

  const thumbStyle = {
    right: '1.5px',
    borderRadius: '5px',
    backgroundColor: '#35daa0',
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


<template>
  <Header/>
  <div class="row flex justify-center q-px-none" >
    <div class="col-10">
      <div class="entire-container row">

        <div class="left-container col-2">
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
        </div>
        <div class="right-container q-pa-sm col-10">
          <div class="scene-info-container q-mb-sm ">
            <div class="q-pa-sm out-back" style="height: 100%">
              <div class="in-back q-pa-sm" style="height: 100%">
                <div class="scene-background-container">
                  <q-img
                    src="~assets/recording/tmp_scene_thumbnail.jpg"
                    :ratio="16/9"
                    style="height: 100%; border-radius: 15px"
                  />
                </div>
              </div>
            </div>
          </div>
          <div class="script-controller-container row">
            <div class="scene-script-container npsfont text-center col-8 q-pr-xs ">
              <div class="out-back q-pa-sm" style="height: 100%">
                <div class="in-back q-pa-sm" style="height: 100%">
                  <div class="script-background">
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
            </div>
            <div class="controller-container col-4 q-pl-xs">
              <div class="out-back q-pa-sm" style="height: 100%">
                <div class="in-back q-pa-sm" style="width: 100%; height: 100%">
                  <div class="button-container row">
                    <div class="left-button-container col-3">
                      <q-btn round color="grey-9" icon="mdi-arrow-left-bold" size="lg" />
                    </div>
                    <div class="center-button-container col-6">
                      <div class="play-button-container">
                        <q-btn round color="blue-12" icon="mdi-play" size="lg" />
                      </div>
                      <div class="stop-button-container">
                        <q-btn outline round color="white" text-color="red-5" icon="mdi-stop" size="lg" />
                      </div>
                    </div>
                    <div class="right-button-container col-3">
                      <q-btn round color="grey-9" icon="mdi-arrow-right-bold" size="lg" />
                    </div>
                  </div>
                </div>
              </div>
            </div>
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
    background: #35daa0;
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
    border: #cc765a dashed 4px
  }

  .script-background {
    background: white;
    border-radius: 15px;
    height: 100%;
    border: #cc765a dashed 4px;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  .button-container {
    background: white;
    border-radius: 15px;
    height: 100%;
    border: #cc765a dashed 4px;
  }

  .scene-bordering {
    border-radius: 15px;
    border: red solid 4px
  }
</style>
