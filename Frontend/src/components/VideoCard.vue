<template>
  <q-img src="https://cdn.quasar.dev/img/mountains.jpg" class="dotori-border">
    <div class="absolute-bottom dotori-border">
      <div class="bottom-bar-container flex justify-between">
        <div class="bottom-bar-info flex row no-wrap">
          <q-avatar class="q-mr-md">
            <img :src="props.roomInfo.profileImagePath">
          </q-avatar>
          <div>
            <div>
              <div class="text-subtitle2 ellipsis" v-text="props.roomInfo.title"></div>
              <div class="text-subtitle2 ellipsis" v-text="props.roomInfo.date"></div>
            </div>
          </div>
        </div>
        <q-btn>
          <q-icon name="download"></q-icon>
        </q-btn>
      </div>
    </div>
  </q-img>
</template>

<script setup>
  const props = defineProps({
    roomInfo: {
      profileImagePath: String,
      title: String,
      date: String,
      downloadLink: String,
    },
  });
</script>

<style lang="scss" scoped>
  .dotori-border {
    border-radius: $dotori-border-radius;
  }
</style>
