<script setup>
  import {defineProps, onMounted, ref} from 'vue';

  const props = defineProps({streamManager: Object, id: String});

  const video = ref();
  onMounted(() => {
    video.value.setAttribute('id', props.id);
    props.streamManager.addVideoElement(video);
  });
</script>

<template>
  <video autoplay ref="video" />
</template>

<style scoped>
  //video {
  //  display: none;
  //}
</style>
