<template>
  <div class="container" style="padding-right: 0">
    <div
      class="row col-md-12 resiwrap"
      style="padding-bottom: 0; padding-top: 0:"
    >
      <div class="col-md-4 ml-auto mr-auto">
        <a class="btn btn-neutral btn-round btn-block" style="color: #000000"
          >가격</a
        >
      </div>
      <div class="col-md-4 ml-auto mr-auto">
        <a class="btn btn-neutral btn-round btn-block" style="color: #000000"
          >면적</a
        >
      </div>
      <div class="col-md-4 ml-auto mr-auto">
        <a class="btn btn-neutral btn-round btn-block" style="color: #000000"
          >선호</a
        >
      </div>
    </div>
    <template>
      <div class="scroll row col-md-12 mr-0 mt-0">
        <card
          v-for="(a, idx) in resiList"
          :key="idx"
          class="card-nav-tabs text-center"
          header-classes="card-header-warning"
          style="min-width: 300px; max-height: 280px"
        >
          <div class="row" style="margin-rigth: 0">
            <div class="col-md-8">
              <img style="max-width: 100%; height: auto;" :src="a.imageUrl[0].url" />
            </div>
            <div class="col-md-4 pr-0 pl-0">
              <div class="col-md-12 pl-0 pb-0 title">
                <strong
                  ><h5 class="mb-1">{{ a.residenceType.type }}</h5></strong
                >
              </div>
              <div class="col-md-12 pt-1 pl-0 pr-0 title">
                
                <strong v-if="a.residenceType.type=='전세'"
                   ><h5 class="mb-1">

                    {{ showPrice(a.jeonseCost) }}
                    </h5></strong
                >
                <strong v-else-if="a.residenceType.type=='매매'"
                   ><h5 class="mb-1">
                    {{ showPrice(a.cost) }}
                    </h5></strong
                >
                <strong v-else-if="a.residenceType.type=='월세'"
                   ><h5 class="mb-1">
                    {{ showPrice(a.jeonseCost) }}/{{ a.wolseCost }}
                    </h5></strong
                >
              </div>
              <div class="col-md-12 pt-1 pl-0 pr-0 title">
                <h6 class="mb-1">{{ a.residenceCategory.categoryName }}</h6>
              </div>
              <div
                class="row col-md-12 ml-auto mr-auto mt-1 pt-1 pl-0 pr-0 title"
              >
                <div class="col-md-6 pr-0">
                  <i
                    class="now-ui-icons ui-2_favourite-28"
                    @click="myFavorite"
                  ></i>
                </div>
                <div class="col-md-6 pr-0">
                  <i class="now-ui-icons ui-1_zoom-bold" @click="showModal"></i>
                </div>
              </div>
            </div>
            <div slot="footer" class="col-md-12 mt-0 text-muted mb-2">
              <p>
                {{ a.myFloor }},{{ a.area }}평,
                
                <strong v-for="(name,idx) in a.feature" :key="idx"> 
                  {{ name.featureName }}
                  </strong>
              </p>
            </div>
          </div>
        </card>
      </div>
    </template>
    <template>
      <modal
        class="resiDetailModal"
        :show.sync="showResiDetail"
        modal-classes="modal-lg"
        header-classes="justify-content-center"
      >
        <ResiDetail />
      </modal>
    </template>
  </div>
</template>
<script>
import { Card } from "@/components";
import Modal from "@/components/Modal.vue";
import ResiDetail from "@/pages/map/ResiDetail.vue";
import VueSimpleAlert from "vue-simple-alert";
export default {
  components: {
    Card,
    Modal,
    ResiDetail,
  },
  setup() {},
  props: { resiList: Array },
  data() {
    return {
      showResiDetail: false,
    };
  },
  methods: {
    showPrice(number){
      
      var inputNumber  = number < 0 ? false : number;
      var unitWords    = ['', '억', '조', '경'];
      var splitUnit    = 10000;
      var splitCount   = unitWords.length;
      var resultArray  = [];
      var resultString = '';

      for (var i = 0; i < splitCount; i++){
          var unitResult = (inputNumber % Math.pow(splitUnit, i + 1)) / Math.pow(splitUnit, i);
          unitResult = Math.floor(unitResult);
          if (unitResult > 0){
              resultArray[i] = unitResult;
          }
      }

      for (var i = 0; i < resultArray.length; i++){
          if(!resultArray[i]) continue;
          resultString = String(resultArray[i]) + unitWords[i] + resultString;
      }
     
      return resultString
      
    },
    showModal() {
      this.showResiDetail = !this.showResiDetail;
    },
    myFavorite() {
      console.log("aaaaa");
      if (localStorage.getItem("accessToken")) {
        //NOTE: 로그인시 로직 구현 필요! 매물 데이터 필요!!
      } else {
        VueSimpleAlert.fire({
          title: "서비스 권한 없음",
          text: "찜하기 서비스는 회원 전용 서비스 입니다!",
          type: "error",
        });
      }
    },
  },
};
</script>
<style scoped>
.title {
  text-align: left;
}
.resiwrap {
  display: flex;
}
.scroll {
  overflow: auto;
  height: calc(100vh - 170px);
}
.scroll::-webkit-scrollbar {
  width: 5px; /*스크롤바의 너비*/
}
.scroll::-webkit-scrollbar-thumb {
  background-color: #2f3542;
  border-radius: 10px;
}
.scroll::-webkit-scrollbar-track {
  background-color: grey;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
.resiDetailModal {
  display: flex;
}
</style>
