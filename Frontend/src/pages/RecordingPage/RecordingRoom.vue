<template>
  <!--  <Header />-->
  <div class="row flex justify-center q-px-none">
    <!--    <q-btn @click='sendMessage' label='testBtn'/>-->
    <div class="col-11">
      <div class="entire-container row">

        <!--        <div class="left-container col-2">-->
        <!--          <SideBar />-->
        <!--        </div>-->
        <div class="right-container q-pr-sm q-pt-sm q-pl-sm col-9">
          <SceneContainer />
          <side-bar></side-bar>
          <!--          <div class="script-controller-container row">-->
          <!--&lt;!&ndash;            <Script />&ndash;&gt;-->
          <!--&lt;!&ndash;            <SceneController />&ndash;&gt;-->
          <!--          </div>-->
        </div>
        <div class="left-container col-3 q-pt-sm">
          <Script />
          <SceneController />
          <init-my-video />
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div v-if='mainStreamManager'>
      <ov-video :stream-manager='mainStreamManager' id="connectPlayer" />
    </div>
    <ov-video v-for='sub in subscribers' :key='sub.stream.connection.connectionId' :stream-manager='sub'
              :id='sub.stream.streamId' />

  </div>
</template>


<script setup>
  // import Header from 'components/CommonComponents/Header.vue';
  import SideBar from 'components/RecordingPageComponents/SideBar.vue';
  import SceneContainer from 'components/RecordingPageComponents/MainScene.vue';
  import Script from 'components/RecordingPageComponents/Script.vue';
  import SceneController from 'components/RecordingPageComponents/SceneController.vue';
  import {ref, onMounted} from 'vue';
  import InitMyVideo from 'components/RecordingPageComponents/PublishMyVideo.vue';
  import OvVideo from 'components/RecordingPageComponents/OvVideo.vue';

  import {useOpenViduStore} from 'stores/openvidu';

  const ovstore = useOpenViduStore();
  const subscribers = ovstore.subscribers;
  const mainStreamManager = ovstore.mainStreamManager;

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

  .right-container {
    height: 100%;
    //border: blue solid 1px;
  }


  .script-controller-container {
    height: 30%;
  }


</style>
