<template>
  <div class="player">
    <div class="row">
      <div v-for="player in 4" :key="player" class="col-6 q-pa-sm text-h3">
        <div v-if="player <= props.playerList.length">
          <!-- 플레이어가 있으면서 사용자의 프로필이 맞는 경우-->
          <div class="profile-background q-pa-sm" >
<!--               v-if="props.memberId === props.playerList[player-1].memberId"-->
            <div class="dashed column items-center">
              <img :src="props.playerList[player - 1].profileImg" class="profile-pic q-mr-md q-mt-sm" alt="user-profile-img" style="object-fit: cover">
              <h4 class="q-mr-md q-mt-md q-mb-sm player-name">{{ props.playerList[player - 1].roleName }}</h4>
              <div class="row q-mt-none q-mb-sm">
                <q-btn unelevated rounded color="my-brown q-mr-sm btn-font">
                  <q-menu fit anchor="bottom start" self="top left">
                    <q-item v-for="(role, index) in roleList" :key="role" clickable  @click="toggleRole(player,index)">
                      <q-item-section>{{ role.name }}</q-item-section>
                    </q-item>
                  </q-menu>
                  <div>역할 선택하기</div>
                </q-btn>
                <q-btn unelevated rounded color="my-green q-ml-sm btn-font" @click="cancelRole(player)">
                  <div>선택 취소</div>
                </q-btn>
              </div>
            </div>
          </div>
<!--          &lt;!&ndash; 플레이어가 있으면서 사용자의 프로필이 아닌 경우&ndash;&gt;-->
<!--          <div class="profile-background q-pa-sm" v-else>-->
<!--            <div class="dashed column items-center">-->
<!--              <img :src="props.playerList[player - 1].profileImg" class="profile-pic q-mr-md q-mt-sm" alt="user-profile-img" style="object-fit: cover">-->
<!--              <h4 class="q-mr-md q-mt-md q-mb-sm player-name">{{ props.playerList[player - 1].roleName }}</h4>-->
<!--              <div class="row q-mt-none q-mb-sm" style="visibility: hidden">-->
<!--                <q-btn unelevated rounded color="my-brown q-mr-sm btn-font">-->
<!--                  <q-menu fit anchor="bottom start" self="top left">-->
<!--                    <q-item clickable>-->
<!--                      <q-item-section>토끼</q-item-section>-->
<!--                    </q-item>-->
<!--                    <q-item clickable>-->
<!--                      <q-item-section>거북이</q-item-section>-->
<!--                    </q-item>-->
<!--                  </q-menu>-->
<!--                  <div>역할 선택하기</div>-->
<!--                </q-btn>-->
<!--                <q-btn unelevated rounded color="my-green q-ml-sm btn-font">-->
<!--                  <div>커스텀 아바타</div>-->
<!--                </q-btn>-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
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
  import {storeToRefs} from 'pinia';
  import {useOpenViduStore} from 'stores/openvidu';

  const props = defineProps({
    playerList: Object,
    roleList: Object,
    memberId: Object,
  });

  const openViduStore = useOpenViduStore();
  const {sendingRoleData} = storeToRefs(openViduStore);
  const {sendRoleInfoToOpenVidu} = openViduStore;


  const makeSendingRoleData = () => {
    sendingRoleData.value.roleList = props.roleList;
    sendingRoleData.value.playerList = props.playerList;
  }


  const toggleRole = (player, selectedIndex) => {
    // 역할 선택
    if (props.playerList[player-1].roleIndex === selectedIndex) {
      alert('정상적으로 선택되었습니다.');
      return;
    }
    // 역할 중복 선택 불가
    const selectedRole = props.roleList[selectedIndex];
    if (selectedRole && selectedRole.selected) {
      alert('해당 역할은 이미 선택되었습니다.');
      return;
    }
    const prevSelectedRole = props.roleList[props.playerList[player-1].roleIndex];
    if (prevSelectedRole) {
      prevSelectedRole.selected = false;
    }
    if (selectedRole) {
      selectedRole.selected = true;
      props.playerList[player-1].roleName = selectedRole.name;
      props.playerList[player-1].roleIndex = selectedIndex;
    }
    makeSendingRoleData();
    sendRoleInfoToOpenVidu();
  };

  const cancelRole = (player) => {
    props.roleList[props.playerList[player-1].roleIndex].selected = false;
    props.playerList[player-1].roleName = props.playerList[player-1].name;
    props.playerList[player-1].roleIndex = 5;
    makeSendingRoleData();
    sendRoleInfoToOpenVidu();
  }
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
