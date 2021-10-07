<template>
  <div
    id="residenceInput"
    v-if="getUserInfo"
    class="col-md-10 ml-auto mr-auto"
    style="margin-top: 0px"
  >
    <div class="row collections">
      <div class="col-md-12 inputLabel">
        <label for="name"><span>부동산 정보</span></label>
        <hr style="margin-top: 0" />
      </div>
      <h6 class="col-md-12 inputLabel">
        <label style="margin-bottom: 0px" for="name"
          ><span>부동산 이름</span></label
        >
      </h6>
      <fg-input
        style="padding-left: 5px; padding-right: 5px"
        class="col-md-12 input-lg"
        disabled
        type="text"
        v-model="getUserInfo.user.estateInfo.name"
        name="userName"
      >
      </fg-input>
      <h6 class="col-md-12 inputLabel">
        <label style="margin-bottom: 0px" for="name"
          ><span>부동산 위치</span></label
        >
      </h6>
      <fg-input
        style="padding-left: 5px; padding-right: 5px"
        class="col-md-12 input-lg"
        disabled
        type="text"
        v-model="getUserInfo.user.estateInfo.address"
        name="userId"
      >
      </fg-input>
      <h6 class="col-md-12 inputLabel">
        <label style="margin-bottom: 0px" for="name"
          ><span>중개사 번호</span></label
        >
      </h6>
      <fg-input
        style="padding-left: 5px; padding-right: 5px"
        class="col-md-12 input-lg"
        disabled
        type="text"
        v-model="getUserInfo.user.userPhone"
        name="email"
      >
      </fg-input>
      <h6 class="col-md-12 inputLabel">
        <label style="margin-bottom: 0px" for="name"
          ><span>중개사 이름</span></label
        >
      </h6>
      <fg-input
        style="padding-left: 5px; padding-right: 5px"
        class="col-md-12 input-lg"
        disabled
        type="text"
        v-model="getUserInfo.user.userName"
        name="email"
      >
      </fg-input>
    </div>
    <div class="row collections">
      <div style="padding-left: 5px; padding-right: 5px" class="col-md-12">
        <label for="name"><span>매물 정보</span></label>
        <hr style="margin-top: 0" />
      </div>

      <div style="padding-left: 5px; padding-right: 5px" class="col-md-2">
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>매물 유형</span></label
          >
        </h6>

        <drop-down style="border-radius: 10px">
          <n-button
            slot="title"
            class="dropdown-toggle"
            data-toggle="dropdown"
            block
            style="
              background-color: #edf2ff;
              border: 1px solid #ced4da;
              padding-top: 15px;
              padding-bottom: 15px;
              margin-top: 0px;
              border-radius: 10px;
            "
          >
            {{ categoryName }}
          </n-button>
          <ul class="dropdown-scrollbar">
            <a
              v-for="(categories, index) in categoryList"
              :key="index"
              class="dropdown-item"
              @click="clickCategory(categories, index)"
              >{{ categories.categoryName }}
            </a>
          </ul>
        </drop-down>
      </div>
      <div style="padding-left: 5px; padding-right: 5px" class="col-md-2">
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>거래 유형</span></label
          >
        </h6>

        <drop-down style="border-radius: 10px">
          <n-button
            slot="title"
            class="dropdown-toggle"
            data-toggle="dropdown"
            block
            style="
              background-color: #edf2ff;
              border: 1px solid #ced4da;
              padding-top: 15px;
              padding-bottom: 15px;
              margin-top: 0px;
              border-radius: 10px;
            "
          >
            {{ typeName }}
          </n-button>
          <ul class="dropdown-scrollbar">
            <a
              v-for="(types, index) in typeList"
              :key="index"
              class="dropdown-item"
              @click="clickType(types, index)"
              >{{ types.type }}
            </a>
          </ul>
        </drop-down>
      </div>
      <div style="padding-left: 5px; padding-right: 5px" class="col-md-2">
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"><span>가격</span></label>
        </h6>

        <fg-input class="input-lg" placeholder="가격" v-model="pay" type="text">
        </fg-input>
      </div>
      <div style="padding-left: 5px; padding-right: 5px" class="col-md-3">
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>보증금</span></label
          >
        </h6>

        <fg-input
          :disabled="this.isMonthType"
          v-model="residence.deposit"
          class="input-lg"
          type="text"
          placeholder="보증금"
        >
        </fg-input>
      </div>
      <div style="padding-left: 5px; padding-right: 5px" class="col-md-3">
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>관리비</span></label
          >
        </h6>
        <fg-input
          class="input-lg"
          v-model="residence.manageCost"
          type="text"
          placeholder="관리비"
        >
        </fg-input>
      </div>
    </div>

    <div class="row collections">
      <div style="padding-left: 5px; padding-right: 5px" class="col-md-6">
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"><span>주소</span></label>
        </h6>

        <fg-input
          class="input-lg"
          :value="this.addr1"
          type="text"
          placeholder="Zip Code"
          @click="findAddr()"
        >
        </fg-input>
      </div>
      <div style="padding-left: 5px; padding-right: 5px" class="col-md-3">
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>매물 이름</span></label
          >
        </h6>

        <fg-input
          class="input-lg"
          v-model="residence.name"
          type="text"
          placeholder="매물 이름"
          name="userId"
        >
        </fg-input>
      </div>
      <div style="padding-left: 5px; padding-right: 5px" class="col-md-3">
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>방 구조</span></label
          >
        </h6>
        <drop-down style="border-radius: 10px">
          <n-button
            slot="title"
            class="dropdown-toggle"
            data-toggle="dropdown"
            block
            :disabled="this.isStructureType"
            style="
              background-color: #edf2ff;
              border: 1px solid #ced4da;
              padding-top: 15px;
              padding-bottom: 15px;
              margin-top: 0px;
              border-radius: 10px;
            "
          >
            {{ structureName }}
          </n-button>
          <ul
            class="dropdown-scrollbar"
            v-for="(structures, index) in structureList"
            :key="index"
            v-show="structures.valid"
            :value="structures.id"
            @click="clickStructure(structures, index)"
          >
            <a class="dropdown-item">{{ structures.name }} </a>
          </ul>
        </drop-down>
      </div>

      <div style="padding-left: 5px; padding-right: 5px" class="col-md-3">
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>매물 층수</span></label
          >
        </h6>

        <fg-input
          class="input-lg"
          v-model="residence.myFloor"
          type="text"
          placeholder="매물 층수"
          name="userId"
        >
        </fg-input>
      </div>
      <div style="padding-left: 5px; padding-right: 5px" class="col-md-3">
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>전체 층수</span></label
          >
        </h6>

        <fg-input
          class="input-lg"
          v-model="residence.buildingFloor"
          type="text"
          placeholder="전체 층수"
          name="userId"
        >
        </fg-input>
      </div>
      <div style="padding-left: 5px; padding-right: 5px" class="col-md-3">
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>매물 평수</span></label
          >
        </h6>

        <fg-input
          class="input-lg"
          v-model="residence.area"
          type="text"
          placeholder="매물 평수"
          name="userId"
        >
        </fg-input>
      </div>
      <div
        style="
          padding-left: 5px;
          padding-right: 5px;
          margin-bottom: 10px;
          height: 45px;
        "
        class="col-md-3"
      >
        <h6 class="inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>매물 방향</span></label
          >
        </h6>
        <treeselect
          v-model="residence.direction"
          :multiple="false"
          :options="directionList"
          placeholder="동, 서, 남, 북"
          style="border-radius: 10px; background: #edf2ff"
        />
      </div>
      <h6 class="col-md-12 inputLabel">
        <label style="margin-bottom: 0px" for="name"
          ><span>매물 옵션</span></label
        >
      </h6>
      <div
        style="
          padding-left: 5px;
          padding-right: 5px;
          margin-bottom: 10px;
          height: 45px;
        "
        class="col-md-12"
      >
        <treeselect
          v-model="residence.feature"
          :multiple="true"
          :options="featureList"
          placeholder="다중 선택 가능"
          style="border-radius: 10px; background: #edf2ff"
        />
      </div>
      <h6 class="col-md-12 inputLabel">
        <label style="margin-bottom: 0px" for="name"
          ><span>매물 이미지</span></label
        >
      </h6>
      <div style="padding-left: 5px; padding-right: 5px" class="col-md-12">
        <label class="input-file-button" for="input-file" style="z-index: 99">
          업로드
        </label>
        <input
          multiple
          class="filestyle"
          id="input-file"
          ref="images"
          type="file"
          style="margin-left: -73px"
        />
      </div>
    </div>
    <div class="row collections mt-4">
      <div class="col-md-3"></div>
      <div class="col-md-6">
        <div class="text-center">
          <a
            @click="clickRegistResi()"
            class="btn btn-info btn-round btn-lg btn-block"
            >등록</a
          >
        </div>
      </div>

      <div class="col-md-3"></div>
    </div>
  </div>
