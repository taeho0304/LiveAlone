<template>
  <div id="app">
    <link
      href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
      rel="stylesheet"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
      crossorigin="anonymous"
    />
    <main>
      <br />
      <div
        v-if="getResidenceInfo"
        class="data-table"
        actionmode="multiple"
        actions="view,Edit,Delete"
      >
        <!-- <div class="data-table-per-page">
                        <span>Show</span>
                        <select>
                            <option value="10">
                                10
                            </option>
                            <option value="25">
                                25
                            </option>
                            <option value="50">
                                50
                            </option>
                            <option value="100">
                                100
                            </option>
                        </select>
                        <span>entries</span>
                    </div> -->
        <div class="data-table-search-filter">
          <span>search:</span>
          <input type="text" />
        </div>
        <div class="data-table-table">
          <table class="table table-striped table-hover">
            <thead>
              <tr>
                <!-- <th data-sorting="" class="column sortable">
                                          
                                            <div class="column-content">
                                                <span>Name</span><div class="data-table-sorting-icons">
                                                    <div class="icon asc"></div>
                                                    <div class="icon desc"></div>
                                                </div>
                                            </div>
                                        </th>-->

                <th
                  v-for="(header, index) in headers"
                  :key="index"
                  data-sorting=""
                  class="column sortable"
                >
                  <div class="column-content">
                    <span>{{ header.name }}</span>
                    <div class="data-table-sorting-icons"></div>
                  </div>
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(info, index) in getResidenceInfo" :key="index">
                <td
                  v-if="info.sale"
                  style="
                    text-decoration: line-through;
                    background-color: #bdcdd4;
                  "
                >
                  {{ info.id }}
                </td>
                <td v-else>
                  {{ info.id }}
                </td>

                <td
                  v-if="info.sale"
                  style="
                    text-decoration: line-through;
                    background-color: #bdcdd4;
                  "
                >
                  {{ info.residenceCategory.categoryName }}
                </td>
                <td v-else>
                  {{ info.residenceCategory.categoryName }}
                </td>

                <td
                  v-if="info.sale"
                  style="
                    text-decoration: line-through;
                    background-color: #bdcdd4;
                  "
                >
                  {{ info.residenceType.type }}
                </td>
                <td v-else>
                  {{ info.residenceType.type }}
                </td>

                <td
                  v-show="info.sale"
                  v-if="info.residenceType.id === 1"
                  style="background-color: #bdcdd4"
                >
                  거래된 매물
                </td>
                <td v-show="!info.sale" v-if="info.residenceType.id === 1">
                  전세가 : {{ showPrice(info.jeonseCost) }}만원<br />
                  관리비 : {{ showPrice(info.manageCost) }}만원
                </td>

                <td
                  v-show="info.sale"
                  v-if="info.residenceType.id === 2"
                  style="background-color: #bdcdd4"
                >
                  거래된 매물
                </td>
                <td v-show="!info.sale" v-if="info.residenceType.id === 2">
                  매매가 : {{ showPrice(info.cost) }}만원<br />
                  관리비 : {{ showPrice(info.manageCost) }}만원
                </td>

                <td
                  v-show="info.sale"
                  v-if="info.residenceType.id === 3"
                  style="background-color: #bdcdd4"
                >
                  거래된 매물
                </td>
                <td v-show="!info.sale" v-if="info.residenceType.id === 3">
                  보증금 : {{ showPrice(info.deposit) }}만원<br />
                  월세가 : {{ showPrice(info.wolseCost) }}만원<br />
                  관리비 : {{ showPrice(info.manageCost) }}만원
                </td>
                <td
                  v-if="info.sale"
                  style="
                    text-decoration: line-through;
                    background-color:#bdcdd4;
                  "
                >
                  {{ info.dong.gugun.si.siName }}
                  {{ info.dong.gugun.gugunName }} {{ info.dong.dongName }}
                </td>
                <td v-if="!info.sale">
                  {{ info.dong.gugun.si.siName }}
                  {{ info.dong.gugun.gugunName }} {{ info.dong.dongName }}
                </td>

                <td
                  v-if="info.sale"
                  style="
                    text-decoration: line-through;
                    background-color: #bdcdd4;
                  "
                >
                  {{ info.name }}
                </td>
                <td v-if="!info.sale">
                  {{ info.name }}
                </td>

                <td
                  v-if="info.sale"
                  style="
                    text-decoration: line-through;
                    background-color: #bdcdd4;
                  "
                >
                  {{ info.direction }}
                </td>
                <td v-if="!info.sale">
                  {{ info.direction }}
                </td>

                <td
                  v-if="info.sale"
                  style="
                    text-decoration: line-through;
                    background-color: #bdcdd4;
                  "
                >
                  {{ info.area }}평<br />
                  {{ info.myFloor }}/{{ info.buildingFloor }}
                </td>
                <td v-else>
                  {{ info.area }}평<br />
                  {{ info.myFloor }}/{{ info.buildingFloor }}
                </td>
                <!-- <td >
                                        <div v-for="(feat,idx) in info.feature" :key="idx" >
                                          {{feat.featureName}}
                                        </div>
                                    </td> -->
                <td
                  v-if="info.sale"
                  class="action-cell"
                  style="background-color: #bdcdd4"
                >
                  <button class="btn btn-outline-success">
                    <i class="fa fa-eye"></i>
                  </button>
                </td>
                <td v-else>
                  <button
                    class="btn btn-outline-success"
                    @click="clickDetail(info)"
                  >
                    <i class="fa fa-eye"></i>
                  </button>
                </td>

                <td
                  v-if="info.sale"
                  class="action-cell"
                  style="background-color: #bdcdd4"
                >
                  <button class="btn btn-outline-primary">
                    <i data-v-88002dec="" class="fa fa-edit"></i>
                  </button>
                </td>
                <td
                  v-else
                  style="text-decoration: line-through"
                  class="action-cell"
                >
                  <button
                    class="btn btn-outline-primary"
                    @click="clickModify(info, index)"
                  >
                    <i data-v-88002dec="" class="fa fa-edit"></i>
                  </button>
                </td>
                <td
                  v-if="info.sale"
                  data-v-2cc91267=""
                  class="action-cell"
                  style="background-color: #bdcdd4"
                >
                  <button
                    data-v-436722bd=""
                    data-v-2cc91267=""
                    class="btn btn-outline-dark"
                  >
                    <i data-v-436722bd="" class="fa fa-trash"></i>
                  </button>
                </td>
                <td v-else>
                  <button
                    data-v-436722bd=""
                    data-v-2cc91267=""
                    class="btn btn-outline-dark"
                  >
                    <i
                      data-v-436722bd=""
                      class="fa fa-trash"
                      @click="clickDelete(info, index)"
                    ></i>
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <VueModal v-model="showModal" title="매물 수정" modal-class="modal-lg">
          <form novalidate>
            <div class="row collections">
              <div class="col-md-6">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>매물 타입</span></label
                  >
                </h6>
                <fg-input
                  class="input-lg"
                  type="text"
                  disabled
                  v-model="residence.category"
                />
              </div>
              <div class="col-md-6">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>매물 유형</span></label
                  >
                </h6>
                <fg-input
                  class="input-lg"
                  type="text"
                  disabled
                  v-model="residence.type"
                />
              </div>
              <div class="col-md-4">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>보증금</span></label
                  >
                </h6>
                <fg-input
                  :disabled="!this.isMonth"
                  class="input-lg"
                  type="text"
                  v-model="residence.deposit"
                />
              </div>
              <div class="col-md-4">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>{{ residence.type }}가</span></label
                  >
                </h6>
                <fg-input
                  class="input-lg"
                  type="text"
                  v-model="residence.cost"
                />
              </div>
              <div class="col-md-4">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>관리비</span></label
                  >
                </h6>
                <fg-input
                  class="input-lg"
                  type="text"
                  v-model="residence.manage"
                />
              </div>
              <div class="col-md-6">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>이름</span></label
                  >
                </h6>
                <fg-input
                  class="input-lg"
                  type="text"
                  v-model="residence.name"
                />
              </div>
              <div class="col-md-2">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>매물 층수</span></label
                  >
                </h6>
                <fg-input
                  class="input-lg"
                  type="text"
                  disabled
                  v-model="residence.myFloor"
                />
              </div>
              <div class="col-md-2">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>전체 층수</span></label
                  >
                </h6>
                <fg-input
                  class="input-lg"
                  type="text"
                  disabled
                  v-model="residence.buildingFloor"
                />
              </div>
              <div class="col-md-2">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>매물 평수</span></label
                  >
                </h6>
                <fg-input
                  class="input-lg"
                  type="text"
                  disabled
                  v-model="residence.area"
                />
              </div>
              <div class="col-md-6">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>방 구조</span></label
                  >
                </h6>
                <fg-input
                  class="input-lg"
                  type="text"
                  disabled
                  v-model="residence.structureType"
                />
              </div>
              <div class="col-md-6">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>매물방향</span></label
                  >
                </h6>
                <treeselect
                  v-model="residence.direction"
                  :options="directionList"
                  placeholder="동, 서, 남, 북"
                  style="border-radius: 10px; background: #edf2ff"
                />
              </div>

              <div class="col-md-12">
                <h6 class="inputLabel">
                  <label style="margin-bottom: 0px" for="name"
                    ><span>상세정보</span></label
                  >
                </h6>
                <treeselect
                  v-model="residence.feature"
                  :multiple="true"
                  :options="featureList"
                  placeholder="다중 선택 가능"
                  style="border-radius: 10px; background: #edf2ff"
                />
              </div>
            </div>
            <div class="row collections mt-4">
              <div class="col-md-4"></div>
              <div class="col-md-4">
                <div class="text-center">
                  <a
                    @click="clickModifyResi(residence)"
                    class="btn btn-info btn-round btn-lg btn-block"
                    >수정</a
                  >
                </div>
              </div>

              <div class="col-md-4"></div>
            </div>
          </form>
        </VueModal>
        <!-- <div class="data-table-info">
                            Showing 1 to 10 of {{this.getTotalPage}} entries
                        </div>
                        -->
        <div class="data-table-pagination">
          <ul class="pagination">
            <n-pagination
              class="ml-auto mr-auto"
              type="info"
              :value="data.pageNum"
              :pageCount="this.getTotalPage"
              @input="requestNext"
            >
              <span slot="prev">Previous</span>
              <span slot="next">Next</span>
            </n-pagination>
          </ul>
        </div>
      </div>
      <br />
    </main>
  </div>
