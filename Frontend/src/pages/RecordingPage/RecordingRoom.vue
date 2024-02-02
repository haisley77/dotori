<template>
  <!--  <Header />-->
  <div class="row">
        <q-btn color="brown-5" class="col-2 npsfont" label="방만들기" @click="connectToNewSession"></q-btn>
        <div class="col-2"></div>
        <q-input color="brown-5" class="col-2 npsfont" v-model='sessionId'></q-input>
        <q-btn color="brown-5" class="col-2 npsfont" label="방참가" @click="connectToAnotherSession"></q-btn>
    <!--    onMount시점에 내 비디오를 켜고, 캔버스를 생성해서 모델을 입힌다.-->
    <!--    비디오와 캔버스를 displaynone하고-->
    <!--    publish가 된다-->
  </div>
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
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>
  // import Header from 'components/CommonComponents/Header.vue';
  import SideBar from 'components/RecordingPageComponents/SideBar.vue';
  import SceneContainer from 'components/RecordingPageComponents/MainScene.vue';
  import Script from 'components/RecordingPageComponents/Script.vue';
  import SceneController from 'components/RecordingPageComponents/SceneController.vue';
  import {ref,onMounted} from 'vue';
  import {useOpenViduStore2} from 'stores/openvidu2';

  const ovstore2 = useOpenViduStore2();
  // import {useOpenViduStore} from 'stores/openvidu';
  //
  // const openViduStore = useOpenViduStore();
  // // const text = ref('');
  // const sendMessage = () => {
  //   console.log("btnclick")
  //   openViduStore.sendText('hello!!!');
  // };
  // const thumbStyle = {
  //   right: '1.5px',
  //   borderRadius: '5px',
  //   backgroundColor: '#C7A96E',
  //   width: '5px',
  //   opacity: 0.75,
  // };
  //
  // const barStyle = {
  //   borderRadius: '9px',
  //   backgroundColor: '#ffffff',
  //   width: '8px',
  //   opacity: 0,
  // };

  const sessionId = ref('');
  const connectToNewSession = () => {
    ovstore2.connectToNewSession();
  };
  const connectToAnotherSession = () => {
    ovstore2.connectToAnotherSession(sessionId.value);
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

  .right-container {
    height: 100%;
    //border: blue solid 1px;
  }


  .script-controller-container {
    height: 30%;
  }


</style>
