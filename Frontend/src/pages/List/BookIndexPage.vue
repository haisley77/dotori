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
            <div class="col">
              <div class="text-subtitle1 text-weight-bold"
              :style="{lineHeight:'1.1em'}"
              >
               흥부와 놀부
              </div>
              <div class="text-caption text-grey-9 q-mt-xs">
                저자 : 김흥돌
              </div>


              <!-- Dialog -->
              <div class="q-pa-md q-gutter-sm">
                <q-btn label="Maximized" color="primary" @click="dialog = true" />

                <q-dialog
                  v-model="dialog"
                  persistent
                  :maximized="maximizedToggle"
                  transition-show="slide-up"
                  transition-hide="slide-down"
                >
                  <q-card class="bg-primary text-white">
                    <q-bar>
                      <q-space />

                      <q-btn dense flat icon="minimize" @click="maximizedToggle = false" :disable="!maximizedToggle">
                        <q-tooltip v-if="maximizedToggle" class="bg-white text-primary">Minimize</q-tooltip>
                      </q-btn>
                      <q-btn dense flat icon="crop_square" @click="maximizedToggle = true" :disable="maximizedToggle">
                        <q-tooltip v-if="!maximizedToggle" class="bg-white text-primary">Maximize</q-tooltip>
                      </q-btn>
                      <q-btn dense flat icon="close" v-close-popup>
                        <q-tooltip class="bg-white text-primary">Close</q-tooltip>
                      </q-btn>
                    </q-bar>

                    <q-card-section>
                      <div class="text-h6">Alert</div>
                    </q-card-section>

                    <q-card-section class="q-pt-none">
                      Lorem ipsum dolor sit amet consectetur adipisicing elit. Rerum repellendus sit voluptate voluptas eveniet porro. Rerum blanditiis perferendis totam, ea at omnis vel numquam exercitationem aut, natus minima, porro labore.
                    </q-card-section>
                  </q-card>
                </q-dialog>
              </div>



            </div>
          </div>
        </article>
      </div>
    </div>
  </q-page>
</template>

<script>
import { ref } from 'vue'

export default {
  setup () {
    return {
      dialog: ref(false),
      maximizedToggle: ref(true)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
