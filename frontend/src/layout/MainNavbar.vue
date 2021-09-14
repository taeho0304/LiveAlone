<template>
  <div>
  <OneSearchBar :visible="isOneRoom"/>
  <ApartSearchBar :visible="isApart"/>
  <navbar
    position="fixed"
    type="info"
    menu-classes="ml-auto"
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
            전체가구 40%인<br>1인 가구를 위하여..
        </div>
      </el-popover>
    </template>
  
    <template slot="navbar-menu">
      <drop-down
              tag="li"
              :title="residenceType"
              class="nav-item select"
      >
        <div class="nav-link"  v-for="(items, index) in getRoomType.residenceCategoryList" :key="index" @click="changeItem(items.categoryName, index)"> 
          <i class="now-ui-icons education_paper"></i> {{ items.categoryName }}
        </div>
        
      </drop-down>
      <template v-if="isLogin">
        <li class="nav-item">
          <a class="nav-link">
            <router-link to="/login"> <i class="now-ui-icons media-1_button-power"></i><p>로그인</p></router-link>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link">
            <router-link to="/signup"><p>회원가입</p></router-link>
          </a>
        </li>
      </template>
      <template v-else>
        <li class="nav-item">
          <a class="nav-link">
            <span @click="clickLogout()"><p>로그아웃</p></span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link">
            <router-link to="/profile"><i class="now-ui-icons users_circle-08"></i><p>마이페이지</p></router-link>
          </a>
        </li>
      </template>

    </template>
 
  </navbar>

  </div>
</template>

<script>
import { DropDown, Navbar, } from '@/components';
import { Popover } from 'element-ui';
import OneSearchBar from '../pages/SearchBar/OneSearchBar.vue';
import ApartSearchBar from '../pages/SearchBar/ApartSearchBar.vue';
import { mapGetters } from 'vuex';
export default {
  name: "main-navbar",
  props: {
    transparent: Boolean,
    colorOnScroll: Number,
  },
  components: {

    DropDown,
    OneSearchBar,
    ApartSearchBar,
    Navbar,
    [Popover.name]: Popover
  },
  data(){
    return{
      isLogin:false,
      isOneRoom:false,
      isApart:false,
      residenceType:"방 종류",
      residenceIndex:0,
    }
  },
  methods:{
    clickSearch(index){
      if(index==0){
        //console.log("원룸");
        this.isOneRoom = true;
        this.isApart = false;
        //console.log(this.isOneRoom);
        return;
      }else if(index== 1){
        //console.log("오피스텔");
        this.isOneRoom = false;
        this.isApart = true;
        //console.log(this.isOneRoom);
        return;
      }
    },
    changeItem(a, index) {
      this.residenceType = a;
      this.residenceIndex = index;
      this.clickSearch(this.residenceIndex);
      return;
    },
    clickLogout() {
      this.isLogin=false;
      localStorage.clear();
    },

  },
  mounted(){
    if(localStorage.getItem("accessToken")!=null){
        this.isLogin=true;
    }else{
      this.isLogin=false;
    }
  },
  create(){
    if(localStorage.getItem("accessToken")!=null){
        this.isLogin=true;
    }else{
      this.isLogin=false;
    }
  },
  computed :{
    ...mapGetters('search',['getRoomType',]),
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
