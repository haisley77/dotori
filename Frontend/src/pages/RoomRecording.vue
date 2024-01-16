<script setup>

</script>

<template>
  <div class="entire-container row">
    <div class="left-container col-2">
      <div class="scene-list-container">

      </div>
    </div>
    <div class="right-container q-pa-sm col-10">
      <div class="scene-info-and-controller-container">
        <div class="scene-info-container">

        </div>
        <div class="controller-container">

        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.entire-container{
  height: 100vh;
  border: green solid 1px;
}

.left-container{
  height: 100%;
  border: red solid 1px;
}

.right-container{
  height: 100%;
  border: blue solid 1px;
}

.scene-info-and-controller-container{
  height: 100%;
  border: aqua solid 1px;
}

.scene-info-container{
  height: 90%;
  border: black solid 1px;
}


.controller-container{
  height: 10%;
  border: black solid 1px;
}

</style>
