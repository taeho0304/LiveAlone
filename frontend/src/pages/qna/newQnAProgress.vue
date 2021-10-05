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

      <div class="row container" style="
    margin-left: 0px;
    margin-right: 0px;
" v-if="index == 0">
        <h6 class="col-md-12 inputLabel">
        <label style="margin-bottom: 0px" for="name"
          ><span>1순위</span></label>
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
          <p
            class="dropdown-item"
            v-for="(items, index) in SiList"
            :key="index"
            @click="clickSi(items.name, index)"
          >
            {{ items.name }}
          </p>
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
          <p
            class="dropdown-item"
            v-for="(items, index) in GuList"
              :key="index"
            @click="clickGu(items, index)"
              >{{ items }}
          </p>
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
          <p
            class="dropdown-item"
            v-for="(items, index) in dongList"
              :key="index"
            @click="clickDong(items, index)"
              >{{ items.dongName }}
          </p>
        </drop-down>
        <h6 v-if="isSecond" class="col-md-12 inputLabel">
        <label style="margin-bottom: 0px" for="name"
          ><span>2순위</span></label>
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
          <p
            class="dropdown-item"
            v-for="(items, index) in GuList2"
              :key="index"
            @click="clickGu2(items, index)"
              >{{ items }}
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
          :title="this.Dong2"
        >
          <p
            class="dropdown-item"
            v-for="(items, index) in dongList2"
              :key="index"
            @click="clickDong2(items, index)"
              >{{ items.dongName }}
          </p>
        </drop-down>
        <a
          v-if="isNext"
          class="
            col-md-8
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
  
      <div
        class="container"
        v-else
        v-for="(a, idx) in QnA[index].answer"
        :key="idx"
        @click="next(a, idx)"
      >
        <a
          class="
            col-md-8
            ml-auto
            my-4
            mr-auto
            btn btn-info btn-round btn-lg btn-block
          "
          >{{ a }}
        </a>
      </div>

      <div slot="footer" class="card-footer text-muted mb-2">live-alone</div>
    </card>
  </div>
</template>

<script>
import { Progress } from "@/components";
import { mapActions, mapGetters } from "vuex";
import { DropDown } from "@/components";
import { Card } from "@/components";
import http from "@/util/http-common";
export default {
  components: {
    [Progress.name]: Progress,
    DropDown,
    Card,
  },
  data() {
    return {
      isNext:false,
      isSecond:false,
      areatitle: "지역선택",
      areaindex: 0,
      QnARes: [

      ],
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
    clickDong(dongItems, idx) {
      this.Dong = dongItems.dongName;
      this.isSecond=true;
    },
    clickDong2(dongItems, idx) {
      this.Dong2 = dongItems.dongName;
      this.isNext=true;
      
    },
    // clickDong(dongItems, idx) {
    //   this.Dong = dongItems.dongName;

    //   let temp = this.Si + " " + this.Gu + " " + this.Dong;
    //   console.log(dongItems);

    //   const data = {
    //     si: this.Si,
    //     gugun: this.Gu,
    //     dong: this.Dong,
    //     lat: dongItems.lat,
    //     long: dongItems.lon,
    //   };

    //   console.log(data);
    // },
    clickGu(guName, idx) {
      http.get("/api/v1/search/dong" + "?dong=" + guName).then((res) => {
        console.log(res.data.dongModelList);
        this.dongList = res.data.dongModelList;
      });
      this.Gu = guName;
    },
    clickSi(siName, idx) {
      http
        .get("/api/v1/search/gugun" + "?siName=" + "서울특별시")
        .then((res) => {
          console.log(res.data.guGunListList);
          this.GuList = res.data.guGunListList;
        });
      this.Si = siName;
    },
    clickGu2(guName, idx) {
      http.get("/api/v1/search/dong" + "?dong=" + guName).then((res) => {
        console.log(res.data.dongModelList);
        this.dongList2 = res.data.dongModelList;
      });
      this.Gu2 = guName;
    },
    clickSi2(siName, idx) {
      http
        .get("/api/v1/search/gugun" + "?siName=" + "서울특별시")
        .then((res) => {
          console.log(res.data.guGunListList);
          this.GuList2 = res.data.guGunListList;
        });
      this.Si2 = siName;
    },
    changeArea(a, ans) {
      this.areatitle = a;
      this.areaindex = ans;
      return;
    },
    next(a, ans) {
      this.QnARes.push(ans);
      //console.log(a);
      //console.log(ans);

      if (this.index == 4) {
        console.log(this.QnARes);
        this.setRes(this.QnARes);
        return;
      }
      this.index += 1;
      this.pr += 25;
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
</style>