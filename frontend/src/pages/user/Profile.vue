<template>
  <div>
    <div class="section">
      <div class="container">
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
                        class="btn btn-primary btn-round btn-lg btn-block"
                        >수정</a
                      >
                      <a
                        v-if="isClick"
                        @click="clickModify()"
                        class="btn btn-primary btn-round btn-lg btn-block"
                        >확인</a
                      >
                    </div>
                  </div>
                  <div class="col-md-3">
                    <div
                      style="margin-top: -10px"
                      class="card-footer text-center"
                    >
                      <a class="btn btn-primary btn-round btn-lg btn-block"
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
              <template>
                <VueSlickCarousel
                  v-bind="settings"
                  style="padding: 20px 30px 20px 30px"
                >
                  <div class="card" style="width: 20rem; border-radius: 0.2rem">
                    <img
                      class="card-img-top"
                      src="img/main.jpg"
                      alt="Card image cap"
                    />
                    <div class="card-body">
                      <h4 class="card-title">Card title</h4>
                      <p class="card-text">
                        Some quick example text to build on the card title and
                        make up the bulk of the card's content.
                      </p>
                      <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                  </div>

                  <div class="card" style="width: 20rem">
                    <img
                      class="card-img-top"
                      src="img/main.jpg"
                      alt="Card image cap"
                    />
                    <div class="card-body">
                      <h4 class="card-title">Card title</h4>
                      <p class="card-text">
                        Some quick example text to build on the card title and
                        make up the bulk of the card's content.
                      </p>
                      <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                  </div>

                  <div class="card" style="width: 20rem">
                    <img
                      class="card-img-top"
                      src="img/main.jpg"
                      alt="Card image cap"
                    />
                    <div class="card-body">
                      <h4 class="card-title">Card title</h4>
                      <p class="card-text">
                        Some quick example text to build on the card title and
                        make up the bulk of the card's content.
                      </p>
                      <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                  </div>
                </VueSlickCarousel>
              </template>
            </tab-pane>

            <tab-pane
              ><!--검색 필터 저장-->
              <span slot="label">
                <i class="now-ui-icons design_bullet-list-67"></i> Filter
              </span>
              <div class="col-md-10 ml-auto mr-auto">
                <div class="row collections">
                  <div class="col-md-2">
                    <span class="badge badge-neutral">방 유형</span>
                  </div>
                  <div class="col-md-2">
                    <span class="badge badge-neutral">거래 유형</span>
                  </div>
                  <div class="col-md-3">
                    <span class="badge badge-neutral">가격</span>
                  </div>
                  <div class="col-md-5">
                    <span class="badge badge-neutral">추가필터</span>
                  </div>
                </div>
              </div>

              <div class="col-md-10 ml-auto mr-auto">
                <div class="row collections">
                  <div class="col-md-2">
                    <span class="badge badge-primary">원룸</span>
                    <span class="badge badge-info">투,쓰리룸</span>
                  </div>
                  <div class="col-md-2">
                    <span class="badge badge-success">전세</span>
                    <span class="badge badge-warning">매매</span>
                  </div>
                  <div class="col-md-3">
                    <span class="badge badge-success"> 0~1억5000만원</span>
                    <span class="badge badge-warning"> 1억~2억5000만원</span>
                  </div>
                  <div class="col-md-3">
                    <span class="badge badge-default">없음</span>
                  </div>
                  <div class="col-md-2">
                    <span @click="remove()" class="badge badge-danger"
                      ><i class="now-ui-icons ui-1_simple-remove"></i
                    ></span>
                  </div>
                </div>
              </div>
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

export default {
  name: "profile",
  bodyClass: "profile-page",
  components: {
    Tabs,
    TabPane,
    [FormGroupInput.name]: FormGroupInput,
    VueSlickCarousel,
  },
  data: function () {
    return {
      items: ["id", "name", "LastName"],
      total: 100,
      columns: [""],
      actions: [""],
      settings: {
        dots: true,
        infinite: true,
        initialSlide: 2,
        speed: 500,
        slidesToShow: 4,
        slidesToScroll: 1,
        swipeToSlide: true,
      },
      isClick: false,
      user: {
        userName: "",
        userId: "",
        userPass: "",
        userPhone: "",
        userEmail: "",
      },
    };
  },
  computed: {
    ...mapGetters("user", ["getUserInfo"]),
  },
  methods: {
    ...mapActions("user", [
      "requestUserInfo",
      "requestDelete",
      "requestModify",
    ]),
    click() {
      this.isClick = !this.isClick;
      console.log(this.isClick);
    },
    clickModify() {
      console.log(this.isClick);
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
  },
  created() {
    console.log(this.getUserInfo);
  },
  mounted() {},
};
</script>
<style>
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