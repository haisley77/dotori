<template>
  <q-page
    style="
            position: absolute;
            top: 55%;
            left: 50%;
            transform: translate(-50%, -50%);
        "
  >
    <div class="row flex justify-center">
      <div class="text-h3 npsfont">내 정보</div>
    </div>
    <hr class="q-my-md" style="border: white 1px solid" />
    <div class="profile-container column items-center">
      <div
        style="
                    width: 210px;
                    height: 210px;
                    border-radius: 50%;
                    border: #c7a96e 5px solid;
                "
        class="q-mb-lg flex justify-center items-center relative-position"
      >
        <img
          :src="memberInfo.profileImg"
          style="
                        width: 200px;
                        height: 200px;
                        border-radius: 50%;
                        object-fit: cover;
                    "
        />
        <q-btn
          unelevated
          rounded
          label="편집"
          class="absolute-bottom-right my-info-btn"
          @click="openFilePopup"
        ></q-btn>
      </div>
      <q-space></q-space>

      <div class="input-box q-py-sm" style="border: 5px solid #c7a96e">
        <div class="profile-info">
          <div class="q-my-sm row">
            <q-input
              dense
              label="프로필 이름"
              v-model="memberInfo.nickName"
              class="col-7 offset-1"
              color="brand"
            />
            <q-btn
              @click="changeNickname(memberInfo.nickName)"
              unelevated
              rounded
              class="my-info-btn col-2 offset-1"
            >변경
            </q-btn
            >
          </div>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script setup>
  import {useRouter} from 'vue-router';
  import {localAxios,imgAxios} from 'src/axios/http-commons';
  import {useOpenViduStore} from 'stores/openvidu';
  import {storeToRefs} from 'pinia';

  const openViduStore = useOpenViduStore();
  const {memberInfo} = storeToRefs(openViduStore);
  const axios = localAxios();
  const fileAxios = imgAxios();
  const router = useRouter();

  const changeProfileImg = async (event) => {
    const formData = new FormData();
    console.log(event.target.files[0]);
    const newProfileImg = event.target.files[0];
    formData.append("profileImg", newProfileImg);
    try {
      const response = await fileAxios.put('/api/members/update_profileimg', formData);
      memberInfo.value.profileImg = response.data.profileImg;
      alert('프로필 이미지 변경 성공');
      router.push('/my-page/info');
    } catch (error) {
      console.error('API 호출 중 오류 발생:', error);
    }
  };

  const openFilePopup = async () => {
    // 파일 선택 창 열기
    const fileInput = document.createElement('input');
    fileInput.type = 'file';
    fileInput.accept = 'image/*'; // 이미지 파일만 선택 가능하도록 설정할 수 있음
    fileInput.onchange = changeProfileImg;
    fileInput.click();
  }

  const changeNickname = async (newNickname) => {
    try {
      const response = await axios.put(
        '/api/members/update_nickname',
        {},
        {
          params: {newNickname},
        },
      );
      alert('닉네임 변경 성공');
      router.push('/my-page/info');
    } catch (error) {
      console.error('API 호출 중 오류 발생:', error);
    }
  };

</script>

<style lang="scss" scoped>
  .my-info-btn {
    background-color: #c7a96e;
    font-family: 'NPSfontBold';
    color: white;
  }

  .profile-bg-white {
    background-color: white;
    border-radius: 50%;
    height: 200px;
    width: 200px;
    border: 5px solid #c7a96e;
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
