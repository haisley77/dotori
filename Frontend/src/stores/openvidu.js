import {ref} from 'vue';
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

  const room_id = ref(0);
  const room_name = ref(null);
  const room_password = ref(null);
  const is_private = ref(false);
  const member_id = ref(50);


  // 세션 정보 저장
  const sessionInfo = ref({
    session, // 세션 객체
    ovToken, // 토큰 정보
    room_id, // 방 ID
    room_name, // 방 이름
    room_password, // 방 비밀번호
    is_private, // 비밀 방 여부
    member_id, // 사용자 ID
  });


  // 방 세션 설정 정보
  const session_properties = ref({});

  // 커넥션 설정 정보
  const connection_properties = ref({});

  // 방 생성 정보
  const room_info = ref({
    hostId: member_id.value,
    title: null,
    password: null,
    isRecording: false,
    joinCnt: 0,
    limitCnt: 0,
    isPublic: true,
  });




  // 방 생성 요청 시 전달할 파라미터
  const roomInitializationParam = ref({
    sessionProperties: null,
    connectionProperties: null,
    roomInfo: null,
    bookInfo: null,
  });


  const createRoom = (bookmodal) => {
    return new Promise((resolve, reject) => {
      const apiPath = apiRootPath + '/session';

      room_info.value.title = room_name.value;
      room_info.value.password = room_password.value;
      room_info.value.isPublic = !is_private;
      room_info.value.limitCnt = bookmodal.roleCnt;

      roomInitializationParam.value.bookInfo = bookmodal;
      roomInitializationParam.value.roomInfo = room_info.value;

      // 방 정보 setting
      if (room_password.value === null && is_private === true) {
        console.log('비밀번호 입력 필수');
        reject('비밀번호 입력 필수'); // Reject the promise with an error message
        return;
      }

      axios.post(apiPath, roomInitializationParam.value)
        .then((response) => {
          console.log(response.status);
          if (response.status === 201) {
            room_id.value = response.data.roomId;
            ovToken.value = response.data.token;

            resolve(response.data); // Resolve the promise with the response data
          }
        })
        .catch((error) => {
          console.error('방 생성 실패 !!');
          console.error(error.response);
          reject(error); // Reject the promise with the error
        });
    });
  };

  const getConnectionToken = (room) => {
    console.log('getConnectionToken 호출됨', room);
    return new Promise((resolve, reject) => {
      const apiPath = apiRootPath + `/connection/${room.roomId}`;

      axios.post(apiPath, connection_properties.value)
        .then((response) => {
          if (response.status === 200) {
            room_id.value = response.data.roomId;
            ovToken.value = response.data.token;
            resolve(response.data); // Resolve with the response data
          }
          if (response.status === 202) {
            console.log(response.data.message);
            reject(new Error(response.data.message)); // Reject with an error containing the message
          }
        })
        .catch((error) => {
          console.error(error.response);
          console.error('커넥션 생성 실패');
          reject(error); // Reject with the axios error
        });
    });
  };

  const addRoomMember = () => {
    return new Promise((resolve, reject) => {
      const apiPath = apiRootPath + `/add/${room_id.value}/${member_id.value}`;

      axios.post(apiPath)
        .then((response) => {
          if (response.status === 200) {

            resolve(response.data); // Resolve the promise with the response data
          } else if (response.status === 201) {
            console.log('인원 초과로 방 참여 처리 불가');
            reject('인원 초과로 방 참여 처리 불가'); // Reject the promise with an error message
          }
        })
        .catch((error) => {
          console.log(error.response);
          console.error('방 참여 정보 갱신 처리 중 오류 발생');
          reject(error); // Reject the promise with the error
        });
    });
  };

  const removeRoomMember = () => {
    const apiPath = apiRootPath + `/remove/${room_id.value}/${member_id.value}`;

    axios.delete(apiPath)
      .then((response) => {
      if (response.status === 200) {
        console.log('방 나가기 정보 갱신 성공 !!');
        // 페이지 이동
      }
    }).catch((error) => {
      console.error(error.response);
      console.error('방 나가기 정보 갱신 처리 중 오류 발생');
    });

  };

  const connectToOpenVidu = () => {
    return new Promise((resolve, reject) => {
      session.connect(ovToken.value)
        .then(() => {
          console.log('ov와 연결 성공!');
          resolve(); // Resolve the promise on successful connection
        })
        .catch((error) => {
          console.error('ov와 연결 실패:', error);
          reject(error); // Reject the promise with the error on connection failure
        });
    });
  };


  return {
    session,
    sessionInfo,
    room_name,
    room_password,
    is_private,
    ovToken,
    roomInitializationParam,
    createRoom,
    connectToOpenVidu,
    addRoomMember,
    getConnectionToken
  };
}, {persist: {storage: sessionStorage}});
