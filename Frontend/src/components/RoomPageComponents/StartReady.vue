<script setup>
  import {useRouter} from 'vue-router';
  import {ref} from 'vue';
  import {useOpenViduStore} from 'stores/openvidu';
  import {storeToRefs} from 'pinia';

  const props = defineProps({
    roomInfo: Object,
    memberId: Object,
    playerList: Object,
  });

  const router = useRouter();
  const btnValue = ref(false);
  const openViduStore = useOpenViduStore();
  const {sendingMoveData} = storeToRefs(openViduStore);
  const {updateRoom,sendMoveInfoToOpenVidu} = openViduStore;

  const updateState = () => {
    props.playerList.forEach((user) => {
      // 준비하기 or 시작하기 버튼을 누르면 자신의 준비상태를 true로 업데이트한다.
      if (user.memberId === props.memberId) {
        user.readyState = true;
        // 방장이 아닌 경우에는 준비하기 버튼을 비활성화 한다.
        if (props.roomInfo.hostId !== props.memberId) {
          btnValue.value = true;
          alert('곧 시작합니다. 잠시만 기다려주세요!');
        }
      }
    })
    if (props.roomInfo.hostId === props.memberId) {  // 방장인 경우에는 참여자들의 준비상태를 확인한다.
      if (checkReadyState()) {  // 모든 참여자들이 준비상태인 경우
        // openvidu 서버에 모든 참여자 녹화방으로 이동하라는 메시지 send
        props.roomInfo.isRecording = true;
        sendingMoveData.value.recording = true;
        sendMoveInfoToOpenVidu()
          .then(() => {
            // 역할 정보 db 반영하러 간다.  (axios) -> 정말 의미없지 않을까?

            updateRoom(props.roomInfo.isRecording)    // db에 room 정보 반영하러 간다. (axios)
              .then((response) => {
                moveRecording();
              })
              .catch((error) => {
                console.error('방 정보 갱신 중 에러 발생 : ' + error);
              })
          })
          .catch((error) => {
            console.error(error);
          })
      }
    }
  }

  const checkReadyState = () => {
    props.playerList.forEach((user) => {
      if (!user.readyState) return false;
    })
    return true;
  }
  const moveRecording = () => {

    // 준비 상태 확인

    // 역할 고정

    // 녹화방으로 이동
    router.push('/recording');
  };
</script>

<template>
  <div style='height: 100%' class='background-green q-pa-sm'>

    <div
      style='
      height: 100%;
      display: flex;
      flex-direction: column;
      justify-content: center;
      '
      class='background-yellow q-pa-sm'
    >
      <q-btn unelevated class='start-btn' @click='updateState' >
        <svg xmlns='http://www.w3.org/2000/svg' height='48' width='54' viewBox='0 0 576 512'>
          <!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
          <path fill='#FFD43B'
                d='M316.9 18C311.6 7 300.4 0 288.1 0s-23.4 7-28.8 18L195 150.3 51.4 171.5c-12 1.8-22 10.2-25.7 21.7s-.7 24.2 7.9 32.7L137.8 329 113.2 474.7c-2 12 3 24.2 12.9 31.3s23 8 33.8 2.3l128.3-68.5 128.3 68.5c10.8 5.7 23.9 4.9 33.8-2.3s14.9-19.3 12.9-31.3L438.5 329 542.7 225.9c8.6-8.5 11.7-21.2 7.9-32.7s-13.7-19.9-25.7-21.7L381.2 150.3 316.9 18z' />
        </svg>

        {{ (props.roomInfo.hostId === props.memberId) ? '시작하기' : '준비하기' }}
        <svg xmlns='http://www.w3.org/2000/svg' height='48' width='54' viewBox='0 0 576 512'>
          <!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
          <path fill='#FFD43B'
                d='M316.9 18C311.6 7 300.4 0 288.1 0s-23.4 7-28.8 18L195 150.3 51.4 171.5c-12 1.8-22 10.2-25.7 21.7s-.7 24.2 7.9 32.7L137.8 329 113.2 474.7c-2 12 3 24.2 12.9 31.3s23 8 33.8 2.3l128.3-68.5 128.3 68.5c10.8 5.7 23.9 4.9 33.8-2.3s14.9-19.3 12.9-31.3L438.5 329 542.7 225.9c8.6-8.5 11.7-21.2 7.9-32.7s-13.7-19.9-25.7-21.7L381.2 150.3 316.9 18z' />
        </svg>

      </q-btn>
    </div>

  </div>
</template>

<style scoped>

  .start-btn {
  //background: #35daa0; //color: white; font-family: NPSfontBold;
    font-size: 3.5em;
    color: #6E4E1F;
    background: white;
    height: 100%;
    border-radius: 20px 20px 20px 20px;
    border: dashed #6E4E1F 5px;

  }

  .background-green {
    background: #C7A96E;
    border-radius: 20px 20px 20px 20px;

  }

  .background-yellow {
    background: white;
    border-radius: 20px 20px 20px 20px;
  //border: dashed #cc765a 5px;
  }

  .background-white {
    background: floralwhite;
    height: 260px;

    border-radius: 20px 20px 20px 20px;
    font-family: NPSfontBold;
    border: solid #cc765a 2px;

  }

  .bookimg {
    border-radius: 20px 20px 20px 20px;
    border: solid #cc765a 2px;
  }
</style>
