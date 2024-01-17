<template>
    <q-header class="bg-white text-grey-8" height-hint="56">
      <q-toolbar>
        <q-btn flat round dense icon="menu" class="q-mr-sm"/>
        <q-avatar>
          <!-- 로고 이미지 -->
        </q-avatar>

        <q-toolbar-title shrink class="text-subtitle1 text-weight-bolder">
          도토리
        </q-toolbar-title>

        <q-space/>
        
        <q-space/>

        <q-btn flat round icon="more_vert" class="q-mr-sm"/>

        <q-btn
          outline
          rounded
          color="primary"
          label="로그인"
          />
      </q-toolbar>
    </q-header>
</template>

<script setup>
import { ref } from 'vue';
const isSearchFocus = ref(false);
</script>

<style lang="scss" scoped>
.q-toolbar{
  height:56px;
  border-style : solid;
  border-width: 0 0 1px 0;
  border-color: #c9c9c9;
}
.search{
  min-width:100px;
  max-width: 600px;
  width : 55%;
  &-btn{
    border-style : solid;
    border-width: 1px 1px 1px 0;
    border-color: #c9c9c9;
    max-width : 60px;
    width:100%;
    height:40px;
  }
}
</style>
