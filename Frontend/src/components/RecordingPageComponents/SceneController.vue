<script setup>
  import {useOpenViduStore} from 'stores/openvidu';
  import {onMounted} from 'vue';
  import axios from 'axios';

  const ovstore = useOpenViduStore();
  const props = defineProps(
    {
      curPage: Number,
      customLayoutFolder: String
    },
  );
  const emit = defineEmits(['moveToPage']);

  let openViduRecordingId;
  const sessionId = ovstore.session.sessionId;

  const openviduAxios = axios.create({
    baseURL: 'https://dotori.online',
    headers: {
      'Authorization': 'Basic T1BFTlZJRFVBUFA6MTBTU0FGWUE1MDI=',
      'Content-Type': 'application/json',
    },
  });

  const nextPage = () => {
    if (ovstore.bookDetail.scenes.length > props.curPage) {
      //방장일 경우만 실행하도록 로직을 추가해야함
      ovstore.session.signal({
          data: props.curPage + 1,
          type: 'page',
        }
      );
      emit('moveToPage', props.curPage + 1);
    } else {
      console.log('마지막 페이지 입니다!');
    }
  };

  const beforePage = () => {
    if (1 < props.curPage) {
        //방장일 경우만 실행하도록 로직을 추가해야함
        ovstore.session.signal({
                data: props.curPage - 1,
                type: 'page',
            }
        );
      emit('moveToPage', props.curPage - 1);
    } else {
      console.log('첫번째 페이지 입니다!');
    }
  };

  const startOpenViduRecording = () => {
    openviduAxios.post('/openvidu/api/recordings/start',
      {
        "session": sessionId,
        "name": "TestRecording",
        "hasAudio": true,
        "hasVideo": true,
        "outputMode": "COMPOSED",
        "recordingLayout": "CUSTOM",
        "customLayout":  props.customLayoutFolder +  '/scene-' + props.curPage,
        "resolution": "1280x640",
        "frameRate": 30,
        "shmSize": 536870912,
        "ignoreFailedStreams": false,
      })
      .then((response) => {
        console.log('id : ' + response.data.id);
        console.log('name : ' + response.data.name);
        console.log('hasAudio : ' + response.data.hasAudio);
        console.log('hasAudio : ' + response.data.hasVideo);
        console.log('outputMode : ' + response.data.outputMode);
        console.log('recordingLayout : ' + response.data.recordingLayout);
        console.log('customLayout : ' + response.data.customLayout);
        console.log('frameRate : ' + response.data.frameRate);
        openViduRecordingId = response.data.id;
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const stopOpenViduRecording = () => {
    openviduAxios.post(`/openvidu/api/recordings/stop/${openViduRecordingId}`)
      .then((response) => {
        console.log('id : ' + response.data.id);
        console.log('hasAudio : ' + response.data.hasAudio);
        console.log('hasAudio : ' + response.data.hasVideo);
        console.log('outputMode : ' + response.data.outputMode);
        console.log('recordingLayout : ' + response.data.recordingLayout);
        console.log('frameRate : ' + response.data.frameRate);
        console.log('url : ' + response.data.url);
      })
      .catch((error) => {
        console.log(error);
      });
  };

</script>

<template>
  <div class='controller-container col-4 q-pt-sm'>
    <div class='out-back ' style='height: 100%'>
      <div class='in-back q-pa-sm' style='width: 100%; height: 100%'>
        <div class='button-container row'>
          <div class='left-button-container col-3'>
            <q-btn round color='grey-9' icon='mdi-arrow-left-bold' size='lg' @click='beforePage' />
          </div>
          <div class='center-button-container col-6'>
            <div class='play-button-container'>
              <q-btn round color='blue-12' icon='mdi-play' size='lg' @click="startOpenViduRecording" />
            </div>
            <div class='stop-button-container'>
              <q-btn outline round color='white' text-color='red-5' icon='mdi-stop' size='lg' @click="stopOpenViduRecording" />
            </div>
          </div>
          <div class='right-button-container col-3'>
            <q-btn round color='grey-9' icon='mdi-arrow-right-bold' size='lg' @click='nextPage' />
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


  .controller-container {
    height: 180px;
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
    padding: 6px;
    border-radius: 15px;
  }

  .in-back {
  //background: #ffee9a; background: #ffffff; border-radius: 15px;
  }

  .button-container {
    background: white;
    border-radius: 15px;
    height: 100%;
  //border: #cc765a dashed 4px;
  }

</style>
