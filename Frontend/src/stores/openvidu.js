import {onMounted, ref} from 'vue';
import {defineStore} from 'pinia';
import {OpenVidu} from 'openvidu-browser';
import {useAxios} from 'vue-axios';

export const useOpenViduStore
  = defineStore('openViduStore', () => {

  const OV = new OpenVidu();
  const session = OV.initSession();
  const ovToken = ref(null);
  const {axios} = useAxios();
  const apiRootPath = "/api/rooms";

  const room_id = ref(null);
  const room_name = ref(null);
  const room_password = ref(null);


  // onMounted(() => {
  //
  //   sessionStorage.setItem('ovToken', 'wss://dotori.online:5443?sessionId=ses_TjFHYfB26j&token=tok_JM6mGDG3n09Aoy46');
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
  const session_properties = ref({
      // 일단은 작성하지 않겠습니다 ~ default 값 사용
  });

  // 방 생성 정보
  const room_info = ref({
    roomId: room_id.value,
    hostId: 0,
    title: null,
    password: null,
    isPublic: true
  });

  // 방 생성 요청 시 전달할 파라미터
  const roomInitializationParam = ref({
    sessionProperties: session_properties.value,
    roomInfo: room_info.value
  });


  const createRoomSession = async () => {
    const apiPath = apiRootPath + "/sessions";

    // 방 정보 setting
    room_info.value.hostId = 1; // 근데 host id 가 왜 Long 으로 입력되어야 하나요??
    room_info.value.title = room_name.value;
    if (room_password.value !== null) {
      room_info.value.password = room_password.value;
      room_info.value.isPublic = false;
    }
    if (room_password.value === undefined) {
      room_info.value.isPublic = false;
    }

    try {
      const response = await axios.post(apiPath, roomInitializationParam.value);
      room_id.value = response.data;
    } catch(error) {
      console.error("방 생성 실패: ", error);
    }
  }

  // 방 connection 생성 요청 시 전달할 파라미터
  const connectionInitializationParams = ref({
    // 일단은 작성하지 않겠습니다 ~ default 값 사용
  });

  const getConnectionToken = async () => {
    const apiPath = apiRootPath + "/connections/" + room_id.value;
    // connection 설정 정보 setting
    try {
      const response = await axios.post(apiPath); // connection 설정 정보 입력할 거면 같이 보내도록 수정하겠습니다 ~
      ovToken.value = response.data;
    } catch (error) {
      console.error("토큰 받기 실패 : ", error);
    }
  }


  const connectToOpenVidu = () => {
    // session.
    // spring 서버에서 받아둔 토큰을 sessionStorage에 저장해야 함
    // 테스트 커넥션 토큰 "wss://dotori.online:8443?sessionId=ses_Kf1uTdDIrS&token=tok_PbRa2BKbddHcrhsu"
    session.connect(ovToken.value)
      .then(() => {
        console.log('ov와 연결 성공!');
      })
      .catch((error) => {
        console.error('ov와 연결 실패:', error);
      });
  };
  return {
    room_id,
    ovToken,
    createRoomSession,
    getConnectionToken,
    connectToOpenVidu,
  };
}, {persist: {storage: sessionStorage}});
