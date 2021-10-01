<template>
    <div>

        <div class="section">
            <div class="container">


                <div style="margin-top:15px;">
                    <tabs type="primary" tabContentClasses="tab-subcategories" square centered class="nav-align-center">
                        <tab-pane>
                            <span slot="label">
                                <i class="now-ui-icons business_badge"></i> Profile
                            </span>
                            <div v-if="getUserInfo" class="col-md-10 ml-auto mr-auto" style="margin-top:0px">
                                <div class="row collections">
                                    <div class="col-md-1">
                                    </div>
                                    <div class="col-md-10">
                                        <fg-input class="input-lg" disabled type="text" placeholder="이름" v-model="getUserInfo.user.estateInfo.name" name="userName">
                                        </fg-input>
                                        <fg-input class="input-lg" disabled type="text" placeholder="아이디" v-model="getUserInfo.user.estateInfo.address" name="userId">
                                        </fg-input>
                                        <fg-input class="input-lg" disabled type="text" placeholder="이메일" v-model="getUserInfo.user.estateInfo.representative" name="email">
                                        </fg-input>
                                    </div>
                                    <div class="col-md-1">
                                    </div>
                                </div>
                                <hr style="margin-top:0;">
                                <div class="row collections">
                                    <div class="col-md-1">
                                    </div>
                                    <div class="col-md-10">
                                        <fg-input class="no-border input-lg" v-bind:disabled="!isClick" type="text" addon-left-icon="now-ui-icons users_circle-08" placeholder="이름" v-model="getUserInfo.user.userName" name="userName">
                                        </fg-input>
                                        <fg-input class="no-border input-lg" v-bind:disabled="!isClick" type="text" addon-left-icon="now-ui-icons users_circle-08" placeholder="아이디" v-model="getUserInfo.user.userId" name="userId">
                                        </fg-input>
                                        <fg-input class="no-border input-lg" v-bind:disabled="!isClick" type="text" addon-left-icon="now-ui-icons users_circle-08" placeholder="이메일" v-model="getUserInfo.user.userEmail" name="email">
                                        </fg-input>
                                        <fg-input class="no-border input-lg" v-bind:disabled="!isClick" type="text" addon-left-icon="now-ui-icons users_circle-08" v-model="getUserInfo.user.userPhone" name="phone">
                                        </fg-input>
                                    </div>
                                    <div class="col-md-1">
                                    </div>
                                    <div class="col-md-3">
                                    </div>
                                    <div class="col-md-3">
                                        <div style="margin-top:-10px;" class="text-center">
                                            <a v-if="!isClick" @click="click()" class="btn btn-info btn-round btn-lg btn-block">수정</a>
                                            <a v-if="isClick" @click="clickModify()" class="btn btn-info btn-round btn-lg btn-block">확인</a>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div style="margin-top:-10px;" class="text-center">
                                            <a class="btn btn-info btn-round btn-lg btn-block">탈퇴</a>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                    </div>

                                </div>
                            </div>

                        </tab-pane>

                        <tab-pane>
                            <!--등록된 매물-->
                            <span slot="label">
                                <i class="now-ui-icons shopping_shop"></i> Registed
                            </span>
                            <template>
                                <!-- <v-app id="inspire">
                                  <v-toolbar dark color="primary" fixed>
                                    <v-toolbar-title class="white--text">Nutrition</v-toolbar-title>
                                    <v-spacer></v-spacer>
                                    <v-text-field v-model="search" append-icon="search" label="Search" single-line hide-details></v-text-field>
                                    <v-menu offset-y :nudge-left="170" :close-on-content-click="false">
                                        <v-btn icon slot="activator">
                                            <v-icon>more_vert</v-icon>
                                          </v-btn>
                                        <v-list>
                                          <v-list-tile  v-for="(item) in headers"  :key="item.value"   @click="changeSort(item.value)">
                                            <v-list-tile-title>{{ item.text }}<v-icon v-if="pagination.sortBy === item.value">{{pagination.descending ? 'arrow_downward':'arrow_upward'}}</v-icon></v-list-tile-title>
                                          </v-list-tile>
                                        </v-list>
                                      </v-menu>
                                  </v-toolbar>
                                      <v-layout v-resize="onResize" column style="padding-top:56px">
                                        <v-data-table :headers="headers" :items="desserts" :search="search" :pagination.sync="pagination" :hide-headers="isMobile" :class="{mobile: isMobile}">
                                          <template slot="items" slot-scope="props">
                                            <tr v-if="!isMobile">
                                              <td>{{ props.item.name }}</td>
                                              <td class="text-xs-right">{{ props.item.calories }}</td>
                                              <td class="text-xs-right">{{ props.item.fat }}</td>
                                              <td class="text-xs-right">{{ props.item.carbs }}</td>
                                              <td class="text-xs-right">{{ props.item.protein }}</td>
                                              <td class="text-xs-right">{{ props.item.iron }}</td>
                                            </tr>
                                            <tr v-else>
                                              <td>
                                                <ul class="flex-content">
                                                  <li class="flex-item" data-label="Name">{{ props.item.name }}</li>
                                                  <li class="flex-item" data-label="Calories">{{ props.item.calories }}</li>
                                                  <li class="flex-item" data-label="Fat (g)">{{ props.item.fat }}</li>
                                                  <li class="flex-item" data-label="Carbs (g)">{{ props.item.carbs }}</li>
                                                  <li class="flex-item" data-label="Protein (g)">{{ props.item.protein }}</li>
                                                  <li class="flex-item" data-label="Iron (%)">{{ props.item.iron }}</li>
                                                </ul>
                                              </td>
                                            </tr>
                                          </template>
                                          <v-alert slot="no-results" :value="true" color="error" icon="warning">
                                            Your search for "{{ search }}" found no results.
                                          </v-alert>
                                        </v-data-table>
                                      </v-layout>
                                </v-app> -->
                            </template>

                        </tab-pane>

                        <tab-pane>
                            <!--검색 필터 저장-->
                            <span slot="label">
                                <i class="now-ui-icons design_bullet-list-67"></i> Regist
                            </span>
                            <div v-if="getUserInfo" class="col-md-10 ml-auto mr-auto" style="margin-top:0px">
                                <div class="row collections">
                                    
                                    <div class="col-md-12 inputLabel">
                                        <label for="name"><span>부동산 정보</span></label>
                                        <hr style="margin-top:0;">
                                    </div>
                                    <h6 class="col-md-12 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>부동산 이름</span></label>
                                    </h6>
                                        <fg-input style="padding-left:5px; padding-right:5px;" class="col-md-12 input-lg" disabled type="text" v-model="getUserInfo.user.estateInfo.name" name="userName">
                                        </fg-input>
                                    <h6 class="col-md-12 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>부동산 위치</span></label>
                                    </h6>
                                        <fg-input style="padding-left:5px; padding-right:5px;" class="col-md-12 input-lg" disabled type="text" v-model="getUserInfo.user.estateInfo.address" name="userId">
                                        </fg-input>
                                    <h6 class="col-md-12 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>중개사 번호</span></label>
                                    </h6>
                                        <fg-input style="padding-left:5px; padding-right:5px;" class="col-md-12 input-lg" disabled type="text" v-model="getUserInfo.user.userPhone" name="email">
                                        </fg-input>
                                    <h6 class="col-md-12 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>중개사 이름</span></label>
                                    </h6>
                                        <fg-input style="padding-left:5px; padding-right:5px;" class="col-md-12 input-lg" disabled type="text" v-model="getUserInfo.user.userName" name="email">
                                        </fg-input>
                                    
                                </div>
                                <div class="row collections">
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-12">
                                        <label for="name"><span>매물 정보</span></label>
                                        <hr style="margin-top:0;">
                                    </div>
                                    <h6 class="col-md-2 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>매물 유형</span></label>
                                    </h6>
                                    <h6 class="col-md-2 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>거래 유형</span></label>
                                    </h6>
                                    <h6 class="col-md-2 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>가격</span></label>
                                    </h6>
                                    <h6 class="col-md-3 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>보증금</span></label>
                                    </h6>
                                    <h6 class="col-md-3 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>관리비</span></label>
                                    </h6>
                                    
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-2">
                                      <drop-down style="border-radius: 10px;">
                                        <n-button
                                          slot="title"
                                          class="dropdown-toggle"
                                          data-toggle="dropdown"
                                          block
                                          style="background-color: #edf2ff; border: 1px solid #ced4da; padding-top:15px; padding-bottom:15px; margin-top:0px; border-radius: 10px;"
                                        >
                                          {{categoryName}}
                                        </n-button>
                                        <ul class="dropdown-scrollbar">
                                          <a
                                            v-for="(categories, index) in categoryList"
                                            :key="index"
                                            class="dropdown-item"
                                            @click="clickCategory(categories, index)"
                                            >{{ categories.name }}
                                          </a>
                                        </ul>
                                      </drop-down>
                                    </div>
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-2">
                                        <drop-down style="border-radius: 10px;">
                                        <n-button
                                          slot="title"
                                          class="dropdown-toggle"
                                          data-toggle="dropdown"
                                          block
                                          style="background-color: #edf2ff; border: 1px solid #ced4da; padding-top:15px; padding-bottom:15px; margin-top:0px; border-radius: 10px;"
                                        >
                                          {{typeName}}
                                        </n-button>
                                        <ul class="dropdown-scrollbar">
                                          <a
                                            v-for="(types, index) in typeList"
                                            :key="index"
                                            class="dropdown-item"
                                            @click="clickType(types, index)"
                                            >{{ types.name }}
                                          </a>
                                        </ul>
                                      </drop-down>
                                    </div>
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-2">
                                        <fg-input class="input-lg" placeholder="가격" v-model="pay" type="text" >
                                        </fg-input>
                                    </div>
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-3">
                                        <fg-input :disabled="this.isMonthType" v-model="residence.deposit" class="input-lg" type="text"  placeholder="보증금">
                                        </fg-input>
                                    </div>
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-3">
                                        <fg-input class="input-lg" v-model="residence.manageCost" type="text" placeholder="관리비">
                                        </fg-input>
                                    </div>
                                </div>
                                
                                <div class="row collections">
                                    <h6 class="col-md-6 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>주소</span></label>
                                    </h6>
                                    <h6 class="col-md-3 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>매물 이름</span></label>
                                    </h6>
                                    <h6 class="col-md-3 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>방 구조</span></label>
                                    </h6>
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-6">
                                        <fg-input class="input-lg" :value="this.addr1"  type="text" placeholder="Zip Code" @click="findAddr()">
                                        </fg-input>
                                    </div>
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-3">
                                        <fg-input class="input-lg" v-model="residence.name" type="text" placeholder="매물 이름" name="userId">
                                        </fg-input>
                                    </div>
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-3">
                                        <drop-down style="border-radius: 10px;">
                                        <n-button
                                          slot="title"
                                          class="dropdown-toggle"
                                          data-toggle="dropdown"
                                          block
                                          :disabled="this.isStructureType"
                                          style="background-color: #edf2ff; border: 1px solid #ced4da; padding-top:15px; padding-bottom:15px; margin-top:0px; border-radius: 10px;"
                                        >
                                          {{structureName}}
                                        </n-button>
                                        <ul class="dropdown-scrollbar" v-for="(structures, index) in structureList"
                                            :key="index"
                                            v-show="structures.valid"
                                            :value="structures.id"
                                            @click="clickStructure(structures, index)"> 
                                          <a
                                            class="dropdown-item"
                                            >{{ structures.name }}
                                          </a>
                                        </ul>
                                      </drop-down>
                                    </div>
                                    <h6 class="col-md-3 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>매물 층수</span></label>
                                    </h6>
                                    <h6 class="col-md-3 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>전체 층수</span></label>
                                    </h6>
                                    <h6 class="col-md-3 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>매물 평수</span></label>
                                    </h6>
                                    <h6 class="col-md-3 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>매물 방향</span></label>
                                    </h6>
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-3">
                                        <fg-input class="input-lg" v-model="residence.myFloor" type="text" placeholder="매물 층수" name="userId">
                                        </fg-input>
                                    </div>                                    
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-3">
                                        <fg-input class="input-lg" v-model="residence.buildingFloor" type="text" placeholder="전체 층수" name="userId">
                                        </fg-input>
                                    </div>                                    
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-3">
                                        <fg-input class="input-lg" v-model="residence.area" type="text" placeholder="매물 평수" name="userId">
                                        </fg-input>
                                    </div>
                                    <div style=" padding-left:5px; padding-right:5px; margin-bottom: 10px; height: 45px;" class="col-md-3">
                                        <treeselect
                                          placeholder="매물 방향"
                                          v-model="residence.direction"
                                          :options="directionList"
                                          style="border-radius: 10px; background: #edf2ff;"
                                          />
                                    </div>
                                    <h6 class="col-md-3 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>매물 옵션</span></label>
                                    </h6>
                                    <div style=" padding-left:5px; padding-right:5px; margin-bottom: 10px; height: 45px;" class="col-md-12">
                                        <treeselect
                                          v-model="residence.feature"
                                          :multiple="true"
                                          :options="featureList"
                                          placeholder="다중 선택 가능"
                                          style="border-radius: 10px; background: #edf2ff;"
                                          />
                                    </div>
                                    <h6 class="col-md-12 inputLabel">
                                        <label style="margin-bottom: 0px;" for="name"><span>방 이미지</span></label>
                                    </h6>
                                    <div style="padding-left:5px; padding-right:5px;" class="col-md-12">
                                      <div id="my-strictly-unique-vue-upload-multiple-image" style=" display: flex; justify-content: center;">
                                        <vue-upload-multiple-image
                                          @upload-success="uploadImageSuccess"
                                          @before-remove="beforeRemove"
                                          @edit-image="editImage"
                                          @data-change="dataChange"
                                          ></vue-upload-multiple-image>
                                      </div>
                                    </div>
                                </div>
                                
                                <div class="row collections mt-4">
                                    <div class="col-md-3">
                                    </div>
                                    <div class="col-md-6">
                                        <div class="text-center">
                                            <a @click="clickRegistResi()" class="btn btn-info btn-round btn-lg btn-block">등록</a>
                                        </div>
                                    </div>
                              
                                    <div class="col-md-3">
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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f52d6b75a8a65ca935ff31e1ba7eace5&libraries=services,clusterer,drawing"/>
<script src="https://unpkg.com/vue-upload-multiple-image@1.1.6/dist/vue-upload-multiple-image.js"></script>
<script src="path/to/vue.js"></script>
<script src="path/to/dist/vue-upload-multiple-image.js"></script>
<script>
    import {
        Tabs,
        TabPane,
        FormGroupInput,
        Button,
        DropDown,
    } from '@/components';
    import {
        mapActions,
        mapState,
        mapGetters
    } from 'vuex';
    import Treeselect from '@riophae/vue-treeselect'
    import axios from 'axios'
    import VueUploadMultipleImage from 'vue-upload-multiple-image-korean'
    export default {
        name: 'manage',
        bodyClass: 'manage-page',
        components: {
            Tabs,
            TabPane,
            [FormGroupInput.name]: FormGroupInput,
            [Button.name]: Button,
            DropDown,
            Treeselect,
            VueUploadMultipleImage,

        },
        data: function() {
            return {
              pay:"",
              images:[],
              files:[],
              direct:[],
              featureList: [
                {id: "보증금조절가능", label:"보증금조절가능"},
                {id: "주차가능", label:"주차가능"},
                {id: "엘리베이터", label:"엘리베이터"},
                {id: "단기가능", label:"단기가능"},
                {id: "빌트인", label:"빌트인"},
                {id: "발코니", label:"발코니"},
                {id: "풀옵션", label:"풀옵션"},
                {id: "보안/안전", label:"보안/안전"},
                {id: "옵션 없음", label:"옵션 없음"},
              ],
              categoryList:[
                {id: 1, name:"원룸"},
                {id: 2, name:"투룸"},
                {id: 3, name:"쓰리룸"},
                {id: 4, name:"오피스텔"},
                {id: 5, name:"아파트"},
              ],
              typeList:[
                {id: 1, name:"매매"},
                {id: 2, name:"전세"},
                {id: 3, name:"월세"},
              ],
              structureList:[
                {id: "분리형", name:"주방 분리형(1.5룸)", valid:true,},
                {id: "복층", name:"복층", valid:true,},
                {id: "", name:"투룸", valid:true,},
                {id: "", name:"쓰리룸 이상", valid:true,},
              ],
              directionList: [ {
                id: '동',
                label: '동',
              }, {
                id: '서',
                label: '서',
              }, {
                id: '남',
                label: '남',
                children: [ {
                  id: '남동',
                  label: '남동',
                },{
                  id: '남서',
                  label: '남서',
                } ],
              },{
                id: '북',
                label: '북',
                children: [ {
                  id: '북동',
                  label: '북동',
                },{
                  id: '북서',
                  label: '북서',
                } ],
              } ],




              pagination: {
                  sortBy: 'name'
                },
                selected: [],
                search: '',
                isMobile: false,
                zip: '', 
                addr1: '', 
                addr2: '',
                isMonthType: true,
                isStructureType: false,
                ////////////////컴포넌트 초기 정의///////////////////
                categoryName:'매물 유형',
                typeName:'거래 유형',
                structureName:'방 구조',
                directionName:'매물 방향',
                //////////////////////////////////////
                residence:{
                  residenceCategory:0,//매물유형
                  residenceType:0,//거래유형
                  cost:"",//매매가격
                  jeonseCost:"",//전세가격
                  wolseCost:"",//월세가격
                  deposit:"",//보증금
                  manageCost:"",//관리비
                  dong:"",
                  lat:0,
                  lon:0,
                  name:"",
                  structure:"",
                  myFloor:"",
                  buildingFloor:"",
                  area:"",//평수
                  direction:"",//매물 방향
                  feature:[],
                  thumbnails:[],
                  estateId:"",
                },
                ///////////////////////////////////////////////////
                headers: [{
                    text: 'Dessert (100g serving)',
                    align: 'left',
                    value: 'name'
                  },
                  {
                    text: 'Calories',
                    value: 'calories'
                  },
                  {
                    text: 'Fat (g)',
                    value: 'fat'
                  },
                  {
                    text: 'Carbs (g)',
                    value: 'carbs'
                  },
                  {
                    text: 'Protein (g)',
                    value: 'protein'
                  },
                  {
                    text: 'Iron (%)',
                    value: 'iron'
                  }
                ],
                desserts: [{
                    value: false,
                    name: 'Frozen Yogurt',
                    calories: 159,
                    fat: 6.0,
                    carbs: 24,
                    protein: 4.0,
                    iron: '1%'
                  },
                  {
                    value: false,
                    name: 'Ice cream sandwich',
                    calories: 237,
                    fat: 9.0,
                    carbs: 37,
                    protein: 4.3,
                    iron: '1%'
                  },
                  {
                    value: false,
                    name: 'Eclair',
                    calories: 262,
                    fat: 16.0,
                    carbs: 23,
                    protein: 6.0,
                    iron: '7%'
                  },
                  {
                    value: false,
                    name: 'Cupcake',
                    calories: 305,
                    fat: 3.7,
                    carbs: 67,
                    protein: 4.3,
                    iron: '8%'
                  },
                  {
                    value: false,
                    name: 'Gingerbread',
                    calories: 356,
                    fat: 16.0,
                    carbs: 49,
                    protein: 3.9,
                    iron: '16%'
                  },
                  {
                    value: false,
                    name: 'Jelly bean',
                    calories: 375,
                    fat: 0.0,
                    carbs: 94,
                    protein: 0.0,
                    iron: '0%'
                  },
                  {
                    value: false,
                    name: 'Lollipop',
                    calories: 392,
                    fat: 0.2,
                    carbs: 98,
                    protein: 0,
                    iron: '2%'
                  },
                  {
                    value: false,
                    name: 'Honeycomb',
                    calories: 408,
                    fat: 3.2,
                    carbs: 87,
                    protein: 6.5,
                    iron: '45%'
                  },
                  {
                    value: false,
                    name: 'Donut',
                    calories: 452,
                    fat: 25.0,
                    carbs: 51,
                    protein: 4.9,
                    iron: '22%'
                  },
                  {
                    value: false,
                    name: 'KitKat',
                    calories: 518,
                    fat: 26.0,
                    carbs: 65,
                    protein: 7,
                    iron: '6%'
                  }
                ],
                items: ["id", "name", "LastName"],
                total: 100,
                columns: [""],
                actions: [""],
                settings: {
                    "dots": true,
                    "infinite": true,
                    "initialSlide": 2,
                    "speed": 500,
                    "slidesToShow": 4,
                    "slidesToScroll": 1,
                    "swipeToSlide": true
                },
                isClick: false,
                user: {
                    userName: "",
                    userId: "",
                    userPass: "",
                    userPhone: "",
                    userEmail: "",
                },

            }
        },
        computed: {
            ...mapGetters('user', ['getUserInfo']),
        },
        methods: {
            ...mapActions('user', ['requestUserInfo', 'requestDelete', 'requestModify','requestRegistResi']),
          clickRegistResi(){
            this.residence.estateId=this.getUserInfo.user.estateInfo.id;
            if(this.residence.residenceType=="0"){
              this.residence.cost=this.pay;
              this.residence.deposit=0;
              this.residence.jeonseCost=0;
              this.residence.wolseCost=0;
            }
            else if(this.residence.residenceType=="1"){
              this.residence.jeonseCost=this.pay;
              this.residence.deposit=0;
              this.residence.cost=0;
              this.residence.wolseCost=0;
            }
            else if(this.residence.residenceType=="2"){
              this.residence.wolseCost=this.pay;
              this.residence.cost=0;
              this.residence.jeonseCost=0;
            }
            
            console.log(this.residence);
            this.requestRegistResi(this.residence);

          },
          uploadImageSuccess(formData, index, fileList) {
            console.log('data', formData, index, fileList)
            this.residence.thumbnails=fileList;
            console.log("얼레 ",this.residence.thumbnails)
          },
          beforeRemove (index, done, fileList) {
            console.log('index', index, fileList)
            this.$confirm("이미지를 삭제하겠습니다.", "Error", "error", {
              reverseButtons: true
            })
              .then((r) => {
                if(r==true){
                  done();
                }
              })
              .catch(() => {});
          },
          editImage (formData, index, fileList) {
            console.log('edit data', formData, index, fileList)
          },
          dataChange (data) {
            console.log(data)
          },
          clickStructure(items, index){
            this.structureName=items.name;
            this.residence.structure=items.id;
          },
          clickCategory(items, index){
            this.categoryName=items.name;
            this.residence.residenceCategory=index;
            if(items.name=="원룸" || items.name=="오피스텔"){
              this.structureList[0].valid=true;
              this.structureList[1].valid=true;
              this.structureList[2].valid=false;
              this.structureList[3].valid=false;
              this.isStructureType=false;
            }else if(items.name=="투룸" || items.name=="쓰리룸"){
              this.structureList[0].valid=false;
              this.structureList[1].valid=false;
              this.structureList[2].valid=true;
              this.structureList[3].valid=true;
              this.isStructureType=false;
            }else if(items.name=="아파트"){
              this.isStructureType=true;
            }
          },
          clickType(items, index){
            if(items.name=="월세"){
              this.isMonthType=false;
            }else{
              this.isMonthType=true;
            }
            this.typeName=items.name;
            this.residence.residenceType=index;
          },
          findAddr(){
            new window.daum.Postcode({
              oncomplete: (data) =>{
                  console.log(data);
                
                  let fullRoadAddr = data.roadAddress; // 도로명 주소 변수 
                  let extraRoadAddr = ''; // 도로명 조합형 주소 변수 
                  
                  // 법정동명이 있을 경우 추가한다. (법정리는 제외) 
                  // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다. 
                  if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){ 
                    extraRoadAddr += data.bname; 
                  } 
                  // 건물명이 있고, 공동주택일 경우 추가한다. 
                  if(data.buildingName !== '' && data.apartment === 'Y'){ 
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName); 
                  } 
                  // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다. 
                  if(extraRoadAddr !== ''){ 
                    extraRoadAddr = ' (' + extraRoadAddr + ')'; 
                  } 
                  // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다. 
                  if(fullRoadAddr !== ''){ 
                    fullRoadAddr += extraRoadAddr; 
                  } 
                  // 우편번호와 주소 정보를 해당 필드에 넣는다. 
                  this.residence.dong = data.bname; //5자리 새우편번호 사용 
                  this.addr1 = fullRoadAddr;

                  const REST_API_KEY="KakaoAK 74297f3fdce4f2f45a1c824e5f4d922c";
                  axios.get('https://dapi.kakao.com/v2/local/search/address.json?&query='+this.addr1, {headers: {'Authorization': REST_API_KEY }})
                    .then(res => {
                      this.residence.lat=res.data.documents[0].address.y;
                      this.residence.lon=res.data.documents[0].address.x;
                    })
                    .catch(error => console.log(error))
                  

              }
          }).open()
        },
        onResize() {
          if (window.innerWidth < 800)
            this.isMobile = true;
          else
            this.isMobile = false;
        },
        toggleAll() {
          if (this.selected.length) this.selected = []
          else this.selected = this.desserts.slice()
        },
        changeSort(column) {
          console.log(column);
          if (this.pagination.sortBy === column) {
            this.pagination.descending = !this.pagination.descending
          } else {
            this.pagination.sortBy = column
            this.pagination.descending = false
          }
        },
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
                }
                this.requestModify(modifyed);
                this.click();
            },
            init() {
                this.requestUserInfo();
            },
            remove() {
                alert("삭제완료");
            }
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
#my-strictly-unique-vue-upload-multiple-image {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.input-file-button{
  padding: 6px 25px;
  background-color:#2CA8FF;
  border-radius: 4px;
  color: white;
  cursor: pointer;
}
.image-container[data-v-00458278] {
    width: calc(100vw - 440px);
    height: 180px;
    border: 1px dashed #ced4da;
    border-radius: 10px;
    background-color: #fff;
}
    .inputLabel{
      padding-left: 5px;padding-right: 5px;margin-bottom: 3px;
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
    -webkit-transition-duration: .2s;
    transition-duration: .2s;
    -webkit-transition-property: border-color,width,height,background-color,opacity,-webkit-box-shadow;
    transition-property: border-color,width,height,background-color,opacity,-webkit-box-shadow;
    transition-property: border-color,box-shadow,width,height,background-color,opacity;
    transition-property: border-color,box-shadow,width,height,background-color,opacity,-webkit-box-shadow;
    -webkit-transition-timing-function: cubic-bezier(.215,.61,.355,1);
    transition-timing-function: cubic-bezier(.215,.61,.355,1);
}
    .profile-page .gallery {
        margin-top: 5px;
    }

    .slick-prev:before,
    .slick-next:before {
        font-family: 'slick';
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
        padding-right: 50px;
        margin: 0;
        list-style: none;
        text-align: center;
    }
     .mobile {
      color: #333;
    }

    @media screen and (max-width: 800px) {
      .mobile table.v-table tr {
        max-width: 100%;
        position: relative;
        display: block;
      }

      .mobile table.v-table tr:nth-child(odd) {
        border-left: 6px solid deeppink;
      }

      .mobile table.v-table tr:nth-child(even) {
        border-left: 6px solid cyan;
      }

      .mobile table.v-table tr td {
        display: flex;
        width: 100%;
        border-bottom: 1px solid #f5f5f5;
        height: auto;
        padding: 10px;
      }

      .mobile table.v-table tr td ul li:before {
        content: attr(data-label);
        padding-right: .5em;
        text-align: left;
        display: block;
        color: #999;

      }
      .v-datatable__actions__select
      {
        width: 50%;
        margin: 0px;
        justify-content: flex-start;
      }
      .mobile .theme--light.v-table tbody tr:hover:not(.v-datatable__expand-row) {
        background: transparent;
      }

    }
    .flex-content {
      padding: 0;
      margin: 0;
      list-style: none;
      display: flex;
      flex-wrap: wrap;
      width: 100%;
    }

    .flex-item {
      padding: 5px;
      width: 50%;
      height: 40px;
      font-weight: bold;
    }
</style>