</template>

<style lang="css">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.modal {
  min-width: 300px;
}
@media (min-width: 480px) {
  .modal.modal-sm {
    max-width: 300px;
  }
}
@media (min-width: 992px) {
  .modal.modal-lg,
  .modal.modal-xl {
    max-width: 800px;
  }
}
@media (min-width: 1200px) {
  .modal.modal-xl {
    max-width: 1140px;
  }
}
.modal-footer {
  padding: 15px 0px 0px 0px;
  border-top: 1px solid #e5e5e5;
  margin-left: -14px;
  margin-right: -14px;
}
</style>


<script>
import { mapActions, mapGetters } from "vuex";
import { Tabs, TabPane, FormGroupInput } from "@/components";
import VueModal from "@kouts/vue-modal";
import "@kouts/vue-modal/dist/vue-modal.css";
import Treeselect from "@riophae/vue-treeselect";
import VueSimpleAlert from "vue-simple-alert";
export default {
  name: "App",
  data() {
    return {
      showModal: false,
      isMonth: true,
      modifyIndex: 0,
      headers: [
        { name: "ID" },
        { name: "매물타입" },
        { name: "거래유형" },
        { name: "매물가격" },
        { name: "주소" },
        { name: "이름" },
        { name: "매물방향" },
        { name: "매물정보" },
        { name: "상세정보" },
        { name: "Edit" },
        { name: "Delete" },
      ],
      data: {
        estateId: localStorage.getItem("accessEstate"),
        pageNum: 1,
      },
      totalPage: null,
      residence: {
        id: null,
        type: null,
        category: null,
        deposit: 0,
        cost: 0,
        manage: 0,
        name: null,
        direction: null,
        feature: [],
        myFloor: null,
        buildingFloor: null,
        area: 0,
        structure: "",
        structureType: "",
      },
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
      directionList: [
        { id: "동", label: "동" },
        { id: "서", label: "서" },
        {
          id: "남",
          label: "남",
          children: [
            { id: "남동", label: "남동" },
            { id: "남서", label: "남서" },
          ],
        },
        {
          id: "북",
          label: "북",
          children: [
            { id: "북동", label: "북동" },
            { id: "북서", label: "북서" },
          ],
        },
      ],
    };
  },
  created() {
    this.init();
  },
  mounted() {
    this.init();
  },
  components: {
    VueModal,
    [FormGroupInput.name]: FormGroupInput,
    Treeselect,
  },
  computed: {
    ...mapGetters("user", ["getUserInfo", "getResidenceInfo", "getTotalPage"]),
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
      "requestGetResi",
      "requestModifyResi",
      "requestResiSaled",
    ]),
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
    clickDelete(info, index) {
      console.log(info);
      this.requestResiSaled(info.id);
      this.getResidenceInfo[index].sale = true;
      VueSimpleAlert.fire({
        title: "거래 완료",
        text: "거래가 완료되었습니다!",
        type: "success",
      });
    },
    clickModifyResi(items) {
      this.getResidenceInfo[this.modifyIndex].feature = [];
      for (var idx in this.residence.feature) {
        var insert = {
          id: idx,
          featureName: this.residence.feature[idx],
        };
        this.getResidenceInfo[this.modifyIndex].feature.push(insert);
      }
      console.log(items);
      if (items.type === "매매") {
        let modify = {
          residenceId: this.residence.id,
          cost: parseInt(this.residence.cost),
          manageCost: parseInt(this.residence.manage),
          feature: this.residence.feature,
          name: this.residence.name,
          jeonseCost: 0,
          wolseCost: 0,
          deposit: 0,
        };

        this.getResidenceInfo[this.modifyIndex].manageCost = parseInt(
          this.residence.manage
        );
        this.getResidenceInfo[this.modifyIndex].cost = parseInt(
          this.residence.cost
        );
        this.getResidenceInfo[this.modifyIndex].name = this.residence.name;

        this.requestModifyResi(modify);
      } else if (items.type === "전세") {
        let modify = {
          residenceId: this.residence.id,
          jeonseCost: parseInt(this.residence.cost),
          manageCost: parseInt(this.residence.manage),
          feature: this.residence.feature,
          name: this.residence.name,
          cost: 0,
          wolseCost: 0,
          deposit: 0,
        };
        this.getResidenceInfo[this.modifyIndex].manageCost = parseInt(
          this.residence.manage
        );
        this.getResidenceInfo[this.modifyIndex].jeonseCost = parseInt(
          this.residence.cost
        );
        this.getResidenceInfo[this.modifyIndex].name = this.residence.name;
        this.requestModifyResi(modify);
      } else {
        let modify = {
          residenceId: this.residence.id,
          deposit: parseInt(this.residence.deposit),
          wolseCost: parseInt(this.residence.cost),
          manageCost: parseInt(this.residence.manage),
          feature: this.residence.feature,
          name: this.residence.name,
          cost: 0,
          jeonseCost: 0,
        };
        this.getResidenceInfo[this.modifyIndex].manageCost = parseInt(
          this.residence.manage
        );
        this.getResidenceInfo[this.modifyIndex].deposit = parseInt(
          this.residence.deposit
        );
        this.getResidenceInfo[this.modifyIndex].wolseCost = parseInt(
          this.residence.cost
        );
        this.getResidenceInfo[this.modifyIndex].name = this.residence.name;

        this.requestModifyResi(modify);
      }

      this.showModal = false;

      this.$fire({
        type: "success",
        title: "수정 완료",
        text: "매물정보가 수정되었습니다.",

        // footer: '<a href="">Why do I have this issue?</a>'
      });
    },
    requestNext(itemnum) {
      console.log(itemnum);
      this.data.pageNum = itemnum;
      this.requestGetResi(this.data);
    },
    onChangePage(pageOfItems) {
      // update page of items
      this.pageOfItems = pageOfItems;
    },
    init() {
      this.requestGetResi(this.data);
    },
    clickDetail(data) {
      var detail = "<strong>옵션</strong><br>";
      for (var idx in data.feature) {
        detail += data.feature[idx].featureName + "<br>";
        console.log(data.feature[idx].featureName);
      }

      console.log(detail);
      this.$fire({
        title: "상세정보",
        html: `${detail}`,
      });
    },
    clickModify(info, index) {
      this.modifyIndex = index;
      console.log(info);
      this.residence.id = info.id;
      this.showModal = true;
      this.residence.category = info.residenceCategory.categoryName;
      this.residence.type = info.residenceType.type;
      this.residence.name = info.name;
      this.residence.myFloor = info.myFloor;
      this.residence.buildingFloor = info.buildingFloor;
      this.residence.area = info.area + "평";
      if (info.residenceType.type === "월세") {
        this.isMonth = true;
        this.residence.deposit = info.deposit;
        this.residence.cost = info.wolseCost;
        this.residence.manage = info.manageCost;
      } else if (info.residenceType.type === "전세") {
        this.isMonth = false;
        this.residence.cost = info.jeonseCost;
        this.residence.manage = info.manageCost;
      } else if (info.residenceType.type === "매매") {
        this.isMonth = false;
        this.residence.cost = info.cost;
        this.residence.manage = info.manageCost;
      }

      if (info.residenceCategory.categoryName === "투룸") {
        this.residence.structureType = "투룸";
        this.residence.structure = info.structure;
      } else if (info.residenceCategory.categoryName === "쓰리룸 이상") {
        this.residence.structureType = "쓰리룸 이상";
        this.residence.structure = info.structure;
      } else {
        if (info.structure === "분리형") {
          this.residence.structureType = "주방 분리형(1.5룸)";
          this.residence.structure = info.structure;
        } else {
          this.residence.structureType = info.structure;
          this.residence.structure = info.structure;
        }
      }
      console.log(info.direction);
      for (var idx in info.feature) {
        this.residence.feature[idx] = info.feature[idx].featureName;
      }
      this.residence.direction = info.direction;
      this.residence.name = info.name;
    },
    clickClose() {
      this.showModal = false;
    },
    handleAction(actionName, data) {
      console.log(actionName, data);
      window.alert("check out the console to see the logs");
    },
  },
};
</script>
