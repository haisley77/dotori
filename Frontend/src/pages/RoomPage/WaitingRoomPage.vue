<template>
  <div class='row'>
    <div class='col-10 offset-1'>
      <div class='row'>
        <RoomTitle :roomInfo='roomInfo'></RoomTitle>
      </div>
      <div class='row'>
        <div class='col-8 q-pa-sm'>
          <PlayerList :memberId="memberId"></PlayerList>
        </div>
        <div class='col-4 q-pa-sm'>
          <BookInfo :bookInfo='bookInfo'></BookInfo>
        </div>
      </div>
      <div class='row'>
        <div class='col-8 q-pa-sm' style='height: 210px'>
          <RoomChat :playerList='playerList' :memberId='memberId'></RoomChat>
        </div>
        <div class='col-4 q-pa-sm'>
          <StartReady :roomInfo='roomInfo' :memberId='memberId'></StartReady>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
  import PlayerList from 'components/RoomPageComponents/PlayerList.vue';
  import BookInfo from 'components/RoomPageComponents/BookInfo.vue';
  import StartReady from 'components/RoomPageComponents/StartReady.vue';
  import RoomChat from 'components/RoomPageComponents/RoomChat.vue';
  import RoomTitle from 'components/RoomPageComponents/RoomTitle.vue';
  import {onMounted, ref} from 'vue';
  import {useOpenViduStore} from 'stores/openvidu';
  import {storeToRefs} from 'pinia';

  const openViduStore = useOpenViduStore();
  const {roomInitializationParam,sendingIncomingData,playerList,isHost} = storeToRefs(openViduStore);
  const {sendIncomingInfoToOpenVidu} = openViduStore;
  const bookInfo = roomInitializationParam.value.bookInfo;
  const roomInfo = roomInitializationParam.value.roomInfo;
  const memberId = ref(0);

  onMounted(() => {
    // 대기방에 들어온 사용자의 아이디를 조회
    // memberId = await axios.get(path, accessToken);
    memberId.value = 30;

    const player = {
      name: '방장하은',
      memberId: memberId.value,
      profileImg: 'src/assets/MyPageImages/iupic.jpg',
      roleName: '방장하은',
      roleIndex: 5,
      readyState: false,
    };


    if (isHost.value) {  // 방장이면 참여자 리스트에 본인 추가
      playerList.value.push(player);
    } else {  // 방장이 아니면 들어왔다고 signal
      sendingIncomingData.value.player = player;
      sendIncomingInfoToOpenVidu();
    }
  })


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

</style>
