<template>
  <q-page>
    <div class="row">
      <div v-for="n in 16" :key="n" class="col-12 col-sm-6 col-md-4 col-lg-3">
        <article>
          <q-img
            :ratio="16/9"
            src="https://cdn.quasar.dev/img/mountains.jpg"
          />
          <div class="row">
            <div class="col-auto">
              <q-avatar>
                <q-img size="sm" src="https://cdn.quasar.dev/img/boy-avatar.png"/>
              </q-avatar>
            </div>
            <div class="col">
              <div class="text-subtitle1 text-weight-bold">
                토끼와 거북이 연극방 들어와라
              </div>
              <div class="text-caption text-grey-9">책벌레</div>
            </div>
          </div>
        </article>
      </div>
    </div>
  </q-page>
</template>

<script setup></script>

<style lang="scss" scoped>
</style>
