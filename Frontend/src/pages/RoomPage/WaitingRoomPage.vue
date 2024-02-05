<template>
  <div class='row'>
    <div class='col-10 offset-1'>
      <div class='row'>
        <RoomTitle :roomInfo='roomInfo'></RoomTitle>
      </div>
      <div class='row'>
        <div class='col-8 q-pa-sm'>
          <PlayerList :memberId="member_id"></PlayerList>
        </div>
        <div class='col-4 q-pa-sm'>
          <BookInfo :bookInfo='bookInfo'></BookInfo>
        </div>
      </div>
      <div class='row'>
        <div class='col-8 q-pa-sm' style='height: 210px'>
          <RoomChat :session='session'></RoomChat>
        </div>
        <div class='col-4 q-pa-sm'>
          <StartReady :roomInfo='roomInfo' :memberId='member_id' :playerList='playerList'></StartReady>
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
  const {roomInitializationParam,sendingPlayerData,playerList,roleList} = storeToRefs(openViduStore);
  const {sendPlayerInfoToOpenVidu} = openViduStore;
  const bookInfo = roomInitializationParam.value.bookInfo;
  const roomInfo = roomInitializationParam.value.roomInfo;

  const member_id = ref(0);

  onMounted(() => {
    // 대기방에 들어온 사용자의 아이디를 조회
    // member_id = await axios.get(path, accessToken);
    member_id.value = 20;

    // 대기방에 들어오면 내 정보를 playerList에 추가하고 갱신을 요청합니다.
    const player = {
      name: '방장',
      memberId: member_id.value,
      profileImg: 'src/assets/MyPageImages/iupic.jpg',
      roleName: '방장',
      roleIndex: 5,
      readyState: false,
    };
    playerList.value.push(player);

    sendingPlayerData.value.player = player;
    sendPlayerInfoToOpenVidu();

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
