<script setup>
  import {ref} from 'vue';
  const { room } = defineProps(['room']);
  const dialog = ref('true');
</script>

<template>
  <article>
    <div style='background: #C7A96E; border-radius: 0px' class='q-pa-xs'>
      <div style='background: white; border-radius: 0px' class='q-pa-xs'>
        <q-img
          :ratio='9/9'
          :src="room.book.bookImg"
          style='border-radius: 0px'
        />
        <hr style='border: #C7A96E 1px solid' />
        <div class='row q-mt-xs q-pa-xs flex justify-center items-center'
             style='background: white; '>
          <div class='col-9'>
            <div class=' npsfont text-h6 ellipsis'>
              {{ room.title }}
            </div>
            <div class='npsfont'>
              {{ room.book.title }}
            </div>
            <div class='flex justify-between'>
              <div class='npsfont'>
                {{ room.hostId }}
              </div>
              <div class='npsfont'>
                인원 : {{ room.joinCnt }}/{{ room.limitCnt }}
              </div>
            </div>
          </div>
          <div class='col-3'>
            <div class='q-pa-sm flex justify-end items-center'>
              <q-btn flat @click='dialog = true' style='color: #C7A96E'
                     class='npsfont q-ma-none q-pa-none text-h6 '>입장
              </q-btn>
            </div>
          </div>
        </div>
      </div>
    </div>
  </article>
</template>

<style scoped>

</style>
