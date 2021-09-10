<template>
  <div>
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
      <li class="nav-item" @click="clickSearch()">
        <a class="nav-link"><i class="now-ui-icons objects_globe"></i><p>상세검색</p></a>
      </li>
      <li class="nav-item">
        <a class="nav-link">
          <router-link to="/login"> <i class="now-ui-icons objects_key-25"></i><p>로그인</p></router-link>
        </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
        >
          <i class="now-ui-icons arrows-1_cloud-download-93"></i>
          <p>회원가입</p>
        </a>
      </li>
      <!-- <drop-down
              tag="li"
              title="Examples"
              icon="now-ui-icons design_image"
              class="nav-item"
      >
        <nav-link to="/landing">
          <i class="now-ui-icons education_paper"></i> Landing
        </nav-link>
        <nav-link to="/login">
          <i class="now-ui-icons users_circle-08"></i> Login
        </nav-link>
        <nav-link to="/profile">
          <i class="now-ui-icons users_single-02"></i> Profile
        </nav-link>
      </drop-down> -->
      <!-- <li class="nav-item">
        <a
          class="nav-link btn btn-neutral"
          href="https://www.creative-tim.com/product/vue-now-ui-kit-pro"
          target="_blank"
        >
          <i class="now-ui-icons arrows-1_share-66"></i>
          <p>Upgrade to PRO</p>
        </a>
      </li> -->
    </template>
 
  </navbar>
  <SearchBar :visible="isSearch"/>
  </div>
</template>

<script>
import { /*DropDown*/ Navbar, } from '@/components';
import { Popover } from 'element-ui';
import SearchBar from '../pages/SearchBar.vue';
export default {
  name: 'main-navbar',
  props: {
    transparent: Boolean,
    colorOnScroll: Number
  },
  components: {
    SearchBar,
    Navbar,
    [Popover.name]: Popover
  },
  data(){
    return{
      isSearch:false,
    }
  },
  methods:{
    clickSearch(){
      this.isSearch = !this.isSearch;
      console.log(this.isSearch);
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
