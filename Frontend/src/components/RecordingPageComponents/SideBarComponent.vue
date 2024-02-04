<script setup>
  const props = defineProps({pageNo: Number, curPage: Number});
  const emit = defineEmits(['moveToPage']);
  const movePage = (page) => {
    // alert('clicked!')
    emit('moveToPage', page);
  };
</script>

<template>
  <div class=' col-3 q-pr-sm relative-position hoverpointer' @click='movePage(pageNo)'>
    <img src='~assets/MyPageImages/karina.jpg' alt='dja' class=' side-img'
         v-bind:class="{ 'side-bar-selected': curPage === pageNo, 'side-bar-unselected': curPage !== pageNo }" />
    <div class='absolute-top-left q-pa-xs text-center npsfont side-page-num '
         v-bind:class="{ 'side-bar-selected': curPage === pageNo, 'side-bar-unselected': curPage !== pageNo }">
      장면 : {{ pageNo }}
    </div>

  </div>
</template>

<style scoped>
  @font-face {
    font-family: 'NPSfontBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2310@1.0/NPSfontBold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
  }

  .npsfont {
    font-family: 'NPSfontBold';
  }

  .side-img {
    height: 172px;
    width: 100%;
    border-radius: 15px 0px 15px 0px;
    background: #ffee9a;
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
    border: 6px solid #6E4E1F;
  }

  .hoverpointer {
    cursor: pointer;

  }

  .side-img:hover{
    transform : scale(1.015);
  }
</style>
