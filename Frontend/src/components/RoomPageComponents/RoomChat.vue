<template>
<div style="height: 100%" class="background-green q-pa-sm">
    <div style="height: 100%" class="background-yellow q-pa-sm">
      <div class="column">
        <div class="background-white">
      <!-- 채팅 로그 -->
      <div ref="chatLog" style="height: 100px; overflow-y: auto; solid #ccc; padding: 10px;">
        <div v-for="(message, index) in messageList" :key="index">{{ message }}</div>
      </div>
        <div class="row ">
      <!-- 메시지 input -->
      <q-input color="green" bg-color="green-1" v-model="chatMessage" @keyup.enter="sendMessage" placeholder="메시지를 입력하세요" :dense="dense" class="col-11"/>
          <q-btn color="my-green" bg-color="white" class="col-1 q-pa-none npsfont chat">전송</q-btn>
    </div>
    </div>
    </div>
  </div>
  </div>
</template>

<script setup>
import { ref, onMounted, defineProps } from 'vue';
import {useOpenViduStore} from 'stores/openvidu';
const openViduStore = useOpenViduStore();
const {session} = openViduStore;
const chatMessage = ref('');
const messageList = ref([]);

onMounted(() => {
  if (session) {
    session.on('signal:chat', (event) => {
      const data = JSON.parse(event.data);
    });
  }
});

const sendMessage = () => {
  if (chatMessage.value && session) {
    const data = {
      message: chatMessage.value,
      nickname: '사용자 닉네임',
    };
    session.signal({
      data: JSON.stringify(data),
      type: 'chat',
    });

    appendMessage(data.nickname, data.message);
    chatMessage.value = '';
  }
};

const appendMessage = (nickname, message) => {
  // Display the message in the template
  messageList.value.push(`${nickname}: ${message}`);
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

