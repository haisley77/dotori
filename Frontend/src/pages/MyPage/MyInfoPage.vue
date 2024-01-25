<template>

  <q-page style="
      position: absolute;
      top:50%;
      left:50%;
      transform :translate(-50%,-50%);
  ">
    <div class="row flex justify-center">
      <div class="text-h3 npsfont ">내 정보</div>
    </div>
    <hr class="q-my-md" style='border: white 1px solid'>
    <div class="profile-container  column items-center">
      <div style="width: 210px;height: 210px; border-radius: 50%; border: #35daa0 5px solid"
           class="q-mb-lg flex justify-center items-center relative-position">
        <img src="../../assets/winter.png" style="width:200px;height:200px;border-radius: 50%;object-fit: cover">
        <q-btn unelevated rounded label="편집" class="absolute-bottom-right my-info-btn"></q-btn>
      </div>
      <q-space></q-space>

      <div class="input-box q-py-sm " style='border: 5px solid #35daa0'>
        <div class="profile-info ">
          <div class=" q-my-sm row">
            <q-input dense label="프로필 이름" :model-value="dummyUser.profileName" class="col-7 offset-1" />
            <q-btn unelevated rounded class="my-info-btn col-2 offset-1">변경</q-btn>
          </div>
          <div class=" q-my-sm row">
            <q-input dense label="프로필 이름" :model-value="dummyUser.name" class="col-7 offset-1" />
            <q-btn unelevated rounded class="my-info-btn col-2 offset-1">변경</q-btn>
          </div>
          <div class=" q-my-sm row">
            <q-input dense label="프로필 이름" :model-value="dummyUser.profileDescription" class="col-7 offset-1" />
            <q-btn unelevated rounded class="my-info-btn col-2 offset-1">변경</q-btn>
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
  .my-info-btn {
    background-color: #35daa0;
    font-family: "NPSfontBold";
    color: white;

  }

  .profile-bg-white {
    background-color: white;
    border-radius: 50%;
    height: 200px;
    width: 200px;
    border: 5px solid #35daa0;

  }


  .light-brown-bg {
    background-color: $dotori-deep-brown;
  }

  .input-box {
    background: white;
    border-radius: $dotori-border-radius;
    width: 30%;

  }

</style>
