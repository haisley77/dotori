<script setup>

</script>

<template>
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
    //border: #cc765a dashed 4px;
  }

  .scene-bordering {
    border-radius: 15px;
    border: red solid 4px
  }
</style>
