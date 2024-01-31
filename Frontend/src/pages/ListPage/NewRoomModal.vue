<template>
  <div class='row'>
    <div class='col-10 offset-1'>
      <div class='body q-ma-sm q-pa-sm'>
        <!-- 책과 책 정보 row-->
        <div class='book row q-gutter-x-sm'>
          <!-- 왼쪽 칼럼   책 이미지 -->
          <div class='col-4 flex justify-center items-center q-pa-sm'
               style='border: 5px solid #C7A96E; border-radius: 20px;height: 100%'>
            <img :src="bookdetail.bookImg" alt='책'
                 style='object-fit: fill;border-radius: 20px;'>
          </div>
          <!-- 오른쪽 칼럼-->
          <div class='book-info col-8 q-gutter-y-sm'>
            <!--            책 제목과 줄거리-->
            <div style='border: 5px solid #C7A96E; border-radius: 20px;height: 50%' class='q-pa-sm'>
              <div class='text-h5'>제목 : {{bookdetail.title}}</div>
              <div>저자 : {{ bookdetail.author }}</div>
              <hr />
              {{ bookdetail.summary }}
            </div>
            <!--            등장 인물-->
            <div style='border: 5px solid #C7A96E; border-radius: 20px;height: 50%' class='q-pa-sm'>
              <div class='text-h5'>역할 소개</div>
              <hr />
              <div class='flex'>
                <Character v-for='item in 4' />
              </div>
            </div>
          </div>
        </div>
        <!--방 새로 만들기-->
        <div class='row'>
          <div class='col q-mt-sm'>
            <q-card flat class='mycard room-input col-12' style='border-radius: 20px;border: 5px solid #C7A96E'>
              <div class='room-info-inner'>
                <q-card-section>
                  <div class='q-pa-none q-ma-none text-h5'>방을 직접 만들 수 있어요!</div>
                </q-card-section>
                <q-separator inset />
                <div class='row q-mb-sm q-mt-sm'>
                  <div class='col-8 offset-1'>
                    <q-input rounded outlined label='방 제목을 입력하세요!' v-model='room_name' />
                  </div>
                  <div class='col-3 flex justify-center'>
                    <q-checkbox keep-color v-model='is_public' label='비밀로 할래요!' color='cyan' />
                  </div>
                </div>
                <div class='row q-mb-sm' v-if='is_public'>
                  <div class='col-8 offset-1'>
                    <q-input rounded outlined label='방 비밀번호를 입력하세요!' type='password' v-model='room_password' />
                  </div>
                </div>
                <div class='row q-mb-sm'>
                  <div class='col-9 flex'>
                  </div>
                  <div class='col-3 flex justify-center'>
                    <q-btn unelevated color='my-green' rounded label='방 만들기' @click='joinRoom'></q-btn>
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
<script setup>
    import Character from 'components/MyPageComponents/Character.vue';

    import {storeToRefs} from 'pinia';

    import {useRouter} from 'vue-router';

    const router = useRouter();

    import {useOpenViduStore} from 'stores/openvidu';
    import {onMounted} from 'vue';
    const openViduStore = useOpenViduStore();
    const {book_info,room_name,room_password,is_public} = storeToRefs(openViduStore);
    const {createRoom, connectToOpenVidu} = openViduStore;

<<<<<<< Updated upstream
    onMounted(() => {
      book_info.value = props.bookdetail;
    })

=======
>>>>>>> Stashed changes

    const components = {Character};
    const joinRoom = async () => {
      try {
        await createRoom();
        await connectToOpenVidu();
        console.log('소켓 연결 성공');
      } catch (error) {
        console.error(error);
      }
    };
</script>

<script>
  export default{
    props:['bookdetail'],
  }
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
        //border: dashed #cc765a 5px; border-radius: 20px;
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
        background-color: #C7A96E;
    }

    .role-info {
        display: flex;
    }

    .room-input {
        width: 100%;
        display: flex;
        border-radius: 20px;
        background-color: #C7A96E;
        flex-direction: column;
    }

    .room-input > div > q-input {
        margin: 100px;
    }

    .bg-my-green {
        background: #C7A96E !important;
    }

    @font-face {
        font-family: 'NPSfontBold';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2310@1.0/NPSfontBold.woff2') format('woff2');
        font-weight: 700;
        font-style: normal;
    }
</style>

<style lang='scss'>
    .mycard.q-card > div:last-child {
        //border-bottom: dashed #cc765a 5px !important;
        //border-top: dashed #cc765a 5px !important;
        background-color: #ffffff;
        height: 100%;
    }

</style>
