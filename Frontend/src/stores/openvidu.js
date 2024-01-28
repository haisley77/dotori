import {onMounted, ref} from 'vue';
import {defineStore} from 'pinia';
import {OpenVidu} from 'openvidu-browser';
import {useRouter} from 'vue-router';

const router = useRouter();
export const useOpenViduStore
  = defineStore('openViduStore', () => {

  const OV = new OpenVidu();
  const session = OV.initSession();
  const ovToken = ref(null);


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
  //
  // const connectToOpenVidu = () => {
  //   // session.
  //   // spring 서버에서 받아둔 토큰을 sessionStorage에 저장해야 함
  //   session.connect('wss://dotori.online:8443?sessionId=ses_Lcix8KPupw&token=tok_HXKUP5jq6f5u71lv')
  //     .then(() => {
  //       console.log('ov와 연결 성공!');
  //     })
  //     .catch((error) => {
  //       console.error('ov와 연결 실패:', error);
  //     });
  // };
  const connectToOpenVidu = () => {
    return new Promise((resolve, reject) => {
      session.connect('wss://dotori.online:8443?sessionId=ses_MauTkcyqed&token=tok_PdafMNDH87acByjL')
        .then(() => {
          console.log('ov와 연결 성공!');
          resolve(); // Resolve the promise if connection successful
        })
        .catch((error) => {
          console.error('ov와 연결 실패:', error);
          reject(error); // Reject the promise with the error if connection fails
        });
    });
  };

  return {
    connectToOpenVidu,
  };
}, {persist: {storage: sessionStorage}});
