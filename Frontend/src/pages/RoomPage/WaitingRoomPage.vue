<template>
  <div class='row'>
    <div class='col-10 offset-1'>
      <div class='row'>
        <RoomTitle :roomInfo='roomInfo'></RoomTitle>
      </div>
      <div class='row'>
        <div class='col-8 q-pa-sm'>
          <PlayerList :playerList='playerList'></PlayerList>
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
          <StartReady></StartReady>
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
  import {storeToRefs} from 'pinia';
  import {useRouter} from 'vue-router';
  import {useOpenViduStore} from 'stores/openvidu';

  const openViduStore = useOpenViduStore();
  const {roomInitializationParam, session, playerList: playerList} = storeToRefs(openViduStore);
  const bookInfo = roomInitializationParam.value.bookInfo;
  const roomInfo = roomInitializationParam.value.roomInfo;

  onMounted(() => {
    console.log("세션정보" ,session);
    console.log("대기방책정보",bookInfo);
    console.log("대기방룸정보",roomInfo);
  })
  const router = useRouter();

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
