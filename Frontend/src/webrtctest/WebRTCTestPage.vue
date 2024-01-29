<script setup>
  import {ref, onMounted} from 'vue';
  import axios from 'axios';
  import {OpenVidu} from 'openvidu-browser';
  import OvVideo from 'src/webrtctest/OvVideo.vue';

  // import UserVideo from 'src/webrtctest/UserVideo.vue';
  const OV = new OpenVidu();
  const session = OV.initSession();
  const publisher = OV.initPublisher('publisherElement', {insertMode: 'APPEND'});

  const subscribers = ref([]);
  const mainStreamManager = ref();
  // -------------------------------------------------------------------------------------------------------------


  const instance = axios.create({
    baseURL: 'https://dotori.online',
    headers: {
      'Authorization': 'Basic T1BFTlZJRFVBUFA6MTBTU0FGWUE1MDI=',
      'Content-Type': 'application/json',
    },

  });

  const sessionId = ref('default');
  const token = ref('');


  const createSession = () => {
    instance.post('/openvidu/api/sessions')
      .then((response) => {
        console.log(response);
        console.log('세션아이디 : ' + response.data.id);
        sessionId.value = response.data.id;
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const createConnection = () => {
    instance.post('/openvidu/api/sessions/' + sessionId.value + '/connection')
      .then((response) => {
        console.log(response);
        console.log('토큰 : ' + response.data.token);
        token.value = response.data.token;

      })
      .catch((error) => {
        console.log(error);
      });
  };


  const joinRoom = () => {

    //세션 설정을 하자
    // 세션에 스트림이 생기면 subscriber를 추가한다(구독자)
    session.on('streamCreated', ({stream}) => {
      const subscriber = session.subscribe(stream.stream);
      subscribers.value.push(subscriber);
    });

    // 세션에서 스트림이 삭제되면 subscriber를 삭제한다(구독 취소)
    session.on('streamDestroyed', ({stream}) => {
      const index = subscribers.value.indexOf(stream.streamManager, 0);
      if (index >= 0) {
        subscribers.value.splice(index, 1);
      }
    });

    //세션에 연결한다
    session.connect(token.value).then(() => {
      console.log('연결 성공!!');
      //연결을 성공했으면 publish할 준비를 한다
      let publisherA = OV.initPublisher(undefined, {
        //audioSource: undefined, // The source of audio. If undefined default microphone
        //videoSource: undefined, // The source of video. If undefined default webcam
        publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
        publishVideo: true, // Whether you want to start publishing with your video enabled or not
        resolution: '640x480', // The resolution of your video
        frameRate: 30, // The frame rate of your video
        insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
        //mirror: false, // Whether to mirror your local video or not
      });
      // Set the main video in the page to display our webcam and store our Publisher
      mainStreamManager.value = publisherA;
      publisher.value = publisherA;


    }).catch((error) => {
      console.log(error);
      console.log('연결 실패!!');
    });
  };


  const updateMainVideoStreamManager = (stream) => {
    if (mainStreamManager.value === stream) return;
    mainStreamManager.value = stream;
  };
</script>
<!-- -&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;-->
<template>
  <h1>WELCOME TO TEST PAGE</h1>
  sessionId : {{ sessionId }}<br />
  token : {{ token }}<br />
  <div class="row">
    <h6 class="col-2 flex items-center q-ma-none">세션 아이디 :</h6>
    <q-input v-model="sessionId" class="col"></q-input>
  </div>
  <q-btn label='create Session' @click="createSession" />
  <q-btn label='create Connection' @click="createConnection" />
  <q-btn label='joinRoom' @click="joinRoom" />


  <div v-if="mainStreamManager">
    <ov-video :stream-manager="mainStreamManager" />
  </div>
  <hr />
  <h1>{{ subscribers.length }}</h1>
  <ov-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"
            @click.native="updateMainVideoStreamManager(sub)" />

</template>

<style scoped>

</style>
