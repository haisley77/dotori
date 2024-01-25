<template>
  <Header/>
  <q-layout view="hHh lpR lFf">

<!--  <ListDrawer/>-->

    <q-page-container>
      <router-view/>
    </q-page-container>
</q-layout>
</template>

<script setup>

import Header from 'components/CommonComponents/Header.vue';


</script>

<style lang="scss" scoped>

</style>
