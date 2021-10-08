<template>
  <div>
    <div class="section">
      <div class="container">
        <modal
          class="myfavotieModal"
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

        <div class="card row" style="margin-top: 15px">
          <tabs
            type="primary"
            tabContentClasses="tab-subcategories"
            square
            centered
            class="nav-align-center"
          >
            <tab-pane>
              <span slot="label">
                <i class="now-ui-icons business_badge"></i> Profile
              </span>
              <div
                v-if="getUserInfo"
                class="col-md-10 ml-auto mr-auto"
                style="margin-top: 0px"
              >
                <div class="row collections">
                  <div class="col-md-1"></div>
                  <div class="col-md-10">
                    <fg-input
                      class="no-border input-lg"
                      v-bind:disabled="!isClick"
                      type="text"
                      addon-left-icon="now-ui-icons users_circle-08"
                      placeholder="이름"
                      v-model="getUserInfo.user.userName"
                      name="userName"
                    >
                    </fg-input>
                    <fg-input
                      class="no-border input-lg"
                      v-bind:disabled="!isClick"
                      type="text"
                      addon-left-icon="now-ui-icons users_circle-08"
                      placeholder="아이디"
                      v-model="getUserInfo.user.userId"
                      name="userId"
                    >
                    </fg-input>
                    <fg-input
                      class="no-border input-lg"
                      v-bind:disabled="!isClick"
                      type="text"
                      addon-left-icon="now-ui-icons users_circle-08"
                      placeholder="이메일"
                      v-model="getUserInfo.user.userEmail"
                      name="email"
                    >
                    </fg-input>
                    <fg-input
                      class="no-border input-lg"
                      v-bind:disabled="!isClick"
                      type="text"
                      addon-left-icon="now-ui-icons users_circle-08"
                      v-model="getUserInfo.user.userPhone"
                      name="phone"
                    >
                    </fg-input>
                  </div>
                  <div class="col-md-1"></div>
                  <div class="col-md-3"></div>
                  <div class="col-md-3">
                    <div
                      style="margin-top: -10px"
                      class="card-footer text-center"
                    >
                      <a
                        v-if="!isClick"
                        @click="click()"
                        class="btn btn-info btn-round btn-lg btn-block"
                        >수정</a
                      >
                      <a
                        v-if="isClick"
                        @click="clickModify()"
                        class="btn btn-info btn-round btn-lg btn-block"
                        >확인</a
                      >
                    </div>
                  </div>
                  <div class="col-md-3">
                    <div
                      style="margin-top: -10px"
                      class="card-footer text-center"
                    >
                      <a class="btn btn-danger btn-round btn-lg btn-block"
                        >탈퇴</a
                      >
                    </div>
                  </div>
                  <div class="col-md-3"></div>
                </div>
              </div>
            </tab-pane>

            <tab-pane
              ><!--찜한 물건-->
              <span slot="label">
                <i class="now-ui-icons shopping_shop"></i> Room
              </span>
              <template v-if="myfavorite != null">
                <VueSlickCarousel
                  v-bind="settings"
                  style="padding: 20px 30px 20px 30px"
                >
                  <div
                    v-for="(a, idx) in getMyfavoriteList"
                    :key="idx"
                    class="card mydata"
                    style="width: 20rem; border-radius: 0.2rem"
                  >
                    <img
                      class="card-img-top myimg"
                      :src="a.residenceInfo.imageUrl[0].url"
                      alt="Card image cap"
                    />
                    <div class="card-body">
                      <h4 class="card-title">
                        <strong>
                          {{ a.residenceInfo.residenceType.type }}
                        </strong>
                        <strong
                          v-if="a.residenceInfo.residenceType.type == '전세'"
                        >
                          {{ showPrice(a.residenceInfo.jeonseCost) }}
                        </strong>
                        <strong
                          v-else-if="
                            a.residenceInfo.residenceType.type == '매매'
                          "
                        >
                          {{ showPrice(a.residenceInfo.cost) }}
                        </strong>
                        <strong
                          v-else-if="
                            a.residenceInfo.residenceType.type == '월세'
                          "
                        >
                          {{ showPrice(a.residenceInfo.deposit) }}/{{
                            a.residenceInfo.wolseCost
                          }}
                        </strong>
                      </h4>
                      <p class="card-text">
                        {{ a.residenceInfo.residenceCategory.categoryName }}
                        {{ showResiName(a.residenceInfo.name) }} <br />
                        {{ a.residenceInfo.myFloor }}/{{
                          a.residenceInfo.area
                        }}평 <br />
                        {{ showFeature(a.residenceInfo.feature) }}
                      </p>
                      <div class="row mt-1">
                        <a
                          href="#"
                          class="btn btn-info mr-2"
                          @click="showModal(idx)"
                          >상세 보기</a
                        >
                        <a href="#" class="btn" @click="delFavorite(idx)"
                          >찜 삭제</a
                        >
                      </div>
                    </div>
                  </div>
                </VueSlickCarousel>
              </template>
              <template v-if="myfavorite == null"> 매물을 찜해주세요 </template>
            </tab-pane>
          </tabs>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Tabs, TabPane, FormGroupInput } from "@/components";
