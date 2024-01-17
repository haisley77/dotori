<template>
  <q-drawer v-model="drawer"
  :width="240"
  bordered
  :mini="$q.screen.lt.sm"
  :breakpoint="$q.screen.sizes.sm"
  >
    <q-scroll-area class="fit">
         
          <q-avatar size="xl">
            <q-img src="https://cdn.quasar.dev/img/boy-avatar.png"/>
          </q-avatar>


          <q-list>
            <q-item-label header>나의 정보</q-item-label>
            <template v-for="(menuItem, index) in menuList1" :key="index">
              <q-item clickable :active="menuItem.label === 'Outbox'" v-ripple>
                <q-item-section avatar>
                  <q-icon :name="menuItem.icon" />
                </q-item-section>
                <q-item-section>
                  {{ menuItem.label }}
                </q-item-section>
              </q-item>
              <q-separator :key="'sep' + index"  v-if="menuItem.separator" />
            </template>
          </q-list>
        </q-scroll-area>
  </q-drawer>
</template>

<script setup>
import {ref} from 'vue';
const drawer = ref(true);
const menuList1 = [
{
    icon: 'send',
    label: 'My Page',
    separator: false
  },
  {
    icon: 'send',
    label: 'Make Room',
    separator: false
  },
 
]

</script>

<style lang="scss" scoped>

</style>
