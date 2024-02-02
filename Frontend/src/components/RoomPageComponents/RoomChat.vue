<template>
  <div style="height: 100%" class="background-green q-pa-sm">
    <div style="height: 100%" class="background-yellow q-pa-sm">

      <div class="column">
        <div class="background-white">
          [19:45] 🔔도토리씨앗유저님이 로비에 참가하셨습니다.<br/>
          [19:55] 도토리씨앗유저 : 안녕하세요~<br/>
          [19:55] 도토리씨앗유저 : 안녕하세요~<br/>
          [19:55] 도토리씨앗유저 : 안녕하세요~<br/>
        </div>
        <div class="row ">
          <q-input color="green" bg-color="green-1" v-model="text" :dense="dense" class="col-11"/>
          <q-btn color="my-green" bg-color="white" class="col-1 q-pa-none npsfont chat">전송</q-btn>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
  import {ref, onMounted } from 'vue'

  const chatMessage = ref('');

  // // 변경하지 않는 부분
  // onMounted(() => {
  //   //채팅 기능을 초기화.
  //   if (openviduSession.value) {
  //     openviduSession.value.on('signal:chat', (event) => {
  //       const data = JSON.parse(event.data);
  //       appendMessage(data.nickname, data.message);
  //     });
  //   }
  // });
  //
  // const sendMessage = () => {
  //   if (chatMessage.value && openviduSession.value) {
  //     const data = {
  //       message: chatMessage.value,
  //       nickname: '사용자 닉네임',
  //     };
  //     openviduSession.value.signal({
  //       data: JSON.stringify(data),
  //       type: 'chat',
  //     });
  //
  //     appendMessage(data.nickname, data.message);
  //
  //     chatMessage.value = '';
  //   }
  // };
  //
  // const appendMessage = (nickname, message) => {
  //   const chatLog = document.getElementById('chatLog');
  //   if (chatLog) {
  //     const messageElement = document.createElement('div');
  //     messageElement.textContent = `${nickname}: ${message}`;
  //     chatLog.appendChild(messageElement);
  //     chatLog.scrollTop = chatLog.scrollHeight;
  //   }
  // };
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
