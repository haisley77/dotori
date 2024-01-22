<template>
  <div style="background:#85fff3;height: 100vh">
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
  import PlayerList from 'components/PlayerList.vue';
  import BookInfo from 'components/BookInfo.vue';
  import StartReady from 'components/StartReady.vue';
  import RoomChat from 'components/RoomChat.vue';
</script>

<style scoped>
  @font-face {
    font-family: 'NPSfontBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2310@1.0/NPSfontBold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
  }

</style>
