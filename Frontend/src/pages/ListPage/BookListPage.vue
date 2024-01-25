<template>
  <q-page padding>

    <div class="row q-mb-md">
      <div class="search row flex justify-center items-center" style="width: 100%">
        <q-input
          class=""
          standout rounded dense placeholder="검색"
          color="black" bg-color="white"
          style="width: 50%; border: #35daa0 solid 5px; border-radius: 50px"
        >
        </q-input>
        <q-btn
          class=""
          icon="search"
          round
          size="lg"
          flat
          unelevated
          style="color: #35daa0; font-weight: bolder"
        />
      </div>
    </div>

    <div class="row q-col-gutter-x-md q-col-gutter-y-xl">
      <div v-for="n in 16" :key="n" class="col-12 col-sm-6 col-md-4 col-lg-3">
        <article>
          <div style="background: #35daa0; border-radius: 20px" class="q-pa-sm">
            <div style="background: #ebf686; border-radius: 20px" class="q-pa-sm">
              <q-img
                :ratio="16/9"
                src="~assets/RecordingPage/tmp_scene_thumbnail.jpg"
                style="border-radius: 20px"
              />
              <div class="row q-mt-md q-pa-xs flex justify-center items-center"
                   style="background: white; border: #a84d2f dashed 3px; border-radius: 15px">
                <div class="col-6 text-center">
                  <div class="text-subtitle1 text-weight-bold"
                       :style="{lineHeight:'1.1em'}"
                  >
                    흥부와 놀부
                  </div>
                  <div class="text-caption text-grey-9 q-mt-xs">
                    저자 : 김흥돌
                  </div>
                </div>
                  <!-- Dialog -->
                <div class="col-6">
                  <div class="q-pa-md">
                    <q-btn label="연극하러 가기" @click="dialog = true" style="background: #35daa0"/>
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

                          <q-btn dense flat icon="minimize" @click="maximizedToggle = false"
                                 :disable="!maximizedToggle">
                            <q-tooltip v-if="maximizedToggle" class="bg-white text-primary">Minimize</q-tooltip>
                          </q-btn>
                          <q-btn dense flat icon="crop_square" @click="maximizedToggle = true"
                                 :disable="maximizedToggle">
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
                          Lorem ipsum dolor sit amet consectetur adipisicing elit. Rerum repellendus sit voluptate
                          voluptas
                          eveniet porro. Rerum blanditiis perferendis totam, ea at omnis vel numquam exercitationem aut,
                          natus minima, porro labore.
                        </q-card-section>
                      </q-card>
                    </q-dialog>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </article>
      </div>
    </div>
  </q-page>
</template>

<script>
  import {ref} from 'vue';

  export default {
    setup() {
      return {
        dialog: ref(false),
        maximizedToggle: ref(true),
      };
    },
  };
</script>

<style lang="scss" scoped>

</style>
