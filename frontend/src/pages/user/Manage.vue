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
                                        <fg-input class="input-lg" v-bind:disabled="!isClick" type="text" placeholder="이름" v-model="getUserInfo.user.estateInfo.name" name="userName">
                                        </fg-input>
                                        <fg-input class="input-lg" v-bind:disabled="!isClick" type="text" placeholder="아이디" v-model="getUserInfo.user.estateInfo.address" name="userId">
                                        </fg-input>
                                        <fg-input class="input-lg" v-bind:disabled="!isClick" type="text" placeholder="이메일" v-model="getUserInfo.user.estateInfo.representative" name="email">
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
                                <v-app id="inspire">
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
                                </v-app>
                            </template>

                        </tab-pane>

                        <tab-pane>
                            <!--검색 필터 저장-->
                            <span slot="label">
                                <i class="now-ui-icons design_bullet-list-67"></i> Regist
                            </span>
                            <div v-if="getUserInfo" class="col-md-10 ml-auto mr-auto" style="margin-top:0px">
                                <div class="row collections">
                                    <div class="col-md-1">
                                    </div>
                                    <div class="col-md-10">
                                        <fg-input class="input-lg" v-bind:disabled="!isClick" type="text" placeholder="이름" v-model="getUserInfo.user.estateInfo.name" name="userName">
                                        </fg-input>
                                        <fg-input class="input-lg" v-bind:disabled="!isClick" type="text" placeholder="아이디" v-model="getUserInfo.user.estateInfo.address" name="userId">
                                        </fg-input>
                                        <fg-input class="input-lg" v-bind:disabled="!isClick" type="text" placeholder="이메일" v-model="getUserInfo.user.estateInfo.representative" name="email">
                                        </fg-input>
                                    </div>
                                    <div class="col-md-1">
                                    </div>
                                </div>
                                <hr style="margin-top:0;">
                                <div class="row collections">
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
                    </tabs>

                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import {
        Tabs,
        TabPane,
        FormGroupInput
    } from '@/components';
    import {
        mapActions,
        mapState,
        mapGetters
    } from 'vuex';


    export default {
        name: 'manage',
        bodyClass: 'manage-page',
        components: {
            Tabs,
            TabPane,
            [FormGroupInput.name]: FormGroupInput,
     

        },
        data: function() {
            return {
              pagination: {
                  sortBy: 'name'
                },
                selected: [],
                search: '',
                isMobile: false,
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
            ...mapActions('user', ['requestUserInfo', 'requestDelete', 'requestModify']),
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
<style>
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