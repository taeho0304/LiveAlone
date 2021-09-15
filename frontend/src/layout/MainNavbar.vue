<template>
  <div>
    <DetailSearch v-show="isdetail" />

    <navbar position="fixed" type="info" menu-classes="ml-auto ">
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
        <drop-down class="nav-item">
          <n-button
            slot="title"
            type="primary"
            class="dropdown-toggle"
            data-toggle="dropdown"
            block
            round
          >
            {{ Si }}
          </n-button>
          <a
            v-for="(items, index) in SiList"
            :key="index"
            class="dropdown-item"
            @click="clickSi(items.name, index)"
            >{{ items.name }}
          </a>
        </drop-down>

        <drop-down class="nav-item">
          <n-button
            slot="title"
            type="primary"
            class="dropdown-toggle"
            data-toggle="dropdown"
            block
            round
          >
            {{ Gu }}
          </n-button>
          <a
            v-for="(items, index) in GuList[SiIdx].gu"
            :key="index"
            class="dropdown-item"
            @click="clickGu(items.name, index)"
            >{{ items.name }}
          </a>
        </drop-down>
        <drop-down class="nav-item" style="margin-right: 100px">
          <n-button
            slot="title"
            type="primary"
            class="dropdown-toggle"
            data-toggle="dropdown"
            block
            round
          >
            {{ Dong }}
          </n-button>
          <a
            v-for="(items, index) in dongList[GuIdx].dong"
            :key="index"
            class="dropdown-item"
            @click="clickDong(items.name, index)"
            >{{ items.name }}
          </a>
        </drop-down>
      </template>
      <template slot="navbar-menu">
        <li class="nav-item" style="margin-left: 80px">
          <a class="nav-link mt-2" @click="changeItem()"> 상세 검색 </a>
        </li>

        <template v-if="!isLogin">
          <li class="nav-item">
            <a class="nav-link mt-2">
              <router-link to="/login">
                <i class="now-ui-icons media-1_button-power"></i>
                <p>로그인</p></router-link
              >
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link mt-2">
              <router-link to="/signup"><p>회원가입</p></router-link>
            </a>
          </li>
        </template>

        <template v-if="isLogin">
          <li class="nav-item">
            <a class="nav-link mt-2">
              <span @click="clickLogout()"><p>로그아웃</p></span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link mt-2">
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
import { DropDown, Navbar, Button } from "@/components";
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
    DropDown,
    [Button.name]: Button,
  },
  data() {
    return {
      isLogin: false,
      SiIdx: 0,
      GuIdx: 0,
      DongIdx: 0,
      Dong: "동 선택하세요",
      Si: "시 선택하세요",
      Gu: "구 선택하세요",
      isdetail: false,
      residenceType: "방 종류",
      residenceIndex: 0,
      SiList: [{ name: "서울특별시" }, { name: "내가사는 천안시" }],
      GuList: [
        {
          gu: [
            { name: "용산구" },
            { name: "강남구" },
            { name: "강북구" },
            { name: "서대문구" },
          ],
        },
      ],
      dongList: [
        {
          dong: [],
        },
        {
          dong: [],
        },
        {
          dong: [
            { name: "삼양동" },
            { name: "미아동" },
            { name: "송천동" },
            { name: "수유1동", lat: "37.5252081", long: "126.9300509" },
          ],
        },
      ],
      emitData: null,
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
    clickDong(dongName, idx) {
      this.Dong = dongName;
      this.DongIdx = idx;

      let temp = this.Si + " " + this.Gu + " " + this.Dong;

      const data = {
        juso: temp,
        lat: this.dongList[this.GuIdx].dong[this.DongIdx].lat,
        long: this.dongList[this.GuIdx].dong[this.DongIdx].long,
      };
      this.emitData = data;
      console.log("emit : ", this.emitData);
      this.$emit("maker", this.emitData);
    },

    clickGu(guName, idx) {
      this.Gu = guName;
      this.GuIdx = idx;
    },
    clickSi(siName, idx) {
      this.Si = siName;
      this.SiIdx = idx;
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
