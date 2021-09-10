<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <card type="login" plain>
            <div slot="header" class="logo-container">
              <img v-lazy="'img/login.png'" alt="" />
            </div>

            <fg-input
              class="no-border input-lg"
              v-model="user.id"
              addon-left-icon="now-ui-icons users_circle-08"
              placeholder="아이디"
            >
            </fg-input>

            <fg-input
              class="no-border input-lg"
              addon-left-icon="now-ui-icons text_caps-small"
              v-model="user.password"
              placeholder="비밀번호"
            >
            </fg-input>

            <template slot="raw-content">
              <div class="card-footer text-center">
                <a
                  @click="clickLogin"
                  class="btn btn-primary btn-round btn-lg btn-block"
                  >Get Started</a
                >
              </div>
              <div class="pull-left">
                <h6>
                  <a href="#pablo" class="link footer-link">Create Account</a>
                </h6>
              </div>
              <div class="pull-right">
                <h6>
                  <a href="#pablo" class="link footer-link">Need Help?</a>
                </h6>
              </div>
            </template>
          </card>
        </div>
      </div>
    </div>
    <main-footer></main-footer>
  </div>
</template>
<script>
import { Card, Button, FormGroupInput } from "@/components";
import MainFooter from "@/layout/MainFooter";
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
    };
  },
  methods: {
    ...mapActions("user", ["requestLogin"]),
    clickLogin() {
      console.log(this.user.id);
      console.log(this.user.password);
      this.requestLogin(this.user);
    },
  },
  components: {
    Card,
    MainFooter,
    [Button.name]: Button,
    [FormGroupInput.name]: FormGroupInput,
  },
};
</script>
<style></style>
