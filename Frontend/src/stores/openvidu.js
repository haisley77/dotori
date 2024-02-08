import {ref} from 'vue';
import {defineStore} from 'pinia';
import {OpenVidu} from 'openvidu-browser';

import {localAxios} from '../axios/http-commons';
import {useRouter} from 'vue-router';

const isLogin = ref();
const memberInformation = ref({
});


const router = useRouter();
const axios = localAxios();
export const useOpenViduStore
  = defineStore('openViduStore', () => {


  const OV = new OpenVidu();
  const session = OV.initSession();
  const ovToken = ref(null);

  const apiRootPath = '/api/rooms';

  const roomId = ref(0);
  const memberId = ref(30);

  const subscribers = ref([]);
  const mainStreamManager = ref();
  var mainStreamManagerReal = null;
  const isPublished = ref(false);
  // 방장인지 아닌지 판단
  const isHost = ref(true);

  //나중에 역할 선택에 따라 변경할 부분
  const minRole = ref(0);
  const canvasStream = ref();
  const changeCanvasStream = (stream) => {
    canvasStream.value = stream;
  };
  // 방 세션 설정 정보
  const session_properties = ref({});
  // 커넥션 설정 정보
  const connection_properties = ref({});

  // 방 설정 정보
  const roomInfo = ref({
    hostId: memberId.value,
    title: null,
    password: null,
    isRecording: false,
    joinCnt: 0,
    limitCnt: 0,
    isPublic: true,
  });

  // 책, 역할 설정 정보
  const bookDetail = ref({
    book: {},
    roles: [],
    scenes: [],
  });

  const roomInitializationParam = ref({
    sessionProperties: null,
    connectionProperties: null,
    roomInfo: null,
    bookInfo: null,
  });

  const createRoom = (bookmodal) => {
    return new Promise((resolve, reject) => {
      const apiPath = apiRootPath + '/session';
      roomInitializationParam.value.bookInfo = bookmodal;
      roomInitializationParam.value.roomInfo = roomInfo.value;

      axios.post(apiPath, roomInitializationParam.value)
        .then((response) => {
          roomId.value = response.data.roomId;
          ovToken.value = response.data.token;
          resolve(response.data);
        })
        .catch((error) => {
          console.error('방 생성 실패 : ' + error.response);
          reject(error);
        });
    });
  };

  const getConnectionToken = (room) => {
    return new Promise((resolve, reject) => {
      roomInitializationParam.value.bookInfo = room.book;
      roomInitializationParam.value.roomInfo = room;

      const apiPath = apiRootPath + `/connection/${room.roomId}`;

      axios.post(apiPath, connection_properties.value)
        .then((response) => {
          if (response.status === 200) {
            roomId.value = response.data.roomId;
            ovToken.value = response.data.token;
            resolve(response.data);
          }
          if (response.status === 202) {
            console.log(response.data.message);
            reject(new Error(response.data.message));
          }
        })
        .catch((error) => {
          console.error(error.response);
          reject(error);
        });
    });
  };

  const addRoomMember = (book) => {
    return new Promise((resolve, reject) => {
      const apiPath = apiRootPath + `/add/${roomId.value}/${memberId.value}/${book.bookId}`;

      axios.post(apiPath)
        .then((response) => {
          if (response.status === 200) {
            bookDetail.value = response.data.bookInfo;
            minRole.value = bookDetail.value.roles[0].roleId;
            resolve(response.data);
          } else if (response.status === 201) {
            console.log('인원 초과로 방 참여 처리 불가');
            reject('인원 초과로 방 참여 처리 불가');
          }
        })
        .catch((error) => {
          console.error('방 참여 정보 갱신 처리 중 오류 발생 : ', error.response);
          reject(error);
        });
    });
  };

  const removeRoomMember = () => {
    return new Promise((resolve, reject) => {
      const apiPath = apiRootPath + `/remove/${roomId.value}/${memberId.value}`;

      axios.delete(apiPath)
        .then((response) => {
          resolve(response.data);
        })
        .catch((error) => {
          console.error('방 나가기 정보 갱신 처리 중 오류 발생 : ', error.response);
          reject(error);
        });
    });
  };

  const updateRoom = (isRecording) => {
    return new Promise((resolve, reject) => {
      const apiPath = apiRootPath + `/update/${roomId.value}`;

      roomInfo.value.isRecording = isRecording;

      axios.post(apiPath, roomInfo.value)
        .then((response) => {
          roomId.value = response.data.roomId;
          resolve(response.data);
        })
        .catch((error) => {
          console.error('방 정보 업데이트 처리 중 문제 발생 : ', error.response);
          reject(error);
        });
    });
  };


  const connectToOpenVidu = () => {
    return new Promise((resolve, reject) => {
      //새로운 스트림이 생기면 그 스트림에 구독한다
      session.on('streamCreated', ({stream}) => {
        const subscriber = session.subscribe(stream, stream.streamId);
        subscribers.value.push(subscriber);
      });
      //스트림이 사라지면 그 스트림은 구독을 취소한다
      session.on('streamDestroyed', ({stream}) => {
        const index = subscribers.value.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          subscribers.value.splice(index, 1);
        }
      });

      session.connect(ovToken.value)
        .then(() => {
          console.log(ovToken.value);
          resolve();
        })
        .catch((error) => {
          console.error('ov와 연결 실패 : ', error);
          reject(error);
        });
    });
  };
  const publish = (publisher) => {
    session.publish(publisher).then(() => {
      mainStreamManager.value = publisher;
      mainStreamManagerReal = publisher;
      console.log('published my video!');
      isPublished.value = true;
    }).catch((error) => {
      // isPublished.value = true;
      console.log(error);
    });
  };

  const unpublish = () => {
    console.log(mainStreamManager.value.streamId);
    session.unpublish(mainStreamManagerReal).then(() => {
      console.log('unpublished my video!!');
      isPublished.value = false;
      mainStreamManager.value = null;
      mainStreamManagerReal = null;
    }).catch((error) => {
      // isPublished.value = false;
      console.log(session.connection);
      console.log(mainStreamManager.value.stream.connection);
      console.log('unpblish failed!' + error);
    });

  };

  const playerList = ref([]);
  const myRole = ref();

  return {
    roomInfo,
    memberId,
    isHost,
    session,
    playerList,
    ovToken,
    bookDetail,
    roomInitializationParam,
    createRoom,
    connectToOpenVidu,
    addRoomMember,
    updateRoom,
    publish,


    subscribers, mainStreamManager, OV,
    getConnectionToken,
    removeRoomMember,

    unpublish,
    isPublished,
    myRole, minRole, canvasStream, changeCanvasStream,
  };
}, {persist: {storage: sessionStorage}});
