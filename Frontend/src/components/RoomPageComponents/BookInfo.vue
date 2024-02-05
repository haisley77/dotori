<script setup>
  const props = defineProps({bookInfo: Object});

</script>

<template>
  <div style='height: 100%' class='background-green q-pa-sm'>

    <div style='height: 100%' class='background-yellow q-pa-sm '>
      <div class='column items-center'>
        <img src='../../assets/BookImages/rabbitandturtle.jpg' class='bookimg q-pb-sm'>
      </div>
      <hr class='q-ma-none' />
      <div class=' column q-pa-sm background-white'>
        제목 : {{bookInfo.title}}<br />
        저자 :  {{bookInfo.author}}<br />
        필요 인원 : {{bookInfo.roleCnt}}<br />
        줄거리 : {{bookInfo.summary}}
        <br />
      </div>
    </div>

  </div>
</template>

<style scoped>
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
    height: 10em;

    border-radius: 20px 20px 20px 20px;
    font-family: NPSfontBold;
  //color : #a84d2f;

  }

  .bookimg {
    border-radius: 20px 20px 20px 20px;
  //border: solid #cc765a 2px; height: 20em;
  }
</style>
