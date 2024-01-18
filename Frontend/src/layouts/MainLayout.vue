<script setup>
  import Header from 'layouts/Header.vue';
</script>

<template>
  <Header />
</template>

<script>
import { defineComponent, ref } from 'vue';
import EssentialLink from 'components/EssentialLink.vue';

const linksList = [
{
    title: '시작하기',
    caption: '시작하기',
    icon: 'school',
    to: '/',
  },
  {
    title: '우리의 서비스',
    caption: '이해하기',
    icon: 'school',
    to: '/intro',
  },
];

export default defineComponent({
  name: 'MainLayout',

  components: {
    EssentialLink,
  },

  setup() {
    const leftDrawerOpen = ref(false);

    return {
      essentialLinks: linksList,
      leftDrawerOpen,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value;
      },
    };
  },
});
</script>
