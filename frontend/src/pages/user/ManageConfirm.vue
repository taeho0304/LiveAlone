<template>
  <div id="app">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <main>
            <br>
                <div v-if="getResidenceInfo" class="data-table" actionmode="multiple" actions="view,Edit,Delete">
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
                        <input type="text"></div>
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
                                      
                                    <th v-for="(header,index) in headers" :key="index" data-sorting="" class="column sortable">
                                            <div class="column-content">
                                                <span>{{header.name}}</span>
                                                <div class="data-table-sorting-icons">
                                                    
                                                </div>
                                            </div>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                  <tr v-for="(info, index) in getResidenceInfo" :key="index">
                                    <td>
                                        {{info.id}}
                                    </td>
                                    <td>
                                        {{info.residenceCategory.categoryName}}
                                    </td>
                                    <td>
                                        {{info.residenceType.type}}
                                    </td>
                                    <td v-if="info.residenceType.id === 1">
                                        전세가 : {{info.jeonseCost}}<br/>
                                        관리비 : {{info.manageCost}}
                                    </td>
                                    <td v-if="info.residenceType.id === 2">
                                        매매가 : {{info.cost}}<br/>
                                        관리비 : {{info.manageCost}}
                                    </td>
                                    <td v-if="info.residenceType.id === 3">
                                        보증금 : {{info.deposit}}<br/>
                                        월세가 : {{info.wolseCost}}<br/>
                                        관리비 : {{info.manageCost}}
                                    </td>
                                    <td>
                                        {{info.dong.gugun.si.siName}} {{info.dong.gugun.gugunName}} {{info.dong.dongName}}
                                    </td>
                                    <td>
                                        {{info.name}}
                                    </td>
                                    <td>
                                        {{info.direction}}
                                    </td>
                                    <td>
                                        {{info.area}}평<br>
                                        {{info.myFloor}}/{{info.buildingFloor}}
                                    </td>
                                    <!-- <td >
                                        <div v-for="(feat,idx) in info.feature" :key="idx" >
                                          {{feat.featureName}}
                                        </div>
                                    </td> -->
                                    <td class="action-cell">
                                        <button class="btn btn-outline-success" @click="clickDetail(info)">
                                            <i class="fa fa-eye"></i>
                                        </button>
                                    </td>
                                    <td class="action-cell">
                                        <button class="btn btn-outline-primary" @click="clickModify()">
                                            <i data-v-88002dec="" class="fa fa-edit"></i>
                                        </button>
                                    </td>
                                    <td data-v-2cc91267="" class="action-cell">
                                        <button data-v-436722bd="" data-v-2cc91267="" class="btn btn-outline-dark">
                                            <i data-v-436722bd="" class="fa fa-trash"></i>
                                        </button>
                                    </td>
                                  </tr>
                                </tbody>
                          </table>
                        </div>
                        <VueModal v-model="showModal"
                          title="매물 가격 수정"
                          modal-class="modal-lg"
                        >
                          <form novalidate>
                          <div class="row collections">

                            <div style="padding-left: 5px; padding-right: 5px" class="col-md-6">
                              <h6 class="inputLabel">
                                <label style="margin-bottom: 0px" for="name"><span>부동산 이름</span></label>
                              </h6>
                              <fg-input
                              class="input-lg"
                              disabled
                              type="text"
                              v-model="getUserInfo.user.estateInfo.name"
                              name="userName"
                            />
                            </div>
                            <div style="padding-left: 5px; padding-right: 5px" class="col-md-6">
                              <h6 class="inputLabel">
                                <label style="margin-bottom: 0px" for="name"><span>부동산 이름</span></label>
                              </h6>
                              <fg-input
                              class="input-lg"
                              disabled
                              type="text"
                              v-model="getUserInfo.user.estateInfo.name"
                              name="userName"
                            />
                            </div>
                          </div>
                        </form>
                        </VueModal>
                        <div class="data-table-info">
                            Showing 1 to 10 of {{this.getTotalPage}} entries
                        </div>
                        <!-- <div class="data-table-pagination">
                            <div class="pagination-search">
                                <span>Go to page</span>
                                <input max="31" min="1" type="number" class="form-control">
                                    <button class="btn btn-primary">
                                        GO
                                    </button>
                                </div>
                                <ul class="pagination">
                                    <li class="page-item disabled">
                                        <span class="page-link">
                                            Previous
                                        </span>
                                    </li>
                                    <li class="page-item active">
                                        <span class="page-link">
                                            1
                                        </span>
                                    </li>
                                    <li class="page-item">
                                        <span class="page-link">
                                            2
                                        </span>
                                    </li>
                                    <li class="page-item">
                                        <span class="page-link">
                                            3
                                        </span>
                                    </li>
                                    <li class="page-item">
                                        <span class="page-link">
                                            4
                                        </span>
                                    </li>
                                    <li class="page-item">
                                        <span class="page-link">
                                            5
                                        </span>
                                    </li>
                                    <li class="page-item disabled">
                                        <span class="page-link">
                                            ...
                                        </span>
                                    </li>
                                    <li class="page-item">
                                        <span class="page-link">
                                            31
                                        </span>
                                    </li>
                                    <li class="page-item">
                                        <span class="page-link">
                                            Next
                                        </span>
                                    </li>
                                </ul>
                            </div> -->

                        </div>
                        <br></main>
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
import { Tabs, TabPane, FormGroupInput, Button, DropDown } from "@/components";
import VueModal from '@kouts/vue-modal'
import '@kouts/vue-modal/dist/vue-modal.css'
export default {
  name: "App",
  data(){
    return{
      showModal: false,
      headers:[
        {name: "ID"},
        {name: "매물타입"},
        {name: "거래유형"},
        {name: "매물가격"},
        {name: "주소"},
        {name: "이름"},
        {name: "매물방향"},
        {name: "매물정보"},
        {name: "상세정보"},
        {name: "Edit"},
        {name: "Delete"},
      ],
      data:{
        estateId: localStorage.getItem("accessEstate"),
        pageNum: 1
      },
      totalPage:null,
    }
  },
  created(){
    this.init();
  },
  mounted(){
    //this.init();
  },
  components:{
    VueModal,
    [FormGroupInput.name]: FormGroupInput,
  },
  computed: {
    ...mapGetters("user", ["getUserInfo","getResidenceInfo","getTotalPage"]),
  },

  methods: {
    ...mapActions("user", [
      "requestUserInfo",
      "requestDelete",
      "requestModify",
      "requestRegistResi",
      "requestGetResi",
    ]),
    init(){
      this.requestGetResi(this.data)
    },
    clickDetail(data){
      var detail="";
      for(var idx in data.feature){
        detail+=data.feature[idx].featureName+"<br>"
        console.log(data.feature[idx].featureName)
      }
      console.log(detail)
      this.$fire({
        title: "상세정보",
        html: `${detail}`,

        // footer: '<a href="">Why do I have this issue?</a>'
      });
    },
    clickModify(){
      this.showModal=true;
    },
    clickClose(){
      this.showModal=false;
    },
    handleAction(actionName, data) {
      console.log(actionName, data);
      window.alert("check out the console to see the logs");
    }
  }
};
</script>
