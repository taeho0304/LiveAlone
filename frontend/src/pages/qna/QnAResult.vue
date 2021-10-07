<template>
  <div class="container" style="padding-right: 0; padding-top: 0">
    <template>
      <div class="col-md-12 mt-2 pt-0 pb-0 ml-auto mr-auto">
        <strong
          ><h4 class="neon">
            추천매물 Top{{ getQuestionResult.length }}
          </h4></strong
        >
      </div>
      <div class="scroll row col-md-12 mr-0 mt-0">
        <card
          v-for="(a, idx) in getQuestionResult"
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
import { mapActions } from "vuex";
import http from "@/util/http-common";
import Modal from "@/components/Modal.vue";
import ResiDetail from "@/pages/map/ResiDetail.vue";
import VueSimpleAlert from "vue-simple-alert";
export default {
  components: {
    Card,
    Modal,
    ResiDetail,
  },
  props: {
    getQuestionResult: Array,
  },
  data() {
    return {
      resiDetail: null,
      showResiDetail: false,
      resiCommercial: [],
    };
  },
  methods: {
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
      this.resiDetail = this.getQuestionResult[res].residenceInfo;
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
    ...mapActions("user", ["requsetFavoriteList"]),
    delFavorite(idx) {
      console.log(this.getQuestionResult[idx]);
      var deldata = this.getQuestionResult[idx].residenceInfo.id;
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
          this.getQuestionResult[idx].present = false;
        });
    },
    myFavorite(idx) {
      console.log(this.getQuestionResult[idx]);
      var postdata = this.getQuestionResult[idx].residenceInfo.id;
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
              this.getQuestionResult[idx].present = true;
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
    movethisResi(idx) {
      console.log(
        this.getQuestionResult[idx].residenceInfo.lat,
        this.getQuestionResult[idx].residenceInfo.lon
      );
      var position = {
        lat: this.getQuestionResult[idx].residenceInfo.lat,
        lon: this.getQuestionResult[idx].residenceInfo.lon,
      };
      this.$emit("moveThisResi", position);
    },
  },
};
</script>
<style >
.iconwrapper {
  position: relative;
  object-fit: cover;
}
.neon {
  animation: neon 1s ease infinite;
  -moz-animation: neon 1s ease infinite;
  -webkit-animation: neon 1s ease infinite;
}

@keyframes neon {
  0%,
  100% {
    text-shadow: 0 0 5 #fa1c16, 0 0 3vw #fa1c16, 0 0 10vw #fa1c16,
      0 0 10vw #fa1c16, 0 0 0.4vw #fed128, 0.5vw 0.5vw 0.1vw #806914;
    color: #fed128;
  }
  50% {
    text-shadow: 0 0 1 #800e0b, 0 0 1.5vw #800e0b, 0 0 5vw #800e0b,
      0 0 5vw #800e0b, 0 0 0.2vw #800e0b, 0.5vw 0.5vw 0.1vw #40340a;
    color: #806914;
  }
}
.imgthum {
  justify-content: left;
  width: 100%;
  height: 100%;
  object-fit: cover;
  max-width: 150px;
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
</style>