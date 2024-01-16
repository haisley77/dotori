<template>
  <div class="row npsfont justify-between">
    <h2 class = "q-ma-md">도토리🐿️</h2>
    <h2 class = "q-ma-md">
      로그인🐣
    </h2>
  </div>
  <div style="background:#d2fff7;height: 100vh">
    <div class="row">
      <RoomTitle></RoomTitle>
    </div>
    <div class="row">
      <div class="col-8 q-pa-sm">
        <PlayerList></PlayerList>
      </div>
      <div class="col-4 q-pa-sm">
        <BookInfo></BookInfo>
      </div>
    </div>
    <div class="row">
      <div class="col-8 q-pa-sm" style="height: 210px">
        <RoomChat></RoomChat>
      </div>
      <div class="col-4 q-pa-sm">
        <StartReady></StartReady>
      </div>
    </div>
  </div>

</template>
<script setup>
import PlayerList from 'components/RoomPageComponents/PlayerList.vue';
import BookInfo from 'components/RoomPageComponents/BookInfo.vue';
import StartReady from 'components/RoomPageComponents/StartReady.vue';
import RoomChat from 'components/RoomPageComponents/RoomChat.vue';
import RoomTitle from "components/RoomPageComponents/RoomTitle.vue";
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
