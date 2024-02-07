<template>
  <div class='row flex justify-center q-px-none'>
    <div class='col-11'>
      <div class='entire-container row'>
        <div class='right-container q-pr-sm q-pt-sm q-pl-sm col-9'>
          <MainScene :curPage='curPage' />
          <side-bar :curPage='curPage' @moveToPage='moveToPage'></side-bar>
        </div>
        <div class='left-container col-3 q-pt-sm'>
          <Script :cur-page='curPage' />
          <SceneController :curPage='curPage' @moveToPage='moveToPage' />
        </div>
      </div>
    </div>
  </div>
  <PublishMyVideo :currentRoles='currentRoles' :myAvatar='myAvatar' @changeCanvasStream="changeCanvasStream" />
</template>


<script setup>
  import SideBar from 'components/RecordingPageComponents/SideBar.vue';
  import MainScene from 'components/RecordingPageComponents/MainScene.vue';
  import Script from 'components/RecordingPageComponents/Script.vue';
  import SceneController from 'components/RecordingPageComponents/SceneController.vue';
  import {onMounted, ref} from 'vue';
  import PublishMyVideo from 'components/RecordingPageComponents/PublishMyVideo.vue';

  import {useOpenViduStore} from 'stores/openvidu';

  const ovstore = useOpenViduStore();

  const curPage = ref(1);

  //현재 페이지의 역할을 넣는다
  //ovstore에 있는 bookDetail에 있는 roles를 onMount 시점에 가져와서 초기화를 시켜준다
  const currentRoles = ref();
  const currentScript = ref(new Set());

  const canvasStream = ref();
  const myAvatar = ref('');

  const changeCanvasStream = (stream) => {
    canvasStream.value = stream;
  };
  //영상 퍼블리시
  const publish = () => {
    if (!ovstore.isPublished) {
      const publisher = ovstore.OV.initPublisher(undefined, {
        audioSource: canvasStream.value.getAudioTracks()[0], // The source of audio. If undefined default microphone
        videoSource: canvasStream.value.getVideoTracks()[0], // The source of video. If undefined default webcam
        // videoSource: canvasStream, // The source of video. If undefined default webcam
        // videoSource: undefined, // The source of video. If undefined default webcam
        publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
        publishVideo: true, // Whether you want to start publishing with your video enabled or not
        resolution: '640x480', // The resolution of your video
        frameRate: 30, // The frame rate of your video
        insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
        // mirror: false, // Whether to mirror your local video or not
      });
      ovstore.publish(
        //add publisher later
        publisher,
      );
    }
  };

  //퍼블리시 끊기
  const unpublish = () => {
    if (ovstore.isPublished) ovstore.unpublish();
  };

  //페이지를 이동한다
  //페이지를 이동할때마다 현재 페이지의 역할 목록을 초기화 시켜준다
  //스크립트 또한 초기화 시켜준다
  const moveToPage = (nextPage) => {
    curPage.value = nextPage;
    //역할 목록을 변경해준다
    currentRoles.value = getRoles(curPage.value);
    //페이지 정보를 변경해준다
    currentScript.value = ovstore.bookDetail.scenes[curPage.value];

    //값을 다 바꿨으면 내 역할과 확인해서 비디오를 켤지 말지를 판단한다
    //내가 연극할 차례일때
    if (currentRoles.value.has(ovstore.myRole)) {
      //퍼블리시중이 아니라면 시작
      if (!ovstore.isPublished) publish();

    }
    //내 차례가 아닐때
    else if (!currentRoles.value.has(ovstore.myRole)) {
      //퍼블리시 중이라면 종료
      if (ovstore.isPublished) unpublish();
    }
  };

  //curPage값에 따라 역할 목록을 반환한다.
  const getRoles = (page) => {
    //scriptDto를 받아온다
    let scriptDto = ovstore.bookDetail.scenes[page - 1].scriptDto;
    //역할을 만들 set을 생성한다
    const set = new Set();
    for (let script in scriptDto) {
      let roleId = script.roleDto.roleId;//역할 번호
      set.add(roleId);
    }
    return set;
  };

  //mounted 후 실행되는 코드..초기화 작업
  onMounted(() => {
    //척페이지 역할 초기화
    currentRoles.value = getRoles(1);
    //첫 페이지 정보 넣기
    currentScript.value = ovstore.bookDetail.scenes[0];

    //내 아바타 주소 받아옴!
    myAvatar.value = ovstore.bookDetail.roles[ovstore.myRole - 1].maskPath;
  });
</script>
<style scoped>
  @font-face {
    font-family: 'NPSfontBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2310@1.0/NPSfontBold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
  }


  .entire-container {
    height: 85vh;
    //border: green solid 1px; //background: #d2fff7;
  }

  .left-container {
    height: 100%;
    //border: red solid 1px;
  }

  .right-container {
    height: 100%;
    //border: blue solid 1px;
  }


</style>
