<script setup>
  import {ref} from 'vue';
  import NewRoomModal from 'pages/ListPage/NewRoomModal.vue';

  const dialog = ref('true');
</script>

<template>
  <article>
    <div style='background: #C7A96E; border-radius: 0px' class='q-pa-xs'>
      <div style='background: white; border-radius: 0px' class='q-pa-xs'>
        <q-img
          :ratio='9/9'
          src='~assets/BookImages/rabbitandturtle.jpg'
          style='border-radius: 0px'
        />
        <hr style='border: #C7A96E 1px solid' />
        <div class='row q-mt-xs q-pa-xs flex justify-center items-center'
             style='background: white; '>
          <div class='col-9'>
            <div class=' npsfont text-h6 ellipsis'>
              책 제목이 여기 들어갑니다
            </div>
            <div class=' npsfont'>
              도토리
            </div>
            <!-- Dialog -->
          </div>
          <div class='col-3'>
            <div class='q-pa-sm flex justify-end items-center'>
              <q-btn flat @click='dialog = true' style='color: #C7A96E'
                     class='npsfont q-ma-none q-pa-none text-h6 '>시작
              </q-btn>
              <q-dialog v-model='dialog'>
                <q-card-actions align='right' class='bg-white text-teal'>
                  <q-btn flat label='OK' v-close-popup />
                </q-card-actions>
                <q-card style='width: 700vw; max-width: 80vw;'>
                  <q-card-actions align='right' class='bg-white text-teal'>
                    <q-btn flat icon='close' color='black' v-close-popup />
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
