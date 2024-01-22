<template>
  <div class="book">
    <div class="q-pa-md row items-start q-gutter-md">
      <q-card class="my-card">
        <img class="book-image" src="https://cdn.quasar.dev/img/mountains.jpg">
        <div class="text-h6">{{ name }}</div>
      </q-card>
    </div>
    <div>
      <q-card dark bordered class="bg-yellow-7 book-info book-story">
        <q-card-section>
          <div class="text-h6">토끼와 거북이</div>
          <div class="text-subtitle2">도토리 오리지널</div>
        </q-card-section>

        <q-separator dark inset />

        <q-card-section>
          옛날 옛적에, 토끼와 거북이가 살고 있었다.
          토끼는 매우 빨랐고, 거북이는 매우 느렸다.
          어느날 토끼가 거북이를 느림보라고 놀려대자, 거북이는 자극을 받고 토끼에게 달리기 경주를 제안하였다.
          경주를 시작한 토끼는 거북이가 한참 뒤쳐진 것을 보고 안심을 하고 중간에 낮잠을 잔다.
          그런데 토끼가 잠을 길게 자자 거북이는 토끼를 지나친다.
          잠에서 문득 깬 토끼는 거북이가 자신을 추월했다는 사실을 깨닫게 되고 빨리 뛰어가보지만 결과는 거북이의 승리였다.
        </q-card-section>
      </q-card>
      <q-card dark bordered class="bg-yellow-5 book-info book-roles">
        <q-card-section>
          <div class="text-h6">역할 소개</div>
        </q-card-section>

        <q-separator dark inset />

        <q-card-section>
          <div class="roles">
            <Character v-for="index in 5" :key="index" />
          </div>
        </q-card-section>
      </q-card>
    </div>

  </div>
</template>

<script>
  import Character from 'components/Character.vue';
  import {defineComponent} from 'vue';

  export default defineComponent({
    components: {Character},
  });

</script>

<style scoped>
.book-info{
  width: 100%;
  max-width:600px;
  color: black;
}
.roles{
  display:flex;
}
.book{
  display:flex;
}
.book-image{
  width: 100%;
  max-width:200px;
  height:450px;
}
</style>
