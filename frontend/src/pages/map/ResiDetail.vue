<template>
  <div id="app">
    <VueSlickCarousel
      v-bind="settings"
      style="margin-left: 4%; width: 500px; max-height: 245px"
    >
      <div v-for="(a, idx) in resiDetail.imageUrl" :key="idx">
        <img class="imgfile" :src="a.url" />
      </div>
    </VueSlickCarousel>

    <card class="mt-3">
      <div class="row col-md-12">
        <div class="col-md-3">{{ showName(resiDetail.name) }}</div>
        <div class="col-md-3">
          <strong> {{ resiDetail.residenceType.type }} </strong>
          <strong v-if="resiDetail.residenceType.type == '전세'">
            {{ showPrice(resiDetail.jeonseCost) }}
          </strong>
          <strong v-else-if="resiDetail.residenceType.type == '매매'">
            {{ showPrice(resiDetail.cost) }}
          </strong>
          <strong v-else-if="resiDetail.residenceType.type == '월세'">
            {{ showPrice(resiDetail.jeonseCost) }}/{{ resiDetail.wolseCost }}
          </strong>
        </div>
        <div class="col-md-3">관리비</div>
        <div class="col-md-3">{{ resiDetail.manageCost }}</div>
      </div>

      <div class="row col-md-12">
        <div class="col-md-3">매물특징</div>
        <div class="col-md-3">
          <p v-for="(a, idx) in resiDetail.feature" :key="idx">
            {{ a.featureName }}
          </p>
        </div>
        <div class="col-md-3">저/ 최고층</div>
        <div class="col-md-3">
          {{ resiDetail.myFloor }}/ {{ resiDetail.buildingFloor }}
        </div>
      </div>

      <div class="row col-md-12">
        <div class="col-md-3">방 크기</div>
        <div class="col-md-3">{{ resiDetail.area }}</div>
        <div class="col-md-3">건물 방향</div>
        <div class="col-md-3">{{ resiDetail.direction }}향</div>
      </div>
    </card>

    <card>
      <div class="row col-md-12">
        <div class="col-md-3">공인중개사</div>
        <div class="col-md-3">{{ resiDetail.estateInfo.name }}</div>
        <div class="col-md-3">중개사 이름</div>
        <div class="col-md-3">{{ resiDetail.estateInfo.representative }}</div>
      </div>

      <div class="row col-md-12">
        <div class="col-md-3">중개사 위치</div>
        <div class="col-md-3">{{ resiDetail.estateInfo.address }}</div>
        <div class="col-md-3">중개사 번호</div>
        <div class="col-md-3">{{ resiDetail.estateInfo.phone }}</div>
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
  props: {
    resiDetail: Object,
  },
  components: {
    Card,
    VueSlickCarousel,
  },
  methods: {
    showName(name) {
      if (name == "") {
        return this.resiDetail.residenceCategory.categoryName;
      } else {
        return this.resiDetail.name;
      }
    },
    showPrice(number) {
      var inputNumber = number < 0 ? false : number;
      var unitWords = ["", "억", "조", "경"];
      var splitUnit = 10000;
      var splitCount = unitWords.length;
      var resultArray = [];
      var resultString = "";

      for (var i = 0; i < splitCount; i++) {
        var unitResult =
          (inputNumber % Math.pow(splitUnit, i + 1)) / Math.pow(splitUnit, i);
        unitResult = Math.floor(unitResult);
        if (unitResult > 0) {
          resultArray[i] = unitResult;
        }
      }

      for (var i = 0; i < resultArray.length; i++) {
        if (!resultArray[i]) continue;
        resultString = String(resultArray[i]) + unitWords[i] + resultString;
      }

      return resultString;
    },
  },
};
</script>
<style scoped>
.imgfile {
  width: 495px;
  height: 245px;
  margin-top: 10px;
}
</style>