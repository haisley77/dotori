<template>
  <q-page padding>

    <div class="row q-mb-md">
      <q-space/>
    <div class="search row items-center ">
        <q-input
        class="col-grow"
        outlined square dense placeholder="검색"
        @focus="isSearchFocus = true"
        @blur="isSearchFocus = false">

          <template v-if="isSearchFocus" #prepend>
            <q-icon name="search"/>
          </template>
          <template #append>
            <q-icon name="keyboard"/>
          </template>
        </q-input>
        <q-btn
        class="search-btn"
        icon="search"
        unelevated color="grey-3"
        text-color="black"
        square
        />
        <q-btn class="q-ml-sm"
        icon="mic"
        round color="grey-2"
        text-color="black"
        unelevated/>
        </div>
        <q-space/>
      </div>

    <div class="row q-col-gutter-x-md q-col-gutter-y-xl">
      <div v-for="n in 16" :key="n" class="col-12 col-sm-6 col-md-4 col-lg-3">
        <article>
          <q-img
            :ratio="16/9"
            src="https://cdn.quasar.dev/img/parallax2.jpg"
          />
          <div class="row q-col-gutter-x-sm q-mt-md">
            <div class="col-auto">
              <q-avatar size="md">
                <q-img src="https://cdn.quasar.dev/img/boy-avatar.png"/>
              </q-avatar>
            </div>
            <div class="col">
              <div class="text-subtitle1 text-weight-bold"
              :style="{lineHeight:'1.1em'}"
              >
               와와와와
              </div>
              <div class="text-caption text-grey-9 q-mt-xs">
                와와와
              </div>
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
