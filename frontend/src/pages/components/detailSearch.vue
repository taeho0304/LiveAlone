<template>
  <div class="contaioner">
    <div class="col-md-12 wrap">
      <div class="row lineTRL">
        <div class="col-md-2">
          <div class="category">방 유형</div>
        </div>
        <div class="row col-md-5">
          <div class="col-md-4 lineL">
            <n-checkbox
              value="1"
              v-model="checkedResiCategory[0].checkResi0"
              @input="changeResiCategory"
              >원룸</n-checkbox
            >
          </div>
          <div class="col-md-4">
            <n-checkbox
              value="2"
              v-model="checkedResiCategory[0].checkResi1"
              @input="changeResiCategory"
              >투 . 쓰리 룸</n-checkbox
            >
          </div>
          <div class="col-md-4">
            <n-checkbox
              value="3"
              v-model="checkedResiCategory[0].checkResi2"
              @input="changeResiCategory"
              >오피스텔</n-checkbox
            >
          </div>
        </div>
        <div class="col-md-5 lineL">
          <n-checkbox
            value="4"
            v-model="checkedResiCategory[0].checkResi3"
            @input="changeResiCategory"
            >아파트</n-checkbox
          >
        </div>
      </div>

      <div class="row lineTRL">
        <div class="col-md-2 mt-auto mb-auto">
          <div class="category">거래 유형</div>
        </div>
        <div class="col-md-3 lineL">
          <n-checkbox
            value="전세"
            v-model="checkedResiType[0].TradingJ"
            @input="changeResiType"
            >전세</n-checkbox
          >
        </div>
        <div class="col-md-3 lineL">
          <n-checkbox
            value="월세"
            v-model="checkedResiType[0].TradingW"
            @input="changeResiType"
            >월세</n-checkbox
          >
        </div>
        <div class="col-md-3 lineL">
          <n-checkbox
            value="매매"
            v-model="checkedResiType[0].TradingM"
            @input="changeResiType"
            >매매</n-checkbox
          >
        </div>
      </div>

      <div class="row lineRL">
        <div class="col-md-2 mt-auto mb-auto">
          <div class="category">가격</div>
        </div>
        <div class="col-md-3 lineL" v-show="checkedResiType[0].TradingJ">
          <strong>보증금/전세가</strong>
          <div class="row">
            <div class="col-md-6 ml-auto mr-auto">
              <p>{{ JrangeS }} ~</p>
            </div>

            <div class="col-md-6 end">
              <p>{{ JrangeE }}</p>
            </div>
          </div>
          <div class="col-md-12">
            <slider
              class="slider-info mt-4"
              v-model="rangeSlider"
              :range="Jrange"
              :connect="true"
              @input="chaneJRange"
              type="primary"
            >
            </slider>
          </div>
        </div>
        <div class="col-md-3 lineL" v-show="!checkedResiType[0].TradingJ">
          선택후 설정 해라
        </div>
        <div class="col-md-3 lineL" v-show="checkedResiType[0].TradingW">
          <strong> 월세</strong>
          <div class="row">
            <div class="col-md-6 ml-auto mr-auto">
              <p>{{ WrangeS }} ~</p>
            </div>
            <div class="col-md-6 end">
              <p>{{ WrangeE }}</p>
            </div>
          </div>
          <div class="col-md-12">
            <slider
              class="slider-info mt-4"
              v-model="dealMonthRange"
              :range="Wrange"
              :connect="true"
              @input="changeWRange"
              type="primary"
            >
            </slider>
          </div>
          <!--<Slider /> -->
        </div>
        <div class="col-md-3 lineL" v-show="!checkedResiType[0].TradingW">
          선택후 설정 해라
        </div>

        <div class="col-md-3 lineL" v-show="checkedResiType[0].TradingM">
          <strong> 매매가</strong>
          <div class="row">
            <div class="col-md-6 ml-auto mr-auto">
              <p>{{ MrangeS }} ~</p>
            </div>
            <div class="col-md-6 end">
              <p>{{ MrangeE }}</p>
            </div>
          </div>
          <div class="col-md-12">
            <slider
              class="slider-info mt-4"
              v-model="dealTradingRange"
              :range="Mrange"
              :connect="true"
              @input="changeMRange"
              type="primary"
            >
            </slider>
          </div>
        </div>
        <div class="col-md-3 lineL" v-show="!checkedResiType[0].TradingM">
          선택후 설정 해라
        </div>
      </div>

      <div class="row lineAll">
        <div class="col-md-2">
          <div class="category">추가필터</div>
        </div>
        <div class="col-md-2 lineL">
          <strong> 관리비</strong>
          <div class="row">
            <div class="col-md-6 ml-auto mr-auto">
              <p>{{ ManageStart }} ~</p>
            </div>
            <div class="col-md-6 end">
              <p>{{ ManageEnd }}</p>
            </div>
          </div>
          <div class="col-md-12">
            <slider
              class="slider-info mt-4"
              v-model="costOfManage"
              :connect="true"
              :range="CofMrange"
              @input="changeCM"
              type="primary"
            >
            </slider>
          </div>
        </div>

        <div class="col-md-2 lineL">
          <strong> 방크기</strong>
          <div class="row">
            <div class="col-md-6">
              <p>{{ roomSizeStart }} ~</p>
            </div>
            <div class="col-md-6 end">
              <p>{{ roomSizeEnd }}</p>
            </div>
          </div>
          <div class="col-md-12">
            <slider
              class="slider-info mt-4"
              v-model="roomSize"
              :connect="true"
              :range="CofMrange"
              @input="changeRoomsize"
              type="primary"
            >
            </slider>
          </div>
        </div>

        <div class="col-md-5 lineL" v-show="!checkedResiCategory[0].checkResi3">
          <strong>층 수</strong>
          <div class="row col-md-12">
            <div class="col-md-2 ml-auto floor">
              <n-checkbox
                value="1층"
                v-model="checkedFloorType[0].floor1"
                @input="changeFloorType"
                >1층</n-checkbox
              >
            </div>
            <div class="col-md-2 ml-auto floor">
              <n-checkbox
                value="2층"
                v-model="checkedFloorType[0].floor2"
                @input="changeFloorType"
                >2층</n-checkbox
              >
            </div>
            <div class="col-md-2 ml-auto floor">
              <n-checkbox
                value="3층"
                v-model="checkedFloorType[0].floor3"
                @input="changeFloorType"
                >3층</n-checkbox
              >
            </div>
            <div class="col-md-2 ml-auto floor">
              <n-checkbox
                value="4층"
                v-model="checkedFloorType[0].floor4"
                @input="changeFloorType"
                >4층</n-checkbox
              >
            </div>
            <div class="col-md-3 ml-auto floor">
              <n-checkbox
                value="5층 이상"
                v-model="checkedFloorType[0].floor5"
                @input="changeFloorType"
                >5층 이상</n-checkbox
              >
            </div>
          </div>
          <strong>방 구조</strong>
          <div class="row col-md-12">
            <div class="col-md-3 ml-auto floor">
              <n-checkbox
                value="1.5룸"
                v-model="checkedRoomStruc[0].struct1"
                @input="changeStruct"
                >주방 분리형(1.5 룸)</n-checkbox
              >
            </div>
            <div class="col-md-3 ml-auto floor">
              <n-checkbox
                value="복층"
                v-model="checkedRoomStruc[0].struct2"
                @input="changeStruct"
                >복층</n-checkbox
              >
            </div>
            <div class="col-md-3 ml-auto floor">
              <n-checkbox
                value="투룸"
                v-model="checkedRoomStruc[0].struct3"
                @input="changeStruct"
                >투룸</n-checkbox
              >
            </div>
            <div class="col-md-3 ml-auto floor">
              <n-checkbox
                value="쓰리룸 이상"
                v-model="checkedRoomStruc[0].struct4"
                @input="changeStruct"
                >쓰리룸 이상</n-checkbox
              >
            </div>
          </div>
        </div>

        <div class="col-md-5 lineL" v-show="checkedResiCategory[0].checkResi3">
          아파트 설정 불가
        </div>

        <div class="col-md-1">
          <a
            class="btn btn-warning btn-round btn-block"
            style="margin-top: 100px"
            @click="setDetailSave"
            >저장</a
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Slider, Checkbox } from "@/components";

