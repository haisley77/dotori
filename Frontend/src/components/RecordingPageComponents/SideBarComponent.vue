<script setup>
  import {useOpenViduStore} from 'stores/openvidu';
  import {ref} from 'vue';

  const ovstore = useOpenViduStore();

  const props = defineProps({pageNo: Number, curPage: Number});
  const emit = defineEmits(['moveToPage']);
  const movePage = (page) => {
    // alert('clicked!')
    emit('moveToPage', page);
  };
  const imagesrc = ref(ovstore.bookInfoList[props.pageNo-1].img);
</script>

<template>
  <div class=' col-3 q-pr-sm q-pt-sm relative-position hoverpointer' @click='movePage(pageNo)'>
    <img :src='imagesrc' alt='dja' class=' side-img'
         v-bind:class="{ 'side-bar-selected': curPage === pageNo, 'side-bar-unselected': curPage !== pageNo }" />
    <div class='absolute-top-left q-pa-xs q-mt-sm text-center npsfont side-page-num '
         v-bind:class="{ 'side-bar-selected': curPage === pageNo, 'side-bar-unselected': curPage !== pageNo }">
      장면 : {{ pageNo }}
    </div>

  </div>
</template>

<style scoped>


  .side-img {
    height: 172px;
    width: 280px;
    border-radius: 15px 0px 15px 0px;
    //background: #ffee9a;
    padding: 4px;
    object-fit: cover;

  }

  .side-page-num {
    background: white;
    height: 3em;
  //width: 3em; color: #6E4E1F; border-radius: 15px 0px 15px 0px;
  }

  .side-bar-unselected {
    border: 6px solid #C7A96E;
  }

  .side-bar-selected {
    border: 6px solid #a84d2f;
  }

  .hoverpointer {
    cursor: pointer;

  }

  .side-img:hover{
    transform : scale(1.015);
  }
</style>
