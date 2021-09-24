<template>
  <div class="page-header">
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <card type="login" plain style="margin-top:-20px;">
            <div slot="header" class="logo-container">
              <img v-lazy="'img/login.png'" alt="" style="margin-top:-30px;"/>
            </div>

            <fg-input
              class="no-border input-lg"
              v-model="user.id"
              type="text"
              addon-left-icon="now-ui-icons users_circle-08"
              placeholder="아이디"
            >
            </fg-input>
            

            <fg-input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons text_caps-small"
              v-model="user.password"
              placeholder="비밀번호"
              type="password"
              @keyup.enter="clickLogin"
            >
            </fg-input>
            
            <card class="text-center" style="border-radius:10px;" >
              <h6 class="card-text" v-if="!errors.requireId" style="color:red;">아이디를 입력해주세요.</h6>
              <h6 class="card-text" v-if="!errors.requirePw" style="color:red;">비밀번호를 입력해주세요.(영문자/숫자/특수문자)</h6>
            </card>

            <template>
              <div style="margin-top:-30px;" class="card-footer text-center">
                <a @click="clickLogin" class="btn btn-primary btn-round btn-lg btn-block">로그인</a>
              </div>
              <div class="pull-left">
                <h6>
                  <router-link to="signup"><a class="link footer-link">아직 계정이 없으신가요?</a></router-link>
                </h6>
              </div>
              <div class="pull-right">
                <h6>
                  <a href="#pablo" class="link footer-link">도움말</a>
                </h6>
              </div>
            </template>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Card, Button, FormGroupInput } from "@/components";
import { mapActions } from "vuex";

export default {
  name: "login-page",
  bodyClass: "login-page",
  data() {
    return {
      user: {
        id: "",
        password: "",
      },
      errors:{
        requireId:true,
        requirePw:true,
      },
    };
  },
  methods: {
    ...mapActions("user", ["requestLogin"]),
    clickLogin() {
      if(this.user.id==""){
        this.errors.requireId=false;
        return;
      }else if(this.user.id!=""&&this.user.password==""){
        this.errors.requireId=true;
        this.errors.requirePw=false;
        return;
      }else{
        this.errors.requireId=true;
        this.errors.requirePw=true;
        this.requestLogin(this.user);
        return;
      }
      
    },

  },
  components: {
    Card,
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
};
</script>
<style>
.input-group, .form-group {
    margin-bottom: 0px;
    position: relative;
}
</style>
