<template>
<div class = "player">
  <div v-for="item in 2" key="item" class="row">
    <div v-for="player in 2" key="player" class="col-6  q-pa-sm text-h3 ">
      <div class=" profile-background q-pa-sm">
        <div class = "dashed column items-center">
        <img src="../../assets/acorn_character_img.png" class="profile-pic q-mr-md q-mt-sm" alt="user-profile-img">
        <h4 class="q-mr-md q-mt-md q-mb-sm player-name">도토리씨앗유저</h4>
        <div class="row q-mt-none q-mb-sm">
          <q-btn unelevated rounded color="my-brown q-mr-sm btn-font">
            <q-menu fit anchor="bottom start" self="top left">
              <q-item clickable>
                <q-item-section>토끼</q-item-section>
              </q-item>
              <q-item clickable>
                <q-item-section>거북이</q-item-section>
              </q-item>
            </q-menu>
            <div>역할 선택하기</div>
          </q-btn>
          <q-btn unelevated rounded color="my-green q-ml-sm btn-font">
            <div>커스텀 아바타</div>
          </q-btn>
        </div>
        </div>
      </div>
    </div>
  </div>
</div>

</template>
<script setup>

</script>

<style scoped>

  .bg-my-green {
    background: #35daa0 !important;
  }

  .bg-my-brown {
    background: #a84d2f !important;
  }

  .player {
    background: #35daa0;
    border-radius: 20px 20px 20px 20px;
  }

  .profile-pic {
    background: ghostwhite;
    border-radius: 50%;
    height: 1.5em;

  }

  .profile-background {
    background: white;
    border-radius: 20px 20px 20px 20px;
  }
  .dashed{
    border: dashed #cc765a 5px;
    border-radius: 20px 20px 20px 20px;
    width: 100%;
  }

  .round {
    border-radius: 20px 20px 20px 20px;
  }

  .btn-font {
    color: #a84d2f;
    font-family: NPSfontBold;
  }




  .player-name {
    color: #a84d2f;
    font-family: NPSfontBold;
  }
</style>
