<template>
  <div>
    <DetailSearch
      v-show="isdetail"
      v-bind:juso="emitData"
      @mydetailS="requestDetailSearch"
    />

    <navbar
      position="fixed"
      type="info"
      menu-classes="ml-auto"
      style="height: 30px; background: linear-gradient(60deg, #4481eb, #04befe)"
    >
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
            :disabled="isAvailable"
            slot="title"
            class="dropdown-toggle btn-neutral"
            data-toggle="dropdown"
            block
            round
            style="color: #000000"
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
            :disabled="isAvailable"
            slot="title"
            class="dropdown-toggle btn-neutral"
            data-toggle="dropdown"
            block
            round
            style="color: #000000"
          >
            {{ Gu }}
          </n-button>
          <ul class="dropdown-scrollbar">
            <a
              v-for="(items, index) in GuList"
              :key="index"
              class="dropdown-item"
              @click="clickGu(items, index)"
              >{{ items }}
            </a>
          </ul>
        </drop-down>

        <drop-down class="nav-item" style="margin-right: 100px">
          <n-button
            :disabled="isAvailable"
            slot="title"
            class="dropdown-toggle btn-neutral"
            data-toggle="dropdown"
            block
            round
            style="color: #000000"
          >
            {{ Dong }}
          </n-button>
          <ul class="dropdown-scrollbar">
            <a
              v-for="(items, index) in dongList"
              :key="index"
              class="dropdown-item"
              @click="clickDong(items, index)"
              >{{ items.dongName }}
            </a>
          </ul>
        </drop-down>
      </template>
      <template slot="navbar-menu">
        <li class="nav-item" style="margin-left: 80px">
          <a class="nav-link mt-2" @click="changeItem(isAvailable)">
            상세 검색
          </a>
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
          <li class="nav-item" v-if="isUser" @click="getInfo()">
            <a class="nav-link mt-2">
              <router-link to="/profile"
                ><i class="now-ui-icons users_circle-08"></i>
                <p>마이페이지</p></router-link
              >
            </a>
          </li>
          <li class="nav-item" v-if="isEstate">
            <a class="nav-link mt-2">
              <router-link to="/manage"
                ><i class="now-ui-icons education_paper"></i>
                <p>관리페이지</p></router-link
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
import { mapGetters, mapActions } from "vuex";
import DetailSearch from "../pages/map/detailSearch.vue";
import http from "@/util/http-common";
export default {
  name: "main-navbar",
  props: {
    transparent: Boolean,
    colorOnScroll: Number,
    isAvailable: Boolean,
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
      isEstate: false,
      isUser: false,
      SiIdx: 0,
      GuIdx: 0,
      DongIdx: 0,
      Dong: "동 선택하세요",
      Si: "시 선택하세요",
      Gu: "구 선택하세요",
      isdetail: false,
      residenceType: "방 종류",
      residenceIndex: 0,
      SiList: [{ name: "서울특별시" }],
      GuList: [],
      dongList: [],
      emitData: null,
    };
  },
  computed: {
    ...mapGetters("user", ["getUserInfo"]),
  },
  methods: {
    ...mapActions("user", ["requestUserInfo"]),
    changeItem(check) {
      if (check == false) {
        this.isdetail = !this.isdetail;
      }
    },
    requestDetailSearch(data) {
      console.log("mainnav", data);
      this.$emit("detailS", data);
    },
    clickLogout() {
      this.isLogin = false;
      localStorage.clear();
      this.$router.push("/");
    },
    clickDong(dongItems, idx) {
      this.Dong = dongItems.dongName;

      let temp = this.Si + " " + this.Gu + " " + this.Dong;
      console.log(dongItems);

      const data = {
        si: this.Si,
        gugun: this.Gu,
        dong: this.Dong,
        lat: dongItems.lat,
        long: dongItems.lon,
      };

      console.log(data);

      this.emitData = data;

      console.log("emit : ", this.emitData);
      this.$emit("maker", this.emitData);
    },
    getInfo() {
      this.requestUserInfo();
    },
    clickGu(guName, idx) {
      http.get("/api/v1/search/dong" + "?dong=" + guName).then((res) => {
        console.log(res.data.dongModelList);
        this.dongList = res.data.dongModelList;
      });
      this.Gu = guName;
    },
    clickSi(siName, idx) {
      http
        .get("/api/v1/search/gugun" + "?siName=" + "서울특별시")
        .then((res) => {
          console.log(res.data.guGunListList);
          this.GuList = res.data.guGunListList;
        });
      this.Si = siName;
    },
  },
  mounted() {
    if (localStorage.getItem("accessToken") != null) {
      if (localStorage.getItem("accessEstate") != "null") {
        this.isEstate = true;
        this.isUser = false;
      } else {
        this.isEstate = false;
        this.isUser = true;
      }
      this.isLogin = true;
    } else {
      this.isUser = false;
      this.isEstate = false;
      this.isLogin = false;
    }
  },
  create() {
    if (localStorage.getItem("accessToken") != null) {
      if (localStorage.getItem("accessEstate") != "null") {
        this.isEstate = true;
        this.isUser = false;
      } else {
        this.isEstate = false;
        this.isUser = true;
      }
      this.isLogin = true;
    } else {
      this.isUser = false;
      this.isEstate = false;
      this.isLogin = false;
    }
  },
};
</script>


<style scoped>
.bg-info {
  background-color: linear-gradient(60deg, #74ebd5, #9face6) !important;
}
.bg-default {
  background-color: #ffffff !important;
}
.dropdown-scrollbar {
  height: 150px;
  overflow-x: hidden;
  overflow-y: scroll;
}
</style>
