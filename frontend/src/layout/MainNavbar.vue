<template>
  <div>
    <DetailSearch v-show="isdetail" />

    <navbar position="fixed" type="info" menu-classes="ml-auto">
      <template>
        <router-link v-popover:popover1 class="navbar-brand" to="/">
          나 혼자 살거다
        </router-link>
        <el-popover
          ref="popover1"
          popper-class="popover"
          placement="bottom"
          width="200"
          trigger="hover"
        >
          <div class="popover-body">
            전체가구 40%인<br />1인 가구를 위하여..
          </div>
        </el-popover>
      </template>

      <template slot="navbar-menu">
        <li class="nav-item">
          <a class="nav-link" @click="changeItem()"> 상세 검색 </a>
        </li>
        <template v-if="!isLogin">
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/login">
                <i class="now-ui-icons media-1_button-power"></i>
                <p>로그인</p></router-link
              >
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/signup"><p>회원가입</p></router-link>
            </a>
          </li>
        </template>

        <template v-if="isLogin">
          <li class="nav-item">
            <a class="nav-link">
              <span @click="clickLogout()"><p>로그아웃</p></span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/profile"
                ><i class="now-ui-icons users_circle-08"></i>
                <p>마이페이지</p></router-link
              >
            </a>
          </li>
        </template>
      </template>
    </navbar>
  </div>
</template>

<script>
import { DropDown, Navbar } from "@/components";
import { Popover } from "element-ui";
import OneSearchBar from "../pages/SearchBar/OneSearchBar.vue";
import ApartSearchBar from "../pages/SearchBar/ApartSearchBar.vue";
import { mapGetters } from "vuex";
import DetailSearch from "../pages/components/detailSearch.vue";
export default {
  name: "main-navbar",
  props: {
    transparent: Boolean,
    colorOnScroll: Number,
  },
  components: {
    DetailSearch,
    Navbar,
    [Popover.name]: Popover,
  },
  data() {
    return {
      isLogin: false,
      isdetail: false,
      residenceType: "방 종류",
      residenceIndex: 0,
    };
  },
  methods: {
    changeItem() {
      this.isdetail = !this.isdetail;
    },
    clickLogout() {
      this.isLogin = false;
      localStorage.clear();
    },
  },
  mounted() {
    if (localStorage.getItem("accessToken") != null) {
      this.isLogin = true;
    } else {
      this.isLogin = false;
    }
  },
  create() {
    if (localStorage.getItem("accessToken") != null) {
      this.isLogin = true;
    } else {
      this.isLogin = false;
    }
  },
};
</script>


<style scoped>
.bg-info {
  background-color: #eb8816 !important;
}
.bg-default {
  background-color: #ffffff !important;
}
</style>
