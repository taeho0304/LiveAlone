<template>
  <div>
    
    <div class="section">
      <div class="container" >
        <!-- <div class="button-container">
          <a href="#button" class="btn btn-primary btn-round btn-lg">Follow</a>
          <a
            href="#button"
            class="btn btn-default btn-round btn-lg btn-icon"
            rel="tooltip"
            title="Follow me on Twitter"
          >
            <i class="fab fa-twitter"></i>
          </a>
          <a
            href="#button"
            class="btn btn-default btn-round btn-lg btn-icon"
            rel="tooltip"
            title="Follow me on Instagram"
          >
            <i class="fab fa-instagram"></i>
          </a>
        </div> -->
        <h3 class="title" style="margin-top:1px;">My Page</h3>
      
        <div class="card row">
          <tabs type="primary" tabContentClasses="tab-subcategories"
        square centered class="nav-align-center">
            <tab-pane>
              <span slot="label">
                <i class="now-ui-icons business_badge"></i> Profile
              </span>
              <div class="col-md-10 ml-auto mr-auto" style="margin-top:0px">
                <div class="row collections">
                  <div class="col-md-1">
                  </div>
                  <div class="col-md-10">
                    <fg-input
                        class="no-border input-lg"
                        v-bind:disabled="!isClick"
                        type="text"
                        addon-left-icon="now-ui-icons users_circle-08"
                        placeholder="이름"
                      >
                    </fg-input>
                    <fg-input
                        class="no-border input-lg"
                        v-bind:disabled="!isClick"
                        type="text"
                        addon-left-icon="now-ui-icons users_circle-08"
                        placeholder="아이디"
                      >
                    </fg-input>
                    <fg-input
                        class="no-border input-lg"
                        v-bind:disabled="!isClick"
                        type="text"
                        addon-left-icon="now-ui-icons users_circle-08"
                        placeholder="이메일"
                      >
                    </fg-input>
                    <fg-input
                        class="no-border input-lg"
                        v-bind:disabled="!isClick"
                        type="text"
                        addon-left-icon="now-ui-icons users_circle-08"
                        placeholder="휴대폰 번호"
                        :v-model="getUserInfo.userPhone"
                      >
                    </fg-input>
                  </div>
                  <div class="col-md-1">
                  </div>
                  <div class="col-md-3">
                  </div>
                  <div class="col-md-3">
                      <div style="margin-top:-30px;" class="card-footer text-center">
                        <a v-if="!isClick" @click="click()" class="btn btn-primary btn-round btn-lg btn-block">수정</a>
                        <a v-if="isClick" @click="clickModify()" class="btn btn-primary btn-round btn-lg btn-block">확인</a>
                      </div>
                  </div>
                  <div class="col-md-3">
                    <div style="margin-top:-30px;" class="card-footer text-center">
                        <a class="btn btn-primary btn-round btn-lg btn-block">탈퇴</a>
                      </div>
                  </div>
                  <div class="col-md-3">
                  </div>

                </div>
              </div>
              
            </tab-pane>

            <tab-pane title="Home">
              <i slot="label" class="now-ui-icons location_world"></i>

              <div class="col-md-10 ml-auto mr-auto">
                <div class="row collections">
                  <div class="col-md-6">
                    <img src="img/bg1.jpg" alt="" class="img-raised" />
                    <img src="img/bg3.jpg" alt="" class="img-raised" />
                  </div>
                  <div class="col-md-6">
                    <img src="img/bg8.jpg" alt="" class="img-raised" />
                    <img src="img/bg7.jpg" alt="" class="img-raised" />
                  </div>
                </div>
              </div>
            </tab-pane>

            <tab-pane title="Messages">
              <i slot="label" class="now-ui-icons sport_user-run"></i>

              <div class="col-md-10 ml-auto mr-auto">
                <div class="row collections">
                  <div class="col-md-6">
                    <img src="img/bg3.jpg" alt="" class="img-raised" />
                    <img src="img/bg8.jpg" alt="" class="img-raised" />
                  </div>
                  <div class="col-md-6">
                    <img src="img/bg7.jpg" alt="" class="img-raised" />
                    <img src="img/bg6.jpg" class="img-raised" />
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
import { Tabs, TabPane, FormGroupInput } from '@/components';
import {mapActions, mapState, mapGetters} from 'vuex';
export default {
  name: 'profile',
  bodyClass: 'profile-page',
  components: {
    Tabs,
    TabPane,
    [FormGroupInput.name]: FormGroupInput,
  },
  data:function(){
    return{
      isClick:false,
    }
  },
  methods:{
    ...mapActions('user',['requestUserInfo', 'requestDelete', 'requestModify']),
    ...mapGetters('user',['getUserInfo']),
    click(){
      this.isClick=!this.isClick;
      console.log(this.isClick);
    },
    clickModify(){
      console.log(this.isClick);
      this.click();
    }
  },
  created(){
    
  },
  mounted(){
    this.requestUserInfo();
    console.log(this.getUserInfo)
  },
};
</script>
<style>
.profile-page .gallery {
    margin-top: 5px;
}
</style>
