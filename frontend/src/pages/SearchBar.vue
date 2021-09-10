<template>
  <div>
  <navbar
    v-show="visible"
    position="fixed"
    style="margin-top:47px"
    type="warning"
    menu-classes="ml-auto"
  >

    <template slot="navbar-menu">
      <li class="nav-item" @click="isSearch = !isSearch">
        <a class="nav-link"><i class="now-ui-icons objects_globe"></i><p>상세검색</p></a>
      </li>
      <li class="nav-item">
        <a class="nav-link">
          <router-link to="/login"> <i class="now-ui-icons objects_key-25"></i><p>로그인</p></router-link>
        </a>
      </li>
      <drop-down
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
      </drop-down>
      
    
    </template>
    
  </navbar>
  
  </div>
</template>

<script>
import { Navbar, DropDown, NavLink} from '@/components';
import { Popover } from 'element-ui';
export default {
  name: 'main-navbar',
  props: {
    visible: {
      type: Boolean,
      require: true,
      default: false
    },
  },
  components: {
    NavLink,
    Navbar,
    DropDown,
    [Popover.name]: Popover
  },
  data(){
    return{
      isSearch:false,
    }
  }
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
