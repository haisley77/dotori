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
          <RoomChat></RoomChat>
        </div>
        <div class='col-4 q-pa-sm'>
          <StartReady :isHost='is_host' :roleCnt='role_cnt'></StartReady>
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
  const {roomInitializationParam} = storeToRefs(openViduStore);
  const bookInfo = roomInitializationParam.value.bookInfo;
  const roomInfo = roomInitializationParam.value.roomInfo;

  const member_id = ref(0);
  const is_host = ref(false);
  const role_cnt = ref(0);

  onMounted(() => {
    // 대기방에 들어온 사용자의 아이디를 조회
    member_id.value = 50;
    // 대기방에 들어온 사용자가 방장인 경우
    if (member_id.value === roomInfo.hostId) {
     is_host.value = true;
    }
   role_cnt.value = roomInfo.limitCnt;
    playerList.value.push({
      name: '유저1',
      profileImg: 'src/assets/MyPageImages/iupic.jpg',
      roleName: '유저1',
      roleIndex: 5,
      state: false,
    });

  })

  const router = useRouter();

  const playerList = ref([
    // {
    //   name: '조석현',
    //   profileImg: 'src/assets/MyPageImages/cho.jpg',
    //   role: '',
    // },
    {
      name: 'Winter',
      profileImg: 'src/assets/MyPageImages/winter.png',
      roleName: 'Winter',
      roleIndex: 5,
      state: false,
    },
    {
      name: '카리나',
      profileImg: 'src/assets/MyPageImages/karina.jpg',
      roleName: '카리나',
      roleIndex: 5,
      state: false,
    },
    // {
    //   name: '아이유',
    //   profileImg: 'src/assets/MyPageImages/iupic.jpg',

    // },


  ]);


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
