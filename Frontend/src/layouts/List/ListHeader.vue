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
        <div class="row items-center">
        <q-input outlined square dense placeholder="검색"
        @focus="isSearchFocus = true"
        @blur="isSearchFocus = false">
        
          <template v-if="isSearchFocus" #prepend>
            <q-icon name="search"/>
          </template>
          <template #append>
            <q-icon name="keyboard"/>
          </template>
        </q-input>
        <q-btn icon="search"/>
        <q-btn icon="mic"/>
        </div>
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

</style>
