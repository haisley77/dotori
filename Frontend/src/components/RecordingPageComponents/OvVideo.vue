<script setup>
  import {defineProps, onMounted, ref} from 'vue';

  const props = defineProps({streamManager: Object, id: String});

  const thisvideo = ref();
  onMounted(() => {
    thisvideo.value.setAttribute('id', props.id);
    console.log("props.id : " + props.id );

    props.streamManager.addVideoElement(document.getElementById(props.id));
  });
</script>

<template>
  <video autoplay ref="thisvideo" id = "myovvideo" style="z-index: 2"/>
</template>

<style scoped>

</style>
