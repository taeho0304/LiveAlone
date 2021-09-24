<template>
  <div id="app">
    <VueSlickCarousel
      v-bind="settings"
      class="ml-auto mr-auto mt-auto mb-auto"
      style="width: 500px; height: 245px"
    >
      <img
        class="imgfile"
        src="https://d1774jszgerdmk.cloudfront.net/512/6a0ecfd6-d540-4349-bd11-ca9b66f8a5a0"
      />

      <img
        class="imgfile"
        src="https://d1774jszgerdmk.cloudfront.net/512/6a0ecfd6-d540-4349-bd11-ca9b66f8a5a0"
      />
    </VueSlickCarousel>

    <card class="mt-3">
      <div class="row col-md-12">
        <div class="col-md-3">스마트빌</div>
        <div class="col-md-3">매매 4억2000만원</div>
        <div class="col-md-3">관리비</div>
        <div class="col-md-3">10</div>
      </div>

      <div class="row col-md-12">
        <div class="col-md-3">매물특징</div>
        <div class="col-md-3">[주차가능,방향 뭐뭐등등]</div>
        <div class="col-md-3">저/ 최고층</div>
        <div class="col-md-3">15층/15층</div>
      </div>

      <div class="row col-md-12">
        <div class="col-md-3">방 크기</div>
        <div class="col-md-3">9평/32m</div>
        <div class="col-md-3">건물 주차수</div>
        <div class="col-md-3">123대</div>
      </div>
    </card>

    <card>
      <div class="row col-md-12">
        <div class="col-md-3">공인중개사</div>
        <div class="col-md-3">세경부동산</div>
        <div class="col-md-3">중개사 이름</div>
        <div class="col-md-3">홍길동</div>
      </div>

      <div class="row col-md-12">
        <div class="col-md-3">중개사 위치</div>
        <div class="col-md-3">경북 구미시 인동 32길</div>
        <div class="col-md-3">중개사 번호</div>
        <div class="col-md-3">010-2459-3840</div>
      </div>
    </card>
  </div>
</template>
<script>
import Card from "../../components/Cards/Card.vue";
import VueSlickCarousel from "vue-slick-carousel";
import "vue-slick-carousel/dist/vue-slick-carousel.css";
import "vue-slick-carousel/dist/vue-slick-carousel-theme.css";
export default {
  data() {
    return {
      settings: {
        dots: true,
        infinite: true,
        initialSlide: 2,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        swipeToSlide: true,
      },
    };
  },
  components: {
    Card,
    VueSlickCarousel,
  },
};
</script>
<style scoped>
.imgfile {
  width: 495px;
  height: 245px;
  margin-top: 10px;
}
.slick-dots {
  position: absolute;
  bottom: 0px;
  display: block;
  width: 100%;
  padding: 0;
  padding-right: 50px;
  margin: 0;
  list-style: none;
  text-align: center;
}
.slick-prev:before,
.slick-next:before {
  font-family: "slick";
  font-size: 20px;
  line-height: 1;
  opacity: 0.75;
  color: coral;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>