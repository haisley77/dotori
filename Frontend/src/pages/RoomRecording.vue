<script setup>
  const thumbStyle = {
    right: '1px',
    borderRadius: '5px',
    backgroundColor: '#b97a60',
    width: '5px',
    opacity: 0.75
  };

  const barStyle = {
    borderRadius: '9px',
    backgroundColor: '#b97a60',
    width: '8px',
    opacity: 0.3
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
        <div v-for="n in 10" :key="n" class="q-px-md">
          <div class="scene-short-info-container q-my-md ">
            <div class="scene-number-container q-ml-xs">
              <p class="text-h6">장면 {{n}}</p>
            </div>
            <div class="scene-thumbnail-container q-px-xs">
              <img src="https://www.w3schools.com/howto/img_forest.jpg" alt="dja" style="width: 100%">
            </div>
          </div>
        </div>
      </q-scroll-area>
    </div>
    <div class="right-container q-pa-sm col-10">
      <div class="scene-info-and-controller-container">
        <div class="scene-info-container">
          <div class="temp-container">

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
  .entire-container {
    height: 100vh;
    border: green solid 1px;
  }

  .left-container {
    height: 100%;
    border: red solid 1px;
  }

  .scene-short-info-container{
    border: black solid 1px;
  }

  .right-container {
    height: 100%;
    border: blue solid 1px;
  }

  .scene-info-and-controller-container {
    height: 100%;
    border: aqua solid 1px;
  }

  .scene-info-container {
    height: 90%;
    border: black solid 1px;
  }

  .scene-thumbnail-container{
    width: 100%;
  }


  .controller-container {
    height: 10%;
    border: black solid 1px;
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

</style>
