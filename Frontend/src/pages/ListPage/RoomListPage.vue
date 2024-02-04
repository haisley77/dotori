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
        <EnterRoomComponent :room='room'></EnterRoomComponent>
      </div>
    </div>
  </q-page>
</template>

<script>
  import EnterRoomComponent from 'components/ListPageComponents/EnterRoomComponent.vue';
  import {ref, onMounted} from 'vue';
  import axios from 'axios';


  export default {
    components: {EnterRoomComponent},
    setup() {
      const rooms = ref([]);

      onMounted(() => {
        fetchRooms();
    });

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

    return {
        rooms,
      };
    },
  };
</script>

<style lang='scss' scoped>
</style>