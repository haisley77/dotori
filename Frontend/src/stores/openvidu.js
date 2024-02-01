import {onMounted, reactive, ref} from 'vue';
import {defineStore} from 'pinia';
import {OpenVidu} from 'openvidu-browser';

import {localAxios} from '../axios/http-commons';
import {useRouter} from 'vue-router';


const router = useRouter();
const axios = localAxios();
export const useOpenViduStore
  = defineStore('openViduStore', () => {

  const OV = new OpenVidu();
  const session = OV.initSession();
  const ovToken = ref(null);

  const apiRootPath = '/api/rooms';

  const room_id = ref(null);
  const room_name = ref(null);
  const room_password = ref(null);
  const is_public = ref(false);
  const is_recording=ref(false);
  const role_cnt= ref(1);
  const member_id = ref(47);


  // onMounted(() => {
  //   member_id = await axios.getMemberId(path정보, accesstoken);
  //   sessionStorage.setItem('ovToken', ovToken.value);
  //   const storedOVToken = sessionStorage.getItem('ovToken');
  //   if (storedOVToken) {
  //     console.log('onMounted 시점에 토큰 발견 : ' + storedOVToken);
  //     ovToken.value = storedOVToken;
  //     session.connect(ovToken.value).then(() => {
  //       console.log('ov와 연결 성공!');
  //     })
  //       .catch((error) => {
  //         console.error('ov와 연결 실패:', error);
  //       });
  //   } else {
  //     console.log('onMounted 시점에 토큰 발견 실패 : ');
  //   }
  // });


  // 방 세션 설정 정보
  const session_properties = ref({});

  // 커넥션 설정 정보
  const connection_properties = ref({});

  // 방 생성 정보
  const room_info= reactive({
    hostId: member_id.value,
    title: room_name.value,
    password: room_password.value,
    isRecording: is_recording.value,
    joinCnt: 0,
    limitCnt: role_cnt.value,
    isPublic: is_public.value,
  });


  // 방 생성 요청 시 전달할 파라미터
  const roomInitializationParam = ref({
    sessionProperties: session_properties.value,
    connectionProperties: connection_properties.value,
    // roomInfo: ref(room_info.value),
    // bookInfo: ref(null),
    roomInfo : null,
    bookInfo : null,
  });


  const createRoom = async (bookmodal) => {
    const apiPath = apiRootPath + '/session';
    // console.log("ovjs : " + bookmodal)
    roomInitializationParam.value.bookInfo = bookmodal;


    roomInitializationParam.value.roomInfo = room_info;
    console.log("title! : " +roomInitializationParam.value.bookInfo.title);
    // 방 정보 setting
    if (room_password.value === null && is_public === false) {
      console.log('방은 비공개인데 비밀번호가 설정되지 않았음');
      return;
    }
    try {
      const response = await axios.post(apiPath, roomInitializationParam.value);

      if (response.data.status === 201) {
        room_id.value = response.data.get('roomId');
        ovToken.value = response.data.get('token');
        console.log('방 생성 성공 !!');
      }
    } catch (error) {
      console.error(error.response);
      console.log(apiPath);
      console.error('방 생성 실패: ', error);
    }
  };

  const getConnectionToken = async () => {
    const apiPath = apiRootPath + '/connection';
    try {
      const response = await axios.post(apiPath, connection_properties.value, {
        params: {
          roomId: room_id.value,
        },
      });
      if (response.status === 200) {
        room_id.value = response.data.get('roomId');
        ovToken.value = response.data.get('token');
      }
      if (response.status === 202) {
        console.log(response.data.get('message'));
      }
    } catch (error) {
      console.error(error.response);
      console.error('커넥션 생성 실패' + error);
    }
  };

  const addRoomMember = async () => {
    const apiPath = apiRootPath + '/add';

    try {
      const response = await axios.post(apiPath, {
        params: {
          roomId: room_id.value,
          memberId: member_id.value,
        },
      });
      if (response.status === 200) {
        console.log('방 참여 정보 갱신 성공 !!');
      }
      if (response.status === 201) {
        console.log('인원 초과로 방 참여 처리 불가');
      }
    } catch (error) {
      console.log(error.response);
      // console.error(error.response.data.get('message'));
      console.error('방 참여 정보 갱신 처리 중 오류 발생');
    }
  };

  const removeRoomMember = async () => {
    const apiPath = apiRootPath + '/remove';

    try {
      const response = await axios.delete(apiPath, {
        params: {
          roomId: room_id.value,
          memberId: member_id.value,
        },
      });
      if (response.status === 200) {
        console.log('방 나가기 정보 갱신 성공 !!');
      }
    } catch (error) {
      console.error(error.response);
      console.error('방 나가기 정보 갱신 처리 중 오류 발생');
    }
  };


  const connectToOpenVidu = () => {
    sessionStorage.setItem('ovToken', ovToken.value);
    console.log('token sessionStorage 저장 성공');

    session.connect(sessionStorage.getItem('ovToken'))
      .then(() => {
        console.log('ov와 연결 성공!');
        // 방 참여 인원 정보 갱신
        addRoomMember().then(()=>console.log('참여 처리 성공')).catch((error)=>console.error('에러에러'));
      })
      .catch((error) => {
        console.error('ov와 연결 실패:', error);
      });
  };

  return {
    room_name,
    room_password,
    is_public,
    ovToken,
    createRoom,
    getConnectionToken,
    connectToOpenVidu,
    addRoomMember,
    removeRoomMember,
  };
}, {persist: {storage: sessionStorage}});
