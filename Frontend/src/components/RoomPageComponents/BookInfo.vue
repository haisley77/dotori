<script setup>

</script>

<template>
  <div style='height: 100%' class='background-green q-pa-sm'>

    <div style='height: 100%' class='background-yellow q-pa-sm '>
      <div class='column items-center'>
        <img src='../../assets/BookImages/rabbitandturtle.jpg' class='bookimg q-pb-sm'>
      </div>
      <hr class='q-ma-none' />
      <div class=' column q-pa-sm background-white'>
        제목 : 토끼와 거북이<br />
        저자 : 도토리<br />
        필요 인원 : 4<br />
        줄거리 : 토끼와 거북이 이야기는 토끼와 거북이가 경주를 벌이는 이야기입니다.
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
