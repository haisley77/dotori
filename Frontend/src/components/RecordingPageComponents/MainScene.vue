<script setup>

  import OvVideo from 'components/RecordingPageComponents/OvVideo.vue';
  import {useOpenViduStore} from 'stores/openvidu';
  import {ref} from 'vue';

  const ovstore = useOpenViduStore();
  const props = defineProps({curPage: Number, currentScene: Object});
  // const imagesrc = ref(ovstore.bookInfoList[props.curPage - 1].img);

</script>

<template>

  <div class='scene-info-container q-pa-sm'>

    <div class=' ' style='height: 100%;border-radius: 15px'>
      <div class='scene-background-container relative-position'>
        <q-img
          class='q-pa-xs'
          :src='props.currentScene.backgroundImage'
          :ratio='16/9'
          style='height: 100%; '
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
    //border-radius: 15px; border: #C7A96E solid 6px;
  }

  .scene-background-container {
    height: 100%;
  }


</style>
