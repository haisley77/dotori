<template>
  <div style=';width: 100%; border: #cc765a 5px dashed' class = "dotori-border q-pa-md">
   <q-img src="https://cdn.quasar.dev/img/mountains.jpg" class="dotori-border">
     <div class="absolute-bottom card-info-border-bottom">
       <div class="bottom-bar-container flex justify-between">
         <div class="bottom-bar-info flex row no-wrap">
           <q-avatar class="q-mr-md">
             <img src="../assets/acorn_character_img.png">
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
  </div>

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
    background-color: #ebf686;


  }
  .card-info-border-bottom{
    border-radius:0px 0px 20px 20px;

  }
</style>
<style>
  .dotori-border{}
</style>
