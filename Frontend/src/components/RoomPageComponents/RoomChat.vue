<template>
  <div style="height: 100%" class="background-green q-pa-sm">
    <div style="height: 100%" class="background-yellow q-pa-sm">
      <div class="column">
        <div class="background-white">
          <!-- 채팅 로그 -->
          <div ref="chatLog" id='chatLog' style="height: 100px; overflow-y: auto; border: 1px solid #ccc; padding:  0px;">
            <div v-for="(message, index) in messageList" :key="index">{{ message }}</div>
<!--            <div ref="scrollMarker"></div>-->
          </div>
        </div>

        <div class="row ">
          <q-input color="green" bg-color="green-1" v-model="chatMessage" @keyup.enter="sendMessage" placeholder="메시지를 입력하세요" class="col-11"/>
          <q-btn color="my-green" bg-color="white" @click="sendMessage" class="col-1 q-pa-none npsfont chat">전송</q-btn>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, defineProps} from 'vue';
import {useOpenViduStore} from 'stores/openvidu';
const openViduStore = useOpenViduStore();
const {session } = openViduStore;
const chatMessage = ref('');
const messageList = ref([]);
const props = defineProps({
    playerList: Object,
    memberId: Object,
  });

onMounted(() => {
  if (session) {
    session.on('signal:chat', (event) => {
    const data = JSON.parse(event.data);
    appendMessage(data.nickname, data.message);
    // scrollToBottom();
    });
  }
});

const sendMessage = () => {
  if (chatMessage.value && session) {
    const matchingPlayer = props.playerList.find(player => player.memberId === props.memberId);
    const data = {
      message: chatMessage.value,
      nickname: matchingPlayer.name,
    };
    session.signal({
      data: JSON.stringify(data),
      type: 'chat',
    });
    chatMessage.value = '';
  }
};

const appendMessage = (nickname, message) => {
  const formattedMessage = `${nickname}: ${message}`;
  messageList.value.push(formattedMessage);
  scrollToBottom(); // 메시지 추가 후 자동 스크롤
};

const scrollToBottom = () => {
  let chatLog = document.getElementById('chatLog');
  console.log("scroll to bottom실행");
  chatLog.scrollTop = chatLog.scrollHeight;
};
</script>

<style scoped>
  .bg-my-green {
    background: #C7A96E !important;
  }

  .input-background {
    background-color: floralwhite;
    height: 1em;

  }

  .send-button {
    height: 1em;
  }

  .background-green {
    background: #C7A96E;
    border-radius: 20px 20px 20px 20px;

  }

  .background-yellow {
    background: white;
    border-radius: 20px 20px 20px 20px;
    //border: dashed #cc765a 5px;
  }

  .background-white {
    background: white;
    height: 7.5em;
    //color : #a84d2f; border-radius: 10px 10px 10px 10px; font-family: NPSfontBold; //border: solid #cc765a 2px;

  }

  .chat {
    border-radius: 20px 20px 20px 20px;
    //border: solid #cc765a 2px;
  }

  .npsfont {
    font-family: 'NPSfontBold';
  }
</style>
