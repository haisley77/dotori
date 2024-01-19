<template>
<div class ="row">
  <div class ="col-10 offset-1">
    <div class="body q-ma-sm q-pa-sm">
      <div class="book row ">
        <div class="col-4 q-ma-sm">
          <q-card dark bordered class="mycard book-story q-pa-sm flex justify-center items-center"
                  style="height:500px">
            <img height=100% src="../assets/rabbitandturtle.jpg" alt="책" style="object-fit: fill;">
          </q-card>
        </div>
        <div class="book-info col-8 q-ma-sm" >
          <q-card dark bordered class="mycard book-story q-pa-sm q-mb-sm" style="height: 50%">
            <div class="book-info-inner">
              <div class="card-inner">
                <q-card-section>
                  <h4 class="q-pa-none q-ma-none">{{ title }}</h4>
                  <div class="text-subtitle1">{{ writer }}</div>
                </q-card-section>
                <q-separator inset />
                <q-card-section>
                  {{ content }}
                </q-card-section>
              </div>
            </div>
          </q-card>
          <q-card dark bordered class="mycard book-roles q-pa-sm" style="height: 50%">
            <div class="book-info-inner">
              <div class="card-inner q-ma-none">
                <q-card-section>
                  <h4 class="q-pa-none q-ma-none">역할 소개</h4>
                </q-card-section>
                <q-separator inset />
                <q-card-section>
                  <div class="role-info">
                    <Character v-for="index in 5" :key="index" />
                  </div>
                </q-card-section>
              </div>
            </div>
          </q-card>
        </div>
      </div>

      <div>
        <div class="col-12 q-pa-sm">
          <q-card class="mycard room-input q-pa-sm q-mb-sm col-12">
            <div class="room-info-inner">
              <q-card-section>
                <h4 class="q-pa-none q-ma-none">방을 직접 만들 수 있어요!</h4>
              </q-card-section>
              <q-separator inset />
              <div class="row q-mb-sm q-mt-sm">
                <div class="col-8 offset-1">
                  <q-input rounded outlined label="방 제목을 입력하세요!" v-model="roomName" />
                </div>
                <div class="col-3 flex justify-center">
                  <q-checkbox keep-color v-model="open" label="비밀로 할래요!" color="cyan" />
                </div>
              </div>
              <div class="row q-mb-sm" v-if="open">
                <div class="col-8 offset-1">
                  <q-input rounded outlined label="방 비밀번호를 입력하세요!" type="password" v-model="roomPassword" />
                </div>
              </div>
              <div class="row q-mb-sm">
                <div class="col-9 flex">
                </div>
                <div class="col-3 flex justify-center">
                  <q-btn unelevated color="my-green" rounded label="방 만들기"></q-btn>
                </div>
              </div>

            </div>
          </q-card>
        </div>
      </div>
    </div>

  </div>

</div>


</template>

<script>
  import Character from 'components/Character.vue';
  import {ref, defineComponent} from 'vue';

  export default defineComponent({
    components: {Character},
    setup() {
      const open = ref(false);
      const imageUrl = ref('../assets/rabbitandturtle.jpg');
      const title = ref('토끼와 거북이');
      const content = ref('옛날 옛적에, 토끼와 거북이가 살고 있었다.\n' +
        '                어느날 토끼가 거북이를 느림보라고 놀려대자, 거북이는 자극을 받고 토끼에게 달리기 경주를 제안하였다.\n' +
        '                경주를 시작한 토끼는 거북이가 한참 뒤쳐진 것을 보고 안심을 하고 중간에 낮잠을 잔다.' +
        '                어느날 토끼가 거북이를 느림보라고 놀려대자, 거북이는 자극을 받고 토끼에게 달리기 경주를 제안하였다.\n' +
        '                경주를 시작한 토끼는 거북이가 한참 뒤쳐진 것을 보고 안심을 하고 중간에 낮잠을 잔다.');
      const writer = ref('도토리 오리지널');

      const roomName = ref('');
      const roomPassword = ref('');

      return {
        open,
        title,
        content,
        writer,
        imageUrl,
        roomName,
        roomPassword,
      };
    },
  });
</script>

<style scoped>

  .body {
    background-color: white;
  }

  .book, .room-input {
    display: flex;
    flex-direction: row;
    border-radius: 10px;
    font-family: 'NPSfontBold', sans-serif;
  }

  .book-info-inner, .room-info-inner {
    //border: dashed #cc765a 5px;
    border-radius: 20px;
  }

  .book-info {
    display: flex;
    flex: 1;
    flex-direction: column;
  }

  .book-story, .book-roles {
    width: 100%;
    color: black;
    border-radius: 20px;
    background-color: #35daa0;
  }

  .role-info {
    display: flex;
  }

  .room-input {
    width: 100%;
    display: flex;
    border-radius: 20px;
    background-color: #35daa0;
    flex-direction: column;
  }

  .room-input > div > q-input {
    margin: 100px;
  }

  .bg-my-green {
    background: #35daa0 !important;
  }

  @font-face {
    font-family: 'NPSfontBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2310@1.0/NPSfontBold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
  }
</style>

<style lang="scss">
  .mycard.q-card > div:last-child {
    //border-bottom: dashed #cc765a 5px !important;
    //border-top: dashed #cc765a 5px !important;
    background-color: #ffffff;
    height: 100%;
  }

</style>
