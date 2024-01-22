<template>
  <Header/>
  <q-layout style = "position: relative">
<!--    <q-header>-->
<!--      header-->
<!--    </q-header>-->

    <q-drawer
      show-if-above
      :width='250'
      :breakpoint='400'
      class='my-drawer'
    >
<!--    <q-drawer-->
<!--      show-if-above-->
<!--      class = "col-3"-->
<!--    >-->
      <q-scroll-area class='scroll-placement light-green-bg'>
        <q-list padding>
          <q-item clickable v-ripple to='/my-page/info' class = "side-bar-menu-bg-white q-mx-sm q-mb-sm">
            <q-item-section avatar>
              <q-icon name='person'/>
            </q-item-section>
            <q-item-section>
              <span class = "npsfont" >내정보</span>
            </q-item-section>
          </q-item>
          <q-item clickable v-ripple to='/my-page/collection' class = "side-bar-menu-bg-white q-mx-sm q-mb-sm">
            <q-item-section avatar>
              <q-icon name='inbox' />
            </q-item-section>

            <q-item-section>
              <span class = "npsfont" >보관함</span>
            </q-item-section>
          </q-item>
          <q-item clickable v-ripple to='/my-page/avatar' class = "side-bar-menu-bg-white q-mx-sm q-mb-sm">
            <q-item-section avatar>
              <q-icon name='cruelty_free' />
            </q-item-section>
            <q-item-section>
              <span class = "npsfont">아바타</span>
            </q-item-section>
          </q-item>
        </q-list>
      </q-scroll-area>

      <div class='absolute-top light-green-bg profile-placement'>
        <div class='absolute-center bg-transparent column items-center'>
          <div class = "profile-bg-white q-mb-md">
            <q-avatar size='120px' class='q-mb-sm content-center'>
              <img src='../assets/acorn_character_img.png'>
            </q-avatar>
          </div>
          <div class='text-weight-bold npsfont'>{{ userInfo.username }}</div>
        </div>
      </div>
    </q-drawer>
    <q-page-container>
      <router-view class='q-pa-lg ' />
    </q-page-container>
  </q-layout>
</template>

<script setup>
  const userInfo = {
    username: '빠른 거북이',
    description: '안녕하세요 ~',
  };
  import Header from 'layouts/Header.vue';
</script>

<style lang='scss' scoped>
  $profile-height: 170px;



  .side-bar-menu-bg-white{
    background-color: white;
    border-radius: 40px;
  }

  .profile-bg-white {
    background-color: white;
    border-radius: 50%;
    height: 120px;
    width: 120px;
  }

  .page-background-skyblue{
    background-color: red;
  }
  .base-bg {
    background-color: $dotori-mint;
  }

  .light-green-bg {
    background-color: $dotori-green;
  }

  .profile-placement {
    height: $profile-height;
    border-right: 1px solid #ddd;
  }

  .scroll-placement {
    height: calc(100% - #{$profile-height});
    margin-top: $profile-height;
    border-right: 1px solid #ddd;
  }
</style>
