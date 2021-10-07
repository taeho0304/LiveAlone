<template>
  <div class="container" style="padding-right: 0">
    <div
      class="row col-md-12 resiwrap"
      style="padding-bottom: 0; padding-top: 0:"
    >
      <div v-show="!clickCost" class="col-md-4 ml-auto mr-auto">
        <a
          class="btn btn-neutral btn-round btn-block"
          style="color: #000000"
          @click="SortByCost()"
          >가격
        </a>
      </div>

      <div v-show="clickCost" class="col-md-4 ml-auto mr-auto">
        <a
          class="btn btn-info btn-round btn-block"
          style="color: #000000"
          @click="SortByCost()"
          >가격
          <i
            v-if="sortCost"
            slot="icon"
            class="now-ui-icons arrows-1_minimal-down"
          ></i>
          <i v-else slot="icon" class="now-ui-icons arrows-1_minimal-up"></i
        ></a>
      </div>

      <div v-show="!clickArea" class="col-md-4 ml-auto mr-auto">
        <a
          class="btn btn-neutral btn-round btn-block"
          style="color: #000000"
          @click="SortByArea()"
          >면적
        </a>
      </div>

      <div v-show="clickArea" class="col-md-4 ml-auto mr-auto">
        <a class="btn btn-info btn-round btn-block" @click="SortByArea()"
          >면적
          <i
            v-if="sortArea"
            slot="icon"
            class="now-ui-icons arrows-1_minimal-down"
          ></i>
          <i v-else slot="icon" class="now-ui-icons arrows-1_minimal-up"></i
        ></a>
      </div>

      <div v-show="!clickLike" class="col-md-4 ml-auto mr-auto">
        <a
          class="btn btn-neutral btn-round btn-block"
          style="color: #000000"
          @click="SortByLike()"
          >선호
        </a>
      </div>

      <div v-show="clickLike" class="col-md-4 ml-auto mr-auto">
        <a class="btn btn-info btn-round btn-block" @click="SortByLike()"
          >선호
          <i
            v-if="sortLike"
            slot="icon"
            class="now-ui-icons arrows-1_minimal-down"
          ></i>
          <i v-else slot="icon" class="now-ui-icons arrows-1_minimal-up"></i
        ></a>
      </div>
    </div>
    <template>
      <div class="scroll row col-md-12 mr-0 mt-0">
        <card
          v-for="(a, idx) in resiList"
          :key="idx"
          class="card-nav-tabs text-center"
          header-classes="card-header-warning"
          style="min-width: 300px; min-height: 170px; max-height: 170px"
        >
          <div class="row" style="margin-rigth: 0" @click="movethisResi(idx)">
            <div class="col-md-6" style="min-height: 160px; max-height: 160px">
              <img class="imgthum" :src="a.residenceInfo.imageUrl[0].url" />
            </div>
            <div class="col-md-4 pr-0 pl-0">
              <div class="col-md-12 pl-0 pb-0 pt-0 title">
                <strong
                  ><h6 class="mb-1">
                    {{ a.residenceInfo.residenceType.type }}
                    <strong v-if="a.residenceInfo.residenceType.type == '전세'">
                      {{ showPrice(a.residenceInfo.jeonseCost) }}
                    </strong>
                    <strong
                      v-else-if="a.residenceInfo.residenceType.type == '매매'"
                    >
                      {{ showPrice(a.residenceInfo.cost) }}
                    </strong>
                    <strong
                      v-else-if="a.residenceInfo.residenceType.type == '월세'"
                    >
                      {{ showPrice(a.residenceInfo.deposit) }}/{{
                        a.residenceInfo.wolseCost
                      }}
                    </strong>
                  </h6></strong
                >
              </div>
              <div class="col-md-12 pt-1 pl-0 pr-0 pb-1 title">
                {{ a.residenceInfo.residenceCategory.categoryName }}
                {{ showResiName(a.residenceInfo.name) }}
              </div>
              <div class="col-md-12 pt-1 pl-0 pr-0 pb-1 title">
                <h6 class="mb-1">
                  {{ a.residenceInfo.myFloor }}/{{ a.residenceInfo.area }}평
                </h6>
              </div>
              <div class="col-md-12 pt-1 pl-0 pr-0 title">
                <h6 class="mb-1">
                  <strong>
                    <!-- {{ name.featureName }} -->
                    {{ showFeature(a.residenceInfo.feature) }}
                  </strong>
                </h6>
              </div>
            </div>

            <div class="col-md-2">
              <div class="iconwrapper">
                <font-awesome-icon
                  v-if="!a.present"
                  :icon="['far', 'heart']"
                  @click="myFavorite(idx)"
                />

                <font-awesome-icon
                  v-if="a.present"
                  :icon="['fas', 'heart']"
                  :style="{ color: 'red' }"
                  @click="delFavorite(idx)"
                />
              </div>
              <div class="iconwrapper detail" style="margin-top: 50%">
                <i
                  slot="icon"
                  class="now-ui-icons ui-1_zoom-bold"
                  @click="showModal(idx)"
                ></i>
              </div>
            </div>
          </div>
        </card>
      </div>
      <template>
        <div class="ml-auto mr-auto mt-2" style="justify-content: center">
          <n-pagination
            class="ml-auto mr-auto"
            type="info"
            :value="pageItem.curpage"
            :pageCount="pageItem.total"
            @input="requestNext"
          >
            <span slot="prev">Previous</span>
            <span slot="next">Next</span></n-pagination
          >
        </div>
      </template>
    </template>
    <template>
      <modal
        class="resiDetailModal"
        :show.sync="showResiDetail"
        modal-classes="modal-lg"
        header-classes="justify-content-center"
      >
        <ResiDetail
          v-if="resiDetail != null"
          v-bind:resiDetail="resiDetail"
          v-bind:resiCommercial="resiCommercial"
        />
      </modal>
    </template>
  </div>
