<template>

  <div class="body q-ma-sm q-pa-sm">
    <div class="book row col-12">
      <div class="col-2 q-ma-sm">
        <q-card dark bordered class="mycard book-story q-pa-sm q-mb-sm flex justify-center items-center" style="height:100%">
          <img class="" height=100% src="https://cdn.quasar.dev/img/parallax1.jpg" alt="책">
        </q-card>
      </div>
      <div class="book-info col-10 q-ma-sm">
        <q-card dark bordered class="mycard book-story q-pa-sm q-mb-sm">
          <div class="book-info-inner">
            <div class="card-inner">
              <q-card-section>
                <h4 class="q-pa-none q-ma-none">토끼와 거북이</h4>
                <div class="text-subtitle1">도토리 오리지널</div>
              </q-card-section>
              <q-separator inset />
              <q-card-section>
                옛날 옛적에, 토끼와 거북이가 살고 있었다.
                어느날 토끼가 거북이를 느림보라고 놀려대자, 거북이는 자극을 받고 토끼에게 달리기 경주를 제안하였다.
                경주를 시작한 토끼는 거북이가 한참 뒤쳐진 것을 보고 안심을 하고 중간에 낮잠을 잔다.
              </q-card-section>
            </div>
          </div>
        </q-card>
        <q-card dark bordered class="mycard book-roles q-pa-sm">
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

      <div class="col-12 q-pa-sm">
        <q-card class = "mycard room-input q-pa-sm q-mb-sm col-12">
          <div class="room-info-inner">
            <q-card-section>
              <h4 class="q-pa-none q-ma-none">방을 직접 만들 수 있어요!</h4>
            </q-card-section>
            <q-separator inset />
            <div class="row q-mb-sm q-mt-sm">
              <div class="col-8 offset-1">
                <q-input rounded outlined label="방 제목을 입력하세요!" />
              </div>
              <div class="col-3 flex justify-center">
                <q-checkbox keep-color v-model="cyan" label="비밀로 할래요!" color="cyan" />
              </div>
            </div>
            <div class="row q-mb-sm" v-if="cyan">
              <div class="col-8 offset-1">
                <q-input rounded outlined label="방 비밀번호를 입력하세요!" type="password" />
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

</template>

<script>
  import Character from 'components/Character.vue';
  import {ref,defineComponent} from 'vue';

  export default defineComponent({
    components: {Character},
    setup() {
      const cyan = ref(false);

      return {
        cyan,
      };
    },
  });
</script>

<style scoped>

  .body {
    background-color: #d2fff7;
  }

  .book, .room-input {
    display: flex;
    flex-direction: row;
    background-color: #35daa0;
    border-radius: 10px;
    font-family: 'NPSfontBold', sans-serif;
  }

  .book-info-inner, .room-info-inner {
    border: dashed #cc765a 5px;
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
    background-color: #ebf686;
  }

  .role-info {
    display: flex;
  }

  .room-input {
    width: 100%;
    display: flex;
    border-radius: 20px;
    background-color: #ebf686;
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

<style>
  .mycard.q-card > div:last-child {
    border-bottom: dashed #cc765a 5px !important;
    border-top: dashed #cc765a 5px !important;
  }

</style>
