<template>
    <Header />
    <div class="q-pa-md">
        <q-carousel
            arrows
            infinite
            :autoplay="autoplay"
            navigation
            animated
            v-model="slide"
            height="570px"
        >
            <q-carousel-slide
                name="first"
                img-src="https://cdn.quasar.dev/img/mountains.jpg"
            >
                <div class="absolute-bottom custom-caption">
                    <div class="text-h2">First stop</div>
                    <div class="text-subtitle1">Mountains</div>
                </div>
                <div class="absolute-bottom custom-button">
                    <q-btn
                        color="primary"
                        label="시작하기"
                        router-link
                        to="/login"
                    />
                </div>
            </q-carousel-slide>
            <q-carousel-slide
                name="second"
                img-src="../assets/mainpage/main2.png"
            >
                <div class="absolute-bottom custom-caption">
                    <div class="text-h2">Second stop</div>
                    <div class="text-subtitle1">Famous City</div>
                </div>
                <div class="absolute-bottom custom-button">
                    <q-btn
                        color="primary"
                        label="시작하기"
                        router-link
                        to="/login"
                    />
                </div>
            </q-carousel-slide>
            <q-carousel-slide
                name="third"
                img-src="https://cdn.quasar.dev/img/parallax2.jpg"
            >
                <div class="absolute-bottom custom-caption">
                    <div class="text-h2">Third stop</div>
                    <div class="text-subtitle1">Famous Bridge</div>
                </div>
                <div class="absolute-bottom custom-button">
                    <q-btn
                        color="primary"
                        label="시작하기"
                        router-link
                        to="/login"
                    />
                </div>
            </q-carousel-slide>
        </q-carousel>
    </div>
    <div class="q-pa-md">
        <q-parallax :height="700" :speed="1.2">
            <template v-slot:media>
                <img src="https://cdn.quasar.dev/img/parallax1.jpg" />
            </template>

            <h1 class="text-white">Docks</h1>
        </q-parallax>
    </div>
    <div class="col-6">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
            <div class="absolute-top text-subtitle1 text-center">Caption</div>
        </q-img>
    </div>
    <div>
        <q-btn color="primary" label="시작하기" router-link to="/login" />
    </div>
</template>

<script setup>
    import Header from 'layouts/Header.vue';
    import {ref} from 'vue';

    const slide = ref('first');
    const autoplay = ref(true);

    // const bounceImage = el => {
    //     // in this example, when the `<div>` comes into view,
    //     // we bounce it for 2 seconds

    //     el.classList.add('animate-bounce');

    //     setTimeout(() => {
    //         // we make sure the node is still in DOM
    //         // (user hasn't navigated away from the Vue component
    //         // rendering our `<div>`)
    //         // so we don't generate an error
    //         if (document.body.contains(el)) {
    //             // then remove the helper class to
    //             // stop bouncing
    //             el.classList.remove('animate-bounce');
    //         }
    //     }, 2000);
    // };
</script>

<style lang="sass" scoped>
    .custom-caption
      text-align: center
      padding: 400px
      color: text-black

    .custom-button
      text-align: center
      padding: 70px

    .animate-bounce
      animation: q-bounce 2s infinite

    @keyframes q-bounce
      0%, 20%, 50%, 80%, 100%
        transform: translateY(0)
      40%
        transform: translateY(-30px)
      60%
        transform: translateY(-15px)
</style>