</template>
<script>
import { Card } from "@/components";
import Modal from "@/components/Modal.vue";

import ResiDetail from "@/pages/map/ResiDetail.vue";
import VueSimpleAlert from "vue-simple-alert";

import http from "@/util/http-common";
import { mapActions } from "vuex";

export default {
  components: {
    Card,
    Modal,
    ResiDetail,
  },
  setup() {},
  props: { resiList: Array[Object], pageItem: Object, sortFilter: String },
  watch: {},
  data() {
    return {
      showResiDetail: false,
      resiDetail: null,
      clickCost: false,
      clickArea: false,
      clickLike: false,
      sortCost: true,
      sortArea: true,
      sortLike: true,
      sortType: null,
      resiCommercial: null,
    };
  },
  watch: {
    sortFilter: function () {
      if (this.sortFilter == "cost") {
        this.sortArea = true;
        this.sortLike = true;
        this.clickCost = true;

        this.clickArea = false;
        this.clickLike = false;
      } else if (this.sortFilter == "area") {
        this.sortCost = true;
        this.sortLike = true;
        this.clickArea = true;

        this.clickCost = false;
        this.clickLike = false;
      } else if (this.sortFilter == "favorite") {
        this.sortCost = true;
        this.sortArea = true;
        this.clickLike = true;

        this.clickCost = false;
        this.clickArea = false;
      } else {
        this.sortCost = true;
        this.sortArea = true;
        this.sortLike = true;
        this.clickLike = false;
        this.clickCost = false;
        this.clickArea = false;
      }
    },
  },
  methods: {
    movethisResi(idx) {
      console.log(
        this.resiList[idx].residenceInfo.lat,
        this.resiList[idx].residenceInfo.lon
      );
      var position = {
        id: this.resiList[idx].residenceInfo.id,
        lat: this.resiList[idx].residenceInfo.lat,
        lon: this.resiList[idx].residenceInfo.lon,
      };
      this.$emit("moveThisResi", position);
    },
    SortByLike() {
      console.log("fafaa");

      var sortData = {
        sortType: "favorite",
        sortOrder: this.sortLike,
      };
      this.sortLike = !this.sortLike;
      this.$emit("sort", sortData);
    },
    SortByArea() {
      var sortData = {
        sortType: "area",
        sortOrder: this.sortArea,
      };
      this.sortArea = !this.sortArea;
      this.$emit("sort", sortData);
    },
    SortByCost() {
      var sortData = {
        sortType: "cost",
        sortOrder: this.sortCost,
      };
      this.clickCost = true;
      this.sortCost = !this.sortCost;
      this.$emit("sort", sortData);
    },

    requestNext(itemnum) {
      console.log(itemnum);
      this.$emit("requestNextItem", itemnum);
    },
    onChangePage(pageOfItems) {
      // update page of items
      this.pageOfItems = pageOfItems;
    },
    ...mapActions("user", ["requsetFavoriteList"]),
    showResiName(name) {
      if (name == "") {
        return "";
      } else {
        return " ," + name;
      }
    },
    showFeature(feature) {
      var resFeature = "";
      for (var i = 0; i < feature.length; i++) {
        if (i == feature.length - 1) {
          resFeature += feature[i].featureName;
        } else {
          resFeature += feature[i].featureName + "/";
        }
        if (resFeature.length >= 10) {
          resFeature += "...";
          break;
        }
      }
      return resFeature;
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

    showModal(res) {
      this.showResiDetail = !this.showResiDetail;
      this.resiDetail = this.resiList[res].residenceInfo;
      console.log(this.resiDetail);

      http
        .post(
          "/api/v1/residences/residencecommercialcount?residenceId=" +
            this.resiDetail.id
        )
        .then((res) => {
          console.log(res.data.residenceCommercialCountModel);
          this.resiCommercial = res.data.residenceCommercialCountModel[0];
        });
    },
    delFavorite(idx) {
      var deldata = this.resiList[idx].residenceInfo.id;
      const CSRF_TOKEN = localStorage.getItem("accessToken");

      console.log(deldata);

      http
        .delete("/api/v1/favorites?residenceId=" + deldata, {
          headers: {
            Authorization: "Bearer " + CSRF_TOKEN,
          },
        })
        .then((res) => {
          VueSimpleAlert.fire({
            title: "찜 제거 성공",
            text: "찜 제거 성공 ! 마이 페이지를 확인해주세요",
            type: "error",
          });
          this.requsetFavoriteList();
          this.resiList[idx].present = false;
        });
    },
    myFavorite(idx) {
      var postdata = this.resiList[idx].residenceInfo.id;
      console.log(postdata);
      const CSRF_TOKEN = localStorage.getItem("accessToken");

      if (localStorage.getItem("accessToken")) {
        //NOTE: 로그인시 로직 구현 필요! 매물 데이터 필요!!

        http
          .post("/api/v1/favorites", postdata, {
            headers: { Authorization: "Bearer " + CSRF_TOKEN },
          })
          .then((res) => {
            console.log(res);
            if (res.status == 201) {
              VueSimpleAlert.fire({
                title: "찜하기 성공",
                text: "찜하기 성공 ! 마이 페이지를 확인해주세요",
                type: "success",
              });
              this.requsetFavoriteList();
              this.resiList[idx].present = true;
            }
          });
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
.imgthum {
  width: 100%;
  height: 100%;
  object-fit: cover;
  max-height: 130px;
}
.title {
  text-align: left;
}
.resiwrap {
  display: flex;
}
.scroll {
  overflow: auto;
  height: calc(100vh - 200px);
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
  margin-top: 1.8% !important;
}
</style>
