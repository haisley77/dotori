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
  </q-page>
</template>

<script>
  import EnterRoomComponent from 'components/ListPageComponents/EnterRoomComponent.vue';
  import {storeToRefs} from 'pinia';
  import {ref, onMounted} from 'vue';
  import { useRouter } from 'vue-router';
  import {useOpenViduStore} from 'stores/openvidu';
  import axios from 'axios';

  export default {
    components: {EnterRoomComponent},
    setup() {
      const router = useRouter();
      const rooms = ref([]);
      const openViduStore = useOpenViduStore();
      const {bookDetail} = storeToRefs(openViduStore);
      const {roomInitializationParam, room_name, room_password, is_private} = storeToRefs(openViduStore);
      const {getConnectionToken, connectToOpenVidu, addRoomMember} = openViduStore;

      const moveWaitingRoom = (room) => {
        router.push('/room');
      };

      onMounted(() => {
        fetchRooms();
      });

      // 방 목록 정보를 불러온다.
      const fetchRooms = async () => {
        try {
          const response = await axios.get('http://localhost:8080/api/rooms');
          console.log('API Response:', response);
          if (response.status === 200) {
            rooms.value = response.data;
          } else {
            console.error('Failed to fetch rooms. Status:', response.status);
          }
        } catch (error) {
          console.error('Error fetching rooms:', error);
        }
      };

      // 해당 방에 대한 책의 모든 정보(역할,책,장면)를 불러온다.
      const fetchBookDetail = async (bookId) => {
        try {
          const response = await axios.get(`http://localhost:8080/api/books/${bookId}/detail`);
          console.log('API Response:', response);
          if (response.status === 200) {
            bookDetail.value = response.data;
          } else {
            console.error('Failed to fetch books. Status:', response.status);
          }
        } catch (error) {
          console.error('Error fetching books:', error);
        }
      };

      const enterRoom = (room) => {
        // 유저 방정보, 책정보 가지고 입장.
        roomInitializationParam.value.bookInfo = room.book;
        roomInitializationParam.value.roomInfo = room;
        console.log("책정보",roomInitializationParam.value.bookInfo);
        console.log("방정보",roomInitializationParam.value.roomInfo);
      getConnectionToken(room)
        .then(() => {
          connectToOpenVidu()
            .then(() => {
              addRoomMember()
                .then(() => {
                  fetchBookDetail(room.book.bookId)
                  moveWaitingRoom(room);
                })
                .catch((error) => {
                  console.log('참여 인원 갱신 중 에러 발생')
                })
            })
            .catch((error) => {
              console.log('ov에 연결 중 에러 발생');
            })
        })
    };

      return {
        rooms,
        moveWaitingRoom,
        enterRoom,
        bookDetail
      };
    },
  };
</script>

<style lang='scss' scoped>
</style>
