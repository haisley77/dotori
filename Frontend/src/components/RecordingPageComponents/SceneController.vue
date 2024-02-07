<script setup>
  import {useOpenViduStore} from 'stores/openvidu';

  const ovstore = useOpenViduStore();
  const props = defineProps({curPage: Number});
  const emit = defineEmits(['moveToPage']);
  const nextPage = () => {
    if (ovstore.bookInfoList.length > props.curPage) {
      emit('moveToPage', props.curPage + 1);
    } else {
      console.log('마지막 페이지 입니다!');
    }
  };

  const beforePage = () => {
    if (1 < props.curPage) {
      emit('moveToPage', props.curPage - 1);
    } else {
      console.log('첫번째 페이지 입니다!');
    }
  };
</script>

<template>
  <div class='controller-container col-4 q-pt-sm'>
    <div class='out-back ' style='height: 100%'>
      <div class='in-back q-pa-sm' style='width: 100%; height: 100%'>
        <div class='button-container row'>
          <div class='left-button-container col-3'>
            <q-btn round color='grey-9' icon='mdi-arrow-left-bold' size='lg' @click='beforePage' />
          </div>
          <div class='center-button-container col-6'>
            <div class='play-button-container'>
              <q-btn round color='blue-12' icon='mdi-play' size='lg' />
            </div>
            <div class='stop-button-container'>
              <q-btn outline round color='white' text-color='red-5' icon='mdi-stop' size='lg' />
            </div>
          </div>
          <div class='right-button-container col-3'>
            <q-btn round color='grey-9' icon='mdi-arrow-right-bold' size='lg' @click='nextPage' />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
  @font-face {
    font-family: 'NPSfontBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2310@1.0/NPSfontBold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
  }

  .npsfont {
    font-family: 'NPSfontBold';
  }


  .controller-container {
    height: 180px;
  //border: black solid 1px;
  }

  .left-button-container {
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .center-button-container {
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .right-button-container {
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .play-button-container {
    margin-right: 10px;
  }

  .stop-button-container {
    margin-left: 10px;
  }

  .out-back {
    background: #C7A96E;
    padding: 6px;
    border-radius: 15px;
  }

  .in-back {
  //background: #ffee9a; background: #ffffff; border-radius: 15px;
  }

  .button-container {
    background: white;
    border-radius: 15px;
    height: 100%;
  //border: #cc765a dashed 4px;
  }

</style>
