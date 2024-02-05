<template>


  <MainPageCarousel />
  <div class="row">

    <div class="col-10 offset-1">

      <div class="row" style="height: 100px"></div>
      <div ref="animatedTextRef1" class="animated-text">
        <ElementOne />
      </div>
      <div class="row" style="height: 300px"></div>
      <div ref="animatedTextRef2" class="animated-text">
        <ElementTwo />
      </div>
      <div class="row" style="height: 300px"></div>
      <div ref="animatedTextRef3" class="animated-text">
        <ElementThree />
        <div class="row" style="height: 300px"></div>
      </div>

    </div>

  </div>


</template>

<script setup>
  import {ref, onMounted, watchEffect} from 'vue';
  import MainPageCarousel from 'components/MainPageComponents/MainPageCarousel.vue';
  import ElementOne from 'components/MainPageComponents/ElementOne.vue';
  import ElementTwo from 'components/MainPageComponents/ElementTwo.vue';
  import ElementThree from 'components/MainPageComponents/ElementThree.vue';


  //화면안에 요소가 들어오면 아래에서 올라오기
  const animatedTextRef1 = ref(null);
  const animatedTextRef2 = ref(null);
  const animatedTextRef3 = ref(null);


  const observeElement = element => {
    if (element instanceof Element) {
      const observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            element.classList.add('entered');
          } else {
            element.classList.remove('entered');
          }
        });
      });
      observer.observe(element);
    }
  };

  watchEffect(() => {
    observeElement(animatedTextRef1.value);
    observeElement(animatedTextRef2.value);
    observeElement(animatedTextRef3.value);

  });
</script>

<style lang="scss" scoped>
  .custom-caption {
    text-align: center;
    padding: 12px;
    color: white;
  }

  .custom-button {
    text-align: center;
    padding: 70px;
  }

  .custom-caption {
    text-align: center;
    padding: 400px;
    color: black;
  }

  .custom-button {
    text-align: center;
    padding: 70px;
  }

  // 글씨 나타나기
  .animated-text {
    opacity: 0;
    transform: translateY(20px);
    transition: opacity 1s ease-in-out, transform 1s ease-in-out;
  }

  .animated-text.entered {
    opacity: 1;
    transform: translateY(0);
  }

  .q-img__content > div {
    background: none !important; /* 배경을 없애는 스타일 */
  }
</style>
