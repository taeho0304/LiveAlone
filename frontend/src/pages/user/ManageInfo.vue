<template>
  <div
    v-if="getUserInfo"
    class="col-md-10 ml-auto mr-auto"
    style="margin-top: 0px"
  >
    <div class="row collections">
      <div class="col-md-1"></div>
      <div class="col-md-10">
        <fg-input
          class="input-lg"
          disabled
          type="text"
          placeholder="이름"
          v-model="getUserInfo.user.estateInfo.name"
          name="userName"
        >
        </fg-input>
        <fg-input
          class="input-lg"
          disabled
          type="text"
          placeholder="아이디"
          v-model="getUserInfo.user.estateInfo.address"
          name="userId"
        >
        </fg-input>
        <fg-input
          class="input-lg"
          disabled
          type="text"
          placeholder="이메일"
          v-model="getUserInfo.user.estateInfo.representative"
          name="email"
        >
        </fg-input>
      </div>
      <div class="col-md-1"></div>
    </div>
    <hr style="margin-top: 0" />
    <div class="row collections">
      <div class="col-md-1"></div>
      <div class="col-md-10">
        <fg-input
          class="no-border input-lg"
          v-bind:disabled="!isClick"
          type="text"
          addon-left-icon="now-ui-icons users_circle-08"
          placeholder="이름"
          v-model="getUserInfo.user.userName"
          name="userName"
        >
        </fg-input>
        <fg-input
          class="no-border input-lg"
          v-bind:disabled="!isClick"
          type="text"
          addon-left-icon="now-ui-icons users_circle-08"
          placeholder="아이디"
          v-model="getUserInfo.user.userId"
          name="userId"
        >
        </fg-input>
        <fg-input
          class="no-border input-lg"
          v-bind:disabled="!isClick"
          type="text"
          addon-left-icon="now-ui-icons users_circle-08"
          placeholder="이메일"
          v-model="getUserInfo.user.userEmail"
          name="email"
        >
        </fg-input>
        <fg-input
          class="no-border input-lg"
          v-bind:disabled="!isClick"
          type="text"
          addon-left-icon="now-ui-icons users_circle-08"
          v-model="getUserInfo.user.userPhone"
          name="phone"
        >
        </fg-input>
      </div>
      <div class="col-md-1"></div>
      <div class="col-md-3"></div>
      <div class="col-md-3">
        <div style="margin-top: -10px" class="text-center">
          <a
            v-if="!isClick"
            @click="click()"
            class="btn btn-info btn-round btn-lg btn-block"
            >수정</a
          >
          <a
            v-if="isClick"
            @click="clickModify()"
            class="btn btn-info btn-round btn-lg btn-block"
            >확인</a
          >
        </div>
      </div>
      <div class="col-md-3">
        <div style="margin-top: -10px" class="text-center">
          <a class="btn btn-info btn-round btn-lg btn-block">탈퇴</a>
        </div>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>
</template>
<script>
import { FormGroupInput, Button } from "@/components";
import { mapActions, mapGetters } from "vuex";
export default {
  name: "manage",
  bodyClass: "manage-page",
  components: {
    [FormGroupInput.name]: FormGroupInput,
    [Button.name]: Button,
  },
  data: function () {
    return {
      isClick: false,
      user: {
        userName: "",
        userId: "",
        userPass: "",
        userPhone: "",
        userEmail: "",
      },
    };
  },
  computed: {
    ...mapGetters("user", ["getUserInfo"]),
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
    ]),

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
      };
      this.requestModify(modifyed);
      this.click();
    },
    init() {
      this.requestUserInfo();
    },
    remove() {
      alert("삭제완료");
    },
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
    padding-right: 0.5em;
    text-align: left;
    display: block;
    color: #999;
  }
  .v-datatable__actions__select {
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