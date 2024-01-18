<template>

  <q-page style ="position: absolute; top:50%; left:50%;transform :translate(-50%,-50%);">
    <div class = "row flex justify-center" >
    <div class="text-h3 npsfont ">내 정보
    </div>
    </div>
    <hr class="q-my-md" style='border: black 1px solid'>
    <div class="profile-container q-pa-lg column items-center" >
      <q-avatar class="q-mt-lg q-mb-lg profile-bg-white" size="200px">
        <img src="../assets/acorn_character_img.png">
      </q-avatar>
      <q-space></q-space>

      <div class="input-box q-py-sm " style='border: 5px solid #35daa0'>
        <div class="profile-info justify-center items-center">
          <div class="flex justify-around q-my-sm">
            <q-input dense label="프로필 이름" :model-value="dummyUser.profileName" class="large-input" />
            <q-btn unelevated rounded class = "my-info-btn">변경</q-btn>
          </div>
          <div class="flex justify-around q-my-sm">
            <q-input dense label="이름" :model-value="dummyUser.name" class="large-input" />
            <q-btn unelevated rounded class = "my-info-btn">변경</q-btn>
          </div>
          <div class="flex justify-around q-my-sm">
            <q-input dense label="프로필 소개" :model-value="dummyUser.profileDescription" class="large-input" />
            <q-btn unelevated rounded class = "my-info-btn">변경</q-btn>
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
 .my-info-btn{
    background-color: #35daa0;
   font-family: "NPSfontBold";
   color : white;

  }
  .profile-bg-white {
    background-color: white;
    border-radius: 50%;
    height: 200px;
    width: 200px;
    border : 5px solid #35daa0 ;

  }
  .large-input {
    width: 60%;
  }

  .light-brown-bg {
    background-color: $dotori-deep-brown;
  }

  .input-box {
    background: white;
    border-radius: $dotori-border-radius;
    width: 50%;
  }

</style>