export default {
  components: {
    Slider,
    [Checkbox.name]: Checkbox,
  },
  data() {
    return {
      myDetailSave: Object,

      JrangeS: "0",
      JrangeE: "무제한",

      WrangeS: "0",
      WrangeE: "무제한",

      MrangeS: "0",
      MrangeE: "무제한",

      ManageStart: "0",
      ManageEnd: "무제한",

      roomSizeStart: "0",
      roomSizeEnd: "무제한",
      checkedRoomStruc: [
        {
          struct1: false,
          struct2: false,
          struct3: false,
          struct4: false,
        },
        { checkStruc: [] },
      ],
      checkedFloorType: [
        {
          floor1: false,
          floor2: false,
          floor3: false,
          floor4: false,
          floor5: false,
        },
        { checkedFloor: [] },
      ],

      checkedResiType: [
        { TradingJ: false, TradingW: false, TradingM: false },
        { checkType: [] },
      ],

      checkedResiCategory: [
        {
          checkResi0: true,
          checkResi1: true,
          checkResi2: true,
          checkResi3: false,
        },
        { checkCategory: [] },
      ],

      Jrange: {
        min: 0,
        max: 1000,
      },
      Wrange: {
        min: 0,
        max: 350,
      },
      Mrange: {
        min: 0,
        max: 1500,
      },
      CofMrange: {
        min: 0,
        max: 50,
      },
      rangeSlider: [0, 1000],
      dealMonthRange: [0, 350],
      dealTradingRange: [0, 1500],
      costOfManage: [0, 50],
      roomSize: [0, 50],
    };
  },
  methods: {
    changeRoomsize() {
      this.roomSize[0] = parseInt(this.roomSize[0]);
      this.roomSize[1] = parseInt(this.roomSize[1]);

      this.roomSizeStart = this.roomSize[0] + "평";
      this.roomSizeEnd = this.roomSize[1] + "평";
      if (this.roomSize[1] == 50) {
        this.roomSizeEnd = "무제한";
      }
      if (this.roomSize[0] == 0) {
        this.roomSizeStart = "0";
      }
      this.requestDetailSave();
    },
    changeCM() {
      this.costOfManage[0] = parseInt(this.costOfManage[0]);
      this.costOfManage[1] = parseInt(this.costOfManage[1]);

      this.ManageStart = this.costOfManage[0] + "만 원";
      this.ManageEnd = this.costOfManage[1] + "만 원";
      if (this.costOfManage[1] == 50) {
        this.ManageEnd = "무제한";
      }
      if (this.costOfManage[0] == 0) {
        this.ManageStart = "0";
      }
      this.requestDetailSave();
    },
    changeMRange() {
      this.dealTradingRange[0] = parseInt(this.dealTradingRange[0]);
      this.dealTradingRange[1] = parseInt(this.dealTradingRange[1]);

      if (parseInt(this.dealTradingRange[0] / 100) == 0) {
        this.MrangeS = parseInt(this.dealTradingRange[0] % 100) + "00만 원";
      } else if (parseInt(this.rangeSlider[0] % 10) == 0) {
        this.MrangeS = parseInt(this.dealTradingRange[0] / 100) + "억";
      } else {
        this.MrangeS =
          parseInt(this.dealTradingRange[0] / 100) +
          "억" +
          parseInt(this.dealTradingRange[0] % 100) +
          "00만 원";
      }

      if (parseInt(this.dealTradingRange[1] / 100) == 0) {
        this.MrangeE = parseInt(this.dealTradingRange[1] % 100) + "00만 원";
      } else if (parseInt(this.dealTradingRange[1] % 100) == 0) {
        this.MrangeE = parseInt(this.dealTradingRange[1] / 100) + "억";
      } else {
        this.MrangeE =
          parseInt(this.dealTradingRange[1] / 100) +
          "억" +
          parseInt(this.dealTradingRange[1] % 100) +
          "00만 원";
      }
      if (this.dealTradingRange[1] == 1500) {
        this.MrangeE = "무제한";
      }
      if (this.dealTradingRange[0] == 0) {
        this.MrangeS = "0";
      }
      this.requestDetailSave();
    },
    changeWRange() {
      this.dealMonthRange[0] = parseInt(this.dealMonthRange[0]);
      this.dealMonthRange[1] = parseInt(this.dealMonthRange[1]);

      this.WrangeS = this.dealMonthRange[0] + "만 원";
      this.WrangeE = this.dealMonthRange[1] + "만 원";

      if (this.dealMonthRange[1] == 350) {
        this.WrangeE = "무제한";
      }
      if (this.dealMonthRange[0] == 0) {
        this.WrangeS = "0";
      }
      this.setDetailSave();
    },
    chaneJRange() {
      this.rangeSlider[0] = parseInt(this.rangeSlider[0]);
      this.rangeSlider[1] = parseInt(this.rangeSlider[1]);

      if (parseInt(this.rangeSlider[0] / 100) == 0) {
        this.JrangeS = parseInt(this.rangeSlider[0] % 100) + "00만 원";
      } else if (parseInt(this.rangeSlider[0] % 10) == 0) {
        this.JrangeS = parseInt(this.rangeSlider[0] / 100) + "억";
      } else {
        this.JrangeS =
          parseInt(this.rangeSlider[0] / 100) +
          "억" +
          parseInt(this.rangeSlider[0] % 100) +
          "00만 원";
      }

      if (parseInt(this.rangeSlider[1] / 100) == 0) {
        this.JrangeE = parseInt(this.rangeSlider[1] % 100) + "00만 원";
      } else if (parseInt(this.rangeSlider[1] % 100) == 0) {
        this.JrangeE = parseInt(this.rangeSlider[1] / 100) + "억";
      } else {
        this.JrangeE =
          parseInt(this.rangeSlider[1] / 100) +
          "억" +
          parseInt(this.rangeSlider[1] % 100) +
          "00만 원";
      }
      if (this.rangeSlider[1] == 1000) {
        this.JrangeE = "무제한";
      }
      if (this.rangeSlider[0] == 0) {
        this.JrangeS = "0";
      }
      this.requestDetailSave();
    },
    changeResiCategory(data, value) {
      console.log("data : ", data, value);

      if (value == "4") {
        this.checkedResiCategory[0].checkResi0 = false;
        this.checkedResiCategory[0].checkResi1 = false;
        this.checkedResiCategory[0].checkResi2 = false;
      } else {
        this.checkedResiCategory[0].checkResi3 = false;
      }

      this.checkedResiCategory[1].checkCategory.length = 0;

      if (this.checkedResiCategory[0].checkResi0) {
        this.checkedResiCategory[1].checkCategory.push("원룸");
      }
      if (this.checkedResiCategory[0].checkResi1) {
        this.checkedResiCategory[1].checkCategory.push("투.쓰리룸");
      }
      if (this.checkedResiCategory[0].checkResi2) {
        this.checkedResiCategory[1].checkCategory.push("오피스텔");
      }
      if (this.checkedResiCategory[0].checkResi3) {
        this.checkedResiCategory[1].checkCategory.push("아파트");
      }

      console.log(this.checkedResiCategory[1].checkCategory);
      this.requestDetailSave();
    },
    changeResiType(e) {
      this.checkedResiType[1].checkType.length = 0;

      if (this.checkedResiType[0].TradingJ) {
        this.checkedResiType[1].checkType.push("전세");
      }
      if (this.checkedResiType[0].TradingW) {
        this.checkedResiType[1].checkType.push("월세");
      }
      if (this.checkedResiType[0].TradingM) {
        this.checkedResiType[1].checkType.push("매매");
      }
      console.log(this.checkedResiType[1].checkType);
      this.requestDetailSave();
    },
    changeFloorType() {
      this.checkedFloorType[1].checkedFloor.length = 0;

      if (this.checkedFloorType[0].floor1) {
        this.checkedFloorType[1].checkedFloor.push("1층");
      }
      if (this.checkedFloorType[0].floor2) {
        this.checkedFloorType[1].checkedFloor.push("2층");
      }
      if (this.checkedFloorType[0].floor3) {
        this.checkedFloorType[1].checkedFloor.push("3층");
      }
      if (this.checkedFloorType[0].floor4) {
        this.checkedFloorType[1].checkedFloor.push("4층");
      }
      if (this.checkedFloorType[0].floor5) {
        this.checkedFloorType[1].checkedFloor.push("5층 이상");
      }
      this.requestDetailSave();
    },
    changeStruct() {
      this.checkedRoomStruc[1].checkStruc.length = 0;

      if (this.checkedRoomStruc[0].struct1) {
        this.checkedRoomStruc[1].checkStruc.push("주방 분리형 1.5룸");
      }
      if (this.checkedRoomStruc[0].struct2) {
        this.checkedRoomStruc[1].checkStruc.push("복층");
      }
      if (this.checkedRoomStruc[0].struct3) {
        this.checkedRoomStruc[1].checkStruc.push("투룸");
      }
      if (this.checkedRoomStruc[0].struct5) {
        this.checkedRoomStruc[1].checkStruc.push("쓰리룸 이상");
      }
      this.requestDetailSave();
    },
    requestDetailSave() {
      const mySave = {
        residenceCategory: this.checkedResiCategory[1].checkCategory,
        residenceType: this.checkedResiType[1].checkType,
        floorDetail: this.checkedFloorType[1].checkedFloor,
        roomStrucDeail: this.checkedRoomStruc[1].checkStruc,

        startPrice: this.dealTradingRange[0],
        endPrice:
          this.dealTradingRange[1] == 1500 ? 0 : this.dealTradingRange[1],

        startJPrice: this.rangeSlider[0],
        endJPrice: this.rangeSlider[1] == 1000 ? 0 : this.rangeSlider[1],

        startWPrice: this.dealMonthRange[0],
        endWPrice: this.dealMonthRange[1] == 350 ? 0 : this.dealMonthRange[1],

        startManagePrice: this.CofMrange[0],
        endManagePrice: this.CofMrange[1] == 50 ? 0 : this.CofMrange[1],

        startArea: this.roomSize[0],
        endArea: this.roomSize[0] == 50 ? 0 : this.roomSize[1],
      };
      console.log(mySave);
    },
    setDetailSave() {
      const mySave = {
        residenceCategory: this.checkedResiCategory[1].checkCategory,
        residenceType: this.checkedResiType[1].checkType,
        floorDetail: this.checkedFloorType[1].checkedFloor,
        roomStrucDeail: this.checkedRoomStruc[1].checkStruc,

        startPrice: this.dealTradingRange[0],
        endPrice:
          this.dealTradingRange[1] == 1500 ? 0 : this.dealTradingRange[1],

        startJPrice: this.rangeSlider[0],
        endJPrice: this.rangeSlider[1] == 1000 ? 0 : this.rangeSlider[1],

        startWPrice: this.dealMonthRange[0],
        endWPrice: this.dealMonthRange[1] == 350 ? 0 : this.dealMonthRange[1],

        startManagePrice: this.CofMrange[0],
        endManagePrice: this.CofMrange[1] == 50 ? 0 : this.CofMrange[1],

        startArea: this.roomSize[0],
        endArea: this.roomSize[0] == 50 ? 0 : this.roomSize[1],
      };
    }
  },
  props: {},
};
</script>

<style scoped>
.wrap {
  z-index: 999;
  margin-top: 47px;
  background-color: white;
}
.category {
  color: black;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}
.end {
  text-align: right;
}
.floor {
  padding-right: 0px;
  padding-left: 0px;
}
.save {
  text-align: bottom;
}
.lineAll {
  border: 1px solid black;
}
.lineTRL {
  border-top: 1px solid black;
  border-right: 1px solid black;
  border-left: 1px solid black;
}
.lineRL {
  border-right: 1px solid black;
  border-left: 1px solid black;
}
.lineL {
  border-left: 1px solid black;
}
</style>
