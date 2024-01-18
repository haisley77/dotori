<template>
  <q-page>
    <div class="text-h3">보관함</div>
    <hr class="q-my-md">
    <div class="container">
      <div class="one-row row q-gutter-y-lg">
        <div class="col-xs-12 col-sm-6 col-lg-4 flex justify-center items-center" v-for="dummy in dummyData"
             key="dummy.id">
          <Card :room-Info=dummy class="q-gutter-lg" />
        </div>
      </div>
    </div>
  </q-page>
</template>

<script setup>
  import Card from 'components/VideoCard.vue';
</script>
<script>
  // dummyData
  const dummyData = [
    {
      id: 1,
      profileImagePath: 'https://cdn.quasar.dev/img/avatar.png',
      title: '토끼와 거북이를 읽어보자1',
      date: '2017-01-04',
      downloadLink: 'https://cdn.quasar.dev/img/avatar.png',
    },
    {
      id: 2,
      profileImagePath: 'https://cdn.quasar.dev/img/avatar.png',
      title: '토끼와 거북이를 읽어보자2',
      date: '2017-01-04',
      downloadLink: 'https://cdn.quasar.dev/img/avatar.png',
    },
    {
      id: 3,
      profileImagePath: 'https://cdn.quasar.dev/img/avatar.png',
      title: '토끼와 거북이를 읽어보자3',
      date: '2017-01-04',
      downloadLink: 'https://cdn.quasar.dev/img/avatar.png',
    },
    {
      id: 4,
      profileImagePath: 'https://cdn.quasar.dev/img/avatar.png',
      title: '토끼와 거북이를 읽어보자4',
      date: '2017-01-04',
      downloadLink: 'https://cdn.quasar.dev/img/avatar.png',
    },
  ];

</script>

<style lang="scss" scoped>
  .dotori-border {
    border-raius: $dotori-border-radius;
  }
</style>
