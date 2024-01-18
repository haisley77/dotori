<template>
  <q-page>
    <div class="text-h3">내 정보</div>
    <hr class="q-my-md">
    <div class="profile-container q-pa-lg column items-center">
      <q-avatar class="q-mt-lg q-mb-lg" size="200px">
        <img src="https://cdn.quasar.dev/img/avatar.png">
      </q-avatar>
      <q-space></q-space>
      <div class="input-box">
        <div class="profile-info justify-center items-center q-gutter-y-md">
          <div class="flex justify-around q-gutter-md">
            <q-input dense label="프로필 이름" :model-value="dummyUser.profileName" class="large-input" />
            <q-btn>변경</q-btn>
          </div>
          <div class="flex justify-around q-gutter-md">
            <q-input dense label="이름" :model-value="dummyUser.name" class="large-input" />
            <q-btn>변경</q-btn>
          </div>
          <div class="flex justify-around q-gutter-md">
            <q-input label="프로필 소개" :model-value="dummyUser.profileDescription" class="large-input" />
            <q-btn>변경</q-btn>
          </div>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script setup>
  const dummyUser = {
    profileName: '호호홓',
    name: '이름이름',
    profileDescription: '안녕하세요~',
  };

  const color = {
    color: 'green',
  };

</script>

<style lang="scss" scoped>
  .large-input {
    width: 60%;
  }

  .light-brown-bg {
    background-color: $dotori-deep-brown;
  }

  .input-box {
    background: $dotori-light-green;
    border-radius: $dotori-border-radius;
    width: 50%;
  }
</style>
