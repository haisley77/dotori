<template>
  <q-page>
    <div class='text-h3 npsfont q-pl-lg'>보관함</div>
    <hr class='q-my-md'>
    <div class="q-pa-md">
      <q-table
        style="height: 60vh"
        flat
        :rows="rows"
        :columns="columns"
        row-key="index"
        virtual-scroll
        v-model:pagination="pagination"
        :rows-per-page-options="[0]"
        class="npsfont"
      >
        <template v-slot:header="props">
          <q-tr :props="props">
            <q-th
              v-for="col in props.cols"
              :key="col.name"
              :props="props"
              style="font-size: 1.5em"
            >
              {{ col.label }}
            </q-th>
          </q-tr>
        </template>
      </q-table>
      <!--      title="Treats"-->
    </div>

  </q-page>
</template>

<script setup>
  import Card from 'components/MyPageComponents/VideoCard.vue';

  const columns = [
    {
      name: 'desc',
      required: true,
      label: '책 제목',
      align: 'left',
      field: row => row.name,
      format: val => `${val}`,
      sortable: true,
    },
    {name: 'title', align: 'left', label: '영상제목', field: 'title', sortable: true},
    {name: 'date', align: 'left', label: '날짜', field: 'date', sortable: true},
    {name: 'download', align: 'left', label: '다운로드', field: 'download', sortable: true},


    // { name: 'fat', label: 'Fat (g)', field: 'fat', sortable: true },
    // { name: 'carbs', label: 'Carbs (g)', field: 'carbs' },
    // { name: 'protein', label: 'Protein (g)', field: 'protein' },
    // { name: 'sodium', label: 'Sodium (mg)', field: 'sodium' },
    // { name: 'calcium', label: 'Calcium (%)', field: 'calcium', sortable: true, sort: (a, b) => parseInt(a, 10) - parseInt(b, 10) },
    // { name: 'iron', label: 'Iron (%)', field: 'iron', sortable: true, sort: (a, b) => parseInt(a, 10) - parseInt(b, 10) }
  ];

  const rows = [
    {
      name: '토끼와 거북이',
      title: '2023-01-19_23:10:10_dotori',
      date: '2023-01-19_23:10:10',
    },
    {
      name: '토끼와 거북이',
      title: '2023-01-19_23:10:10_dotori',
      date: '2023-01-19_23:10:10',
    },
    {
      name: '토끼와 거북이',
      title: '2023-01-19_23:10:10_dotori',
      date: '2023-01-19_23:10:10',
    },
    {
      name: '토끼와 거북이',
      title: '2023-01-19_23:10:10_dotori',
      date: '2023-01-19_23:10:10',
    },


  ];
</script>
<script>


  // // dummyData
  // const dummyData = [
  //   {
  //     id: 1,
  //     profileImagePath: '../assets/acorn_character_img.png',
  //     title: '토끼와 거북이를 읽어보자1',
  //     date: '2017-01-04',
  //     downloadLink: 'https://cdn.quasar.dev/img/avatar.png',
  //   },
  //   {
  //     id: 2,
  //     profileImagePath: 'https://cdn.quasar.dev/img/avatar.png',
  //     title: '토끼와 거북이를 읽어보자2',
  //     date: '2017-01-04',
  //     downloadLink: 'https://cdn.quasar.dev/img/avatar.png',
  //   },
  //   {
  //     id: 3,
  //     profileImagePath: 'https://cdn.quasar.dev/img/avatar.png',
  //     title: '토끼와 거북이를 읽어보자3',
  //     date: '2017-01-04',
  //     downloadLink: 'https://cdn.quasar.dev/img/avatar.png',
  //   },
  //   {
  //     id: 4,
  //     profileImagePath: 'https://cdn.quasar.dev/img/avatar.png',
  //     title: '토끼와 거북이를 읽어보자4',
  //     date: '2017-01-04',
  //     downloadLink: 'https://cdn.quasar.dev/img/avatar.png',
  //   },
  // ];

</script>

<style lang='scss' scoped>
  .dotori-border {
    border-raius: $dotori-border-radius;
  }

  .card-border-inner {
    background-color: $dotori-light-green;
    border-radius: $dotori-border-radius;
    border: 5px dashed $dotori-light-brown;
  }

  .card-border-outer {
    background-color: $dotori-green;
    border-radius: $dotori-border-radius;

  }

</style>
