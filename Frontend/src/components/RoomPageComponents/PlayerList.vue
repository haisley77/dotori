<template>
  <div class="player">
    <div class="row">
      <div v-for="player in 4" :key="player" class="col-6 q-pa-sm text-h3">
        <div v-if="player <= props.playerList.length">
          <div class="profile-background q-pa-sm">
            <div class="dashed column items-center">
              <img :src="props.playerList[player - 1].profileImg" class="profile-pic q-mr-md q-mt-sm" alt="user-profile-img" style="object-fit: cover">
              <h4 class="q-mr-md q-mt-md q-mb-sm player-name">{{ props.playerList[player - 1].name }}</h4>
              <div class="row q-mt-none q-mb-sm">
                <q-btn unelevated rounded color="my-brown q-mr-sm btn-font">
                  <q-menu fit anchor="bottom start" self="top left">
                    <q-item v-for="(role, index) in roleList" :key="role" clickable :disable="selectedRoleList[player - 1]" @click="toggleRole(player - 1, index)">
                      <q-item-section>{{ role }}</q-item-section>
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
        <!-- 플레이어가 없는 경우 -->
        <div v-else>
          <div class="profile-background q-pa-sm">
            <div class="dashed column items-center">
              <img src="../../assets/DotoriImages/acorn_character_img.png" class="profile-pic q-mr-md q-mt-sm" alt="user-profile-img">
              <h4 class="q-mr-md q-mt-md q-mb-sm player-name">사용자 없음</h4>
              <div class="row q-mt-none q-mb-sm" style="visibility: hidden">
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
  </div>
</template>

<script setup>
  import { ref } from 'vue';

  const props = defineProps({ playerList: Object });

  // 역할 리스트
  const roleList = ref(['토끼', '거북이']);
  // 대기방에 접속한 사용자가 선택한 역할 리스트
  const selectedRoleList = ref(Array(roleList.value.length).fill(false));

  // 클릭 시 비활성화
  const toggleRole = (selectedIndex, roleIndex) => {
    selectedRoleList.value[selectedIndex] = !selectedRoleList.value[selectedIndex];
    console.log(`Player ${selectedIndex + 1} chose role: ${roleList.value[roleIndex]}`);
  };
</script>


<style scoped>

  .bg-my-green {
    background: #C7A96E !important;
  }

  .bg-my-brown {
    background: #a84d2f !important;
  }

  .player {
    background: #C7A96E;
    border-radius: 20px 20px 20px 20px;
  }

  .profile-pic {
    background: white;
    border-radius: 50%;
    height: 1.5em;
    width: 1.5em;

  }

  .profile-background {
    background: white;
    border-radius: 20px 20px 20px 20px;
  }

  .dashed {
    border: dashed #6E4E1F 5px;
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
    color: #6E4E1F;
    font-family: NPSfontBold;
  }
</style>
