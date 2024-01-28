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

          <q-input color="green" bg-color="brown-1" v-model="text" :dense="dense" class="col-11"/>
          <q-btn color="my-green" bg-color="white" class="col-1 q-pa-none npsfont chat">전송</q-btn>
        </div>
      </div>
    </div>

  </div>
</template>
<script setup>
import {ref} from 'vue'

const text = ref('')
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
  //color : #a84d2f;
  border-radius: 10px 10px 10px 10px;
  font-family: NPSfontBold;
//border: solid #cc765a 2px;

}

.chat {
  border-radius: 20px 20px 20px 20px;
//border: solid #cc765a 2px;
}

.npsfont {
  font-family: 'NPSfontBold';
}
</style>
