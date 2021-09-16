<template>
  <div
    class="section section-signup"
    id="form"
  >
    <div class="container">
      <div class="row">
        <card class="card-signup" header-classes="text-center" color="orange">
          <template slot="header">
            <h3 class="card-title title-up">Sign Up</h3>
          </template>

          <template>
            <fg-input
              class="no-border"
              placeholder="Name"
              v-model="user.userName"
              addon-left-icon="now-ui-icons users_circle-08"
            >
            </fg-input>

            <fg-input
              class="no-border"
              placeholder="id"
              v-model="user.userId"
              addon-left-icon="now-ui-icons text_caps-small"
            >
            </fg-input>

            <fg-input
              class="no-border"
              placeholder="password"
              v-model="user.userPass"
              addon-left-icon="now-ui-icons text_caps-small"
            >
            </fg-input>

            <fg-input
              class="no-border"
              placeholder="passwordcheck"
              v-model="passwordcheck"
              addon-left-icon="now-ui-icons text_caps-small"
            >
            </fg-input>

            <fg-input
              class="no-border"
              placeholder="Email"
              v-model="user.userEmail"
              addon-left-icon="now-ui-icons ui-1_email-85"
            >
            </fg-input>

            <fg-input
              class="no-border"
              placeholder="phone number"
              v-model="user.userPhone"
              addon-left-icon="now-ui-icons ui-1_email-85"
            >
            </fg-input>
          </template>
          <div class="card-footer text-center">
            <n-button type="neutral" round size="lg" @click="clickRegister"
              >Get Started</n-button
            >
          </div>
        </card>
      </div>

      <div class="col text-center" style="padding-top:15px;">
        <h6>
          <router-link to="signup"><a class="link footer-link">로그인하기</a></router-link>
        </h6>
      </div>
    </div>
  </div>
</template>
<script>
import { Card, FormGroupInput, Button } from "@/components";
import { mapActions } from "vuex";
export default {
  data() {
    return {
      user: {
        userName: "",
        userId: "",
        userPass: "",
        userPhone: "",
        userEmail: "",
      },
      passwordcheck: "",
    };
  },
  methods: {
    ...mapActions("user", ["requestRegister"]),
    clickRegister() {
      //console.log(this.user);
      this.requestRegister(this.user);
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

.card-signup .card-header {
    margin:0 20px;
    padding: 15px 0;
}

.card-signup .card-footer {
    margin-bottom: 10px;
    margin-top: 10px;
    padding: 20px 0;
}
.section-signup .col .btn {
    margin: 15px;
}

</style>
