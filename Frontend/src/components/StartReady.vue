<script setup>

</script>

<template>
  <div style="height: 100%" class="background-green q-pa-sm">

    <div
      style="
      height: 100%;
      display: flex;
      flex-direction: column;
      justify-content: center;
      "
      class="background-yellow q-pa-sm"
    >
      <q-btn unelevated  label="시작하기" class="start-btn" />
    </div>

  </div>
</template>

<style scoped>

  .start-btn {
    //background: #35daa0;
    //color: white;
    font-family: NPSfontBold;
    font-size: 3.5em;
    color: #a84d2f;
    //background: #35daa0;
    height: 100%;
    border-radius: 20px 20px 20px 20px;
    border: dashed #cc765a 5px;

  }

  .background-green {
    background: #35daa0;
    border-radius: 20px 20px 20px 20px;

  }

  .background-yellow {
    background: #ebf686;
    border-radius: 20px 20px 20px 20px;
    //border: dashed #cc765a 5px;
  }

  .background-white {
    background: floralwhite;
    height: 260px;

    border-radius: 20px 20px 20px 20px;
    font-family: NPSfontBold;
    border: solid #cc765a 2px;

  }

  .bookimg {
    border-radius: 20px 20px 20px 20px;
    border: solid #cc765a 2px;
  }
</style>
