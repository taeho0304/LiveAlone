<template>
  <div id="app">
    <card style="border-radius: 13px">
      <div slot="header" class="col-md-12 my-3">
        <h4>
          <n-progress class="my-4" type="info" :value="pr" show-value>
          </n-progress>
          <strong> {{ QnA[index].question }}</strong>
        </h4>
      </div>

      <div
        class="row container"
        style="margin-left: 0px; margin-right: 0px"
        v-if="index == 0"
      >
        <h6 class="col-md-12 inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>1순위</span></label
          >
        </h6>
        <drop-down
          class="
            qnaButton
            select
            col-md-4
            ml-auto
            mr-auto
            btn btn-info btn-round btn-lg btn-block
          "
          :title="this.Si"
        >
          <a
            class="dropdown-item"
            v-for="(items, index) in SiList"
            :key="index"
            @click="clickSi(items.name, index)"
          >
            {{ items.name }}
          </a>
        </drop-down>
        <drop-down
          class="
            qnaButton
            select
            col-md-4
            ml-auto
            mr-auto
            btn btn-info btn-round btn-lg btn-block
          "
          :title="this.Gu"
        >
          <ul class="dropdown-scrollbar">
            <p
              class="dropdown-item"
              v-for="(items, index) in GuList"
              :key="index"
              @click="clickGu(items, index)"
            >
              {{ items }}
            </p>
          </ul>
        </drop-down>
        <drop-down
          class="
            qnaButton
            select
            col-md-4
            ml-auto
            mr-auto
            btn btn-info btn-round btn-lg btn-block
          "
          :title="this.Dong"
        >
          <ul class="dropdown-scrollbar">
            <p
              class="dropdown-item"
              v-for="(items, index) in dongList"
              :key="index"
              @click="clickDong(items, index)"
            >
              {{ items.dongName }}
            </p>
          </ul>
        </drop-down>
        <h6 v-if="isSecond" class="col-md-12 inputLabel">
          <label style="margin-bottom: 0px" for="name"
            ><span>2순위</span></label
          >
        </h6>
        <drop-down
          v-if="isSecond"
          class="
            qnaButton
            select
            col-md-4
            ml-auto
            mr-auto
            btn btn-info btn-round btn-lg btn-block
          "
          style=""
          :title="this.Si2"
        >
          <p
            class="dropdown-item"
            v-for="(items, index) in SiList2"
            :key="index"
            @click="clickSi2(items.name, index)"
          >
            {{ items.name }}
          </p>
        </drop-down>

        <drop-down
          v-if="isSecond"
          class="
            qnaButton
            select
            col-md-4
            ml-auto
            mr-auto
            btn btn-info btn-round btn-lg btn-block
          "
          :title="this.Gu2"
        >
          <ul class="dropdown-scrollbar">
            <p
              class="dropdown-item"
              v-for="(items, index) in GuList2"
              :key="index"
              @click="clickGu2(items, index)"
            >
              {{ items }}
            </p>
          </ul>
        </drop-down>
        <drop-down
          v-if="isSecond"
          class="
            qnaButton
            select
            col-md-4
            ml-auto
            mr-auto
            btn btn-info btn-round btn-lg btn-block
          "
          :title="this.Dong2"
        >
          <ul class="dropdown-scrollbar">
            <p
              class="dropdown-item"
              v-for="(items, index) in dongList2"
              :key="index"
              @click="clickDong2(items, index)"
            >
              {{ items.dongName }}
            </p>
          </ul>
        </drop-down>
        <a
          v-if="isNext"
          class="
            col-md-6
            ml-auto
            mr-auto
            my-4
            select
            btn btn-info btn-round btn-block
            my-4
          "
          @click="next(areatitle, areaindex)"
          >다음
        </a>
      </div>

      <div v-if="index == 2">
        <div class="container" v-for="(a, idx) in QnA[index].answer" :key="idx">
          <a
            v-show="isSelect"
            @click="clickCost(a, idx)"
            class="
              col-md-6
              ml-auto
              my-4
              mr-auto
              btn btn-info btn-round btn-lg btn-block
            "
            >{{ a }}
          </a>
        </div>
        <h6 style="margin-top: 8px; margin-bottom: 0px" v-if="isCost">
          매물에 대한 가격을 설정해주세요.
        </h6>
        <div class="row col-md-12" v-if="isCost">
          <div class="col-md-5 ml-auto mr-auto">
            <p>{{ costStart }} ~</p>
          </div>
          <div class="col-md-5 end">
            <p>{{ costEnd }}</p>
          </div>
        </div>
        <div class="col-md-8 ml-auto mr-auto" v-if="isCost">
          <slider
            class="slider-info mt-4"
            v-model="costRange"
            :connect="true"
            :range="range"
            @input="changeCost"
            type="primary"
          >
          </slider>
        </div>
        <h6 style="margin-top: 8px; margin-bottom: 0px" v-if="isMonth">
          매물에 대한 월세를 설정해주세요.
        </h6>
        <div class="row col-md-12" v-if="isMonth">
          <div class="col-md-5 ml-auto mr-auto">
            <p>{{ wolseStart }} ~</p>
          </div>
          <div class="col-md-5 end">
            <p>{{ wolseEnd }}</p>
          </div>
        </div>
        <div class="col-md-8 ml-auto mr-auto" v-if="isMonth">
          <slider
            class="slider-info mt-4"
            v-model="wolseRange"
            :range="wrange"
            :connect="true"
            @input="changeWRange"
            type="primary"
          >
          </slider>
        </div>
        <h6 style="margin-top: 8px; margin-bottom: 0px" v-if="isMonth">
          매물에 대한 보증금을 설정해주세요.
        </h6>
        <div class="row col-md-12" v-if="isMonth">
          <div class="col-md-5 ml-auto mr-auto">
            <p>{{ monthStart }} ~</p>
          </div>
          <div class="col-md-5 end">
            <p>{{ monthEnd }}</p>
          </div>
        </div>
        <div class="col-md-8 ml-auto mr-auto" v-if="isMonth">
          <slider
            class="slider-info mt-4"
            v-model="monthRange"
            :connect="true"
            :range="mrange"
            @input="changeMonth"
            type="primary"
          >
          </slider>
        </div>
        <div class="col-md-10 ml-auto mr-auto" v-if="isButton">
          <a
            v-show="isButton"
            @click="clickBefore()"
            class="
              col-md-6
              ml-auto
              my-4
              mr-auto
              btn btn-info btn-round btn-lg btn-block
            "
            >이전
          </a>
          <a
            v-show="isButton"
            @click="next()"
            class="
              col-md-6
              ml-auto
              my-4
              mr-auto
              btn btn-info btn-round btn-lg btn-block
            "
            >다음
          </a>
        </div>
      </div>
      <div v-if="index == 1">
        <div class="container" v-for="(a, idx) in QnA[index].answer" :key="idx">
          <a
            @click="next(idx, index)"
            class="
              col-md-6
              ml-auto
              my-4
              mr-auto
              btn btn-info btn-round btn-lg btn-block
            "
            >{{ a }}
          </a>
        </div>
      </div>
      <div v-if="index == 3">
        <div class="container" v-for="(a, idx) in QnA[index].answer" :key="idx">
          <div class="row mr-auto" style="margin-left: 30px">
            <div class="col-md-3 ml-auto my-3 btn btn-info btn-lg">
              {{ a }}
            </div>
            <div v-if="idx == 0" class="col-md-6 pt-1 my-3 mr-auto pl-0">
              <RadioToggleButtons
                v-model="recommend.score[1]"
                :values="values"
                color="#4bb5ff"
                textColor="#000"
                selectedTextColor="#fff"
              />
            </div>
            <div v-if="idx == 1" class="col-md-6 pt-1 my-3 mr-auto pl-0">
              <RadioToggleButtons
                v-model="recommend.score[0]"
                :values="values"
                color="#4bb5ff"
                textColor="#000"
                selectedTextColor="#fff"
              />
            </div>
            <div v-if="idx == 2" class="col-md-6 pt-1 my-3 mr-auto pl-0">
              <RadioToggleButtons
                v-model="recommend.score[5]"
                :values="values"
                color="#4bb5ff"
                textColor="#000"
                selectedTextColor="#fff"
              />
            </div>
          </div>
        </div>

        <a
          v-if="
            recommend.score[0] != 0 &&
            recommend.score[1] != 0 &&
            recommend.score[5] != 0
          "
          @click="next()"
          class="
            col-md-6
            ml-auto
            my-4
            mr-auto
            btn btn-info btn-round btn-lg btn-block
          "
          >다음
        </a>
      </div>

      <div v-if="index == 4">
        <div class="container" v-for="(a, idx) in QnA[index].answer" :key="idx">
          <div class="row mr-auto" style="margin-left: 30px">
            <div class="col-md-3 ml-auto my-3 btn btn-info btn-lg">
              {{ a }}
            </div>
            <div v-if="idx == 0" class="col-md-6 pt-1 my-3 mr-auto pl-0">
              <RadioToggleButtons
                v-model="recommend.score[2]"
                :values="values"
                color="#4bb5ff"
                textColor="#000"
                selectedTextColor="#fff"
              />
            </div>
            <div v-if="idx == 1" class="col-md-6 pt-1 my-3 mr-auto pl-0">
              <RadioToggleButtons
                v-model="recommend.score[4]"
                :values="values"
                color="#4bb5ff"
                textColor="#000"
                selectedTextColor="#fff"
              />
            </div>
            <div v-if="idx == 2" class="col-md-6 pt-1 my-3 mr-auto pl-0">
              <RadioToggleButtons
                v-model="recommend.score[3]"
                :values="values"
                color="#4bb5ff"
                textColor="#000"
                selectedTextColor="#fff"
              />
            </div>
          </div>
        </div>

        <a
          v-if="
            recommend.score[2] != 0 &&
            recommend.score[4] != 0 &&
            recommend.score[3] != 0
          "
          @click="next()"
          class="
            col-md-6
            ml-auto
            my-4
            mr-auto
            btn btn-info btn-round btn-lg btn-block
          "
          >다음
        </a>
      </div>
    </card>
  </div>
