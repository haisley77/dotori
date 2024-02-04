<script setup>

import OvVideo from 'components/RecordingPageComponents/OvVideo.vue';
import {useOpenViduStore} from 'stores/openvidu';
const ovstore = useOpenViduStore();
</script>

<template>
  <div class="scene-info-container ">
    <div class="q-pa-sm out-back" style="height: 100%">
      <div class="in-back q-pa-sm" style="height: 100%">
        <div class="scene-background-container relative-position">
          <q-img
            src="~assets/MyPageImages/karina.jpg"
            :ratio="16/9"
            style="height: 100%; border-radius: 15px"
          />
          <div class='flex justify-center absolute-bottom q-ma-none q-pa-none'>
            <div v-if='ovstore.mainStreamManager' class='q-ma-none q-pa-none'>
              <ov-video :stream-manager='ovstore.mainStreamManager' :id='ovstore.mainStreamManager.stream.streamId' />
            </div>
            <ov-video v-for='sub in ovstore.subscribers' :key='sub.stream.connection.connectionId' :stream-manager='sub'
                      :id='sub.stream.streamId' />
          </div>
          <div id='canvasDiv'></div>
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


  .scene-info-container {
    height: 570px;
    //border: black solid 1px;
  }

  .scene-background-container {
    height: 100%;
  }


  .out-back {
    background: #C7A96E;
    border-radius: 15px;
  }

  .in-back {
    background: white;
    border-radius: 15px;
  }

</style>
