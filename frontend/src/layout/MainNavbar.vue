<template>

  <navbar
    position="fixed"
    type="info"
    menu-classes="ml-auto"
  >
    <template>
      <img v-lazy="'img/mainlogo.jpg'" alt="" style="width:40px;"/>
            
      <router-link v-popover:popover1 class="navbar-brand" to="/search">
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
          전체가구 40%인<br>
          1인 가구를 위하여..
        </div>
      </el-popover>
    </template>
    <template slot="navbar-menu">
      <!-- <li class="nav-item">
        <a
          class="nav-link"
          href="https://www.creative-tim.com/product/vue-now-ui-kit"
          target="_blank"
        >
          <i class="now-ui-icons arrows-1_cloud-download-93"></i>
          <p>Download</p>
        </a>
      </li> -->
      <!-- <drop-down
        tag="li"
        title="Components"
        icon="now-ui-icons design_app"
        class="nav-item"
      >
        <nav-link to="/">
          <i class="now-ui-icons business_chart-pie-36"></i> All components
        </nav-link>
        <a
          href="https://demos.creative-tim.com/vue-now-ui-kit/documentation"
          target="_blank"
          class="dropdown-item"
        >
          <i class="now-ui-icons design_bullet-list-67"></i> Documentation
        </a>
      </drop-down> -->
    
      <li class="nav-item" @click="search = !search">
        <a class="nav-link btn btn-neutral" style="width:110px;" target="_blank">
        <p style="color:#000000;">상세검색 <i class="now-ui-icons ui-1_zoom-bold"></i></p></a>
      </li>
      <navbar v-show="search" type="default" position="fixed" menu-classes="ml-auto" style="margin-top:63px">
        <template slot="navbar-menu">
          <li class="nav-item">
            <a class="nav-link btn btn-neutral" style="width:110px;" target="_blank">
                <p style="color:#000000;">방 종류</p>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link btn btn-neutral" style="width:110px;" target="_blank">
              <p style="color:#000000;">거래방식</p>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link btn btn-neutral" style="width:110px;" target="_blank">
              <p style="color:#000000;">가격대</p>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link btn btn-neutral" style="width:110px;" target="_blank">
              <p style="color:#000000;">방 면적</p>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link btn btn-neutral" style="width:110px;" target="_blank">
              <p style="color:#000000;">관리비</p>
            </a>
          </li>
          
        </template>
      </navbar>
  
      <li class="nav-item">
        <router-link to="/login">
          <a class="nav-link btn btn-neutral" style="width:100px;" target="_blank">
            <p style="color:#000000;">로그인 <i class="now-ui-icons objects_key-25"></i></p></a>
        </router-link>
      </li>
      <li class="nav-item">
        <a
          class="nav-link btn btn-neutral"
          style="width:100px; color:#000000;"
          target="_blank"
        >
          <p>회원가입</p>
        </a>
      </li>
      

      
    </template>
  </navbar>
</template>

<script>
import {  Navbar } from '@/components';
import { Popover } from 'element-ui';
export default {
  name: 'main-navbar',
  props: {
    transparent: Boolean,
    colorOnScroll: Number
  },
  components: {
    
    Navbar,

    [Popover.name]: Popover
  },
  data(){
    return{
      search:false,
    }
  }
};
</script>

<style scoped>
.bg-info {
    background-color: #000000 !important;
}
.bg-default {
    background-color: #ffffff !important;
}
</style>
