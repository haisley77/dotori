import {onMounted, ref} from 'vue';
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

    const member_id = ref(null);


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
    const session_properties = ref({});

    // 커넥션 설정 정보
    const connection_properties = ref({});

    // 방 생성 정보
    const room_info = ref({
        roomId: room_id.value,
        hostId: 0,
        title: null,
        password: null,
        isPublic: true,
    });

    // 방 생성 요청 시 전달할 파라미터
    const roomInitializationParam = ref({
        'sessionProperties': session_properties.value,
        'connectionProperties': connection_properties.value,
        'roomInfo': room_info.value,
    });


    const createRoom = async () => {
        const apiPath = apiRootPath + '/session';

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
            if (response.data.status === 201) {
                room_id.value = response.data.get('roomId');
                ovToken.value = response.data.get('token');
                console.log('방 생성 성공 !!');
            }
        } catch (error) {
            console.error(error.response.data.get('message'));
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
                ovToken.value = response.data.get('ovToken');
            }
            if (response.status === 202) {
                console.log(response.data.get('message'));
            }
        } catch (error) {
            console.error(error.response.data.get('message'));
            console.error('커넥션 생성 실패' + error);
        }
    };

    const addRoomMember = async () => {
        const apiPath = apiRootPath + '/add';
        // 유저 token을 이용해 member pk 정보 가져와서 넣는 로직 추가 필요
        // member_id = await axios.getMemberId(path정보, token);
        try {
            const response = await axios.delete(apiPath, {
                params: {
                    roomId: room_id.value,
                    memberId: 10,
                    // memberId: member_id.value,
                },
            });
            if (response.status === 200) {
                console.log('방 참여 정보 갱신 성공 !!');
            }
            if (response.status === 201) {
                console.log('인원 초과로 방 참여 처리 불가');
            }
        } catch (error) {
            console.error(error.response.data.get('message'));
            console.error('방 참여 정보 갱신 처리 중 오류 발생');
        }
    }

    const removeRoomMember = async () => {
        const apiPath = apiRootPath + '/remove';
        // 유저 token을 이용해 member pk 정보 가져와서 넣는 로직 추가 필요
        // member_id = await axios.getMemberId(path정보, token);
        try {
            const response = await axios.delete(apiPath, {
                params: {
                    roomId: room_id.value,
                    memberId: 10,
                    // memberId: member_id.value,
                },
            });
            if (response.status === 200) {
                console.log('방 나가기 정보 갱신 성공 !!');
            }
        } catch (error) {
            console.error(error.response.data.get('message'));
            console.error('방 나가기 정보 갱신 처리 중 오류 발생');
        }
    };


    const connectToOpenVidu = () => {
        // spring 서버에서 받아둔 토큰을 sessionStorage에 저장
        sessionStorage.setItem('ovtoken', ovToken.value);
        console.log('token session storage에 저장 성공');

        // 테스트 커넥션 토큰 "wss://dotori.online:8443?sessionId=ses_Kf1uTdDIrS&token=tok_PbRa2BKbddHcrhsu"
        session.connect(sessionStorage.getItem('ovtoken'))
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
        createRoom,
        getConnectionToken,
        connectToOpenVidu,
        addRoomMember,
        removeRoomMember,
    };
}, {persist: {storage: sessionStorage}});
