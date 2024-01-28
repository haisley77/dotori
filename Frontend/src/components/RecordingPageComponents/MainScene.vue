<script setup>

</script>

<template>
  <div class="scene-info-container q-mb-sm ">
    <div class="q-pa-sm out-back" style="height: 100%">
      <div class="in-back q-pa-sm" style="height: 100%">
        <div class="scene-background-container">
          <q-img
            src="~assets/RecordingPage/tmp_scene_thumbnail.jpg"
            :ratio="16/9"
            style="height: 100%; border-radius: 15px"
          />
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
