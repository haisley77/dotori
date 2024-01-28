<template>
  <div class='row'>
    <div class='col-10 offset-1'>
      <div class='row'>
        <RoomTitle :roomInfo='roomInfo'></RoomTitle>
      </div>
      <div class='row'>
        <div class='col-8 q-pa-sm'>
          <PlayerList></PlayerList>
        </div>
        <div class='col-4 q-pa-sm'>
          <BookInfo :bookInfo='bookInfo'></BookInfo>
        </div>
      </div>
      <div class='row'>
        <div class='col-8 q-pa-sm' style='height: 210px'>
          <RoomChat></RoomChat>
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
  import Header from 'components/CommonComponents/Header.vue';
  import {ref} from 'vue';

  const roomInfo = ref({
    title: '토끼와 거북이 오실분~',
    roomNumber: '14',
    public: true,
  });

  const bookInfo = ref({
    title: '토끼와 거북이',
    author: '도토리',
    limitCnt: '4',
    joinCnt: '3',
    summary: '토끼와 거북이 이야기는 토끼와 거북이가 경주를 벌이는 이야기입니다.'
  });

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
