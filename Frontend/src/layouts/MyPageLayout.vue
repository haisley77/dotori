<template>
  <q-layout>
    <q-header>
      header
    </q-header>
    <q-drawer
      show-if-above
      :width="200"
      :breakpoint="400"
      class="my-drawer"
    >
      <q-scroll-area class="scroll-placement light-green-bg">
        <q-list padding>
          <q-item clickable v-ripple to="/my-page/info">
            <q-item-section avatar>
              <q-icon name="person" />
            </q-item-section>
            <q-item-section>
              내정보
            </q-item-section>
          </q-item>
          <q-item clickable v-ripple to="/my-page/collection">
            <q-item-section avatar>
              <q-icon name="inbox" />
            </q-item-section>

            <q-item-section>
              보관함
            </q-item-section>
          </q-item>
          <q-item clickable v-ripple to="/my-page/avatar">
            <q-item-section avatar>
              <q-icon name="cruelty_free" />
            </q-item-section>
            <q-item-section>
              아바타
            </q-item-section>
          </q-item>
        </q-list>
      </q-scroll-area>

      <div class="absolute-top light-green-bg profile-placement">
        <div class="absolute-center bg-transparent column items-center">
          <q-avatar size="90px" class="q-mb-sm content-center">
            <img src="https://cdn.quasar.dev/img/boy-avatar.png">
          </q-avatar>
          <div class="text-weight-bold">{{ userInfo.username }}</div>
        </div>
      </div>
    </q-drawer>
    <q-page-container>
      <router-view class="q-pa-lg base-bg" />
    </q-page-container>
  </q-layout>
</template>

<script setup>
  const userInfo = {
    username: '빠른 거북이',
    description: '안녕하세요 ~',
  };
</script>

<style lang="scss" scoped>
  $profile-height: 170px;

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