import { mapActions, mapGetters } from "vuex";
import VueSlickCarousel from "vue-slick-carousel";
import "vue-slick-carousel/dist/vue-slick-carousel.css";
// optional style for arrows & dots
import "vue-slick-carousel/dist/vue-slick-carousel-theme.css";
import http from "@/util/http-common";
import VueSimpleAlert from "vue-simple-alert";
import ResiDetail from "@/pages/map/ResiDetail.vue";
import Modal from "@/components/Modal.vue";
export default {
  name: "profile",
  bodyClass: "profile-page",
  components: {
    Tabs,
    TabPane,
    [FormGroupInput.name]: FormGroupInput,
    VueSlickCarousel,
    ResiDetail,
    Modal,
  },
  data: function () {
    return {
      items: ["id", "name", "LastName"],
      total: 100,
      columns: [""],
      actions: [""],
      resiCommercial: {
        cafeCount: 0,
        healthCount: 0,
        bicycleCount: 0,
        subwayCount: 0,
        busCount: 0,
      },
      settings: {
        dots: true,
        infinite: true,
        initialSlide: 2,
        speed: 500,
        slidesToShow: 4,
        slidesToScroll: 1,
        swipeToSlide: true,
      },
      myfavorite: [],
      resiDetail: null,
      isClick: false,
      user: {
        userName: "",
        userId: "",
        userPass: "",
        userPhone: "",
        userEmail: "",
      },
      showResiDetail: false,
    };
  },
  computed: {
    ...mapGetters("user", ["getUserInfo", "getMyfavoriteList"]),
  },
  methods: {
    ...mapActions("user", [
      "requestUserInfo",
      "requestDelete",
      "requestModify",
      "requsetFavoriteList",
    ]),
    showModal(res) {
      this.resiDetail = this.myfavorite[res].residenceInfo;
      this.showResiDetail = !this.showResiDetail;
      http
        .post(
          "/api/v1/residences/residencecommercialcount?residenceId=" +
            this.resiDetail.id
        )
        .then((res) => {
          this.resiCommercial = res.data.residenceCommercialCountModel[0];
          console.log(res);
        });
    },
    delFavorite(idx) {
      var deldata = this.myfavorite[idx].residenceInfo.id;
      const CSRF_TOKEN = localStorage.getItem("accessToken");

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

          if (this.myfavorite.length == 1) {
            this.myfavorite = null;
          }
        });
      this.myfavorite = this.getMyfavoriteList;
    },
    click() {
      this.isClick = !this.isClick;
    },
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
    clickModify() {
      let modifyed = {
        userName: this.getUserInfo.user.userName,
        userId: this.getUserInfo.user.userId,
        userPass: this.getUserInfo.user.userPass,
        userEmail: this.getUserInfo.user.userEmail,
        userPhone: this.getUserInfo.user.userPhone,
      };
      this.requestModify(modifyed);
      this.click();
    },
    init() {
      this.requestUserInfo();
    },
    remove() {
      alert("삭제완료");
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
  created() {
    this.init();

    this.myfavorite = this.getMyfavoriteList;
  },
  mounted() {},
};
</script>


<style scope>
.myfavotieModal {
  margin-top: 0 !important;
  margin-left: 6% !important;
}
.myimg {
  min-height: calc(100vh - 700px);
  max-height: calc(100vh - 700px);
}
.mydata {
  padding: 20px 30px 20px 30px !important;
  min-height: calc(100vh - 450px);
  max-height: calc(100vh - 450px);
}
.profile-page .gallery {
  margin-top: 5px;
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
.slick-dots {
  position: absolute;
  bottom: 0px;
  display: block;
  width: 100%;
  padding: 0;
  padding-right: 0px;
  margin: 0;
  list-style: none;
  text-align: center;
}
</style>