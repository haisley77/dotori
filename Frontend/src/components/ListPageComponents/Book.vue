<script setup>
  import {ref} from 'vue';
  import NewRoomModal from 'pages/ListPage/NewRoomModal.vue';

  const dialog = ref('true');
</script>

<template>
  <article>
    <div style='background: #C7A96E; border-radius: 20px' class='q-pa-xs'>
      <div style='background: white; border-radius: 20px' class='q-pa-xs'>
        <q-img
          :ratio='9/9'
          src='~assets/BookImages/rabbitandturtle.jpg'
          style='border-radius: 20px'
        />
        <div class='row q-mt-xs q-pa-xs flex justify-center items-center'
             style='background: white; border: #6E4E1F solid 3px; border-radius: 15px'>
          <div class='col-8 text-center npsfont text-h6'>
            토끼와 거북이
          </div>
          <!-- Dialog -->
          <div class='col-4'>
            <div class='q-pa-sm'>
              <q-btn flat label='시작' @click='dialog = true' style='color: #C7A96E'
                     class='npsfont q-ma-none q-pa-none text-h6' />
              <q-dialog v-model='dialog'>
                <q-card-actions align="right" class="bg-white text-teal">
                  <q-btn flat label="OK" v-close-popup />
                </q-card-actions>
                <q-card style='width: 700vw; max-width: 80vw;'>
                  <q-card-actions align="right" class="bg-white text-teal">
                    <q-btn flat icon="close" color='black' v-close-popup />
                  </q-card-actions>
                  <q-card-section>
                    <NewRoomModal />
                  </q-card-section>
                </q-card>
              </q-dialog>
            </div>
          </div>
        </div>
      </div>
    </div>
  </article>
</template>

<style scoped>

</style>