</template>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f52d6b75a8a65ca935ff31e1ba7eace5&libraries=services,clusterer,drawing"/>
<script src="https://unpkg.com/vue-upload-multiple-image@1.1.6/dist/vue-upload-multiple-image.js"></script>
<script src="path/to/vue.js"></script>
<script src="path/to/dist/vue-upload-multiple-image.js"></script>
<script>
import { Tabs, TabPane, FormGroupInput, Button, DropDown } from "@/components";
import { mapActions, mapGetters } from "vuex";
import Treeselect from "@riophae/vue-treeselect";
import axios from "axios";
import http from "@/util/http-common";
import VueUploadMultipleImage from "vue-upload-multiple-image-korean";
export default {
  name: "manage",
  bodyClass: "manage-page",
  components: {
    Tabs,
    TabPane,
    [FormGroupInput.name]: FormGroupInput,
    [Button.name]: Button,
    DropDown,
    Treeselect,
    VueUploadMultipleImage,
  },
  data: function () {
    return {
      pay: "",
      direct: [],
      featureList: [
        { id: "보증금조절가능", label: "보증금조절가능" },
        { id: "주차가능", label: "주차가능" },
        { id: "엘리베이터", label: "엘리베이터" },
        { id: "단기가능", label: "단기가능" },
        { id: "빌트인", label: "빌트인" },
        { id: "발코니", label: "발코니" },
        { id: "풀옵션", label: "풀옵션" },
        { id: "보안/안전", label: "보안/안전" },
        { id: "옵션 없음", label: "옵션 없음" },
      ],
      categoryList: [],
      typeList: [],
      structureList: [
        { id: "분리형", name: "주방 분리형(1.5룸)", valid: true },
        { id: "복층", name: "복층", valid: true },
        { id: "", name: "투룸", valid: true },
        { id: "", name: "쓰리룸 이상", valid: true },
      ],
      directionList: [
        { id: "동", label: "동"},
        { id: "서", label: "서"},
        { id: "남", label: "남",
          children: [
            { id: "남동", label: "남동"},
            { id: "남서", label: "남서"},
          ],
        },
        { id: "북", label: "북",
          children: [
            { id: "북동", label: "북동"},
            { id: "북서", label: "북서"},
          ],
        },
      ],

      zip: "",
      addr1: "",
      addr2: "",
      isMonthType: true,
      isStructureType: false,
      
      categoryName: "매물 유형",
      typeName: "거래 유형",
      structureName: "방 구조",
      directionName: "매물 방향",
      
      residence: {
        residenceCategory: 0, //매물유형
        residenceType: 0, //거래유형
        cost: "", //매매가격
        jeonseCost: "", //전세가격
        wolseCost: "", //월세가격
        deposit: "", //보증금
        manageCost: "", //관리비
        dong: "",
        lat: 0,
        lon: 0,
        name: "",
        structure: "",
        myFloor: "",
        buildingFloor: "",
        area: "", //평수
        direction: null, //매물 방향
        feature: [],
        thumbnails: "",
        estateId: "",
      },
    };
  },
  computed: {
    ...mapGetters("user", ["getUserInfo"]),
    get: function () {
      return this.residence.direction === "" ? null : this.residence.direction;
    },
  },
  methods: {
    ...mapActions("user", [
      "requestUserInfo",
      "requestDelete",
      "requestModify",
      "requestRegistResi",
    ]),

    clickRegistResi() {
      this.residence.estateId = this.getUserInfo.user.estateInfo.id;
      if (this.residence.residenceType == "1") {
        this.residence.cost = this.pay;
        this.residence.deposit = 0;
        this.residence.jeonseCost = 0;
        this.residence.wolseCost = 0;
      } else if (this.residence.residenceType == "2") {
        this.residence.jeonseCost = this.pay;
        this.residence.deposit = 0;
        this.residence.cost = 0;
        this.residence.wolseCost = 0;
      } else if (this.residence.residenceType == "3") {
        this.residence.wolseCost = this.pay;
        this.residence.cost = 0;
        this.residence.jeonseCost = 0;
      }
      this.residence.thumbnails = this.$refs.images.files;
      this.$fire({
        type: "info",
        title: "등록완료",
        text: "adsf",

        // footer: '<a href="">Why do I have this issue?</a>'
      });
      this.requestRegistResi(this.residence);
      this.$router.go();
    },

    clickStructure(items, index) {
      this.structureName = items.name;
      this.residence.structure = items.id;
    },
    clickCategory(items, index) {
      this.categoryName = items.categoryName;
      this.residence.residenceCategory = items.id;
      if (
        items.categoryName == "원룸" ||
        items.categoryName == "오피스텔" ||
        items.categoryName == "도시형생활주택"
      ) {
        this.structureList[0].valid = true;
        this.structureList[1].valid = true;
        this.structureList[2].valid = false;
        this.structureList[3].valid = false;
        this.isStructureType = false;
      } else if (items.categoryName == "투룸") {
        this.structureList[0].valid = false;
        this.structureList[1].valid = false;
        this.structureList[2].valid = true;
        this.structureList[3].valid = false;
        this.isStructureType = false;
      } else if (items.categoryName == "쓰리룸 이상") {
        this.structureList[0].valid = false;
        this.structureList[1].valid = false;
        this.structureList[2].valid = false;
        this.structureList[3].valid = true;
        this.isStructureType = false;
      } else if (items.categoryName == "아파트") {
        this.isStructureType = true;
      }
    },
    clickType(items) {
      if (items.type == "월세") {
        this.isMonthType = false;
      } else {
        this.isMonthType = true;
      }
      this.typeName = items.type;
      this.residence.residenceType = items.id;
    },
    findAddr() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          let fullRoadAddr = data.roadAddress; // 도로명 주소 변수
          let extraRoadAddr = ""; // 도로명 조합형 주소 변수

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
            extraRoadAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if (data.buildingName !== "" && data.apartment === "Y") {
            extraRoadAddr +=
              extraRoadAddr !== ""
                ? ", " + data.buildingName
                : data.buildingName;
          }
          // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if (extraRoadAddr !== "") {
            extraRoadAddr = " (" + extraRoadAddr + ")";
          }
          // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
          if (fullRoadAddr !== "") {
            fullRoadAddr += extraRoadAddr;
          }
          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          this.residence.dong = data.bname; //5자리 새우편번호 사용
          this.addr1 = fullRoadAddr;

          const REST_API_KEY = "KakaoAK 74297f3fdce4f2f45a1c824e5f4d922c";
          axios
            .get(
              "https://dapi.kakao.com/v2/local/search/address.json?&query=" +
                this.addr1,
              { headers: { Authorization: REST_API_KEY } }
            )
            .then((res) => {
              this.residence.lat = res.data.documents[0].address.y;
              this.residence.lon = res.data.documents[0].address.x;
            })
            .catch((error) => console.log(error));
        },
      }).open();
    },
    init() {
      this.requestUserInfo();
      http.get("/api/v1/search/roomtypes").then((res) => {
        this.categoryList = res.data.residenceCategoryList;
      });
      http.get("/api/v1/search/bargaintypes").then((res) => {
        this.typeList = res.data.residenceTypeList;
      });
    },
  },
  created() {
    this.init();
  },
  mounted() {
    this.init();
  },
};
</script>
<style src="@riophae/vue-treeselect/dist/vue-treeselect.min.css"></style>
<style>
.input-file-button {
  padding: 6px 25px;
  background-color: #2ca8ff;
  border-radius: 4px;
  color: white;
  cursor: pointer;
  position: relative;
}
.image-container[data-v-00458278] {
  width: calc(100vw - 440px);
  height: 180px;
  border: 1px dashed #ced4da;
  border-radius: 10px;
  background-color: #fff;
}
.inputLabel {
  padding-left: 5px;
  padding-right: 5px;
  margin-bottom: 3px;
}
.vue-treeselect__control {
  padding-left: 5px;
  padding-right: 5px;
  display: table;
  table-layout: fixed;
  width: 100%;
  height: 47px;
  border: 1px solid #ced4da;
  border-radius: 10px;
  background: #edf2ff;
  -webkit-transition-duration: 0.2s;
  transition-duration: 0.2s;
  -webkit-transition-property: border-color, width, height, background-color,
    opacity, -webkit-box-shadow;
  transition-property: border-color, width, height, background-color, opacity,
    -webkit-box-shadow;
  transition-property: border-color, box-shadow, width, height, background-color,
    opacity;
  transition-property: border-color, box-shadow, width, height, background-color,
    opacity, -webkit-box-shadow;
  -webkit-transition-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
  transition-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
}
</style>