</template>

<script>
import { Progress, Slider, Button } from "@/components";
import { mapActions, mapGetters } from "vuex";
import { DropDown } from "@/components";
import { Card } from "@/components";

import http from "@/util/http-common";

export default {
  components: {
    [Progress.name]: Progress,
    DropDown,
    Card,
    Slider,
    [Button.name]: Button,
  },
  data() {
    return {
      values: [
        { label: "1 점", value: "1" },
        { label: "2 점", value: "2" },
        { label: "3 점", value: "3" },
        { label: "4 점", value: "4" },
        { label: "5 점", value: "5" },
      ],
      currentValue: "",

      recommend: {
        dong: [0, 0],
        resiCategory: [],
        resiType: 0,
        resiCostStart: 0,
        resiCostEnd: 0,
        resiDepositStart: 0,
        resiDepositEnd: 0,
        score: [0, 0, 0, 0, 0, 0], // (카페, 편의점,지하철,따릉이,버스,헬스장 순서대로!!)
      },

      scoreOne: false,
      isSelect: true,
      isNextButton: false,
      isButton: false,
      isFinish: false,
      isValue: false,
      isCost: false,
      isMonth: false,
      costRange: [0, 1500],
      monthRange: [0, 1500],
      wolseRange: [0, 350],
      mrange: {
        min: 0,
        max: 1500,
      },
      wrange: {
        min: 0,
        max: 350,
      },
      range: {
        min: 0,
        max: 1500,
      },
      costStart: "0",
      costEnd: "무제한",
      monthStart: "0",
      monthEnd: "무제한",
      wolseStart: "0",
      wolseEnd: "무제한",

      isNext: false,
      isSecond: false,
      areatitle: "지역선택",
      areaindex: 0,
      QnARes: [],
      QnA: JSON.parse(localStorage.getItem("getQnAOpt")),
      index: 0,
      pr: 0,
      SiList: [{ name: "서울특별시" }],
      GuList: [],
      dongList: [],
      SiList2: [{ name: "서울특별시" }],
      GuList2: [],
      dongList2: [],
      SiIdx: 0,
      GuIdx: 0,
      DongIdx: 0,
      Dong: "동 선택하세요",
      Si: "시 선택하세요",
      Gu: "구 선택하세요",
      Dong2: "동 선택하세요",
      Si2: "시 선택하세요",
      Gu2: "구 선택하세요",
    };
  },
  methods: {
    changeWRange() {
      this.wolseRange[0] = parseInt(this.wolseRange[0]);
      this.wolseRange[1] = parseInt(this.wolseRange[1]);
      this.recommend.resiCostStart = parseInt(this.wolseRange[0]);
      this.recommend.resiCostEnd = parseInt(this.wolseRange[1]);

      this.wolseStart = this.wolseRange[0] + "만 원";
      this.wolseEnd = this.wolseRange[1] + "만 원";

      if (this.wolseRange[1] == 350) {
        this.wolseEnd = "무제한";
        this.recommend.resiCostEnd = 0;
      }
      if (this.wolseRange[0] == 0) {
        this.wolseStart = "0";
        this.recommend.resiCostStart = 0;
      }
    },

    clickCost(a, idx) {
      this.recommend.resiType = idx;
      this.isSelect = false;
      if (a == "월세") {
        this.isCost = false;
        this.isMonth = true;
        this.isButton = true;
      } else {
        this.isCost = true;
        this.isMonth = false;
        this.isButton = true;
      }
      this.recommend.resiType = idx + 1;
    },
    clickBefore() {
      this.isSelect = true;
      this.isCost = false;
      this.isMonth = false;
      this.isButton = false;
    },
    changeCost() {
      this.costRange[0] = parseInt(this.costRange[0]);
      this.costRange[1] = parseInt(this.costRange[1]);
      this.recommend.resiCostStart = parseInt(this.costRange[0]) * 100;
      this.recommend.resiCostEnd = parseInt(this.costRange[1]) * 100;

      if (parseInt(this.costRange[0] / 100) == 0) {
        this.costStart = parseInt(this.costRange[0] % 100) + "00만 원";
      } else if (parseInt(this.costRange[0] % 10) == 0) {
        this.costStart = parseInt(this.costRange[0] / 100) + "억";
      } else {
        this.costStart =
          parseInt(this.costRange[0] / 100) +
          "억" +
          parseInt(this.costRange[0] % 100) +
          "00만 원";
      }

      if (parseInt(this.costRange[1] / 100) == 0) {
        this.costEnd = parseInt(this.costRange[1] % 100) + "00만 원";
      } else if (parseInt(this.costRange[1] % 100) == 0) {
        this.costEnd = parseInt(this.costRange[1] / 100) + "억";
      } else {
        this.costEnd =
          parseInt(this.costRange[1] / 100) +
          "억" +
          parseInt(this.costRange[1] % 100) +
          "00만 원";
      }
      if (this.costRange[1] == 1500) {
        this.costEnd = "무제한";
        this.recommend.resiCostEnd = 0;
      }
      if (this.costRange[0] == 0) {
        this.costStart = "0";
        this.recommend.resiCostStart = 0;
      }
    },
    changeMonth() {
      this.monthRange[0] = parseInt(this.monthRange[0]);
      this.monthRange[1] = parseInt(this.monthRange[1]);
      this.recommend.resiDepositStart = parseInt(this.monthRange[0]) * 100;
      this.recommend.resiDepositEnd = parseInt(this.monthRange[1]) * 100;

      if (parseInt(this.monthRange[0] / 100) == 0) {
        this.monthStart = parseInt(this.monthRange[0] % 100) + "00만 원";
      } else if (parseInt(this.monthRange[0] % 10) == 0) {
        this.monthStart = parseInt(this.monthRange[0] / 100) + "억";
      } else {
        this.monthStart =
          parseInt(this.monthRange[0] / 100) +
          "억" +
          parseInt(this.monthRange[0] % 100) +
          "00만 원";
      }

      if (parseInt(this.monthRange[1] / 100) == 0) {
        this.monthEnd = parseInt(this.monthRange[1] % 100) + "00만 원";
      } else if (parseInt(this.monthRange[1] % 100) == 0) {
        this.monthEnd = parseInt(this.monthRange[1] / 100) + "억";
      } else {
        this.monthEnd =
          parseInt(this.monthRange[1] / 100) +
          "억" +
          parseInt(this.monthRange[1] % 100) +
          "00만 원";
      }
      if (this.monthRange[1] == 1500) {
        this.monthEnd = "무제한";
        this.recommend.resiDepositEnd = 0;
      }
      if (this.monthRange[0] == 0) {
        this.monthStart = "0";
        this.recommend.resiDepositStart = 0;
      }
    },
    clickDong(dongItems, idx) {
      this.Dong = dongItems.dongName;
      this.recommend.dong[0] = dongItems.id;
      this.isSecond = true;
    },
    clickDong2(dongItems, idx) {
      this.Dong2 = dongItems.dongName;
      this.recommend.dong[1] = dongItems.id;

      this.isNext = true;
    },
    clickGu(guName, idx) {
      http.get("/api/v1/search/dong" + "?guGunName=" + guName).then((res) => {
        this.dongList = res.data.dongModelList;
      });
      this.Gu = guName;
    },
    clickSi(siName, idx) {
      http
        .get("/api/v1/search/gugun" + "?siName=" + "서울특별시")
        .then((res) => {
          this.GuList = res.data.guGunListList;
        });
      this.Si = siName;
    },
    clickGu2(guName, idx) {
      http.get("/api/v1/search/dong" + "?guGunName=" + guName).then((res) => {
        this.dongList2 = res.data.dongModelList;
      });
      this.Gu2 = guName;
    },
    clickSi2(siName, idx) {
      http
        .get("/api/v1/search/gugun" + "?siName=" + "서울특별시")
        .then((res) => {
          this.GuList2 = res.data.guGunListList;
        });
      this.Si2 = siName;
    },
    changeArea(a, ans) {
      this.areatitle = a;
      this.areaindex = ans;
      return;
    },
    next(idx, ans) {
      if (ans == 1) {
        if (idx == 0) {
          this.recommend.resiCategory.push(1);
          this.recommend.resiCategory.push(2);
          this.recommend.resiCategory.push(3);
        } else {
          this.recommend.resiCategory.push(idx + 3);
        }
      }

      if (this.index == 4) {
        this.setRes(this.recommend);
        return;
      }
      this.index += 1;
      this.pr += 25;
      this.isNextButton = false;
    },
    ...mapActions("question", ["setQnARes"]),
    setRes(data) {
      this.setQnARes(data);
    },
  },
};
</script>
<style>
.qnaButton {
  font-size: 1em;
  border-radius: 10px !important;
  padding: 7px 5px !important;
}
.container {
  text-align: center;
}
.dropdown-scrollbar {
  height: 150px;
  overflow-x: hidden;
  overflow-y: scroll;
}
</style>