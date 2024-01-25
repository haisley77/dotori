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
                src="https://cdn.quasar.dev/img/mountains.jpg"
                style="border-radius: 20px"
              />
              <div class="row q-mt-md q-pa-sm flex justify-center items-center"
                   style="background: white; border: #a84d2f dashed 3px; border-radius: 15px">
                <div class="col-auto">
                  <q-avatar size="md">
                    <q-img src="https://cdn.quasar.dev/img/boy-avatar.png" />
                  </q-avatar>
                </div>
                <div class="col q-ml-sm">
                  <div class="text-subtitle1 text-weight-bold"
                       :style="{lineHeight:'1.1em'}"
                  >
                    토끼와 거북이 연극방 들어와라
                  </div>
                  <div class="text-caption text-grey-9 q-mt-xs">
                    책벌레
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

<script setup>
</script>

<style lang="scss" scoped>
</style>
