<template>
  <q-page padding>
    <div class='row q-mb-md'>
      <div class='search row flex justify-center items-center' style='width: 100%;'>
        <q-input
          class=''
          standout rounded dense placeholder='검색'
          color='black' bg-color='white'
          style='width: 50%; border: #C7A96E solid 5px; border-radius: 50px; '
        >
        </q-input>
        <q-btn
          class=''
          icon='search'
          round
          size='lg'
          flat
          unelevated
          style='color: #C7A96E; font-weight: bolder'
        />
      </div>
    </div>
    <div class='row q-col-gutter-x-md q-col-gutter-y-md'>
      <div v-for='room in rooms' :key='room.roomId' class='col-12 col-sm-6 col-md-4 col-lg-3 q-pa-md'>
        <EnterRoomComponent :room='room' @click="() => enterRoom(room)"></EnterRoomComponent>
      </div>
    </div>

    <q-dialog v-model="showPasswordModal" persistent>
      <q-card>
        <q-card-section>
          <q-input
            v-model="password"
            type="password"
            label="비밀번호"
          />
        </q-card-section>

        <q-card-actions align="right">
          <q-btn label="취소" color="negative" @click="cancelPasswordCheck" />
          <q-btn label="확인" color="primary" @click="checkPassword" />
        </q-card-actions>
      </q-card>
    </q-dialog>

  </q-page>
</template>

<script>
  import EnterRoomComponent from 'components/ListPageComponents/EnterRoomComponent.vue';
  import {storeToRefs} from 'pinia';
  import {onMounted, ref} from 'vue';
  import {useRouter} from 'vue-router';
  import {useOpenViduStore} from 'stores/openvidu';
  import {localAxios} from 'src/axios/http-commons';
  const axios = localAxios();

  export default {
    components: {EnterRoomComponent},
    setup() {
      const axios = localAxios();
      const router = useRouter();
      const rooms = ref([]);
      const openViduStore = useOpenViduStore();
      const {roomInfo,roomId} = storeToRefs(openViduStore);
      const {getConnectionToken, connectToOpenVidu, addRoomMember} = openViduStore;

      const showPasswordModal = ref(false); // 모달 표시 여부
      const password = ref(''); // 입력된 비밀번호

      // 비밀번호 확인 취소
      const cancelPasswordCheck = () => {
        showPasswordModal.value = false;
        password.value = '';
      };

      // 비밀번호 확인
      const checkPassword = (room) => {
        const enteredPassword = password.value; // 사용자가 입력한 비밀번호
        const correctPassword = room.password; // 실제 비밀번호

        if (enteredPassword !== correctPassword) {
          console.log('비밀번호가 일치하지 않습니다.');
          password.value = '';
        }
        cancelPasswordCheck();// 모달 닫기
      };

      const moveWaitingRoom = (room) => {
        roomId.value = room.roomId;
        roomInfo.value = room;
        router.push('/room');
      };

      onMounted(() => {
        fetchRooms();
      });

      // 방 목록 정보를 불러온다.
      const fetchRooms = async () => {
        try {
          const response = await axios.get('/api/rooms',{withCredentials: true});
          console.log('API Response:', response);
          rooms.value = response.data;
        } catch (error) {
          console.error('Error fetching rooms:', error);
        }
      };

      const enterRoom = (room) => {
        // 비밀번호 있는 방이면 확인
        if(!room.isPublic){
          checkPassword(room);
        }
        getConnectionToken(room)
          .then(() => {
            connectToOpenVidu()
              .then(() => {
                addRoomMember(room.book)
                  .then(() => {
                    openViduStore.roomId = room.roomId;
                    moveWaitingRoom(room);
                  })
                  .catch((error) => {
                    console.error('참여 인원 갱신 중 에러 발생 : ' + error);
                  });
              })
              .catch((error) => {
                console.error('ov에 연결 중 에러 발생 : ' + error);
              });
          });
      };

      return {
        showPasswordModal,
        password,
        cancelPasswordCheck,
        checkPassword,
        rooms,
        moveWaitingRoom,
        enterRoom,
      };
    },
  };
</script>

<style lang='scss' scoped>
</style>
